MagicalCoder采用velocity模板引擎
同时扩展支持MagicalCoderVmUtil.java和MagicalCoderVmUtil.js来定制您的自定义方法
你可以十分方便的使用以上任何一个方式来协助您处理velocity模板 具体使用方法可以参照教程

基本模板语言语法使用
在hellovelocity.vm里面可以看到很多以#和$符开头的内容，这些都是Velocity的语法。在Velocity中所有的关键字都是以#开头的，而所有的变量则是以$开头。Velocity的语法类似于JSP中的JSTL，甚至可以定义类似于函数的宏，下面来看看具体的语法规则。

一、变量
和我们所熟知的其他编程语言一样，Velocity 也可以在模板文件中有变量的概念。

1. 变量定义
#set($name ="velocity")
等号后面的字符串Velocity 引擎将重新解析，例如出现以$开始的字符串时，将做变量的替换。

#set($hello ="hello $name")
上面的这个等式将会给$hello 赋值为"hello velocity"

2. 变量的使用
在模板文件中使用$name或者${name}来使用定义的变量。推荐使用${name}这种格式，因为在模板中同时可能定义了类似$name和$names的两个变量，如果不选用大括号的话，引擎就没有办法正确识别$names这个变量。
对于一个复杂对象类型的变量，例如$person，可以使用${person.name}来访问person的name属性。值得注意的是，这里的${person.name}并不是直接访问person的name属性，而是访问person的getName()方法，所以${person.name}和${person.getName()}是一样的。

3. 变量赋值
在第一小点中，定义了一个变量，同时给这个变量赋了值。对于Velocity来说，变量是弱数据类型的，可以在赋了一个String给变量之后再赋一个数字或者数组给它。可以将以下六种数据类型赋给一个Velocity变量：变量引用,字面字符串,属性引用,方法引用,字面数字,数组列表。
#set($foo = $bar)
#set($foo ="hello")
#set($foo.name = $bar.name)
#set($foo.name = $bar.getName($arg))
# set($foo = 123)
#set($foo = ["foo",$bar])

二、循环
在Velocity 中循环语句的语法结构如下：
#foreach($element in $list)
 This is $element
 $velocityCount
#end
Velocity 引擎会将list 中的值循环赋给element 变量，同时会创建一个$velocityCount 的变量作为计数，从1 开始，每次循环都会加1.

三、条件语句
条件语句的语法如下
#if(condition)
...
#elseif(condition)
…
#else
…
#end

四、关系操作符
Velocity 引擎提供了AND、OR 和NOT 操作符，分别对应&&、||和! 例如：
#if($foo && $bar)
#end

五、宏
Velocity中的宏可以理解为函数定义。定义的语法如下：
#macro(macroName arg1 arg2 …)
...
#end

调用这个宏的语法是：
#macroName(arg1 arg2 …)
这里的参数之间使用空格隔开，下面是定义和使用Velocity 宏的例子：
#macro(sayHello $name)
hello $name
#end

#sayHello("velocity")
输出的结果为hello velocity

六、#parse 和#include
#parse和#include指令的功能都是在外部引用文件，而两者的区别是，#parse会将引用的内容当成类似于源码文件，会将内容在引入的地方进行解析，#include是将引入文件当成资源文件，会将引入内容原封不动地以文本输出。分别看以下例子：

foo.vm 文件：

#set($name ="velocity")
parse.vm：

#parse("foo.vm")
输出结果为：velocity

include.vm：

#include("foo.vm")
输出结果为：#set($name ="velocity")

以上内容包含了部分Velocity 的语法，详细的语法内容可以参考Velocity 的官方文档。
