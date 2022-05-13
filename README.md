# AndroidStudy

哔哩哔哩上面的课程学习   https://www.bilibili.com/medialist/play/ml1202564161/BV13y4y1E7pF?oid=802123187&amp;otype=2



### 依赖



    viewBinding {
        enabled = true
    }
* butterknife  绑定视图依赖BindView，告别findById，不过你还得安装一个butterknife插件才行

  * ```
    implementation 'com.jakewharton:butterknife:10.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
    ```

* 权限请求框架

* ```
   implementation 'com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar'
   ```
  
* 支持okhttp+retrofit+rxjava

* ```
      // Okhttp库
      implementation 'com.squareup.okhttp3:okhttp:3.14.9'
      // Retrofit库
      implementation 'com.squareup.retrofit2:retrofit:2.9.0'
      //日志拦截器
    implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'
      //添加retrofit gson转换会自动下载gson
      implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
      //RxJava依赖
      implementation 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
  ```
  
*  阿里巴巴FastJson

* ```
  api 'com.alibaba:fastjson:1.2.57'
  ```

* 下拉刷新框架

* ```
   implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-alpha-14'
   implementation 'com.scwang.smartrefresh:SmartRefreshHeader:1.1.0-alpha-14'
  ```

* 图片加载框架

* ```java
   implementation 'com.github.bumptech.glide:glide:4.10.0'
      annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
  ```

* lombok插件

```
 compileOnly 'org.projectlombok:lombok:1.18.16'
    annotationProcessor 'org.projectlombok:lombok:1.18.16'
```

## 注：

* lombok插件

* butterknife插件

* 需要在项目build.gradle中也加上配置才能生效

* ```
  classpath 'org.projectlombok:lombok:1.18.0' 
  ```

* ```
  classpath 'com.jakewharton:butterknife-gradle-plugin:10.1.0' 
  ```

## ![image-20220424224415686](C:\Users\zcq\AppData\Roaming\Typora\typora-user-images\image-20220424224415686.png)

* 关于屏幕适配问题需要装一个插件
  * ScreenMatch
* 关于ButterKnife也需要装个插件
  * Android ButterKnife Zelezny
* Android studio中文插件
  * Chinese Language Pack EAP
* Json转Gson插件
  * GsonFormatPlus    ALT+S启动
* Lombok插件
  * Lombok
