自定义模板
    能够自定义模板实在是一个激动人心的功能,你可以使用工具全新写一版符合自己工程的模板,所有代码灵活定制;
    当然这也是一份机具挑战的工作,想要写好模板,您需要对工具进行了解后才能胜任:好在有这篇教程,助您轻松定制自己的模板
    
    模板技术:1 模板引擎:采取Velocity,一切velocity语法都可以使用
             2 数据结构:了解数据结构才能熟知环境变量的字段,才能写出完美模板
             3 环境变量:了解当前已经提供给模板的哪些环境变量,熟练掌握这些环境变量
             4 系统工具类:使用这些类可以灵活处理环境变量
             5 扩展性:如何让模板执行我们自己写的java方法来处理模板变量,系统提供MagicalCoderVmUtil.class专门给您提供新的扩展模板工具类
             6 参考性:soft\vms\newest中的模板,这个模板生成出来的代码是基于youyaboot web工程的;里面很多写法一定有很大的参考意义 请保证你的模板文件以utf-8编码保存 使用另存为utf-8即可 否则会编码问题出现乱码
             7 调试:你的扩展工具类,软件运行的每一步都能调试,有了这个方法,还愁写不好模板吗
             
             
     我们逐条来学习
     
     1 模板引擎
        可以搜索一些关于velocity语法的知识,网上有很多入门教程,十分简单
     2 环境变量:全是前期您使用软件配置的各个参数
        我们知道,velocity中有许多变量可以使用$来获取,本软件提供了ctxMap  
     3 数据结构
          dbProject:工程信息
              private Long id;//主键
              private String databaseName;//数据库名称
              private String databaseRealName;//数据库真实名称
              private String companyName;//公司名称
              private String projectName;//项目名称
              private String commonUtilName;//一般用于通用jar包的目录引用 可以忽略
              private String vmVersion;//模板版本号
         dbTable:表信息
             private Long id;//主键
             private String tableName;//表英文名称
             private String tableRealName;//表数据库真实名称
             private String tableComment;//表中文名称
             private Long projectId;//项目ID
             private Integer tableType;//表类型
             private Long userId;//用户ID
             private Integer detailFormType;//详情页表单类型
         isOtOTable:是否一对一表
         isOtmTable:是否一对多关联表

         nodeList:一个表中有多个字段,这就是字段的属性,不同的是有些字段拥有外键,自然对应外键表,那外键的内容也存在
             dbField:字段解析
                 private Long id;//主键
                 private String dbName;//字段名称
                 private String dbRealName;//字段真实名称
                 private String dbType;//字段类型   参考 java.sql.Types 的各种枚举类型 其中 666666661代表postgresql的uuid类型
                 private String dbLen;//字段长度
                 private String dbDefaultValue;//默认值
                 private Boolean dbPriorityKey;//是否主键
                 private Boolean dbUnique;//是否唯一
                 private Boolean dbAutoIncrease;//是否自增
                 private Boolean dbNotNull;//是否不为空
                 private Boolean dbIndex;//是否索引
                 private Boolean dbZeroFill;//是否填充0
                 private String dbComment;//字段评论
                 private Long dbTableId;//数据库表ID
                 private Long dbConstraintTableId;//外键数据库表ID
                 private Long dbConstraintFieldId;//外键字段ID
                 private Boolean query;//是否查询
                 private Boolean list;//是否列表
                 private Integer relateType;//关联类型
                 private Long userId;//用户ID
                 private Integer orderNum;//排序字段
                 private Boolean relateToParentDetailEdit;//是否加入父表详情页编辑
             dbProject:当前字段所属工程,参考DBProject结构
             dbTable:当前字段所属表,参考DbTable结构
             formItem:业务场景工具提供的功能,表单的属性
                 private Long id;//主键
                 private String inputType;//表单元素类型
                 private String itemId;//元素ID
                 private String itemName;//元素NAME
                 private String title;//元素TITLE
                 private String selectValue;//元素可选值
                 private String placeholder;//元素默认提示
                 private boolean disabled;//是否禁用
                 private String inputClass;//元素可选class
                 private Long tableId;//表ID
                 private Long fieldId;//数据库字段表ID
                 private Long userId;//用户ID
                 private Integer orderNum;//排序字段
             formValidate:业务场景工具提供的功能,表单验证
                 private Long id;//表单验证主键
                 private Boolean required;//必输字段
                 private Boolean mathNumber;//合法的数字
                 private Boolean digits;//必须输入整数
                 private String accept;//合法后缀名入上传文件
                 private String minValues;//最小值
                 private String maxValues;//最大值
                 private Integer minLength;//最小长度
                 private Integer maxLength;//最大长度
                 private Boolean email;//是否邮箱
                 private Boolean url;//是否合法网址
                 private Long tableId;//所属表ID
                 private Long fieldId;//所属字段ID
                 private Long itemId;//表单元素表form_item(id)
                 private Long userId;//所属用户
                 private Integer orderNum;//字段排序
                 private Boolean variable;//字母数字下划线组成
                 private Boolean chineseCharacter;//汉字
                 private String definedOne;//自定义正则1
                 private String definedTwo;//自定义正则2
                 private String definedThree;//自定义正则3
             formQueryList:业务场景工具提供的功能,查询条件 之所以是数组 考虑到将来兼容问题,一个字段可能出现多个组合查询,目前就1个
                 private Long id;//主键
                 private Long itemId;//表单元素表ID
                 private String firstCompare;//第一个比较符号
                 private String secondCompare;//第二个比较符号
                 private Long foreignTableId;//外键表ID
                 private Long foreignItemId;//外键表单元素表ID
                 private Long tableId;//表ID
                 private Long fieldId;//数据库字段表ID
                 private Long userId;//用户ID
                 private Integer orderNum;//排序字段
                 private Integer foreignInputSelectShow;//是否作为外键下拉查询展示
             viewTable:业务场景工具提供的功能,列表
                 private Long id;//主键
                 private Long itemId;//表单元素表ID
                 private boolean sort;//是否排序
                 private Integer orderNum;//排序顺序
                 private Long tableId;//表ID
                 private Long fieldId;//数据库字段表ID
                 private Long userId;//用户ID
                 private Integer defaultOrderByPriority;//默认排序优先级
                 private String defaultOrderByAscDesc;//升序倒序  
    4 系统工具类 见10字典
            关于工具类,建议您新建一个工程,依赖youyajfx-1.0-SNAPSHOT.jar,然后就可以查看源码得知各个工具类的方法了;如果不了解系统工具类,参考newest中的vm写法,便可知道,
            后续会提供一个api,当前您也开源使用MagicalCoderVmUtil来自定义工具类,支持热部署
            MagicConstant:定义了一系列常量
            TableFieldUtil:操作表 变量 字段 类 其中debug方法十分有用,可以查看变量内容
            CreateVmUtil:主要用来处理nodeList等字段,一些外键工具类
            RemoteApiUtil:getNodeList方法是远程获取某个表的字段信息,对于一些外键表获取他的字段是分有用,
            StringUtil:字符串处理类
            MagicalCoderVmUtil:扩展工具类
            JS:可以执行MagicalCoderVmUtil.js的中方法代码
                
    5 扩展性
            MagicalCoderVmUtil:
                1 留给用户自行扩展的工具类,请在soft\vms\下查看,你可以把你自己的类覆盖即可在模板中使用此类的方法,定义时必须是 putlic static方法,使用$MagicalCoderVmUtil.方法(参数即可调用)
                2 支持热部署,把你写好的工具类直接覆盖到soft\vms\下即可
                3 调试工具类:查看调试目录
            MagicalCoderVmUtil.js:
                 定义一系列js方法 可直接用于模板编写工具类中 请保证此文件编码为utf-8
                 模板中调用方法：$JS.execute(functionName,arg0,arg1,...argn)
                 $JS.execute("test_log",1,2)
                 $JS.execute("testMap",$dbProject)
                 您唯一可以使用的调试方法为
                 console.log(msg);
    6 参考性
            soft\vms\:
                1 这个目录留给用户新建版本文件夹,软件中自定义模板-模板版本=你的新建版本文件夹名称,然后就可以使用此版本模板了
                2 demo是一些快速入门的模板 
                3 请新建您自己的目录,否则newest 和 demo 随时有可能被覆盖掉
    7 调试:也可观看官方教程
        给需要开发模板的您使用:以下步骤已亲测可行
            1 当您有了vms.MagicalCoderVmUtil.java如何在生成代码时调试方法呢:
            2 如何在.vm模板文件中查看引用变量的值:
                       参考示例:$TableFieldUtil.debug($nodeList)
           #
           1 双击 soft/windows_debug.bat,便于查看错误日志输出
           目前已经配置的debug方式
           #java -client -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 com.magicalcoder.youyajfx.MainApp
           2 新建一个工程,请想办法把soft\lib和soft\youyajfx-1.0-SNAPSHOT-proguard.jar依赖进来,这样为后续断点调试准备,很抱歉,暂时不开放源码,如果您愿意反编译也可得到源码
           3 idea配置 edit configuration - 左侧添加一个 remote-- 请选择 socket attach  配置 host=localhost port=5005 
            search sources ...选择你的工程包 这个很重要,否则代码调试找不到 然后apply 
            这一步 点击启动remote,控制台出现Connected to the target VM, address: 'localhost:5005', transport: 'socket'便成功啦
            
            到jar中找到一个类, 断点,操作客户端软件,触发部分类,就能进入断点啦
            就可以调试客户端了
            
            这个功能其实是java提供的调试,适用于任何java进程调试
            
          4 我们来讲讲如何调试MagicalCoderVmUtil
            第一步:请在工程新建vms\MagicalCoderVmUtil.java
            第二步:新建一些public static方法
                    public static String test3(){
                        return "hello3";
                    }
            第三步:编译成MagicalCoderVmUtil.class 然后拷贝到 soft\vms目录下覆盖已有的MagicalCoderVmUtil.class
                    关于这一步 如果你能做到 让编译器字段吧output路径配置到soft\vms下 更好啦,否则只能手动拷贝了
            第四步:配置自定义模板模块,记得模板中要出现引用了 $MagicalCoderVmUtil.方法(参数);
                    这些参数也可以是jar包中的任意对象,比如DbProject dbProject,不一定非得是字符串,
            第五步:在MagicalCoderVmUtil.java的方法那行设置一个断点
            第六步:点击生成代码按钮,前提是您已经完成了代码保存路径,项目设置,自定义模板的配置
            第七步:编译器断点就会生效,是不是很赞,我们就可以开发一些强大的工具类来编写各种场景中复杂的代码片段了
                       
