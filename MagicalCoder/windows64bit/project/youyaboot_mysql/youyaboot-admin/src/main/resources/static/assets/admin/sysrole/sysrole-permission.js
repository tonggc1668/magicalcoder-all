layui.config({
    base : "assets/"
}).extend({
    "authtree" : "magicalcoder/v001/rmp/authtree",
    "magicalcoderutil" : "magicalcoder/v001/rmp/magicalcoderutil"
})
layui.use(['authtree','table','form','magicalcoderutil'],function(){
    var form = layui.form
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        authtree = layui.authtree,
        magicalcoderutil = layui.magicalcoderutil,
         $ = layui.jquery
        var authTreeId = "#auth-tree"

    var roleId = magicalcoderutil.getParameter("roleId");
    if(roleId!=''){
        $.getJSON("admin/rest_rmp/tree_data/"+roleId,{date:new Date().getTime()},function (data) {
            if(data.flag){
                authtree.render(authTreeId,data.data,{inputname: 'authids[]', openall: true})
            }else {
                layer.msg("获取权限树失败:"+data.desc)
            }
        })
    }else {
        layer.msg("哎呀，出错了")
    }

    form.on("submit(save)",function(data){
        var authids = authtree.getChecked(authTreeId);
        $.post('admin/rest_rmp/save_tree_data/'+roleId,{ids:authids},function (data) {
            if(!data.flag){
                layer.msg(data.desc)
            }
        })
        return false;
    })
})

