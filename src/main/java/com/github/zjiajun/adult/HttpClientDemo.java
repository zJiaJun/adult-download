package com.github.zjiajun.adult;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujiajun
 * 15/5/14 17:36
 */
public class HttpClientDemo {

    public static final String HOST = "67.220.90.4";

    public static final String URL_PREFIX = "http://67.220.90.4/forum/";

    private static final String LOGIN_URL =  URL_PREFIX + "logging.php?action=login";

    private static final String INDEX_URL = URL_PREFIX + "index.php";

    private static final String ASIA_WUMA_URL = URL_PREFIX + "forum-143-1.html";

    private static final String ASIA_YOUMA_URL = URL_PREFIX + "forum-230-1.html";

    private static final String COOKIE_SID = "cdb3_sid";

    private static final String COOKIE_TIME = "cdb3_cookietime";

    private static final String COOKIE_AUTH = "cdb3_auth";

    public static String DOWNLOAD_PATH = "/Users/zhujiajun/Downloads/torrent/";

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final String RANDOM_USER_AGENT = UserAgentUtils.randomUserAgent();

    private static Header [] getHeaders() {
        return new Header[] {
                new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
                new BasicHeader("Accept-Encoding","gzip, deflate, sdch"),
                new BasicHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6"),
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("User-Agent", RANDOM_USER_AGENT),
                new BasicHeader("Host","67.220.90.4")
        };

    }