9 各种字典方法 static方法都可以直接在模板调用
public class StringUtil {
    //判断字符是否为空
	public static boolean isBlank(String str){
		if(str==null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	//判断字符是否不为空
	public static boolean isNotBlank(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}
		return false;
	}
	//删除最后一个字符 一般拼接时候 删除最后一个逗号比较有用
    public static String deleteLastChar(String str){
        if(isBlank(str)) return str;
        return str.substring(0,str.length()-1);
    }
	//删除最后N个字符
    public static String deleteLastChar(String str,int len){
        if(isBlank(str)) return str;
        return str.substring(0,str.length()-len);
    }
    //从开始删除N个字符
    public static String deleteBeforeChar(String str,int len){
        if(isBlank(str)) return str;
        return str.substring(len);
    }
}

RemoteApiUtil：
	//根据表id获取相关字典 一般用于外键场景
    public static List<VmFieldNode> getNodeList(Long tableId){
        List<BasicNameValuePair> list = new ArrayList<>();
        String url = String.format(MagicalCache.getVmServer()+ MainConstant.nodeListApi,tableId);
        HttpClientDto clientDto = HttpClientUtil.doPost(url,list);
        if(clientDto.isSuccess()){
            RemoteDto remoteDto = clientDto.getRemoteDto();
            Object info = remoteDto.getInfo();
            String nodeListStr = JSON.toJSONString(MapUtil.buildMap("nodeList",info));
            VmFieldNodeResult result = JSON.parseObject(nodeListStr,VmFieldNodeResult.class);
            return result.getNodeList();
        }else {
            return null;
        }
    }
 
 
public class CreateVmUtil {
    //是否是一对多关联表
    public static boolean isOneToManyRelateTable(List<VmFieldNode> nodeList){
        if(ListUtils.isNotBlank(nodeList)){
            boolean one = false;
            boolean many = false;
            for(VmFieldNode node:nodeList){
                if(node.getDbField().getRelateType() == MagicConstant.DB_FIELD_RELATE_TYPE_ONE_TO_MANY_ONE()){
                    one = true;
                }
                if(node.getDbField().getRelateType() == MagicConstant.DB_FIELD_RELATE_TYPE_ONE_TO_MANY_MANY()){
                    many = true;
                }
            }
            return one && many;
        }
        return false;
    }
    //是否是一对多关联表
    public static boolean isOneToOneRelateTable(List<VmFieldNode> nodeList){
        if(ListUtils.isNotBlank(nodeList)){
            boolean one = false;
            for(VmFieldNode node:nodeList){
                if(node.getDbField().getRelateType() == MagicConstant.DB_FIELD_RELATE_TYPE_ONE_TO_ONE()){
                    one = true;
                    break;
                }
            }
            return one;
        }
        return false;
    }
    //根据表单区域排序
    public static List<VmFieldNode> sortFormItem(List<VmFieldNode> nodeList){
        List<VmFieldNode> newNodeList = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){
            newNodeList.add(node);
        }
        Collections.sort(newNodeList, new Comparator<VmFieldNode>() {
            @Override
            public int compare(VmFieldNode o1, VmFieldNode o2) {
                if(o1.getFormItem()==null){
                    return -1;
                }
                if(o2.getFormItem()==null){
                    return 1;
                }
                return o1.getFormItem().getOrderNum().compareTo(o2.getFormItem().getOrderNum());
            }
        });
        return newNodeList;
    }
    //根据列表区域排序
    public static List<VmFieldNode> sortViewTable(List<VmFieldNode> nodeList){
        if(nodeList==null){
            return nodeList;
        }
        List<VmFieldNode> newNodeList = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){
            newNodeList.add(node);
        }
        Collections.sort(newNodeList, new Comparator<VmFieldNode>() {
            @Override
            public int compare(VmFieldNode o1, VmFieldNode o2) {
                if(o1.getViewTable()==null){
                    return -1;
                }
                if(o2.getViewTable()==null){
                    return 1;
                }
                return o1.getViewTable().getOrderNum().compareTo(o2.getViewTable().getOrderNum());
            }
        });
        return newNodeList;
    }
    //根据查询区域排序
    public static List<VmFieldNode> sortFormQuery(List<VmFieldNode> nodeList){
        List<VmFieldNode> newNodeList = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){
            newNodeList.add(node);
        }
        Collections.sort(newNodeList, new Comparator<VmFieldNode>() {
            @Override
            public int compare(VmFieldNode o1, VmFieldNode o2) {
                if(ListUtils.isBlank(o1.getFormQueryList())){
                    return -1;
                }
                if(ListUtils.isBlank(o2.getFormQueryList())){
                    return 1;
                }
                return o1.getFormQueryList().get(0).getOrderNum().compareTo(o2.getFormQueryList().get(0).getOrderNum());
            }
        });
        return newNodeList;
    }
    //过滤掉自增的字段
    public static List<VmFieldNode> noAutoIncreseNodeList(List<VmFieldNode> nodeList){
        List<VmFieldNode> list = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){
            if(!node.getDbField().getDbAutoIncrease()){
                list.add(node);
            }
        }
        return list;
    }
    //字段的java类型是否是日期
    public static boolean isTimestemp(String javaType){
        return "Date,Time,Timestamp".contains(javaType);
    }
    //url中是否包含字段
    public static boolean urlContainsField(String url,String javaField){
        String[] arr = url.split("/");
        for(String a:arr){
            if(a.contains("{")){
                a = a.replace("{","").replace("}","").replaceAll("\\s+","");
                if(a.equals(javaField)){
                    return true;
                }
            }
        }
        return false;
    }
    //url转化成数组 
    public static List<String> urlParamsToArr(String url){
        List<String> list = new ArrayList<String>();
        String[] arr = url.split("/");
        for(String a:arr){
            if(a.contains("{")){
                a = a.replace("{","").replace("}","").replaceAll("\\s+","");
                list.add(a);
            }
        }
        return list;
    }
    //url中包含的字段
    public static VmFieldNode urlParamIsJavaField(String param,List<VmFieldNode> nodeList){
        if(StringUtils.isNotBlank(param)){
            if(ListUtils.isNotBlank(nodeList)){
                for(VmFieldNode node:nodeList){
                    if(node.getDbField().getJavaField().equals(param)){
                        return node;
                    }
                }
            }
        }
        return null;
    }
    //获取第一个字段
    public static VmFieldNode getFirst(List<VmFieldNode> list){
        if(ListUtils.isNotBlank(list)){
            return list.get(0);
        }
        return null;
    }
}

