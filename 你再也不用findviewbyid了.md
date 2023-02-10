# 你再也不用findviewbyid了

https://blog.csdn.net/qq_40447795/article/details/124440252

* 1、build.[gradle](https://so.csdn.net/so/search?q=gradle&spm=1001.2101.3001.7020)（app下的）的dependencies中添加

  ```
  implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
  ```

* 2、build.gradle（app下的）的plugins中添加

  ```
  id 'kotlin-android-extensions'
  ```

* 3、同步 一下就行了