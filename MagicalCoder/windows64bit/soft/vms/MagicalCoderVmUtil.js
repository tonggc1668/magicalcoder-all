/**
 定义一系列js方法 可直接用于模板编写工具类中 请保证此文件编码为utf-8
 模板中调用方法：$JS.execute(functionName,arg0,arg1,...argn)
 $JS.execute("test_log",1,2)
 $JS.execute("testMap",$dbProject)
 您唯一可以使用的调试方法为
 console.log(msg);
 */
//演示打印日志功能
function test_log(a,b) {
    console.log(a+b)
    return a+b;
}
//您也可以传入java对象，在这里像取json对象一样操作它们
function testMap(dbProject) {
    return dbProject.databaseRealName;
}
