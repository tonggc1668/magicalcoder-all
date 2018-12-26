/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - youyaboot
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`youyaboot` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `youyaboot`;

/*Table structure for table `all_type` */

DROP TABLE IF EXISTS `all_type`;

CREATE TABLE `all_type` (
  `long_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bytes_binary` binary(1) DEFAULT NULL,
  `bytes_blob` blob,
  `bytes_mediumblob` mediumblob,
  `bytes_varbinary` varbinary(200) DEFAULT NULL,
  `bytes_tinyblob` tinyblob,
  `bytes_longblob` longblob,
  `boolean_bit` bit(1) DEFAULT NULL,
  `boolean_bool` tinyint(1) DEFAULT NULL,
  `boolean_boolean` tinyint(1) DEFAULT NULL,
  `string_enum` enum('1','2') DEFAULT NULL,
  `string_char` char(1) DEFAULT NULL,
  `string_longtext` longtext,
  `string_mediumtext` mediumtext,
  `string_varchar` varchar(200) DEFAULT NULL,
  `string_set` set('a','b') DEFAULT NULL,
  `string_tinytext` tinytext,
  `string_text` text,
  `byte_tinyint` tinyint(4) DEFAULT NULL,
  `short_smallint` smallint(6) DEFAULT NULL,
  `integer_int` int(11) DEFAULT NULL,
  `integer_mediumint` mediumint(9) DEFAULT NULL,
  `float_float` float DEFAULT NULL,
  `double_double` double DEFAULT NULL,
  `double_real` double DEFAULT NULL,
  `bigdecimal_decimal` decimal(10,0) DEFAULT NULL,
  `bigdecimal_numeric` decimal(10,0) DEFAULT NULL,
  `date_date` date DEFAULT NULL,
  `date_year` year(4) DEFAULT NULL,
  `time_time` time DEFAULT NULL,
  `timestemp_datetime` datetime DEFAULT NULL,
  `timestemp_timestemp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`long_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `all_type` */

insert  into `all_type`(`long_id`,`bytes_binary`,`bytes_blob`,`bytes_mediumblob`,`bytes_varbinary`,`bytes_tinyblob`,`bytes_longblob`,`boolean_bit`,`boolean_bool`,`boolean_boolean`,`string_enum`,`string_char`,`string_longtext`,`string_mediumtext`,`string_varchar`,`string_set`,`string_tinytext`,`string_text`,`byte_tinyint`,`short_smallint`,`integer_int`,`integer_mediumint`,`float_float`,`double_double`,`double_real`,`bigdecimal_decimal`,`bigdecimal_numeric`,`date_date`,`date_year`,`time_time`,`timestemp_datetime`,`timestemp_timestemp`) values (1,'','','','','','','',1,1,'1','1','1','1','1','a','1','1',1,1,1,1,1,1,1,'1','1','2018-08-30',2018,'09:59:26','2018-08-29 09:42:48','2018-08-29 09:59:28');

/*Table structure for table `dict` */

DROP TABLE IF EXISTS `dict`;

CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_category` varchar(50) NOT NULL DEFAULT '0' COMMENT '字典大类',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(300) NOT NULL DEFAULT '' COMMENT '名称',
  `dict_description` longtext COMMENT '描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '所属父类',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `order_no` tinyint(3) DEFAULT '0' COMMENT '序号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8mb4 COMMENT='字典';

/*Data for the table `dict` */

insert  into `dict`(`id`,`dict_category`,`code`,`name`,`dict_description`,`parent_id`,`create_time`,`update_time`,`order_no`) values (331,'类目列表','1-cloth','衣服','一级类目衣服',NULL,'2018-09-10 09:21:25','2018-09-10 09:21:27',2),(332,'类目列表','1-food','食品','1级食品类目',NULL,'2018-09-10 09:23:15','2018-09-10 09:23:17',1),(333,'系统配置','a-sys_config','系统配置','',NULL,'2018-09-10 09:24:42','2018-09-10 09:24:44',1),(334,'类目列表','2-coat','上衣','二级类目',331,'2018-09-10 09:25:17','2018-09-10 09:25:19',1),(335,'类目列表','2-hat','帽子','',331,'2018-09-10 09:25:41','2018-09-10 09:25:44',2),(336,'类目列表','2-apple','苹果','二级类目',332,'2018-09-10 09:26:27','2018-09-10 09:26:29',1);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_name` varchar(200) DEFAULT '' COMMENT '商品名',
  `publish_status` char(1) DEFAULT NULL COMMENT '是否发布',
  `goods_status` tinyint(2) DEFAULT '0' COMMENT '商品状态',
  `price` decimal(12,2) DEFAULT NULL COMMENT '价格',
  `store_count` int(4) DEFAULT '0' COMMENT '库存',
  `short_brief` text COMMENT '简介',
  `goods_description` longtext COMMENT '商品描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `img_src` varchar(200) DEFAULT '' COMMENT '图片',
  `goods_category_id` bigint(20) DEFAULT NULL COMMENT '所属类目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`goods_name`,`publish_status`,`goods_status`,`price`,`store_count`,`short_brief`,`goods_description`,`create_time`,`update_time`,`img_src`,`goods_category_id`) values (46,'上衣','0',2,'111.00',0,'111','<img src=\"upload/20180903153250_674545.jpg\" alt=\"657544268260012985.jpg\">33gg','2018-08-03 00:00:00','2018-08-30 15:07:36','upload/20180903153310_699995.jpg',2),(90,'鞋子','0',2,'99.00',100,'简介','描述','2018-09-03 11:24:00','2018-09-03 11:07:00','',2),(92,'帽子','0',2,'99.00',100,'简介','描述','2018-09-06 00:00:00','2018-09-03 11:07:00','1',1),(93,'大衣','0',2,'99.00',100,'简介','描述','2018-09-08 20:26:56','2018-09-03 11:07:00','1',1),(100,'电脑','0',2,'99.00',100,'简介','描述','2018-09-08 00:00:00','2018-09-03 11:07:00','1',1),(101,'苹果','0',2,'99.00',100,'简介','描述',NULL,'2018-09-03 11:07:00','1',1);

/*Table structure for table `goods_category` */

DROP TABLE IF EXISTS `goods_category`;

CREATE TABLE `goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) DEFAULT '' COMMENT '分类名称',
  `keyword` varchar(50) DEFAULT '' COMMENT '关键词',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `goods_category` */

insert  into `goods_category`(`id`,`name`,`keyword`) values (1,'服装类目','搜索fz'),(2,'鞋子类目','xiezi'),(3,'饮食类目','yinshi');

/*Table structure for table `goods_file` */

DROP TABLE IF EXISTS `goods_file`;

CREATE TABLE `goods_file` (
  `file_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_src` varchar(100) DEFAULT '' COMMENT '文件地址',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '所属商品',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='商品附件';

/*Data for the table `goods_file` */

insert  into `goods_file`(`file_id`,`file_src`,`goods_id`) values (1,'upload/20180910090629_408275.jpg',92);

/*Table structure for table `goods_img` */

DROP TABLE IF EXISTS `goods_img`;

CREATE TABLE `goods_img` (
  `img_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img_src` varchar(100) DEFAULT '' COMMENT '图片地址',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '所属商品',
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='商品图集';

/*Data for the table `goods_img` */

insert  into `goods_img`(`img_id`,`img_src`,`goods_id`) values (1,'',92);

/*Table structure for table `sys_admin_user` */

DROP TABLE IF EXISTS `sys_admin_user`;

CREATE TABLE `sys_admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) DEFAULT '' COMMENT '用户名',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `real_name` varchar(25) DEFAULT '' COMMENT '真名',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `telephone` varchar(20) DEFAULT '' COMMENT '座机号',
  `mobile_phone` varchar(20) DEFAULT '' COMMENT '手机号',
  `address` varchar(100) DEFAULT '' COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `role_id` bigint(20) DEFAULT '0' COMMENT '角色',
  `account_non_expired` tinyint(1) DEFAULT '0' COMMENT '是否未失效',
  `account_non_locked` tinyint(1) DEFAULT '0' COMMENT '是否未锁定',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '是否未失效',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`username`),
  KEY `s_a_r_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `sys_admin_user` */

insert  into `sys_admin_user`(`id`,`username`,`password`,`real_name`,`email`,`telephone`,`mobile_phone`,`address`,`create_time`,`update_time`,`role_id`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values (3,'admin','21232f297a57a5a743894a0e4a801fc3','管理员','','','','','2018-07-30 00:00:00','2018-07-30 00:00:00',1,0,0,0,1),(4,'test','098f6bcd4621d373cade4e832627b4f6','测试人员','','','','','2018-09-03 00:00:00','2018-09-03 00:00:00',2,0,0,0,1),(7,'magicalcoder','6860c37b00ed4e444a7d2c6e8fb66886','系统默认超级管理员账号','','','','','2018-09-06 00:00:00','2018-09-06 00:00:00',1,0,0,0,1);

/*Table structure for table `sys_global_permit_url` */

DROP TABLE IF EXISTS `sys_global_permit_url`;

CREATE TABLE `sys_global_permit_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permit_name` varchar(200) DEFAULT '' COMMENT '通过名称',
  `backend_url_reg` varchar(500) DEFAULT '' COMMENT '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序',
  `module_id` bigint(20) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`),
  KEY `sy_m_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后端请求地址全局允许过滤器,在此表的统一不用再去权限表匹配了';

/*Data for the table `sys_global_permit_url` */

insert  into `sys_global_permit_url`(`id`,`permit_name`,`backend_url_reg`,`module_id`) values (2,'允许进入后台,使用有些通用接口（请勿删除）','/admin/(rmp|page|common_file_rest).*',NULL),(3,'通用获取权限相关接口（请勿删除）','/admin/rest_rmp/(get_permission_list|get_module_list).*',NULL);

/*Table structure for table `sys_log_admin_operate` */

DROP TABLE IF EXISTS `sys_log_admin_operate`;

CREATE TABLE `sys_log_admin_operate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_user_id` bigint(20) DEFAULT '0' COMMENT '管理员',
  `user_name` varchar(200) DEFAULT '0' COMMENT '管理员名称',
  `table_name` varchar(100) DEFAULT '' COMMENT '表名',
  `operate_type` varchar(50) DEFAULT '' COMMENT '操作类型',
  `url` varchar(100) DEFAULT '' COMMENT '链接',
  `primary_id_value` varchar(100) DEFAULT '' COMMENT '主键值',
  `form_body` text COMMENT '提交内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_admin_operate` */

