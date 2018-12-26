/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
//表单通用配置 列表页 详情页都需要
layui.define(['jquery'],function(exports){
    var obj =
        {
            tableNameRest:"goods_rest",
            tableName:"goods",
            moduleName:"goods",//sys_module的moduleName
            formVerifyEditData:{//详情页提交时表单校验 使用方法参考layui官网 表单验证规则

            },
            form:{

                  price:{
                        renderConfig:{"half":false,"readonly":false,"length":5,"inputType":"rate","theme":"0xFFB800","text":false}
                    }
                ,storeCount:{
                        renderConfig:{"input":false,"min":0,"max":1000,"showstep":false,"range":false,"inputType":"select","step":1,"theme":"#009688","type":"default","tips":true,"height":200}
                    }
                ,shortBrief:{
                        renderConfig:{"encode":true,"about":false,"inputType":"code"}
                    }
                ,createTime:{
                        renderConfig:{"calendar":false,"format":"yyyy-MM-dd HH:mm:ss","show":false,"range":false,"trigger":"click","type":"date","isInitValue":true,"showBottom":true,"inputType":"date","theme":"#4d3399ff","position":"absolute","lang":"cn","zIndex":66666666}
                    }
                ,updateTime:{
                        renderConfig:{"calendar":false,"format":"yyyy-MM-dd HH:mm:ss","show":false,"range":false,"trigger":"click","type":"datetime","isInitValue":true,"showBottom":true,"inputType":"select","theme":"default","position":"absolute","lang":"cn","zIndex":66666666}
                    }
                ,imgSrc:{
                        renderConfig:{"auto":true,"acceptMime":"images","multiple":false,"inputType":"imgfile","drag":true,"exts":"jpg|png|gif|bmp|jpeg","accept":"images"}
                    }
            }
        }
    exports('goods_config',obj);
})
