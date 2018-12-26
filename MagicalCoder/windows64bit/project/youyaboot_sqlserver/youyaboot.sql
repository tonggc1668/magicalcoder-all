USE [master]
GO
/****** Object:  Database [youyaboot]    Script Date: 2018/9/21 9:22:24 ******/
CREATE DATABASE [youyaboot]
 CONTAINMENT = NONE
 GO
ALTER DATABASE [youyaboot] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [youyaboot].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [youyaboot] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [youyaboot] SET ANSI_NULLS OFF
GO
ALTER DATABASE [youyaboot] SET ANSI_PADDING OFF
GO
ALTER DATABASE [youyaboot] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [youyaboot] SET ARITHABORT OFF
GO
ALTER DATABASE [youyaboot] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [youyaboot] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [youyaboot] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [youyaboot] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [youyaboot] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [youyaboot] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [youyaboot] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [youyaboot] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [youyaboot] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [youyaboot] SET  DISABLE_BROKER
GO
ALTER DATABASE [youyaboot] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [youyaboot] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [youyaboot] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [youyaboot] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [youyaboot] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [youyaboot] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [youyaboot] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [youyaboot] SET RECOVERY FULL
GO
ALTER DATABASE [youyaboot] SET  MULTI_USER
GO
ALTER DATABASE [youyaboot] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [youyaboot] SET DB_CHAINING OFF
GO
ALTER DATABASE [youyaboot] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [youyaboot] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [youyaboot] SET DELAYED_DURABILITY = DISABLED
GO
EXEC sys.sp_db_vardecimal_storage_format N'youyaboot', N'ON'
GO
ALTER DATABASE [youyaboot] SET QUERY_STORE = OFF
GO
USE [youyaboot]
GO
/****** Object:  Table [dbo].[dict]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[dict](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[dict_category] [varchar](50) NULL,
	[code] [varchar](50) NULL,
	[name] [varchar](300) NULL,
	[dict_description] [text] NULL,
	[parent_id] [bigint] NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[order_no] [tinyint] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[goods]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[goods](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[goods_name] [varchar](200) NULL,
	[publish_status] [char](1) NULL,
	[goods_status] [tinyint] NULL,
	[price] [decimal](12, 2) NULL,
	[store_count] [int] NULL,
	[short_brief] [text] NULL,
	[goods_description] [ntext] NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[img_src] [varchar](200) NULL,
	[goods_category_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[goods_category]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[goods_category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[keyword] [varchar](50) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[goods_file]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[goods_file](
	[file_id] [bigint] IDENTITY(1,1) NOT NULL,
	[file_src] [varchar](50) NULL,
	[goods_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[file_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[goods_img]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[goods_img](
	[img_id] [bigint] IDENTITY(1,1) NOT NULL,
	[img_src] [varchar](100) NULL,
	[goods_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[img_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_admin_user]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_admin_user](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](20) NULL,
	[password] [varchar](50) NULL,
	[real_name] [varchar](25) NULL,
	[email] [varchar](25) NULL,
	[telephone] [varchar](20) NULL,
	[mobile_phone] [varchar](20) NULL,
	[address] [varchar](100) NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[role_id] [bigint] NULL,
	[account_non_expired] [bit] NULL,
	[account_non_locked] [bit] NULL,
	[credentials_non_expired] [bit] NULL,
	[enabled] [bit] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_global_permit_url]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_global_permit_url](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[permit_name] [varchar](200) NULL,
	[backend_url_reg] [varchar](500) NULL,
	[module_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_log_admin_operate]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_log_admin_operate](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[admin_user_id] [bigint] NULL,
	[user_name] [varchar](200) NULL,
	[table_name] [varchar](100) NULL,
	[operate_type] [varchar](50) NULL,
	[url] [varchar](100) NULL,
	[primary_id_value] [varchar](100) NULL,
	[form_body] [text] NULL,
	[create_time] [datetime] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_module]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_module](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[module_name] [varchar](200) NULL,
	[module_url] [varchar](200) NULL,
	[module_category_id] [bigint] NULL,
	[sort_num] [int] NULL,
	[module_title] [varchar](50) NULL,
	[if_show] [bit] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_module_category]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_module_category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[module_category_name] [varchar](20) NULL,
	[sort_num] [int] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_permission]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_permission](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[permission_name] [varchar](50) NULL,
	[filter_platform] [varchar](50) NULL,
	[backend_url_reg] [varchar](200) NULL,
	[front_dom] [varchar](1024) NULL,
	[front_action] [varchar](50) NULL,
	[module_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_role]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_role](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[role_name] [varchar](20) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_role_module_permission]    Script Date: 2018/9/21 9:22:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_role_module_permission](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[role_id] [bigint] NULL,
	[module_id] [bigint] NULL,
	[permission_id] [bigint] NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[dict] ON

INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (1, N'1', N'1', N'1', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (2, N'2', N'1', N'2', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (3, N'3', N'', N'', NULL, 2, NULL, NULL, NULL)
INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (4, N'4', N'', N'', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (5, N'5', N'', N'', NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[dict] ([id], [dict_category], [code], [name], [dict_description], [parent_id], [create_time], [update_time], [order_no]) VALUES (6, N'6', N'', N'', NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[dict] OFF
SET IDENTITY_INSERT [dbo].[goods] ON

INSERT [dbo].[goods] ([id], [goods_name], [publish_status], [goods_status], [price], [store_count], [short_brief], [goods_description], [create_time], [update_time], [img_src], [goods_category_id]) VALUES (1, N'1', N'1', 0, CAST(1.00 AS Decimal(12, 2)), 1, N'1', N'2', CAST(N'2018-09-03T09:44:00.000' AS DateTime), NULL, N'1', 4)
INSERT [dbo].[goods] ([id], [goods_name], [publish_status], [goods_status], [price], [store_count], [short_brief], [goods_description], [create_time], [update_time], [img_src], [goods_category_id]) VALUES (3, N'我是默认值', N'0', 1, CAST(10.00 AS Decimal(12, 2)), 1, N'我是默认值', N'我是默认值', CAST(N'2018-09-03T09:44:00.000' AS DateTime), NULL, N'upload/20180920192144_366230.png', 5)
SET IDENTITY_INSERT [dbo].[goods] OFF
SET IDENTITY_INSERT [dbo].[goods_category] ON

INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (4, N'a', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (5, N'b', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (6, N'c', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (7, N'd', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (8, N'e', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (9, N'f', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (10, N'g', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (11, N'h', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (12, N'i', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (13, N'j', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (14, N'k', N'')
INSERT [dbo].[goods_category] ([id], [name], [keyword]) VALUES (15, N'l', N'')
SET IDENTITY_INSERT [dbo].[goods_category] OFF
SET IDENTITY_INSERT [dbo].[goods_file] ON

INSERT [dbo].[goods_file] ([file_id], [file_src], [goods_id]) VALUES (1, N'('''')', 1)
SET IDENTITY_INSERT [dbo].[goods_file] OFF
SET IDENTITY_INSERT [dbo].[goods_img] ON

INSERT [dbo].[goods_img] ([img_id], [img_src], [goods_id]) VALUES (1, N'upload/20180920190811_816984.png', 1)
SET IDENTITY_INSERT [dbo].[goods_img] OFF
SET IDENTITY_INSERT [dbo].[sys_admin_user] ON

INSERT [dbo].[sys_admin_user] ([id], [username], [password], [real_name], [email], [telephone], [mobile_phone], [address], [create_time], [update_time], [role_id], [account_non_expired], [account_non_locked], [credentials_non_expired], [enabled]) VALUES (3, N'admin', N'21232f297a57a5a743894a0e4a801fc3', N'管理员', N'', N'', N'', N'', CAST(N'2018-07-30T00:00:00.000' AS DateTime), CAST(N'2018-07-30T00:00:00.000' AS DateTime), 1, 0, 0, 0, 1)
INSERT [dbo].[sys_admin_user] ([id], [username], [password], [real_name], [email], [telephone], [mobile_phone], [address], [create_time], [update_time], [role_id], [account_non_expired], [account_non_locked], [credentials_non_expired], [enabled]) VALUES (4, N'test', N'098f6bcd4621d373cade4e832627b4f6', N'测试人员', N'', N'', N'', N'', CAST(N'2018-09-03T00:00:00.000' AS DateTime), CAST(N'2018-09-03T00:00:00.000' AS DateTime), 2, 0, 0, 0, 1)
INSERT [dbo].[sys_admin_user] ([id], [username], [password], [real_name], [email], [telephone], [mobile_phone], [address], [create_time], [update_time], [role_id], [account_non_expired], [account_non_locked], [credentials_non_expired], [enabled]) VALUES (7, N'magicalcoder', N'6860c37b00ed4e444a7d2c6e8fb66886', N'系统默认超级管理员账号', N'', N'', N'', N'', CAST(N'2018-09-06T00:00:00.000' AS DateTime), CAST(N'2018-09-06T00:00:00.000' AS DateTime), 1, 0, 0, 0, 1)
SET IDENTITY_INSERT [dbo].[sys_admin_user] OFF
SET IDENTITY_INSERT [dbo].[sys_global_permit_url] ON

INSERT [dbo].[sys_global_permit_url] ([id], [permit_name], [backend_url_reg], [module_id]) VALUES (2, N'允许进入后台,使用有些通用接口（请勿删除）', N'/admin/(rmp|page|common_file_rest).*', NULL)
INSERT [dbo].[sys_global_permit_url] ([id], [permit_name], [backend_url_reg], [module_id]) VALUES (3, N'通用获取权限相关接口（请勿删除）', N'/admin/rest_rmp/(get_permission_list|get_module_list).*', NULL)
SET IDENTITY_INSERT [dbo].[sys_global_permit_url] OFF
SET IDENTITY_INSERT [dbo].[sys_log_admin_operate] ON

 INSERT [dbo].[sys_log_admin_operate] ([id], [admin_user_id], [user_name], [table_name], [operate_type], [url], [primary_id_value], [form_body], [create_time]) VALUES (405, 3, N'管理员', N'goods_category_rest', N'save', N'/admin/goods_category_rest/save', N'', N'{"id":[""],"name":["10"],"keyword":[""]}', CAST(N'2018-09-20T16:47:03.480' AS DateTime))
INSERT [dbo].[sys_log_admin_operate] ([id], [admin_user_id], [user_name], [table_name], [operate_type], [url], [primary_id_value], [form_body], [create_time]) VALUES (406, 3, N'管理员', N'goods_category_rest', N'save', N'/admin/goods_category_rest/save', N'', N'{"id":[""],"name":["11"],"keyword":[""]}', CAST(N'2018-09-20T16:47:06.997' AS DateTime))
GO
INSERT [dbo].[sys_log_admin_operate] ([id], [admin_user_id], [user_name], [table_name], [operate_type], [url], [primary_id_value], [form_body], [create_time]) VALUES (407, 3, N'管理员', N'goods_category_rest', N'save', N'/admin/goods_category_rest/save', N'', N'{"id":[""],"name":["12"],"keyword":[""]}', CAST(N'2018-09-20T16:47:10.100' AS DateTime))
 INSERT [dbo].[sys_log_admin_operate] ([id], [admin_user_id], [user_name], [table_name], [operate_type], [url], [primary_id_value], [form_body], [create_time]) VALUES (451, 3, N'管理员', N'sys_module_rest', N'delete', N'/admin/sys_module_rest/delete/14', NULL, N'{}', CAST(N'2018-09-21T09:21:34.537' AS DateTime))
SET IDENTITY_INSERT [dbo].[sys_log_admin_operate] OFF
SET IDENTITY_INSERT [dbo].[sys_module] ON

INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (1, N'goods', N'admin/page/goods/list', 1, 1, N'商品管理', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (2, N'goods_category', N'admin/page/goods_category/list', 1, 2, N'商品类别', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (4, N'sys_log_admin_operate', N'admin/page/sys_log_admin_operate/list', 3, 7, N'系统日志', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (5, N'sys_module_category', N'admin/page/sys_module_category/list', 3, 1, N'菜单管理', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (7, N'sys_admin_user', N'admin/page/sys_admin_user/list', 3, 3, N'管理员', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (8, N'sys_global_permit_url', N'admin/page/sys_global_permit_url/list', 3, 6, N'全局地址过滤', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (11, N'sys_role', N'admin/page/sys_role/list', 3, 2, N'角色管理', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (17, N'sys', N'', 3, -1, N'系统表权限载体（请勿删除）', 0)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (18, N'goods_img', N'admin/page/goods_img/list', 1, 4, N'商品图集', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (19, N'goods_file', N'admin/page/goods_file/list', 1, 5, N'商品附件', 1)
INSERT [dbo].[sys_module] ([id], [module_name], [module_url], [module_category_id], [sort_num], [module_title], [if_show]) VALUES (20, N'dict', N'admin/page/dict/list', 3, 10, N'字典配置', 1)
SET IDENTITY_INSERT [dbo].[sys_module] OFF
SET IDENTITY_INSERT [dbo].[sys_module_category] ON

INSERT [dbo].[sys_module_category] ([id], [module_category_name], [sort_num]) VALUES (1, N'商品模块', 1)
INSERT [dbo].[sys_module_category] ([id], [module_category_name], [sort_num]) VALUES (3, N'系统模块', 2)
SET IDENTITY_INSERT [dbo].[sys_module_category] OFF
SET IDENTITY_INSERT [dbo].[sys_permission] ON

INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (2, N'查询权限', N'backend', N'/admin/goods_category_rest/.*', N'', N'', 2)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (13, N'全类型测试', N'backend', N'/admin/all_type_rest/.*', NULL, N'', 14)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (15, N'商品模块保存权限', N'front_backend', N'/admin/goods_rest/(update|save).*', N'.security_edit_form_operate_save', N'show', 1)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (17, N'商品模块添加按钮', N'front', N'', N'.security_list_query_operate_add_news', N'show', 1)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (18, N'商品模块(批量)删除权限', N'front_backend', N'/admin/goods_rest/(delete|batch_delete).*', N'.security_list_table_operate_del,.security_list_query_operate_del_all', N'show', 1)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (19, N'商品模块查询权限', N'backend', N'/admin/goods_rest/(search|page|get).*', N'', N'', 1)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (21, N'所有系统表的所有权限(请勿删除)', N'backend', N'/admin/(rest_rmp|sys_[a-z_]+_rest)/.*', N'', N'show', 17)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (22, N'测试商品模块disabled', N'front', N'', N'.security_edit_form_param_goodsName,.security_edit_form_param_imgSrc,.security_edit_form_param_publishStatus,.security_edit_form_param_goodsStatus,.security_edit_form_param_price,.security_edit_form_param_shortBrief,.security_edit_form_param_goodsDescription,.security_edit_form_param_createTime,.security_edit_form_param_goodsCategoryId', N'show', 1)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (23, N'商品图集全部权限', N'front_backend', N'/admin/goods_img_rest/.*', N'', N'show', 18)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (24, N'商品附件全部权限', N'front_backend', N'/admin/goods_file_rest/.*', N'', N'show', 19)
INSERT [dbo].[sys_permission] ([id], [permission_name], [filter_platform], [backend_url_reg], [front_dom], [front_action], [module_id]) VALUES (25, N'字典全部权限', N'front_backend', N'/admin/dict_rest/.*', N'', N'show', 20)
SET IDENTITY_INSERT [dbo].[sys_permission] OFF
SET IDENTITY_INSERT [dbo].[sys_role] ON

INSERT [dbo].[sys_role] ([id], [role_name]) VALUES (1, N'超级管理员')
INSERT [dbo].[sys_role] ([id], [role_name]) VALUES (2, N'测试账号')
SET IDENTITY_INSERT [dbo].[sys_role] OFF
SET IDENTITY_INSERT [dbo].[sys_role_module_permission] ON

INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (426, 2, 1, 15)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (427, 2, 1, 17)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (428, 2, 1, 18)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (429, 2, 1, 19)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (430, 2, 1, 22)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (431, 2, 18, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (432, 2, 19, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (521, 1, 1, 15)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (522, 1, 1, 17)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (523, 1, 1, 18)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (524, 1, 1, 19)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (525, 1, 1, 22)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (526, 1, 2, 2)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (527, 1, 14, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (528, 1, 18, 23)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (529, 1, 19, 24)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (530, 1, 4, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (531, 1, 5, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (532, 1, 7, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (533, 1, 8, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (534, 1, 11, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (535, 1, 17, 21)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (536, 1, 20, 25)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (537, 1, 21, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (538, 1, 22, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (539, 1, 23, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (540, 1, 24, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (541, 1, 25, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (542, 1, 26, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (543, 1, 27, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (544, 1, 28, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (545, 1, 29, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (546, 1, 30, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (547, 1, 31, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (548, 1, 32, NULL)
INSERT [dbo].[sys_role_module_permission] ([id], [role_id], [module_id], [permission_id]) VALUES (549, 1, 33, NULL)
SET IDENTITY_INSERT [dbo].[sys_role_module_permission] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [sys_admin_user_username_uindex]    Script Date: 2018/9/21 9:22:25 ******/
CREATE UNIQUE NONCLUSTERED INDEX [sys_admin_user_username_uindex] ON [dbo].[sys_admin_user]
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[dict] ADD  DEFAULT ('') FOR [dict_category]
GO
ALTER TABLE [dbo].[dict] ADD  DEFAULT ('') FOR [code]
GO
ALTER TABLE [dbo].[dict] ADD  DEFAULT ('') FOR [name]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ('') FOR [goods_name]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ((0)) FOR [publish_status]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ((0)) FOR [goods_status]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ((0)) FOR [price]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ((0)) FOR [store_count]
GO
ALTER TABLE [dbo].[goods] ADD  DEFAULT ('') FOR [img_src]
GO
ALTER TABLE [dbo].[goods_category] ADD  DEFAULT ('') FOR [name]
GO
ALTER TABLE [dbo].[goods_category] ADD  DEFAULT ('') FOR [keyword]
GO
ALTER TABLE [dbo].[goods_file] ADD  DEFAULT ('') FOR [file_src]
GO
ALTER TABLE [dbo].[goods_img] ADD  DEFAULT ('') FOR [img_src]
GO
ALTER TABLE [dbo].[sys_role] ADD  DEFAULT ('') FOR [role_name]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'大类' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'dict_category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'dict_description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父子关系' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'parent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict', @level2type=N'COLUMN',@level2name=N'order_no'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字典' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'dict'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_category', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_category', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关键词' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_category', @level2type=N'COLUMN',@level2name=N'keyword'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商品类目' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_file', @level2type=N'COLUMN',@level2name=N'file_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_file', @level2type=N'COLUMN',@level2name=N'file_src'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'所属商品' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_file', @level2type=N'COLUMN',@level2name=N'goods_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商品附件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_file'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_img', @level2type=N'COLUMN',@level2name=N'img_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'图片地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_img', @level2type=N'COLUMN',@level2name=N'img_src'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'所属商品' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_img', @level2type=N'COLUMN',@level2name=N'goods_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商品图集' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'goods_img'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'管理员' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_admin_user'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'全局授权地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_global_permit_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作日志' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_log_admin_operate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_module', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'二级菜单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_module'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_module_category', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'一级菜单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_module_category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'一级菜单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_permission'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色菜单权限' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_role_module_permission'
GO
USE [master]
GO
ALTER DATABASE [youyaboot] SET  READ_WRITE
GO
