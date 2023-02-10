# android开发之自定义组件

https://blog.csdn.net/weixin_34072637/article/details/93194457?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166089867716782246486755%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166089867716782246486755&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-93194457-null-null.142^v42^pc_rank_34_1,185^v2^tag_show&utm_term=android%20%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BB%84%E4%BB%B6&spm=1018.2226.3001.4187



##  一：自定义组件：

我认为，自定义组件就是android给我们提供的的一个空白的可以编辑的图片，它帮助我们实现的我们想要的界面，也就是通过自定义组件我们可以把我们要登入的界面画出来；自定义组件继承View；通过F3键，我们可以看到View继承了Drawable.Callback, KeyEvent.Callback,AccessibilityEventSource这三个接口；

自定义组件的实现：

1. 1. 我们定义一个类：Myview 继承View；实现它的构造三个方法；
   2. 定义画布Canvas，位图Bitmap；画笔Paint；在构造方法中实例化Paint（一般是在两个参数的构造方法中实现）；
   3. 重写draw()方法，实例化位图，根据位图创建画布;注意的是在draw()方法中，参数是画布Canvas，所以在实例化时，注意避免参数重名，或者加上当参数名一样时注意加上*this。![img](https://images2015.cnblogs.com/blog/961148/201608/961148-20160808165841277-1115603998.png)**
      *
   4. 自定义组件中，有自带的监听方法，可以重写onTouchEvent（）方法实现监听；（int action = event.getAction();//获取动作事件；）
   5. 自定义组件中的页面刷新方法，相当于java中的重绘paint（）方法：postInvalidate();// 刷新页面方法