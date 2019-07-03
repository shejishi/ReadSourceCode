# AspctJ Aop 在Android中详细使用规则

在上篇文章中，为了看源码的运行流程，我在每个方法中去打印了一句`log`，对于偷懒的我来说，这是非常不能忍的，为了以后能够愉快的阅读源码，我这两天详细的使用了`aspctJ`中的语法使用；搜索网上的介绍`aop`在`Android`上的使用发现基本上都是解释一下用法，然后写一个`demo`，这篇文章将介绍和使用到具体的应用中。

首先看看我们之前打印的log和现在打印的log（点击查看大图）
|![方法运行.png](https://upload-images.jianshu.io/upload_images/2158207-3f3c3db38eb9a7f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240/h/540)|![加入aspctJ之后的log.png](https://upload-images.jianshu.io/upload_images/2158207-491d90d533b68782.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540/h/540)|

### AspctJ 介绍

[官网主页AspctJ项目](https://www.eclipse.org/aspectj/)

[AspctJ详细使用文档](https://www.eclipse.org/aspectj/doc/released/progguide/index.html)

上面是官网的介绍及使用文档，国内很多文档都只是浅显的介绍了`AspctJ`是个什么玩意，然后写一个小例子来，弄得学习的人云里雾里，我再查找这些资料的时候也是吐槽不断，下面我尽量把所有的使用规则介绍，然后编写demo来，希望大家都能明白使用这玩意并不是很难的东西~

