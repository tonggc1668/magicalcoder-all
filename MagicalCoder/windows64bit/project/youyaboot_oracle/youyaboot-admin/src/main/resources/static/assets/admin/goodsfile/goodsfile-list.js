/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({base : "assets/"}).extend({"goodsfile_config":"admin/goodsfile/goodsfile-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","goodsfile_config"],function(){
    var config = $.extend(layui.goodsfile_config,{
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            fileId:'file_id',fileSrc:'file_src',goodsId:'goods_id'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'fileId', title: '主键', minWidth:100, align:"center",sort:true},

            {field: 'fileSrc', title: '文件地址', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.fileSrc) +'" class="magicalcoder-table-text layui-input security_list_table_form_fileSrc" name="fileSrc" data-identify="'+d.fileId+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入文件地址"/>'
                }
                , sort:true
            },
                {field: 'goodsId', title: '所属商品', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.goodsId || d.goodsId==null) ? '' : d.goodsId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_goodsId" lay-ignore="true"  name="goodsId" data-identify="'+d.fileId+'" data-value="" data-url="admin/goods_rest/search" data-id="id" data-text-fields="goodsName" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入所属商品">'+option+'</select>'
                    },sort:true
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