insert  into `sys_log_admin_operate`(`id`,`admin_user_id`,`user_name`,`table_name`,`operate_type`,`url`,`primary_id_value`,`form_body`,`create_time`) values (308,3,'管理员','sys_log_admin_operate_rest','delete','/admin/sys_log_admin_operate_rest/delete/307','','{}','2018-09-10 17:00:43');

/*Table structure for table `sys_module` */

DROP TABLE IF EXISTS `sys_module`;

CREATE TABLE `sys_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_name` varchar(200) DEFAULT '' COMMENT '模块唯一键',
  `module_url` varchar(200) DEFAULT '' COMMENT '模块URL',
  `module_category_id` bigint(20) DEFAULT '0' COMMENT '模块分类',
  `sort_num` int(2) DEFAULT '0' COMMENT '排序',
  `module_title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `if_show` tinyint(1) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `FK_module` (`module_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='模块';

/*Data for the table `sys_module` */

insert  into `sys_module`(`id`,`module_name`,`module_url`,`module_category_id`,`sort_num`,`module_title`,`if_show`) values (1,'goods','admin/page/goods/list',1,1,'商品管理',1),(2,'goods_category','admin/page/goods_category/list',1,2,'商品类别',1),(4,'sys_log_admin_operate','admin/page/sys_log_admin_operate/list',3,7,'系统日志',1),(5,'sys_module_category','admin/page/sys_module_category/list',3,3,'菜单管理',1),(7,'sys_admin_user','admin/page/sys_admin_user/list',3,2,'管理员',1),(8,'sys_global_permit_url','admin/page/sys_global_permit_url/list',3,6,'全局地址过滤',1),(11,'sys_role','admin/page/sys_role/list',3,1,'角色管理',1),(14,'all_type','admin/page/all_type/list',1,3,'全类型测试',1),(17,'sys','',3,-1,'系统表权限载体（请勿删除）',0),(18,'goods_img','admin/page/goods_img/list',1,4,'商品图集',1),(19,'goods_file','admin/page/goods_file/list',1,5,'商品附件',1),(20,'dict','admin/page/dict/list',3,10,'字典配置',1);

