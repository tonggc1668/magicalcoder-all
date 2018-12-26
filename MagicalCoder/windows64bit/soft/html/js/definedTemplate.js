//浏览器调试模式打开此类 否则各种找不到
function getJAVADefinedTemplateHtml() {
    if(DefinedTemplateHtml+'' == 'undefined'){//现在应该不会出现了 java改成static属性了
        layer.msg("哎呀，十分抱歉，您触发了javafx的bug,浏览器端调用Java的对象变成了undefined，请做好数据备份，关掉此页面重新点击自定义模板按钮即可解决")
        return null;
    }
    return DefinedTemplateHtml;
}
function DefinedTemplate(layer) {
    var location = window.location.href;
    var arr = location.split("?");
    var params = arr[1].split("=")
    this.databaseRealName =params[1];
    this.data = []
    this.layer = layer;
}

DefinedTemplate.prototype.init =function() {
    $("#databaseName").html(this.databaseRealName)
    //初始化已保存数据
    var dataList = getJAVADefinedTemplateHtml().userData(this.databaseRealName);
    dataList = JSON.parse(dataList)
    if(dataList!=null && dataList.length>0){
        for(var i=0;i<dataList.length;i++){
            var config = dataList[i];
            $("table > tbody").append(this.buildTr(config));
        }
    }
    //vmVersion
    var vmVersion = getJAVADefinedTemplateHtml().vmVersion(this.databaseRealName);
    $("#vmVersion").val(vmVersion)
    var vmVersionList = JSON.parse(getJAVADefinedTemplateHtml().userConfigVmVersionList());
    for(var j in vmVersionList){
        $("#configDemo").append('<button class="layui-btn defined-data"  name="'+vmVersionList[j]+'">'+vmVersionList[j]+'</button>')
    }
    //初始化
    this.events()
    if(vmVersion==''){
        layer.msg("您可以选择配置示例中的 youyaboot_xxx 并保存。")
    }
}

DefinedTemplate.prototype.events = function (){
    this.saveAs();
    this.deleteAs();
    this.replace()
    this.add()
    this.view()
    this.del()
    this.save()
    this.clearClick()
    this.exampleClick()
    this.defiendDataClick()
}
DefinedTemplate.prototype.saveAs = function () {
    var _t = this
    $("#saveAs").click(function () {
        var vmVersion = $("#vmVersion").val()
        if(vmVersion!=''){
            var canSave = true;
            $(".example").each(function (idx,item) {
                var name = $(this).attr("name")
                if(vmVersion==name){
                    canSave = false;
                }
            })
            if(!canSave){
                layer.msg("模板版本-请使用其他名称")
                return;
            }
            var data = _t.getData();

            getJAVADefinedTemplateHtml().saveAs(vmVersion,JSON.stringify(data))
            if($("button[name='"+vmVersion+"']").length<=0){//更新界面配置示例按钮
                var html = '<button class="layui-btn defined-data"  name="'+vmVersion+'">'+vmVersion+'</button>'
                $("#configDemo").append(html)
                _t.defiendDataClick();
            }
            layer.msg("另存为成功")
        }else {
            layer.msg("请输入模板版本")
        }
    })
}
DefinedTemplate.prototype.deleteAs = function () {
    var _t = this
    $("#deleteAs").click(function () {
        var vmVersion = $("#vmVersion").val()
        if(vmVersion!=''){
            getJAVADefinedTemplateHtml().deleteAs(vmVersion)
            if(!$("button[name='"+vmVersion+"']").hasClass("example")){
                $("button[name='"+vmVersion+"']").remove();
                return;
            }
            $("#vmVersion").val("");
        }else {
            layer.msg("请输入模板版本")
        }
    })
}
DefinedTemplate.prototype.clearClick = function () {
    var _t = this
    $("button[name='clear']").click(function () {
            $("table > tbody").html('')
        }
    )
}
DefinedTemplate.prototype.dataList = function () {
    var dataList = [];
    dataList.push({id:'',templatePath:'/doc/api.vm',
        outputFile:'doc\\(l)\\api.md'})
    dataList.push({id:'',templatePath:'/controller/AdminRestController.vm',
        outputFile:'youyaboot-admin\\src\\main\\java\\com\\magicalcoder\\youyaboot\\admin\\api\\(l)\\Admin(u)RestController.java'})
    dataList.push({id:'',templatePath:'/service/Service.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\service\\(u)Service.java'})
    dataList.push({id:'',templatePath:'/service/ServiceImpl.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\service\\impl\\(u)ServiceImpl.java'})
    dataList.push({id:'',templatePath:'/mapper/Mapper.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\mapper\\(u)Mapper.java'})
    dataList.push({id:'',templatePath:'/model/Model.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\model\\(u).java'})
    dataList.push({id:'',templatePath:'/sql/SqlMapper.vm',
        outputFile:'youyaboot-service\\src\\main\\resources\\sqlmap\\(u)Mapper.xml'})

    dataList.push({id:'',templatePath:'/dto/Dto.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\dto\\(u)Dto.java'})
    dataList.push({id:'',templatePath:'/constant/Constant.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\constant\\(u)Constant.java'})
    dataList.push({id:'',templatePath:'/util/Util.vm',
        outputFile:'youyaboot-service\\src\\main\\java\\com\\magicalcoder\\youyaboot\\service\\(l)\\util\\(u)Util.java'})

    dataList.push({id:'',templatePath:'/jsp/entity-list.vm',
        outputFile:'youyaboot-admin\\src\\main\\resources\\templates\\(l)\\(l)-list.html'})
    dataList.push({id:'',templatePath:'/jsp/entity-edit.vm',
        outputFile:'youyaboot-admin\\src\\main\\resources\\templates\\(l)\\(l)-edit.html'})
    dataList.push({id:'',templatePath:'/js/js-entity-config.vm',
        outputFile:'youyaboot-admin\\src\\main\\resources\\static\\assets\\admin\\(l)\\(l)-config.js'})
    dataList.push({id:'',templatePath:'/js/js-entity-list.vm',
        outputFile:'youyaboot-admin\\src\\main\\resources\\static\\assets\\admin\\(l)\\(l)-list.js'})
    dataList.push({id:'',templatePath:'/js/js-entity-edit.vm',
        outputFile:'youyaboot-admin\\src\\main\\resources\\static\\assets\\admin\\(l)\\(l)-edit.js'})
    return dataList;
}

