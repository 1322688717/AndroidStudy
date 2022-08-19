# android  jetpack

#### 简介

* Jetpack 是一个由多个库组成的套件，可帮助开发者遵循最佳做法、减少样板代码并编写可在各种 Android 版本和设备中一致运行的代码，让开发者可将精力集中于真正重要的编码工作。 

#### 使用

* 导入依赖

  * ```
    dependencies {
        def lifecycle_version = "2.2.0"
    
        implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
        ...
    }
    ```

