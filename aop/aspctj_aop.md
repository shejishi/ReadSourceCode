# AspctJ Aop 在Android中详细使用规则

在上篇文章中，为了看源码的运行流程，我在每个方法中去打印了一句`log`，对于偷懒的我来说，这是非常不能忍的，为了以后能够愉快的阅读源码，我这两天详细的使用了`aspctJ`中的语法使用；搜索网上的介绍`aop`在`Android`上的使用发现基本上都是解释一下用法，然后写一个`demo`，这篇文章将介绍和使用到具体的应用中。

首先看看我们之前打印的log和现在打印的log（点击查看大图）

![方法运行.png](https://upload-images.jianshu.io/upload_images/2158207-3f3c3db38eb9a7f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/h/540)


![加入aspctJ之后的log.png](https://upload-images.jianshu.io/upload_images/2158207-491d90d533b68782.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540/h/540)|


### AspctJ 介绍

[官网主页AspctJ项目](https://www.eclipse.org/aspectj/)

[AspctJ详细使用文档](https://www.eclipse.org/aspectj/doc/released/progguide/index.html)

上面是官网的介绍及使用文档，国内很多文档都只是浅显的介绍了`AspctJ`是个什么玩意，然后写一个小例子来，弄得学习的人云里雾里，我再查找这些资料的时候也是吐槽不断，下面我尽量把所有的使用规则介绍，然后编写demo来，希望大家都能明白使用这玩意并不是很难的东西~

### 一、正式开始
从我们上个项目讲的打印`log`例子开始，我们先了解`aspctJ`大概有些什么东西， 

###### 1.1 配置：
在项目层级的`build.gradle`文件下配置：
```
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.1'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4'
        
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
```

在 `application`模块的`build.gradle`文件下加入插件：
```
apply plugin: 'com.android.application'
apply plugin: 'android-aspectjx'
```

新建一个`library`模块，在其中引入`aspctJ`的依赖：
```
apply plugin: 'com.android.library'
android {
    compileSdkVersion rootProject.ext.android.compileSdkVersion
    defaultConfig {
        minSdkVersion rootProject.ext.android.minSdkVersion
        targetSdkVersion rootProject.ext.android.targetSdkVersion
        versionCode rootProject.ext.android.appVersionCode
        versionName rootProject.ext.android.appVersionName
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    // 引入依赖包
    api 'org.aspectj:aspectjrt:1.9.4'
}
```
到此，编译下载完包之后，一个`aspctJ`环境就配置好了，因为引用到了大佬的`com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4`插件，所以我们使用`aspctJ`看起来并不是那么困难~

###### 1.2 编写代码：
配置完成之后，就要根据我们的需求来编写代码了，由于在分析源码时，不知道源码是怎么样运行的，所以在一开始，我再每个方法里面都写了一句`Log`去打印方法，虽然解决了当时的窘境，但是一些父类的方法并不能打印出来，最致命的是，作为一个想偷懒的我来说，无法忍受~

>所以，我对自己提出需求，能不能使用`aop`的方式获取到运行得所有方法打印出`log`

上面的图片是我实现的并不严谨的需求，打印出来运行的所有方法~

分析需求，我们需要找到某个包名下所有的类，并包括构造方法、父类的方法，查找 资料编写如下代码：
```
@Aspect
public class AspctLogClass {
    public static final String TAG = "AspctLogClass";
    @Pointcut("within(com.makeramen.roundedimageview..*)")
    public void onLoggerMethod() {
    }

    @Before("onLoggerMethod()")
    public void doCacheMethod(JoinPoint point) throws Throwable {
        try {
            final Object object = point.getThis();
            if (object == null) {
                return;
            }
            Signature signature = point.getStaticPart().getSignature();
            if (signature instanceof MethodSignature) {
                //方法执行前
                if(!((MethodSignature) point.getStaticPart()
                            .getSignature()).getMethod().toString()
                            .contains("android.util.Log")) {
                    Log.e(TAG, ((MethodSignature) 
                        point.getStaticPart().getSignature()).getMethod().toString());
                }
            }
            if (signature instanceof ConstructorSignature) {
                if(!((ConstructorSignature) point.getStaticPart()
                            .getSignature()).getConstructor().toString()
                            .contains("android.util.Log")){
                    Log.e(TAG, ((ConstructorSignature) 
                       point.getStaticPart().getSignature()).getConstructor().toString());
                }
            }
            // 执行被切的方法
            ((ProceedingJoinPoint) point).proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
上面的代码可以正常运行，读者可以checkout出我的代码，自行运行~
[https://github.com/shejishi/ReadSourceCode/tree/master/log_annotation](https://github.com/shejishi/ReadSourceCode/tree/master/log_annotation)

下面我们带着疑问如下疑问来分析一下上面的代码：

1、类上的`@Aspect` 是什么？
2、方法上的`@Pointcut`是什么？
3、方法上的`@Before`是什么？

###### 1.3 代码分析—— 类上的`@Aspect` 是什么？

`@Aspect`是什么？  好吧，其实我也回答不好这个问题，插件在运行得时候，会去找到需要使用切面编程的地方，但是，怎么找到一个切入点呢？答案就在这个注解`@Aspect`上，插件会找到使用这个注解的地方，然后进行解析~

### 1.4 代码分析—— 方法上的`@ Pointcut ` 是什么？

这一节涉及的东西太多，所以还是分割到下一节~








