/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
//表单通用配置 列表页 详情页都需要
layui.define(['jquery'],function(exports){
    var obj =
        {
            tableNameRest:"goods_img_rest",
            tableName:"goods_img",
            moduleName:"goods_img",//sys_module的moduleName
            formVerifyEditData:{//详情页提交时表单校验 使用方法参考layui官网 表单验证规则

            },
            form:{

                  imgSrc:{
                        renderConfig:{"auto":true,"acceptMime":"images","multiple":false,"inputType":"imgfile","drag":true,"exts":"jpg|png|gif|bmp|jpeg","accept":"images"}
                    }
            }
        }
    exports('goodsimg_config',obj);
})