public class TableFieldUtil {
    /**把java字段自动转成sql字段
     * @param javaField userName
     * @return  user_name
     */
    public static String javaFieldToSql(String javaField){
        StringBuffer sb = new StringBuffer();
        char[] c = javaField.toCharArray();
        for(int i=0;i<c.length;i++){
            int ci = (int)c[i];
            if(ci >= 'A' && ci <= 'Z'){
                sb.append("_").append((char)(ci+32));
            }else {
                sb.append((char)ci);
            }
        }
        return String.valueOf(sb);
    }
    /**把sql字段自动转成java字段
     * @param sqlField user_name
     * @return  userName
     */
    public static String sqlFieldToJavaField(String sqlField){
        char[] c = sqlField.toCharArray();
        for(int i=0;i<c.length;i++){
            if((int)'_'==(int)c[i] && i<c.length-1){
                i++;
                if(!(c[i]>='0' && c[i]<='9')){//针对非数字
                    c[i] = (char) (c[i]-32);
                }
            }
        }
        return String.valueOf(c).replace("_", "");
    }
    /**把sql表自动转成java类
     * @param tableName user_name
     * @return  userName
     */
    public static String sqlTableToJava(String tableName){
        char[] c = tableName.toCharArray();
        for(int i=0;i<c.length;i++){
            if((int)'_'==(int)c[i] && i<c.length-1){
                i++;
                if(!(c[i]>='0' && c[i]<='9')){//针对非数字
                    c[i] = (char) (c[i]-32);
                }
            }
        }
        return String.valueOf(c).replace("_", "");
    }
    /**
     * 把sql表名转成java类名
     * @param tableName
     * @return
     */
    public static String sqlTableToJavaClassName(String tableName){
        String className = sqlTableToJava(tableName);
        String firstChar = className.substring(0, 1);
        return firstChar.toUpperCase()+className.substring(1);
    }
    /**
     * 首字母转换成小写
     */
    public static String firstCharToLower(String str){
        String firstChar = str.substring(0, 1);
        return firstChar.toLowerCase()+str.substring(1);
    }
    /**
     * 首字母转换成大写
     * @param str
     * @return
     */
    public static String firstCharToUpper(String str){
        String firstChar = str.substring(0, 1);
        return firstChar.toUpperCase()+str.substring(1);
    }
    //获取字段的set方法
    public static String getSetMethod(String javaField){
        if(javaField.length()==1){
            return firstCharToLower(javaField);
        }
        char b = javaField.charAt(1);
        if(b > 'A' && b <'Z'){//比较特殊的情况第二个字母大写 第一个小写 直接返回
            return javaField;
        }
        return firstCharToUpper(javaField);
    }
    //获取字段的get方法
    public static String javaGetName(String javaField){
        return "get"+getSetMethod(javaField);
    }
    //获取字段的set方法
    public static String javaSetName(String javaField){
        return "set"+getSetMethod(javaField);
    }
    //dbType是sqlType的枚举类型 可以通过此方法获取对应java类型
    public static DbFieldJavaDto getFileldJavaDto(String dbType){
        dbType = dbType.toLowerCase();
        if(JdbcTypes.isNewDbType(dbType)){//数字 新版本
            Integer sqlType = Integer.valueOf(dbType);
            String longJavaTypeName = JdbcTypes.getLongColumnClassName(sqlType);
            String shortJavaTypeName = JdbcTypes.getShortColumnClassName(longJavaTypeName);
            return new DbFieldJavaDto(shortJavaTypeName,longJavaTypeName);
        }else {//TODO 未来版本要删除
            return MagicConstant.getDbJavaTypeMap().get(dbType);
        }
    }
    //转化小写
    public static String toLowerCase(String str){
        if(StringUtils.isNotBlank(str)){
            return str.toLowerCase();
        }
        return str;
    }
    //把json字符串转化成map
    public static Map<String,Object> parseJsonToMap(String json){
        if(StringUtils.isNotBlank(json)){
            try {
                return (Map<String, Object>) JSON.parse(json);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }
    //格式化输出json 到一行
    public static String jsonToOneLine(String json){
        if(StringUtils.isNotBlank(json)){
            try {
                return JSON.toJSONString(JSON.parse(json));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return json;
    }
    //selectValue处理
    public static List<SelectValueDto> selectValueToList(String selectValue){
        List<SelectValueDto> returnList = new ArrayList<SelectValueDto>();
        if(StringUtils.isNotBlank(selectValue)){
            try {
                Map<String,Object> extraMap = (Map<String, Object>) JSON.parse(selectValue);
                List<Map<String,Object>> list = (List<Map<String,Object>>)extraMap.get("keyValueList");
                if(list!=null){
                    for(Map<String,Object> map:list){
                        String key = String.valueOf(map.get("key"));
                        String value = String.valueOf(map.get("value"));
                        SelectValueDto dto = new SelectValueDto(key,value);
                        returnList.add(dto);
                    }
                    return returnList;
                }
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
        return selectValueList(selectValue);
    }
    //list转化成map集合
    public static String toSelectValueMapStr(String selectValue){
        if(StringUtils.isBlank(selectValue)){
            return null;
        }
        List<SelectValueDto> selectValueList = selectValueToList(selectValue);
        if(ListUtils.isNotBlank(selectValueList)){
            Map<String,String> realMap = new HashMap<String, String>();
            for(SelectValueDto map:selectValueList){
                realMap.put(map.getKey(),map.getValue());
            }
            return JSON.toJSONString(realMap);
        }
        return null;
    }
    //是否是数值型java类型
    public static boolean isNumJavaType(String javaType){
        if("Integer,int,Long,long,Float,float,BigDecimal,Double,double,Byte".contains(javaType)){
            return true;
        }
        return false;
    }
    //你希望调试对象值 直接在界面输出
    public static String debug(Object obj){
        return JSON.toJSONString(obj);
    }
    //字段名称请勿使用x_单字母开头,转换成javabean会出错
    public static boolean isSuportSqlField(String sqlField){
        if(sqlField.startsWith("_")){
            return false;
        }
        if(sqlField.length()<=1){
            return false;
        }
        if(sqlField.substring(0,1).matches("\\d")){
            return false;
        }
        sqlField = sqlField.toLowerCase();
        String reg = "([a-z0-9A-Z]_)+.+";
        return !sqlField.matches(reg);
    }
    //对于不支持的名称 加个前缀
    public static String aliasName(String sqlField){
        if(sqlField!=null){
            sqlField = sqlField.toLowerCase();
        }
        if(isSuportSqlField(sqlField)){
            return sqlField;
        }
        //不支持的 把x_替换成x
        String[] arr = sqlField.split("_");
        StringBuilder newField = new StringBuilder();
        if(arr.length>0){
            for(String charcat : arr){
                if(charcat.length()>1){
                    newField.append(charcat).append("_");
                }else if(charcat.length()==1) {
                    newField.append(charcat);
                }
            }
        }
        String newFieldStr = newField.toString();
        if(newFieldStr.endsWith("_")){
            return StringUtils.deleteLastChar(newFieldStr);
        }
        return sqlField;
//        return "alias_"+sqlField;
    }
    //获取联合唯一键列表
    public static List<VmFieldNode> uniqueNodes(List<VmFieldNode> nodeList){
        if(ListUtils.isBlank(nodeList)){
            return null;
        }
        List<VmFieldNode> uniqueList = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){//优先选择主键
            if(node.getDbField().getDbPriorityKey()){
                uniqueList.add(node);
            }
        }
        if(ListUtils.isNotBlank(uniqueList)){
            return uniqueList;
        }
        for(VmFieldNode node:nodeList){//其次是唯一键
            if(node.getDbField().getDbUnique()){
                uniqueList.add(node);
            }
        }
        return uniqueList;
    }
    //默认排序
    public static String buildDefaultOrderBy(List<VmFieldNode> nodeList){
        if(ListUtils.isBlank(nodeList)){
            return "";
        }
        List<VmFieldNode> newList = new ArrayList<VmFieldNode>();
        for(VmFieldNode node:nodeList){//优先选择主键
            ViewTableDto viewTable = node.getViewTable();
            if(viewTable!=null
                    && viewTable.getDefaultOrderByPriority()>=0
                    && StringUtils.isNotBlank(viewTable.getDefaultOrderByAscDesc())){
                newList.add(node);
            }
        }
        if(ListUtils.isBlank(newList)){
            return "";
        }
        Collections.sort(newList, new Comparator<VmFieldNode>() {
            @Override
            public int compare(VmFieldNode o1, VmFieldNode o2) {
                return o1.getViewTable().getDefaultOrderByPriority()-o2.getViewTable().getDefaultOrderByPriority();
            }
        });
        StringBuffer orderBy = new StringBuffer();
        for(VmFieldNode node:newList){
            orderBy.append(node.getDbField().getDbName()).append(" ").append(node.getViewTable().getDefaultOrderByAscDesc()).append(",");
        }
        return StringUtils.deleteLastChar(orderBy.toString());
    }
    //避免重复输出同一个外键表
    public static Map<String,String> foreignTableMap(List<VmFieldNode> nodeList){
        Map<String,String> foreignMap = new HashMap<String, String>();
        for(VmFieldNode node:nodeList){
            VmFieldNode foreignNode = node.getDbField().getForeignVmDbFieldNode();
            if(foreignNode!=null){
                foreignMap.put(foreignNode.getDbTable().getTableName(),node.getDbField().getDbName());
            }
        }
        return foreignMap;
    }
    //查找自增字段
    public static VmFieldNode autoPrimaryKeyNode(List<VmFieldNode> nodeList){
        if(ListUtils.isNotBlank(nodeList)){
            for(VmFieldNode node:nodeList){
                if(node.getDbField().getDbAutoIncrease()){
                    return node;
                }
            }
        }
        return null;
    }
    //转化成大写
    public static String toUpperCase(String str){
        if(str==null){
            return str;
        }
        return str.toUpperCase();
    }
    //把-转化成_
    public static String replaceFuHaoToXiaHuaXian(String str){
        if(str==null){
            return str;
        }
        return str.replace("-","_");
    }
    //转义输出双引号
    public static String replaceDoubleYinHao(String str){
        if(StringUtils.isNotBlank(str)){
            return str.replaceAll("\r","").replaceAll("\n","").replaceAll("\"","\\\\\"");
        }
        return str;
    }
}
java.sql.Types 的各种枚举类型 对应dbField.dbType
public class Types {
    public final static int BIT             =  -7;
    public final static int TINYINT         =  -6;
    public final static int SMALLINT        =   5;
    public final static int INTEGER         =   4;
    public final static int BIGINT          =  -5;
    public final static int FLOAT           =   6;
    public final static int REAL            =   7;
    public final static int DOUBLE          =   8;
    public final static int NUMERIC         =   2;
    public final static int DECIMAL         =   3;
    public final static int CHAR            =   1;
    public final static int VARCHAR         =  12;
    public final static int LONGVARCHAR     =  -1;
    public final static int DATE            =  91;
    public final static int TIME            =  92;
    public final static int TIMESTAMP       =  93;
    public final static int BINARY          =  -2;
    public final static int VARBINARY       =  -3;
    public final static int LONGVARBINARY   =  -4;
    public final static int NULL            =   0;
    public final static int OTHER           = 1111;
    public final static int JAVA_OBJECT         = 2000;
    public final static int DISTINCT            = 2001;
    public final static int STRUCT              = 2002;
    public final static int ARRAY               = 2003;
    public final static int BLOB                = 2004;
    public final static int CLOB                = 2005;
    public final static int REF                 = 2006;
    public final static int DATALINK = 70;
    public final static int BOOLEAN = 16;
    public final static int ROWID = -8;
    public static final int NCHAR = -15;
    public static final int NVARCHAR = -9;
    public static final int LONGNVARCHAR = -16;
    public static final int NCLOB = 2011;
    public static final int SQLXML = 2009;
    public static final int REF_CURSOR = 2012;
    public static final int TIME_WITH_TIMEZONE = 2013;
    public static final int TIMESTAMP_WITH_TIMEZONE = 2014;
}
