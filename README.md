<------------- 简介 ------------>

整体搭建 基于AndroidX :
架构: mvvm
网络请求: Retrofit + RxJava
数据传递: viewModel + liveData
基类在base模块, 依赖在项目的build.gradle文件里

<------------- Base模块说明 ------------>

涉及网络请求的界面, 继承BaseDataActivity , BaseDataFragment/BaseLazyFragment
不涉及网络请求的界面, 继承BaseActivity

<------------- 网络请求  ------------>

1.在 UrlConfig 中配置 baseUrl.
  注意: baseUrl要求以/结尾 ,如 : http://v.juhe.cn/weather/

2.所有网络请求调用方法在 ApiService 中统一管理 , get/post 用法里面有说明.
  例:
  @GET(".php?a=fy&f=auto&t=auto&w=hello%20fuck")
    Observable<BaseResponse<TranslationBean>> getNews();
  泛型中的 BaseResponse<TranslationBean> 是为了自己处理回调

3.创建M(Model)层, 新建Model类,调用ApiService中的网络请求方法, 获取Observable<BaseResponse<TranslationBean>>对象

 例如NewModel :
 public Observable<BaseResponse<TranslationBean>> getNews() {
        return  HttpUtils.getInstance().getApiService().getNews();
 }

4.创建VM(ViewModel)层, 继承BaseViewModel(BaseViewModel中默认创建 loadStatusLiveData对象(用于控制网络请求的进度条))

  在viewModel的构造方法中, 初始化Model层的对象 , 并创建网络请求返回的具体Bean类的对象(创建的方式为LiveData的方式).
  在onSuccess()方法中, 为创建的Bean类对象赋值 translationBeanValue.setValue(translationBean);
  例 :
   newsModel.getNews()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(new BaseObserver<TranslationBean>(loadStatusLiveData) {
                      @Override
                      public void onSuccess(TranslationBean translationBean) {
                          translationBeanValue.setValue(translationBean);
                      }

                      @Override
                      public void onFailure(Throwable e, String errorMsg) { //errorMsg为后台返回的msg字段
                          //处理其他状态码的信息
                          //showDialog(); context 取 application
                      }
                  });

  注 : 若使用BaseObserver, 则subscribe(new BaseObserver<TranslationBean>(loadStatusLiveData) 这一行需要手写


5. 在V层请求/处理数据 : 在Activity/Fragment中, 继承的BaseActivity/BaseFragment(传入对应ViewModel泛型), 默认创建好了ViewModel对象:
  请求数据:  mViewModel.getNews();
  处理回调:  mViewModel.translationBeanValue.observe(this, new Observer<TranslationBean>() {
                     @Override
                     public void onChanged(TranslationBean translationBean) {
                        //处理逻辑
                     }
            });


用法可见study模块中 :  NewsActivity / NewsModel / NewsViewModel




<------------- 关于组件化构建 ------------>

1. 新建base , work , login , module

2. app模块为壳子整合其他模块, login和work模块代表业务层 , base模块是公共模块

3. app模块依赖于base模块

4. 在项目根目录的build.gradle里, 加入版本号及常用依赖的配置

5. 把各个模块需要依赖的 公共 库添加到base模块的build.gradle中 ,通过 libs.xxx , cfg.xxx的形式 (第一次需要手动复制) ,
    注意使用api(可公用)关键字

6. 修改login,work模块的build.gradle文件 , 让各个模块依赖于base模块 ,注意业务模块要使用implementation关键字(不可公用)

7. 至此各个模块依赖关系完毕

8. 设置App可以整体运行或者分模块运行 :

    此处需要设置一个布尔值标志位 isUniqueApp : (这里业务模块统一管理了, 也可以设置多个标志位)

    在项目的gradle.properties中，新增isUniqueApp变量(整体/模块调试 , 声明在这因为Gradle可以重新构建)

    将需要单独运行的login, work等模块的 build.gradle文件里顶部的 apply plugin: 'com.android.library'

    修改为 : (isUniqueApp = true ?整体调试 : 模块调试 )

    if(isUniqueApp.toBoolean()){
          apply plugin: 'com.android.library'
     }else{
          apply plugin: 'com.android.application'

    }

9.  设置项目整体运行和模块单独运行的manifest :

    在login , work等模块java文件夹下 , 新建debug文件夹 , 放入一份该模块下的manifest文件 , 在Application标签下
    添加默认Theme ,
    添加默认启动的Activity (单模块运行相当于自己独立是App)
    <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>

    然后在login , work等模块的build.gradle文件下 , 添加

    sourceSets {
        main{
            if(isUniqueApp.toBoolean()){ //整体
                manifest.srcFile 'src/main/AndroidManifest.xml'
                            java{
                                exclude 'debug/**'
                            }
            }else{ //单独
                 manifest.srcFile 'src/main/java/debug/AndroidManifest.xml'
            }
        }
    }

10. 此时可根据 gradle.properties 的isUniqueApp标志位 , 去设置是否模块调试

11. 修改app模块对子模块的依赖 :

    if(isUniqueApp.toBoolean()){ //整体
        implementation project(':login')
        implementation project(':work')
    }
    意思是：如果login模块和work模块是作为单独app运行的，那么不引入first、second模块；
    如果first模块和second模块是作为总app的模块，那么引入first、second模块

12. 设置App图标相关属性还是再App模块下修改的清单文件即可

13. 接下来处理不同模块间跳转,  记得首先将 isUniqueApp 为true,
    只有App作为一个整体的时候, 才可以实现跳转不同的模块.
    确认ARouter的配置在login,work模块的build.gradle中设置完成.
    在defaultConfig 标签下添加 :
        javaCompileOptions {
                annotationProcessorOptions {
                    arguments = [ moduleName : project.getName() ]
                }
        }

    在Base模块添加BaseApplication类, 添加ARouter初始化代码:
    if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
    }
    ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化

    最后在App模块下的manifest name:BaseApplication


14. 这里使用ARouter处理不同模块间跳转, ARouter github : https://github.com/alibaba/ARouter

15. 在模块间添加ARouter的注解, @Route(path = "/login/login") , path的值可自己规范,
    第一个login为模块名, 第二个login为login模块下LoginActivity的简写

16. 跳转代码 :
    a. 应用内简单的跳转
    ARouter.getInstance().build("/login/login").navigation();
    b. 应用内简单的跳转
    ARouter.getInstance().build("/login/login")
            .withLong("key1", 666L)
            .withString("key2", "888")
            .withObject("key3", Object)
            .navigation();

注: 组件化module里, 处理onclick点击事件时, 不可用switch, 只能使用if , else if的形式
