# DataBinding+LiveData+ViewModule

#### databinding使用方法

* 首先在gradle里增加一句话
  * android {
        ...
        buildFeatures {
            dataBinding true
        }
    }
*  布局xml文件需要注意对Activity的对应绑定关系： 
  *  private ActivityLoginBinding binding;
          binding = ActivityLoginBinding.inflate(getLayoutInflater());
                  final EditText usernameEditText = binding.username;



#### livedata使用方法

* 