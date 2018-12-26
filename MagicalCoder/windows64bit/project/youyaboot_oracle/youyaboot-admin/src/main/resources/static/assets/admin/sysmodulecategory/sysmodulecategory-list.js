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
        tableNameRest:"sys_module_category_rest",
        tableName:"sys_module_category",
        moduleName:"sys_module_category",//sys_module的moduleName
        childrenPage:[
            {
                tabTitle:"左侧二级菜单",
                url:"admin/page/sys_module/list",
                mcForeignName:"moduleCategoryId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',moduleCategoryName:'module_category_name',sortNum:'sort_num'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '主键', minWidth:100, align:"center",sort:true},

                {field: 'moduleCategoryName', title: '一级菜单名称', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.moduleCategoryName) +'" class="magicalcoder-table-text layui-input security_list_table_form_moduleCategoryName" name="moduleCategoryName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入一级菜单名称"/>'
                    }
                    , sort:true
                },

                {field: 'sortNum', title: '排序', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.sortNum) +'" class="magicalcoder-table-text layui-input security_list_table_form_sortNum" name="sortNum" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_digits" magicalcoder-verify=""  placeholder="请输入排序"/>'
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
