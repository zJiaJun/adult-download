###Adult Download


Adult Download是个垂直爬虫，对特定的网站分析，下载图片和种子

***

正在进行重构代码，以达到更抽象，更灵活的代码，方便扩展不同的网站。

分为以下组件

* scheduler     URL地址管理

* manager       负责调度，根据策略从scheduler中获取URL并生成请求信息

* downloader    多个下载，负责发送请求信息

* processor     页面解析器


***


### 技术

- 使用Jsoup解析HTML
- 使用Retrofit2做为httpClient
