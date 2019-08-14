# Butterknife 源码分析         

>如果你注意到，在一些复杂的页面，刚刚开始加载的时候会出现黑白屏或者是组件加载不出来，查看耗时方法得知，在`onCreate()`的时候你可能使用了过多的初始化操作，除了这些初始化的操作，其实仔细观察，你会发现：由于界面的复杂性，使用过多的`findViewById()`方法也会导致界面的卡顿；为什么会导致卡顿，这里不作详细讨论。

#### Butterknife所解决的问题
刚开始做开发的时候，你会觉得写`find`是一个及其重复且毫无技术的体力活；于是，就有了`Butterknife`这样的神器诞生了，它的作用就是为了解决我们重复的劳动，还有就是为了提高我们界面的效率，当界面第一次加载的时候，可能会感觉到缓慢，但是再一次加载时会发现快很多；为什么呢，这里卖一个关子，后面解释。

###### Butterknife 的使用
```
class ExampleActivity extends Activity {
  @BindView(R.id.user) EditText username;
  @BindView(R.id.pass) EditText password;

  @BindString(R.string.login_error) String loginErrorMessage;

  @OnClick(R.id.submit) void submit() {
    // TODO call server...
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.simple_activity);
    ButterKnife.bind(this);
    // TODO Use fields...
  }
}
```

添加依赖到主项目中：
```
android {
  ...
  // Butterknife requires Java 8.
  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation 'com.jakewharton:butterknife:10.1.0'
  annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
}
```

如果在`library`中使用`Butterknife`，则需要添加`plugin`
```
buildscript {
  repositories {
    mavenCentral()
    google()
   }
  dependencies {
    classpath 'com.jakewharton:butterknife-gradle-plugin:10.1.0'
  }
}
```
然后将其添加到你的模块中:
```
apply plugin: 'com.android.library'
apply plugin: 'com.jakewharton.butterknife'
```
经过上面的操作，你以前把`Butterknife`集成到你的项目中，但是如果在`library`中使用的话，和在`application`中是不一样的，它需要使用`R2`来代替`R`:
```
class ExampleActivity extends Activity {
  @BindView(R2.id.user) EditText username;
  @BindView(R2.id.pass) EditText password;
...
}
```