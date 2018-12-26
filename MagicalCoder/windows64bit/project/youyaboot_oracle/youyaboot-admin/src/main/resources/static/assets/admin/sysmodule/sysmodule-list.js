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
        tableNameRest:"sys_module_rest",
        tableName:"sys_module",
        moduleName:"sys_module",//sys_module的moduleName
        childrenPage:[
           /* {
                tabTitle:"后端请求地址全局允许过滤器",
                url:"admin/page/sys_global_permit_url/list",
                mcForeignName:"moduleId"
            },*/            {
                tabTitle:"权限规则配置",
                url:"admin/page/sys_permission/list",
                mcForeignName:"moduleId"
            }/*,            {
                tabTitle:"角色模块权限",
                url:"admin/page/sys_role_module_permission/list",
                mcForeignName:"moduleId"
            } */     ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',moduleName:'module_name',moduleUrl:'module_url',moduleCategoryId:'module_category_id',sortNum:'sort_num',moduleTitle:'module_title',ifShow:'if_show'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '主键', minWidth:100, align:"center",sort:true},
                {field: 'moduleCategoryId', title: '一级菜单', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.moduleCategoryId || d.moduleCategoryId==null) ? '' : d.moduleCategoryId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_moduleCategoryId" lay-ignore="true"  name="moduleCategoryId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_module_category_rest/search" data-id="id" data-text-fields="moduleCategoryName" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入所属一级菜单">'+option+'</select>'
                    },sort:true
                },

                {field: 'moduleTitle', title: '二级菜单标题', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.moduleTitle) +'" class="magicalcoder-table-text layui-input security_list_table_form_moduleTitle" name="moduleTitle" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入二级菜单标题"/>'
                    }
                    , sort:true
                },

                {field: 'moduleName', title: '二级菜单唯一值', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.moduleName) +'" class="magicalcoder-table-text layui-input security_list_table_form_moduleName" name="moduleName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="建议使用数据库表名"/>'
                    }
                    , sort:true
                },

                {field: 'moduleUrl', title: '菜单链接地址', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.moduleUrl) +'" class="magicalcoder-table-text layui-input security_list_table_form_moduleUrl" name="moduleUrl" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify="|definedOne=admin/page/[a-z0-9_]+/list.*"  placeholder="必须依照admin/page/表名称小写/list"/>'
                    }
                    , sort:true
                },

                {field: 'ifShow', title: '是否显示',minWidth:150, align:'center', templet:function(d){
                    var checked = (d.ifShow+''=='true' || d.ifShow+'' == '1') ? 'checked' : '';
                    return '<input type="checkbox" class="magicalcoder-table-layswitch security_list_table_form_ifShow" data-identify="'+d.id+'" name="ifShow"  lay-filter="ifShowFilter" lay-skin="switch" lay-text="是|否"  '+checked+' lay-verify="magicalCoderVerify" magicalcoder-verify="" />'
                    }
                    ,sort:true
                },

                {field: 'sortNum', title: '排序', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.sortNum) +'" class="magicalcoder-table-text layui-input security_list_table_form_sortNum" name="sortNum" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_number" magicalcoder-verify=""  placeholder="请输入排序"/>'
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
