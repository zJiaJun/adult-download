###Adult Download


Adult Download是个垂直爬虫，对特定的网站分析，下载图片和种子

***

正在进行重构代码，以达到更抽象，更灵活的代码，方便扩展不同的网站。

分为以下组件

* Input: 输入组件，根据Request对象下载并生成Page对象。

* Process: 处理组件，根据输入信息解析页面，高度灵活。

* Output: 输出组件，可以理解为持久层，如文件形式，数据库形式等。

* Connect: 连接组件，也可以理解为下载组件，本质上是从网站上下载页面提供给输入组件，下载种子文件，图片等。

组件之间的数据流对象

* Request 请求对象，下载页面时必要的请求信息，如URL地址，请求方法，登录用户名和密码等。

* Page 页面对象，请求得到的页面原始信息，交给Process处理。

* PageResult 页面结果对象，Process处理后得到的结果信息，交给Output处理。

***


### 技术

- 使用Jsoup解析HTML
- 使用Retrofit2做为httpClient
