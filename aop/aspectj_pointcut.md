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

这里，我们看到不同的切入点调用的`Pointcuts`方法也不同，这里先不作详细说明，下面会使用详细的例子来说明~

### 2、Pointcuts
在我们的例子中，使用了`within`这样的写法获取到某个包下的信息，这就是`Pointcuts`给我们带来的方便，他提供了多种写法给我们使用，让我们可以过滤到不需要的信息；下表中详细列出了使用方法：

|Pointcuts语法|说明|示例|
|------|-----------|-------|
|within(TypePattern)|TypePattern标示package或者类TypePattern可以使用通配符|表示某个package或者类中的所有JPoint。比如within(Test):Test类中所有JPoint
|withincode(Constructor Signature/Method Signature)|表示某个构造函数或其他函数执行过程中涉及到的JPoint|比如 withinCode(* TestDerived.testMethod(..)) 表示testMethod涉及的JPoint。withinCode( *.Test.new(..))表示Test构造函数涉及的JPoint
cflow(pointcuts)|cflow是call flow的意思，cflow的条件是一个pointcut|比如cflow(call TestDerived.testMethod)：表示调用TestDerived.testMethod函数时所包含的JPoint，包括testMethod的call这个JPoint本身
cflowbelow(Pointcut)|cflow是call flow的意思|比如cflowblow(call TestDerived.testMethod)：表示调用TestDerived.testMethod函数时所包含的JPoint，不包括testMethod的call这个JPoint本身
this(Type)|Join Point 所属的 this 对象是否 instanceOf Type 或者 Id 的类型|JPoint是代码段（不论是函数，异常处理，static block），从语法上说，它都属于一个类。如果这个类的类型是Type标示的类型，则和它相关的JPoint将全部被选中。图2示例的testMethod是TestDerived类。所以this(TestDerived)将会选中这个testMethod JPoint
target(Type)|JPoint的target对象是Type类型和this相对的是target。不过target一般用在call的情况。call一个函数，这个函数可能定义在其他类。|比如testMethod是TestDerived类定义的。那么target(TestDerived)就会搜索到调用testMethod的地方。但是不包括testMethod的execution JPoint
args(TypeSignature)|用来对JPoint的参数进行条件搜索的|比如args(int,..)，表示第一个参数是int，后面参数个数和类型不限的JPoint。

Pointcut 表达式还可以 ！、&&、|| 来组合，语义和java一样。上面 Pointcuts 的语法中涉及到一些 Pattern，下面是这些 Pattern 的规则，[]里的内容是可选的：

|Pattern|规则|
|:------:|:------:|
MethodPattern|[!] [@Annotation] [public,protected,private] [static] [final] 返回值类型 [类名.]方法名(参数类型列表) [throws 异常类型]
ConstructorPattern|[!] [@Annotation] [public,protected,private] [final] [类名.]new(参数类型列表) [throws 异常类型]
FieldPattern|[!] [@Annotation] [public,protected,private] [static] [final] 属性类型 [类名.]属性名
TypePattern|其他 Pattern 涉及到的类型规则也是一样，可以使用 ‘!’、’‘、’..’、’+’，’!’ 表示取反，’‘ 匹配除 . 外的所有字符串，’*’ 单独使用事表示匹配任意类型，’..’ 匹配任意字符串，’..’ 单独使用时表示匹配任意长度任意类型，’+’ 匹配其自身及子类，还有一个 ‘…’表示不定个数。也可以使用 &&、|| 操作符

### 3、Pointcuts 语法

##### 3.1 execution
######3.1.1 execution 找类中方法
```
@Aspect
public class MethodApt {
    public static final String TAG = "AnnotationApt";
    @Pointcut("execution(* com.ellison.aop.method.ExcustionMethodActivity.test())")
    public void executionFindMethod() {
    }

    @Before("executionFindMethod()")
    public void invokMethod(JoinPoint point) throws Throwable {
        Log.d(TAG, "方法之前");
        ((ProceedingJoinPoint) point).proceed();
        Log.d(TAG, "方法之后");
    }
}
```

使用`execution`查找具体的某个类中的某个方法：
```
execution(* com.ellison.aop.method.ExcustionMethodActivity.test())
```
第一个 * 表示方法的修饰符，`public`、`private`、`protected`，上面解释了 * 为通配符，查找类下得所有方法：
```
execution(* com.ellison.aop.method.ExecutionMethodActivity.**())
```
具体的返回值、修饰符、方法查找就在上面的代码上修改即可~
######3.1.1 execution 找注解
首先，我们编写一个自定义注解：
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionAnnotationFindMethod {
}
```
该注解使用在`METHOD`上，接下来我们编写`aspectJ`代码：
```

@Aspect
public class AnnotationApt {
    public static final String TAG = "AnnotationApt";
    @Pointcut("execution(@com...ExecutionAnnotationFindMethod * *(..))")
    public void annotationFindMethod() {
    }

    @Before("annotationFindMethod()")
    public void invokMethod(JoinPoint point) throws Throwable {
        Log.d(TAG, "具体方法之前");
        ((ProceedingJoinPoint) point).proceed();
    }
}
```
因为包名太长，所以我省略了，在`Pointcut`上，需要写上注解的包名全路径，很简单，我们需要给`JoinPoint`切入点写下如下代码即可：
```
execution(@com.ellison.aop.annotation_method.ExecutionAnnotationFindMethod * *(..))
```
写上注解的全路径，之后就是匹配 **返回值**、**方法名**、**方法参数**，例如你需要找到注解了`ExecutionAnnotationFindMethod`的方法，同时还需要找到返回值为`ReturnParam`的方法，则需要编写如下`Pointcut`:
```
execution(@com.ellison.aop.annotation_method.ExecutionAnnotationFindMethod ReturnParam *(..))
```
在比如，你需要找到参数为`String`类型的并注解了`ExecutionAnnotationFindMethod`的方法时，需要编写如下`Pointcut`:

```
execution(@com.ellison.aop.annotation_method.ExecutionAnnotationFindMethod * *(String))
```













