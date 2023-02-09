关于glide图片夹载使用方法

*  依赖

  * ```
    //glide图片加载器implementation 'com.github.bumptech.glide:glide:4.13.0'annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
    ```

    ​			

* github官网

  * https://github.com/bumptech/glide

* 基本使用方法

  * Glide.with(context)
        .load(url)
        .into(imageView);