# android Banner

#### 开源库

* https://github.com/youth5201314/banner

### 方法

#### 依赖

* ```
  repositories {
      maven { url "https://s01.oss.sonatype.org/content/groups/public" }
  }
  
  dependencies{
      //2.1.0以前jcenter的依赖
      //implementation 'com.youth.banner:banner:2.1.0'
      //现在Maven Central
      implementation 'io.github.youth5201314:banner:2.2.2'
  
  }
  ```

  #### 添加权限

  * ```
    <!-- if you want to load images from the internet -->
    <uses-permission android:name="android.permission.INTERNET" /> 
    ```

    