/*Table structure for table `sys_module_category` */

DROP TABLE IF EXISTS `sys_module_category`;

CREATE TABLE `sys_module_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_category_name` varchar(20) DEFAULT '' COMMENT '模块名称',
  `sort_num` int(2) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='模块分类';

/*Data for the table `sys_module_category` */

insert  into `sys_module_category`(`id`,`module_category_name`,`sort_num`) values (1,'商品模块',1),(3,'系统模块',2);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(50) DEFAULT '' COMMENT '过滤器名称:审核通过',
  `filter_platform` varchar(50) DEFAULT '' COMMENT '过滤端:front|backend|front_backend',
  `backend_url_reg` varchar(200) DEFAULT '' COMMENT '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序',
  `front_dom` varchar(1024) DEFAULT '' COMMENT '前端Key,多个逗号分割',
  `front_action` varchar(50) DEFAULT '' COMMENT '前端处理方式show|disabled',
  `module_id` bigint(20) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`),
  KEY `sy_m_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='后端请求地址允许过滤器,不在过滤器的统一拒绝';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission_name`,`filter_platform`,`backend_url_reg`,`front_dom`,`front_action`,`module_id`) values (2,'查询权限','backend','/admin/goods_category_rest/.*','','',2),(13,'全类型测试','backend','/admin/all_type_rest/.*',NULL,'',14),(15,'商品模块保存权限','front_backend','/admin/goods_rest/(update|save).*','.security_edit_form_operate_save','show',1),(17,'商品模块添加按钮','front','','.security_list_query_operate_add_news','show',1),(18,'商品模块(批量)删除权限','front_backend','/admin/goods_rest/(delete|batch_delete).*','.security_list_table_operate_del,.security_list_query_operate_del_all','show',1),(19,'商品模块查询权限','backend','/admin/goods_rest/(search|page|get).*','','',1),(21,'所有系统表的所有权限(请勿删除)','backend','/admin/(rest_rmp|sys_[a-z_]+_rest)/.*','','show',17),(22,'测试商品模块disabled','front','','.security_edit_form_param_goodsName,.security_edit_form_param_imgSrc,.security_edit_form_param_publishStatus,.security_edit_form_param_goodsStatus,.security_edit_form_param_price,.security_edit_form_param_shortBrief,.security_edit_form_param_goodsDescription,.security_edit_form_param_createTime,.security_edit_form_param_goodsCategoryId','show',1),(23,'商品图集全部权限','front_backend','/admin/goods_img_rest/.*','','show',18),(24,'商品附件全部权限','front_backend','/admin/goods_file_rest/.*','','show',19),(25,'字典全部权限','front_backend','/admin/dict_rest/.*','','show',20);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`) values (1,'超级管理员'),(2,'测试账号');

/*Table structure for table `sys_role_module_permission` */

DROP TABLE IF EXISTS `sys_role_module_permission`;

CREATE TABLE `sys_role_module_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  KEY `s_r_id` (`role_id`),
  KEY `s_m_id` (`module_id`),
  KEY `s_pid` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8 COMMENT='角色模块权限';

/*Data for the table `sys_role_module_permission` */

insert  into `sys_role_module_permission`(`id`,`role_id`,`module_id`,`permission_id`) values (407,1,1,15),(408,1,1,17),(409,1,1,18),(410,1,1,19),(411,1,1,22),(412,1,2,2),(413,1,14,13),(414,1,18,23),(415,1,19,24),(416,1,4,NULL),(417,1,5,NULL),(418,1,6,NULL),(419,1,7,NULL),(420,1,8,NULL),(421,1,9,NULL),(422,1,10,NULL),(423,1,11,NULL),(424,1,17,21),(425,1,20,25),(426,2,1,15),(427,2,1,17),(428,2,1,18),(429,2,1,19),(430,2,1,22),(431,2,18,NULL),(432,2,19,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
