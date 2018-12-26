/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({base : "assets/"}).extend({"dict_config":"admin/dict/dict-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","dict_config"],function(){
    var config = $.extend(layui.dict_config,{
        childrenPage:[
            {
                tabTitle:"字典",
                url:"admin/page/dict/list",
                mcForeignName:"parentId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            dictCategory:'dict_category',code:'code',name:'name',parentId:'parent_id',createTime:'create_time',updateTime:'update_time',orderNo:'order_no'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},

            {field: 'dictCategory', title: '字典大类', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.dictCategory) +'" class="magicalcoder-table-text layui-input security_list_table_form_dictCategory" name="dictCategory" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入字典大类"/>'
                }
                , sort:true
            },

            {field: 'code', title: '编码', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.code) +'" class="magicalcoder-table-text layui-input security_list_table_form_code" name="code" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入编码"/>'
                }
                , sort:true
            },

            {field: 'name', title: '名称', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.name) +'" class="magicalcoder-table-text layui-input security_list_table_form_name" name="name" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入名称"/>'
                }
                , sort:true
            },
                {field: 'parentId', title: '所属父类', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.parentId || d.parentId==null) ? '' : d.parentId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_parentId" lay-ignore="true"  name="parentId" data-identify="'+d.id+'" data-value="" data-url="admin/dict_rest/search" data-id="id" data-text-fields="code,name" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入所属父类">'+option+'</select>'
                    },sort:true
                },
            {field: 'createTime', title: '创建时间', align:'center', minWidth:250, templet :"#createTimeTemplate",sort:true},
            {field: 'updateTime', title: '更新时间', align:'center', minWidth:250, templet :"#updateTimeTemplate",sort:true},

            {field: 'orderNo', title: '序号', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.orderNo) +'" class="magicalcoder-table-text layui-input security_list_table_form_orderNo" name="orderNo" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required|mc_digits" magicalcoder-verify=""  placeholder="请输入序号"/>'
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
