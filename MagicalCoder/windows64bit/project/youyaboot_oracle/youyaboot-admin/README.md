启动
java -jar youyaboot-admin.jar --spring.profiles.active=deploy

编码约定：
    url: /admin/{表名称}/operate
        表名称就是模块名称 所有后端接口必须遵从此约定；这样系统会自动判断角色权限；
    
    关于operate起名规范
        insert
        update
        save
        delete
        get
        batch_xxx
        audit_pass
        reject
        等等
        
    method:重要
        POST：仅用于更改数据 系统默认会自动对POST请求记录操作日志
        GET:  仅用于查询数据 系统不会记录日志
        其他：不推荐使用     系统无日志记录
        
    java:AdminXxxController AdminXxxService AdminXxxServiceImpl AdminXxxMapper AdminXxx 
通用工具类：
    AdminUtil 获取登录人员详细
    
统一路径映射：
    所有关于后端界面的地方 参考AdminRmpController
        /admin/page/{tableName}/{list|edit}
        
        
前端：
如何顺利引入各种组件
采取样式名
日期：magicalcoder-laydate
    <input type="text" class="magicalcoder-laydate layui-input" id="updateTime" name="updateTime" lay-verify="datetime" placeholder="请选择更新日期"  autocomplete="off" value=""/>
    id 或 name：必须有存在1个 否则无法绑定 laydate控件用一个选择器绑定多个就会出问题
富文本：magicalcoder-layedit
	<textarea id="goodsDescription" name="goodsDescription" placeholder="请输入商品描述" lay-verify="goodsDescriptionVerifyEdit" class="magicalcoder-layedit layui-textarea layui-hide" ></textarea>
    id:必须存在 参考layedit文档
    lay-verify:校验器自动同步 必须 (id+"VerifyEdit")
文件上传：magicalcoder-layupload
    <div class="layui-input-block">
        <a class="layui-btn a_imgSrc">
            <i class="layui-icon">&#xe67c;</i>上传文件或图片
        </a>
        <input class="layui-input" name="imgSrc" type="text" />
        <div class="layui-upload-list">
            <img class="layui-textarea layui-upload-img img_imgSrc" />
        </div>
    </div>
    
    input : id 或 name 必须有一个 并且请保证一致 
    注 意a_{name} img_{name} 取名规范
    
    
switch切换：magicalcoder-layswitch
先被form包裹
<form>
    <input type="checkbox" class="magicalcoder-layswitch" data-identify="111" name="publishStatus" lay-filter="publishStatus" lay-skin="switch" lay-text="是|否" />
</form>
    data-identify：会智能ajax更新后端数据
    lay-filter：保证跟name一致 事件名称

外键-select2：magicalcoder-foreign-select2
    data-value:
        是否ajax预先赋值 不为空会调用后台接口
        为空不调用 
    <option value="id">text</option>:可以预设初值 一般在列表页显示 因为不能全量一个个读取后台接口 赋值，效率很低，所以一般给个数据库存的比如主键

    
普通下拉-select2：magicalcoder-single-select2

普通多选下拉-select2：magicalcoder-multiply-select2

表格内普通下拉-select2：magicalcoder-table-single-select2

表格内普通多选下拉-select2：magicalcoder-table-multiply-select2

表格内外键下拉-select2：magicalcoder-table-foreign-select2

表格内普通输入框 :magicalcoder-table-text

关于select2插件的使用
<select class="js-example-basic-single layui-input" lay-ignore="true" name="goodsCategoryId" data-value="1" data-url="admin/goods_category_rest/search" data-id="id" data-text-fields="name,keyword"></select>
data-value:默认值 比如编辑页面 可以为空
data-url:将要进行搜索的地址
data-id data-text-fields:搜索完 根据返回结果要读取的{id : item.id,text:item.name+||+item.keyword} 返回给select2 并展示下来中


表格内
	<table id="newsList" lay-filter="newsList" data-primary-fields="id"></table>
	data-primary-fields:这个表有很多增删改查 基于的主键名称
	
滑块 
magicalcoder-slider
magicalcoder-table-slider

颜色选择器
magicalcoder-color-picker
magicalcoder-table-color-picker

评分
magicalcoder-rate
magicalcoder-table-rate

代码修饰器
magicalcoder-code
magicalcoder-table-code


通用js html使用v0xx来区分版本 避免因为升级导致以前生成的代码无法正常运行
