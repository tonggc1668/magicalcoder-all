/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist",
    "authtree" : "magicalcoder/v001/rmp/authtree"
})
layui.use(['magicalcoderlist','authtree','table'],function(){
    var form = layui.form
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        authtree = layui.authtree,
         $ = layui.jquery
    var  magicalcoderlist = layui.magicalcoderlist(
    {
        tableNameRest:"sys_role_rest",
        tableName:"sys_role",
        moduleName:"sys_role",//sys_module的moduleName
        childrenPage:[
            {
                tabTitle:"管理员",
                url:"admin/page/sys_admin_user/list",
                mcForeignName:"roleId"
            },            {
                tabTitle:"角色模块权限",
                url:"admin/page/sys_role_module_permission/list",
                mcForeignName:"roleId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',roleName:'role_name'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '主键', minWidth:100, align:"center",sort:true},

                {field: 'roleName', title: '角色名', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.roleName) +'" class="magicalcoder-table-text layui-input security_list_table_form_roleName" name="roleName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入角色名"/>'
                    }
                    , sort:true
                },
        {title: '操作', minWidth:250, templet:'#newsListOperateTemplate',fixed:"right",align:"center"}
    ]];
    //表格中的一些事件
    var tableEvent = {
        "tool":function (obj,roleId) {
            var layEvent = obj.event
            if(layEvent == 'priority'){
                layui.layer.open({
                    title:'分配权限',
                    type : 2,
                    area:['800px','600px'],
                    content : "admin/page/sys_role/permission?roleId="+roleId
                    ,btn:["保存"]
                    ,yes: function(index, layero){
                        var body = layui.layer.getChildFrame('body', index);
                        body.find("[name='submit']").click();
                        layui.layer.close(index)
                    }
                });
               /* layui.layer.full(index);
                //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
                $(window).on("resize",function(){
                    layui.layer.full(index);
                })*/
                return false;
            }
            return true;
        }
    }
    magicalcoderlist.bindTableArea(cols,tableEvent)
    magicalcoderlist.bindAreaEvents();
})
