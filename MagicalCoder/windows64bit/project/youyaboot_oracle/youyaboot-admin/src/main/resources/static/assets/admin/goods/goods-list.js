/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({base : "assets/"}).extend({"goods_config":"admin/goods/goods-config","magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"})
layui.use(["magicalcoderlist","goods_config"],function(){
    var config = $.extend(layui.goods_config,{
        childrenPage:[
            {
                tabTitle:"商品附件",
                url:"admin/page/goods_file/list",
                mcForeignName:"goodsId"
            },            {
                tabTitle:"商品图集",
                url:"admin/page/goods_img/list",
                mcForeignName:"goodsId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',goodsName:'goods_name',publishStatus:'publish_status',goodsStatus:'goods_status',price:'price',storeCount:'store_count',shortBrief:'short_brief',goodsDescription:'goods_description',createTime:'create_time',updateTime:'update_time',imgSrc:'img_src',goodsCategoryId:'goods_category_id'
        }
    });
    var  magicalcoderlist = layui.magicalcoderlist(config);
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: 'ID', minWidth:100, align:"center",sort:true},

            {field: 'goodsName', title: '商品名称', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.goodsName) +'" class="magicalcoder-table-text layui-input security_list_table_form_goodsName" name="goodsName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="GOODS_NAME"/>'
                }
                , sort:true
            },

                {field: 'publishStatus', title: '是否发布',minWidth:150, align:'center', templet:function(d){
                    var checked = (d.publishStatus+''=='true' || d.publishStatus+'' == '1') ? 'checked' : '';
                    return '<input type="checkbox" class="magicalcoder-table-layswitch security_list_table_form_publishStatus" data-identify="'+d.id+'" name="publishStatus"  lay-filter="publishStatusFilter" lay-skin="switch" lay-text="是|否"  '+checked+' lay-verify="magicalCoderVerify" magicalcoder-verify="" />'
                    }
                    ,sort:true
                },
            {field: 'goodsStatus', title: '商品状态',minWidth:350, align:'center', templet:'#goodsStatusTemplate',sort:true},

            {field: 'price', title: '价格', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.price) +'" class="magicalcoder-table-text layui-input security_list_table_form_price" name="price" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="价格"/>'
                }
                , sort:true
            },

            {field: 'storeCount', title: '库存', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.storeCount) +'" class="magicalcoder-table-text layui-input security_list_table_form_storeCount" name="storeCount" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required|mc_number|mc_digits" magicalcoder-verify="|minValues=1|maxValues=12|minLength=1|maxLength=2|definedOne=\d+"  placeholder="库存"/>'
                }
                , sort:true
            },

            {field: 'shortBrief', title: '简介', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.shortBrief) +'" class="magicalcoder-table-text layui-input security_list_table_form_shortBrief" name="shortBrief" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="简介"/>'
                }
                , sort:true
            },

            {field: 'goodsDescription', title: '描述', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.goodsDescription) +'" class="magicalcoder-table-text layui-input security_list_table_form_goodsDescription" name="goodsDescription" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="描述"/>'
                }
                , sort:true
            },
            {field: 'createTime', title: '创建时间', align:'center', minWidth:250, templet :"#createTimeTemplate",sort:true},
            {field: 'updateTime', title: '更新时间', align:'center', minWidth:250, templet :"#updateTimeTemplate",sort:true},

            {field: 'imgSrc', title: '图片地址', minWidth:200,templet:function (d) {
                    return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.imgSrc) +'" class="magicalcoder-table-text layui-input security_list_table_form_imgSrc" name="imgSrc" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="图片地址"/>'
                }
                , sort:true
            },
                {field: 'goodsCategoryId', title: '所属商品', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.goodsCategoryId || d.goodsCategoryId==null) ? '' : d.goodsCategoryId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_goodsCategoryId" lay-ignore="true"  name="goodsCategoryId" data-identify="'+d.id+'" data-value="" data-url="admin/goods_category_rest/search" data-id="id" data-text-fields="name,keyword" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="所属商品">'+option+'</select>'
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
