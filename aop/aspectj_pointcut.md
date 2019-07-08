AspctJ Aop 在Android中详细使用规则（二）—— @Pointcut`

在上篇文章中，介绍了`aspectJ`的环境配置及简单的使用，接下来，我们来看详细的使用：

>`Pointcut` 意思为切入点，我们知道，在面向对象编程中，我们可以使用反射和动态代理的方式来截取某个类中的某个方法，但是这样是比较损耗性能的，所以才有了`aspectJ`这样的插件诞生，使得我们不需要改变原来的代码结构即可在横向编程中增加自己的业务逻辑~

先看下使用规则：

|Join Point|说明|Pointcuts语法|
|-------|-------|---------|
Method call|方法被调用|call(MethodPattern)
Method execution|方法执行|execution(MethodPattern)
Constructor call|构造函数被调用|call(ConstructorPattern)
Constructor execution|构造函数执行|execution(ConstructorPattern)
Field get|读取属性|get(FieldPattern)
Field set|写入属性|set(FieldPattern)
Pre-initialization|与构造函数有关，很少用到|preinitialization(ConstructorPattern)
Initialization|与构造函数有关，很少用到|initialization(ConstructorPattern)
Static initialization|static 块初始化|staticinitialization(TypePattern)
Handler|异常处理|handler(TypePattern)
Advice execution|所有 Advice 执行|adviceexcution()