    private static String getFormHash() {
        httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(LOGIN_URL);
        httpGet.setHeaders(getHeaders());
        String formHashValue = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, "GBK");
            formHashValue = HtmlParser.formHash(html);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return formHashValue;
    }

    private static HttpResponse login(String userName,String password) {
        HttpPost httpPost = new HttpPost(LOGIN_URL);
        httpPost.setHeaders(getHeaders());
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("formhash",getFormHash()));
        nameValuePairList.add(new BasicNameValuePair("referer","index.php"));
        nameValuePairList.add(new BasicNameValuePair("loginfield","username"));
        nameValuePairList.add(new BasicNameValuePair("username",userName));
        nameValuePairList.add(new BasicNameValuePair("password",password));
        nameValuePairList.add(new BasicNameValuePair("questionid","0"));
        nameValuePairList.add(new BasicNameValuePair("answer",""));
        nameValuePairList.add(new BasicNameValuePair("cookietime","2592000"));
        nameValuePairList.add(new BasicNameValuePair("loginmode",""));
        nameValuePairList.add(new BasicNameValuePair("styleid",""));
        nameValuePairList.add(new BasicNameValuePair("loginsubmit","true"));
        HttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            httpResponse = httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return httpResponse;
    }

    private static CookieStore buildCookie(HttpResponse httpResponse) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header header :allHeaders) {
            if ("Set-Cookie".equals(header.getName())) {
                String headerValue = header.getValue();
                int indexOf = headerValue.indexOf(";");
                if (headerValue.startsWith(COOKIE_SID)) {
                    String cookieValue = headerValue.substring(COOKIE_SID.length()+1, indexOf);
                    BasicClientCookie cookie = new BasicClientCookie(COOKIE_SID,cookieValue);
                    cookie.setDomain(HOST);
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                } else if (headerValue.startsWith(COOKIE_TIME)) {
                    String cookieValue = headerValue.substring(COOKIE_TIME.length()+1, indexOf);
                    BasicClientCookie cookie = new BasicClientCookie(COOKIE_TIME,cookieValue);
                    cookie.setDomain(HOST);
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                } else if (headerValue.startsWith(COOKIE_AUTH)) {
                    String cookieValue = headerValue.substring(COOKIE_AUTH.length()+1, indexOf);
                    BasicClientCookie cookie = new BasicClientCookie(COOKIE_AUTH,cookieValue);
                    cookie.setDomain(HOST);
                    cookie.setPath("/");
                    cookieStore.addCookie(cookie);
                }
            }
        }
        return cookieStore;
    }

    private static HttpContext buildContext(CookieStore cookieStore) {
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        return context;
    }

    private static Map<String,String> requestAndParserThread(String threadListPath) {
        if (ASIA_YOUMA_URL.equals(threadListPath)) {
            DOWNLOAD_PATH += "ASIA-YOUMA";
        } else if (ASIA_WUMA_URL.equals(threadListPath)) {
            DOWNLOAD_PATH += "ASIA-WUMA";
        }
        DOWNLOAD_PATH +=  File.separator;
        HttpGet httpGet = new HttpGet(threadListPath);//请求版块
        httpGet.setHeaders(getHeaders());
        Map<String, String> threadMap = null;
        try {
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String threadHtml = EntityUtils.toString(entity, "GBK");
            threadMap = HtmlParser.thread(threadHtml);//解析列表获得每个列表
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return threadMap;
    }

    private static Map<String,String> requestAndParserImg(String singleThreadPath) {
        String singleHtml = requestSingleThread(singleThreadPath);
        return HtmlParser.img(singleHtml);
    }

    private static Map<String,String> requestAndParserAttachment(String singleThreadPath) {
        String singleHtml = requestSingleThread(singleThreadPath);
        return HtmlParser.attachment(singleHtml);
    }

    private static String requestSingleThread(String singleThreadPath) {
        HttpGet httpGet = new HttpGet(singleThreadPath);//请求单个列表
        httpGet.setHeaders(getHeaders());
        String singleHtml = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            singleHtml = EntityUtils.toString(entity,"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return singleHtml;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("------开始登录------");
        HttpResponse httpResponse = login("leoneye","wqcez136");
        System.out.println("------登录成功------");

        System.out.println("------构建Cookie------");
        CookieStore cookieStore = buildCookie(httpResponse);
        System.out.println("------构建Cookie成功------");

        System.out.println("------构建Context------");
        HttpContext httpContext = buildContext(cookieStore);
        System.out.println("------构建Context成功------");

        System.out.println("------开始解析帖子列表------");
        Map<String, String> threadMap = requestAndParserThread(ASIA_YOUMA_URL);
        System.out.println("------解析成功 一共" + threadMap.size() + "个帖子------");

        int count = 1; //计数

        for (Map.Entry<String,String> entry : threadMap.entrySet()) {
            String filePath = entry.getKey();
            String singleThreadPath = entry.getValue();
            System.out.println("------开始处理第" + count + "个"+ filePath + "------");
            String path = DOWNLOAD_PATH + filePath + File.separator;
            System.out.println("------新建目录: " + path + "------");
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("------新建目录成功------");
                boolean isCreate = file.mkdirs();

                System.out.println("------开始解析附件地址------");
                Map<String, String> attachmentMap = requestAndParserAttachment(singleThreadPath);
                System.out.println("------解析附件地址成功------");

                System.out.println("------开始解析图片地址------");
                Map<String, String> imgMap = requestAndParserImg(singleThreadPath);
                System.out.println("------解析图片地址成功------");

                for (Map.Entry<String,String> imgEntry : imgMap.entrySet()) {
                    String imgName = imgEntry.getKey();
                    String imgUrl = imgEntry.getValue();
                    System.out.println("------开始下载图片------");
                    URLConnection url = new URL(imgUrl).openConnection();
                    url.setRequestProperty("User-Agent", RANDOM_USER_AGENT);
                    url.connect();
                    InputStream inputStream = url.getInputStream();
                    DownloadUtils.download(inputStream, path, imgName);
                    System.out.println("------下载图片成功------");
                }

                for (Map.Entry<String,String> attachEntry : attachmentMap.entrySet()) {
                    String attachName = attachEntry.getKey();
                    String attachUrl = attachEntry.getValue();
                    System.out.println("------附件名称: " + attachName + "------");
                    HttpGet httpGet = new HttpGet(attachUrl);
                    httpGet.setHeaders(getHeaders());
                    //必须新建个client设置disableContentCompression并带上httpContxt,否则下载附件错误
                    CloseableHttpClient attachHttpClient = HttpClients.custom().disableContentCompression().build();
                    System.out.println("------开始下载附件,目录: " + path + " 请耐心稍等------");
                    httpResponse = attachHttpClient.execute(httpGet,httpContext);
                    HttpEntity entity = httpResponse.getEntity();
                    InputStream content = entity.getContent();
                    if (isCreate) {
                        DownloadUtils.download(content, path, attachName);
                        System.out.println("------附件下载成功,目录: " + path);
                    }
                }
            } else {
                System.out.println("------已存在该目录,如该目录下没有文件,请删除该目录重试");
            }
            count++;
        }
    }

}
