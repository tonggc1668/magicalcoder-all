/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
layui.config({base : "assets/"}).extend({"goods_config":"admin/goods/goods-config","goodscategory_config":"admin/goodscategory/goodscategory-config","magicalcoderedit" : "magicalcoder/v002/rmp/magicalcoderedit"})
layui.use(["goods_config","goodscategory_config","magicalcoderedit"],function(){
        //new Form();搞定就好了 剩下的都在Form里面处理 这样就组件化了

    {
        var magicalcoderedit = layui.magicalcoderedit(layui['goods_config']);
        //在提交表单前执行回调方法
        var submitBeforeCallBack = function (postData) {
            return true;//则继续提交
        }
        magicalcoderedit.bindFormArea();
        magicalcoderedit.submitEvents(submitBeforeCallBack);
    }

})
