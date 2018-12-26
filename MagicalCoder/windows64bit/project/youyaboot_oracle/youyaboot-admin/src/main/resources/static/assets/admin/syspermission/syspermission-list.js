/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"
})
layui.use(['magicalcoderlist'],function(){
    var  magicalcoderlist = layui.magicalcoderlist(
    {
        tableNameRest:"sys_permission_rest",
        tableName:"sys_permission",
        moduleName:"sys_permission",//sys_module的moduleName
        childrenPage:[
            {
                tabTitle:"角色模块权限",
                url:"admin/page/sys_role_module_permission/list",
                mcForeignName:"permissionId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            permissionName:'permission_name',filterPlatform:'filter_platform',backendUrlReg:'backend_url_reg',frontDom:'front_dom',frontAction:'front_action',moduleId:'module_id'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'moduleId', title: '所属模块', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.moduleId || d.moduleId==null) ? '' : d.moduleId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_moduleId" lay-ignore="true"  name="moduleId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_module_rest/search" data-id="id" data-text-fields="moduleTitle" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入所属模块">'+option+'</select>'
                    },sort:true
                },

                {field: 'permissionName', title: '权限名称', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.permissionName) +'" class="magicalcoder-table-text layui-input security_list_table_form_permissionName" name="permissionName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入过滤器名称:如审核通过"/>'
                    }
                    , sort:true
                },
                {field: 'filterPlatform', title: '规则适用端',minWidth:350, align:'center', templet:'#filterPlatformTemplate',sort:true},

                {field: 'frontDom', title: '前端Key', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.frontDom) +'" class="magicalcoder-table-text layui-input security_list_table_form_frontDom" name="frontDom" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入前端dom获取"/>'
                    }
                    , sort:true
                },
                {field: 'frontAction', title: '前端处理方式',minWidth:350, align:'center', templet:'#frontActionTemplate',sort:true},

                {field: 'backendUrlReg', title: '后端的地址正则', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.backendUrlReg) +'" class="magicalcoder-table-text layui-input security_list_table_form_backendUrlReg" name="backendUrlReg" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序"/>'
                    }
                    , sort:true
                },
        {title: '操作', minWidth:250, templet:'#newsListOperateTemplate',fixed:"right",align:"center"}
    ]];
    //表格中的一些事件
    var tableEvent = {
        "tool":function (obj,roleId) {//操作部分的按钮
            return true;//不阻止事件继续执行封装的事件
        },
        "sort":function (obj,safeOrderBy) {//排序
            return true;//不阻止事件继续执行封装的事件
        }
    }
    magicalcoderlist.bindTableArea(cols,tableEvent)
    magicalcoderlist.bindAreaEvents();
})
