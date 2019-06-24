# RoundedImageView 使用

`RoundedImageView` 是继承自`ImageView`的自定义`View`，在了解该类之前，我们应该
了解到`Drawable`这个类的子类及其应用，打开源码，我们看到`Drawable`的子类非常多，
这也就是我们在使用`drawable`文件夹中的资源的时候，可以使用类似图片、`shape`、`Selector`
等资源，其实系统就是将其中的资源转换成了对应的`Drawable`:

![drawable子类](https://github.com/shejishi/ReadSourceCode/blob/master/img/Drawable%E5%AD%90%E7%B1%BB.png)