//demo
DefinedTemplate.prototype.exampleClick = function () {
    var _t = this
    $(".example").click(function () {
        var name = $(this).attr("name");
        var dataList = _t.dataList();
        $("table > tbody").html('')
        for(var i=0;i<dataList.length;i++){
            var config = dataList[i];
            $("table > tbody").append(_t.buildTr(config));
        }
        $("#vmVersion").val(name)
        _t.del();
        _t.view();
    })
}
DefinedTemplate.prototype.defiendDataClick = function () {
    var _t = this
    $(".defined-data").unbind("click").bind('click',(function () {
        var name = $(this).attr("name");
        var value = getJAVADefinedTemplateHtml().userTemplateConfigDto(name);
        var dataList = JSON.parse(value);
        $("table > tbody").html('')
        for(var i=0;i<dataList.length;i++){
            var config = dataList[i];
            $("table > tbody").append(_t.buildTr(config));
        }
        $("#vmVersion").val(name)
        _t.del();
        _t.view();
    }))
}

DefinedTemplate.prototype.replace = function () {
    var _t = this
    $("button[name='replace']").click(function () {
        $("table > tbody > tr").each(function () {
            var source = $.trim($("#source").val())
            var target = $.trim($("#target").val())
            var outputFile = $.trim($(this).find("input[name='outputFile']").val())
            if(source!=''){
                var reg = new RegExp( source , "g" )
                var newstr = outputFile.replace( reg , target );
                $(this).find("input[name='outputFile']").val(newstr)
            }
        })
    })
}

DefinedTemplate.prototype.add = function () {
    var _t = this
    $("button[name='add']").click(function () {
        $("table > tbody").prepend(_t.buildTr({id:'',templatePath:'',outputFile:''}));
        _t.del();
        _t.view();
    })
}
//预览模板生成的代码
DefinedTemplate.prototype.view = function () {
    var _t = this
    $("button[name='view']").unbind("click").bind("click",function () {
        var tr = $(this).parent().parent();
        // var id = tr.attr("_id");
        var vmVersion = $("#vmVersion").val()
        var templatePath = $.trim(tr.find("input[name='templatePath']").val())
        var outputFile = $.trim(tr.find("input[name='outputFile']").val())
        if(vmVersion == ''){
            layer.msg("请填写模板版本...")
            return;
        }
        if(templatePath == ''){
            layer.msg("请填写模板相对路径...")
            return;
        }
        if(outputFile == ''){
            layer.msg("请填写代码保存相对路径...")
            return;
        }
        layer.msg("正在打开,请稍后...")
        getJAVADefinedTemplateHtml().openDefinedTemplateTableView(_t.databaseRealName,vmVersion,templatePath,outputFile)

    })
}
DefinedTemplate.prototype.del = function () {
    var _t = this
    $("table > tbody").find("button[name='del']").unbind("click").bind("click",function () {
        //2删除结构
        $(this).parents('tr').remove();
    })
}
//最终去结构遍历取数据 简单很多
DefinedTemplate.prototype.save = function () {
    var _t = this
    $("button[name='save']").click(function () {
        var vmVersion = $("#vmVersion").val()
        if(vmVersion==''){
            _t.layer.msg("请填写版本号,良好的版本号可以帮助你更好的在多个版本的模板之间切换,版本号就是vms目录下的子文件夹目录名称")
            return
        }
        getJAVADefinedTemplateHtml().saveVmVersion(_t.databaseRealName,$("#vmVersion").val())
        _t.data = _t.getData();
        //调用java方法保存
        getJAVADefinedTemplateHtml().save(JSON.stringify(_t.data),_t.databaseRealName)
    })
}
//获取表格的配置
DefinedTemplate.prototype.getData = function (){
    var data = []
    $("table > tbody > tr").each(function () {
        var id = $(this).attr("_id");
        var templatePath = $.trim($(this).find("input[name='templatePath']").val())
        var outputFile = $.trim($(this).find("input[name='outputFile']").val())
        if(!id || 'undefined'==id+''){
            id = "";
        }
        if(templatePath!='' && outputFile!=''){
            data.push({id:id,templatePath:templatePath,outputFile:outputFile})
        }
    })
    return data;
}

DefinedTemplate.prototype.buildTr = function (config) {
    var arr = []
    if(!config.id){
        config.id = ""
    }
    arr.push("<tr _id='"+config.id+"'>")
    arr.push('<td><input name="templatePath" class="layui-input" value="'+config.templatePath+'"/></td>')
    arr.push('<td><input name="outputFile" class="layui-input"  value="'+config.outputFile+'"/></td>')
    arr.push('<td><button class="layui-btn layui-btn-danger" name="del">删除</button><button class="layui-btn" name="view">预览生成代码</button></td>')
    arr.push('</tr>')
    return arr.join("")
}
