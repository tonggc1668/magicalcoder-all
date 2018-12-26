-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 9.6.10

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
SET default_tablespace = '';

SET default_with_oids = false;

--drop TABLE public.dict
CREATE TABLE public.dict (
    id bigint NOT NULL,
    dict_category character varying(50) DEFAULT ''::character varying,
    code character varying(1000) DEFAULT ''::character varying,
    name character varying(300) DEFAULT ''::character varying,
    dict_description text DEFAULT ''::character varying,
    parent_id bigint,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    order_no integer DEFAULT 0
);

COMMENT ON TABLE public.dict IS '字典';
COMMENT ON COLUMN public.dict.id IS '主键';
COMMENT ON COLUMN public.dict.dict_category IS '字典大类';
COMMENT ON COLUMN public.dict.code IS '键';
COMMENT ON COLUMN public.dict.name IS '值';
COMMENT ON COLUMN public.dict.dict_description IS '描述';
COMMENT ON COLUMN public.dict.parent_id IS '所属父类';
COMMENT ON COLUMN public.dict.create_time IS '创建时间';
COMMENT ON COLUMN public.dict.update_time IS '更新时间';
COMMENT ON COLUMN public.dict.order_no IS '序号';
CREATE SEQUENCE public.dict_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.dict_id_seq OWNED BY public.dict.id;
--drop TABLE public.goods;
CREATE TABLE public.goods (
    id bigint NOT NULL,
    goods_name character varying(200) DEFAULT ''::character varying NOT NULL,
    price numeric(12,2),
    publish_status character(1) DEFAULT 0,
    goods_status smallint DEFAULT 0,
    store_count integer,
    short_brief text,
    goods_description text,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    img_src character varying(200) DEFAULT ''::character varying,
    goods_category_id bigint
);

COMMENT ON TABLE public.goods IS '商品表';
COMMENT ON COLUMN public.goods.id IS '主键';
COMMENT ON COLUMN public.goods.goods_name IS '名称';
COMMENT ON COLUMN public.goods.price IS '价格';
COMMENT ON COLUMN public.goods.publish_status IS '是否发布';
COMMENT ON COLUMN public.goods.goods_status IS '商品状态';
COMMENT ON COLUMN public.goods.store_count IS '库存';
COMMENT ON COLUMN public.goods.short_brief IS '简介';
COMMENT ON COLUMN public.goods.goods_description IS '商品描述';
COMMENT ON COLUMN public.goods.create_time IS '创建时间';
COMMENT ON COLUMN public.goods.update_time IS '更新时间';
COMMENT ON COLUMN public.goods.img_src IS '图片';
COMMENT ON COLUMN public.goods.goods_category_id IS '所属类目';
CREATE TABLE public.goods_category (
    id bigint NOT NULL,
    name character varying(50) DEFAULT ''::character varying,
    keyword character varying(50) DEFAULT ''::character varying
);

COMMENT ON TABLE public.goods_category IS '商品类目';
COMMENT ON COLUMN public.goods_category.id IS '主键';
COMMENT ON COLUMN public.goods_category.name IS '分类名称';
COMMENT ON COLUMN public.goods_category.keyword IS '关键词';
CREATE SEQUENCE public.goods_category_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.goods_category_id_seq OWNED BY public.goods_category.id;
CREATE TABLE public.goods_file (
    file_id bigint NOT NULL,
    file_src character varying(100) DEFAULT ''::character varying,
    goods_id bigint
);

COMMENT ON TABLE public.goods_file IS '商品附件';
COMMENT ON COLUMN public.goods_file.file_id IS '主键';
COMMENT ON COLUMN public.goods_file.file_src IS '文件地址';
COMMENT ON COLUMN public.goods_file.goods_id IS '所属商品';
CREATE SEQUENCE public.goods_file_file_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.goods_file_file_id_seq OWNED BY public.goods_file.file_id;
CREATE SEQUENCE public.goods_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.goods_id_seq OWNED BY public.goods.id;
CREATE TABLE public.goods_img (
    img_id bigint NOT NULL,
    img_src character varying(100) DEFAULT ''::character varying,
    goods_id bigint
);

COMMENT ON TABLE public.goods_img IS '商品图片';
COMMENT ON COLUMN public.goods_img.img_id IS '主键';
COMMENT ON COLUMN public.goods_img.img_src IS '图片地址';
COMMENT ON COLUMN public.goods_img.goods_id IS '所属商品';
CREATE SEQUENCE public.goods_img_img_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.goods_img_img_id_seq OWNED BY public.goods_img.img_id;
CREATE TABLE public.sys_admin_user (
    id bigint NOT NULL,
    username character varying(20) DEFAULT ''::character varying,
    password character varying(50),
    real_name character varying(25) DEFAULT ''::character varying,
    email character varying(30) DEFAULT ''::character varying,
    telephone character varying(20) DEFAULT ''::character varying,
    mobile_phone character varying(20) DEFAULT ''::character varying,
    address character varying(100) DEFAULT ''::character varying,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    role_id bigint,
    enabled boolean,
    credentials_non_expired boolean,
    account_non_expired boolean,
    account_non_locked boolean
);

COMMENT ON COLUMN public.sys_admin_user.id IS '主键';
COMMENT ON COLUMN public.sys_admin_user.username IS '用户名';
COMMENT ON COLUMN public.sys_admin_user.password IS '密码';
COMMENT ON COLUMN public.sys_admin_user.email IS '邮箱';
COMMENT ON COLUMN public.sys_admin_user.telephone IS '座机';
COMMENT ON COLUMN public.sys_admin_user.mobile_phone IS '手机号';
COMMENT ON COLUMN public.sys_admin_user.address IS '地址';
COMMENT ON COLUMN public.sys_admin_user.create_time IS '创建时间';
COMMENT ON COLUMN public.sys_admin_user.update_time IS '更新时间';
COMMENT ON COLUMN public.sys_admin_user.role_id IS '角色';
COMMENT ON COLUMN public.sys_admin_user.enabled IS '是否启用';
COMMENT ON COLUMN public.sys_admin_user.credentials_non_expired IS '是否过期';
COMMENT ON COLUMN public.sys_admin_user.account_non_expired IS '是否未过期 暂未使用';
COMMENT ON COLUMN public.sys_admin_user.account_non_locked IS '是否锁定 暂未使用';
CREATE SEQUENCE public.sys_admin_user_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_admin_user_id_seq OWNED BY public.sys_admin_user.id;
CREATE TABLE public.sys_global_permit_url (
    id bigint NOT NULL,
    permit_name character varying(50) DEFAULT ''::character varying,
    backend_url_reg character varying(200) DEFAULT ''::character varying,
    module_id bigint
);

COMMENT ON TABLE public.sys_global_permit_url IS '后端请求地址全局允许过滤器,在此表的统一不用再去权限表匹配了';
COMMENT ON COLUMN public.sys_global_permit_url.id IS '主键';
COMMENT ON COLUMN public.sys_global_permit_url.permit_name IS '通过名称';
COMMENT ON COLUMN public.sys_global_permit_url.backend_url_reg IS '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序';
COMMENT ON COLUMN public.sys_global_permit_url.module_id IS '所属模块';
CREATE SEQUENCE public.sys_global_permit_url_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_global_permit_url_id_seq OWNED BY public.sys_global_permit_url.id;
CREATE TABLE public.sys_log_admin_operate (
    id bigint NOT NULL,
    admin_user_id bigint,
    user_name character varying(100) DEFAULT ''::character varying,
    table_name character varying(100) DEFAULT ''::character varying,
    operate_type character varying(50) DEFAULT ''::character varying,
    url character varying(200) DEFAULT ''::character varying,
    primary_id_value character varying(100) DEFAULT ''::character varying,
    form_body text,
    create_time timestamp without time zone
);

COMMENT ON TABLE public.sys_log_admin_operate IS '系统操作日志';
COMMENT ON COLUMN public.sys_log_admin_operate.id IS '主键';
COMMENT ON COLUMN public.sys_log_admin_operate.admin_user_id IS '管理员';
COMMENT ON COLUMN public.sys_log_admin_operate.user_name IS '管理员名称';
COMMENT ON COLUMN public.sys_log_admin_operate.table_name IS '表名';
COMMENT ON COLUMN public.sys_log_admin_operate.operate_type IS '操作类型';
COMMENT ON COLUMN public.sys_log_admin_operate.url IS '链接';
COMMENT ON COLUMN public.sys_log_admin_operate.primary_id_value IS '主键值';
COMMENT ON COLUMN public.sys_log_admin_operate.form_body IS '提交内容';
COMMENT ON COLUMN public.sys_log_admin_operate.create_time IS '创建时间';
CREATE SEQUENCE public.sys_log_admin_operate_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_log_admin_operate_id_seq OWNED BY public.sys_log_admin_operate.id;
CREATE TABLE public.sys_module (
    id bigint NOT NULL,
    module_name character varying(200) DEFAULT ''::character varying,
    module_url character varying(200) DEFAULT ''::character varying,
    module_category_id bigint DEFAULT '0'::bigint,
    sort_num integer DEFAULT 0,
    module_title character varying(50) DEFAULT ''::character varying,
    if_show boolean
);

COMMENT ON COLUMN public.sys_module.id IS '主键';
COMMENT ON COLUMN public.sys_module.module_name IS '模块名称';
COMMENT ON COLUMN public.sys_module.module_url IS '模块URL';
COMMENT ON COLUMN public.sys_module.module_category_id IS '模块类目';
COMMENT ON COLUMN public.sys_module.sort_num IS '排序';
COMMENT ON COLUMN public.sys_module.module_title IS '模块标题';
CREATE TABLE public.sys_module_category (
    id bigint NOT NULL,
    module_category_name character varying(20) DEFAULT ''::character varying,
    sort_num integer DEFAULT 0
);

COMMENT ON TABLE public.sys_module_category IS '模块分类';
COMMENT ON COLUMN public.sys_module_category.id IS '主键';
COMMENT ON COLUMN public.sys_module_category.module_category_name IS '模块名称';
COMMENT ON COLUMN public.sys_module_category.sort_num IS '排序';
CREATE SEQUENCE public.sys_module_category_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_module_category_id_seq OWNED BY public.sys_module_category.id;
CREATE SEQUENCE public.sys_module_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_module_id_seq OWNED BY public.sys_module.id;
CREATE TABLE public.sys_permission (
    id bigint NOT NULL,
    filter_platform character varying(50) DEFAULT ''::character varying,
    permission_name character varying(50) DEFAULT ''::character varying,
    backend_url_reg character varying(200) DEFAULT ''::character varying,
    front_dom character varying(1024) DEFAULT ''::character varying,
    front_action character varying(50) DEFAULT ''::character varying,
    module_id bigint
);

COMMENT ON TABLE public.sys_permission IS '权限';
COMMENT ON COLUMN public.sys_permission.id IS '主键';
COMMENT ON COLUMN public.sys_permission.filter_platform IS '过滤端:front|backend|front_backend';
COMMENT ON COLUMN public.sys_permission.permission_name IS '过滤器名称:审核通过';
COMMENT ON COLUMN public.sys_permission.backend_url_reg IS '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序';
COMMENT ON COLUMN public.sys_permission.front_dom IS '前端dom获取';
COMMENT ON COLUMN public.sys_permission.front_action IS '前端处理方式show|disabled';
COMMENT ON COLUMN public.sys_permission.module_id IS '所属模块';
CREATE SEQUENCE public.sys_permission_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_permission_id_seq OWNED BY public.sys_permission.id;
CREATE TABLE public.sys_role (
    id bigint NOT NULL,
    role_name character varying(20) DEFAULT ''::character varying
);

COMMENT ON COLUMN public.sys_role.id IS '主键';
COMMENT ON COLUMN public.sys_role.role_name IS '角色名';
CREATE SEQUENCE public.sys_role_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_role_id_seq OWNED BY public.sys_role.id;
CREATE TABLE public.sys_role_module_permission (
    id bigint NOT NULL,
    role_id bigint,
    module_id bigint,
    permission_id bigint
);

COMMENT ON TABLE public.sys_role_module_permission IS '角色模块权限';
COMMENT ON COLUMN public.sys_role_module_permission.id IS '主键';
COMMENT ON COLUMN public.sys_role_module_permission.role_id IS '角色';
COMMENT ON COLUMN public.sys_role_module_permission.module_id IS '模块';
COMMENT ON COLUMN public.sys_role_module_permission.permission_id IS '权限';
CREATE SEQUENCE public.sys_role_module_permission_id_seq
    START WITH 2000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.sys_role_module_permission_id_seq OWNED BY public.sys_role_module_permission.id;
ALTER TABLE ONLY public.dict ALTER COLUMN id SET DEFAULT nextval('public.dict_id_seq'::regclass);
ALTER TABLE ONLY public.goods ALTER COLUMN id SET DEFAULT nextval('public.goods_id_seq'::regclass);
ALTER TABLE ONLY public.goods_category ALTER COLUMN id SET DEFAULT nextval('public.goods_category_id_seq'::regclass);
ALTER TABLE ONLY public.goods_file ALTER COLUMN file_id SET DEFAULT nextval('public.goods_file_file_id_seq'::regclass);
ALTER TABLE ONLY public.goods_img ALTER COLUMN img_id SET DEFAULT nextval('public.goods_img_img_id_seq'::regclass);
ALTER TABLE ONLY public.sys_admin_user ALTER COLUMN id SET DEFAULT nextval('public.sys_admin_user_id_seq'::regclass);
ALTER TABLE ONLY public.sys_global_permit_url ALTER COLUMN id SET DEFAULT nextval('public.sys_global_permit_url_id_seq'::regclass);
ALTER TABLE ONLY public.sys_log_admin_operate ALTER COLUMN id SET DEFAULT nextval('public.sys_log_admin_operate_id_seq'::regclass);
ALTER TABLE ONLY public.sys_module ALTER COLUMN id SET DEFAULT nextval('public.sys_module_id_seq'::regclass);
ALTER TABLE ONLY public.sys_module_category ALTER COLUMN id SET DEFAULT nextval('public.sys_module_category_id_seq'::regclass);
ALTER TABLE ONLY public.sys_permission ALTER COLUMN id SET DEFAULT nextval('public.sys_permission_id_seq'::regclass);
ALTER TABLE ONLY public.sys_role ALTER COLUMN id SET DEFAULT nextval('public.sys_role_id_seq'::regclass);
ALTER TABLE ONLY public.sys_role_module_permission ALTER COLUMN id SET DEFAULT nextval('public.sys_role_module_permission_id_seq'::regclass);

INSERT INTO public.sys_admin_user (id, username, password, real_name, email, telephone, mobile_phone, address, create_time, update_time, role_id, enabled, credentials_non_expired, account_non_expired, account_non_locked) VALUES (7, 'magicalcoder', '6860c37b00ed4e444a7d2c6e8fb66886', '系统默认超级管理员账号', '', '', '', '', '2018-09-06 00:00:00.000000', '2018-09-06 00:00:00.000000', 1, true, null, null, null);
INSERT INTO public.sys_admin_user (id, username, password, real_name, email, telephone, mobile_phone, address, create_time, update_time, role_id, enabled, credentials_non_expired, account_non_expired, account_non_locked) VALUES (4, 'test', '098f6bcd4621d373cade4e832627b4f6', '测试人员', '', '', '', '', '2018-09-03 00:00:00.000000', '2018-09-03 00:00:00.000000', 2, true, null, null, null);
INSERT INTO public.sys_admin_user (id, username, password, real_name, email, telephone, mobile_phone, address, create_time, update_time, role_id, enabled, credentials_non_expired, account_non_expired, account_non_locked) VALUES (3, 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '', '', '', '', '2018-07-30 00:00:00.000000', '2018-07-30 00:00:00.000000', 1, true, null, null, null);

INSERT INTO public.sys_global_permit_url (id, permit_name, backend_url_reg, module_id) VALUES (3, '通用获取权限相关接口（请勿删除）', '/admin/rest_rmp/(get_permission_list|get_module_list).*', null);
INSERT INTO public.sys_global_permit_url (id, permit_name, backend_url_reg, module_id) VALUES (2, '允许进入后台,使用有些通用接口（请勿删除）', '/admin/(rmp|page|common_file_rest).*', null);

INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (17, 'sys', '', 3, -1, '系统表权限载体（请勿删除）', null);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (2, 'goods_category', 'admin/page/goods_category/list', 1, 2, '商品类别', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (18, 'goods_img', 'admin/page/goods_img/list', 1, 4, '商品图集', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (19, 'goods_file', 'admin/page/goods_file/list', 1, 5, '商品附件', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (14, 'all_type', 'admin/page/all_type/list', 1, 3, '全类型测试', false);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (11, 'sys_role', 'admin/page/sys_role/list', 3, 1, '角色管理', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (7, 'sys_admin_user', 'admin/page/sys_admin_user/list', 3, 2, '管理员', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (5, 'sys_module_category', 'admin/page/sys_module_category/list', 3, 3, '菜单管理', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (8, 'sys_global_permit_url', 'admin/page/sys_global_permit_url/list', 3, 6, '全局地址过滤', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (4, 'sys_log_admin_operate', 'admin/page/sys_log_admin_operate/list', 3, 7, '系统日志', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (20, 'dict', 'admin/page/dict/list', 3, 10, '字典配置', true);
INSERT INTO public.sys_module (id, module_name, module_url, module_category_id, sort_num, module_title, if_show) VALUES (1, 'goods', 'admin/page/goods/list', 1, 1, '商品管理', true);

INSERT INTO public.sys_module_category (id, module_category_name, sort_num) VALUES (3, '系统模块', 2);
INSERT INTO public.sys_module_category (id, module_category_name, sort_num) VALUES (1, '商品模块', 1);

INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (2, 'backend', '查询权限', '/admin/goods_category_rest/.*', '', '', 2);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (13, 'backend', '全类型测试', '/admin/all_type_rest/.*', null, '', 14);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (15, 'front_backend', '商品模块保存权限', '/admin/goods_rest/(update|save).*', '.security_edit_form_operate_save', 'show', 1);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (17, 'front', '商品模块添加按钮', '', '.security_list_query_operate_add_news', 'show', 1);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (18, 'front_backend', '商品模块(批量)删除权限', '/admin/goods_rest/(delete|batch_delete).*', '.security_list_table_operate_del,.security_list_query_operate_del_all', 'show', 1);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (19, 'backend', '商品模块查询权限', '/admin/goods_rest/(search|page|get).*', '', '', 1);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (22, 'front', '测试商品模块disabled', '', '.security_edit_form_param_goodsName,.security_edit_form_param_imgSrc,.security_edit_form_param_publishStatus,.security_edit_form_param_goodsStatus,.security_edit_form_param_price,.security_edit_form_param_shortBrief,.security_edit_form_param_goodsDescription,.security_edit_form_param_createTime,.security_edit_form_param_goodsCategoryId', 'show', 1);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (23, 'front_backend', '商品图集全部权限', '/admin/goods_img_rest/.*', '', 'show', 18);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (24, 'front_backend', '商品附件全部权限', '/admin/goods_file_rest/.*', '', 'show', 19);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (25, 'front_backend', '字典全部权限', '/admin/dict_rest/.*', '', 'show', 20);
INSERT INTO public.sys_permission (id, filter_platform, permission_name, backend_url_reg, front_dom, front_action, module_id) VALUES (21, 'backend', '所有系统表的所有权限(请勿删除)', '/admin/(rest_rmp|sys_[a-z_]+_rest)/.*', '', 'show', 17);

INSERT INTO public.sys_role (id, role_name) VALUES (1, '超级管理员');
INSERT INTO public.sys_role (id, role_name) VALUES (2, '测试账号');

INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (56, 1, 17, 21);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (57, 1, 2, 2);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (58, 1, 18, 23);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (59, 1, 19, 24);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (60, 1, 14, 13);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (61, 1, 11, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (62, 1, 7, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (63, 1, 5, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (64, 1, 8, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (65, 1, 4, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (66, 1, 20, 25);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (67, 1, 1, 15);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (68, 1, 1, 17);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (69, 1, 1, 18);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (70, 1, 1, 19);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (71, 1, 1, 22);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (49, 2, 18, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (50, 2, 19, null);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (51, 2, 1, 15);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (52, 2, 1, 17);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (53, 2, 1, 18);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (54, 2, 1, 19);
INSERT INTO public.sys_role_module_permission (id, role_id, module_id, permission_id) VALUES (55, 2, 1, 22);

ALTER TABLE ONLY public.dict
    ADD CONSTRAINT dict_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.goods_category
    ADD CONSTRAINT goods_category_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.goods_file
    ADD CONSTRAINT goods_file_pkey PRIMARY KEY (file_id);
ALTER TABLE ONLY public.goods_img
    ADD CONSTRAINT goods_img_pkey PRIMARY KEY (img_id);
ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_admin_user
    ADD CONSTRAINT sys_admin_user_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_global_permit_url
    ADD CONSTRAINT sys_global_permit_url_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_log_admin_operate
    ADD CONSTRAINT sys_log_admin_operate_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_module_category
    ADD CONSTRAINT sys_module_category_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_module
    ADD CONSTRAINT sys_module_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_permission
    ADD CONSTRAINT sys_permission_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_role_module_permission
    ADD CONSTRAINT sys_role_module_permission_pkey PRIMARY KEY (id);
ALTER TABLE ONLY public.sys_role
    ADD CONSTRAINT sys_role_pkey PRIMARY KEY (id);
CREATE INDEX dict_code_index ON public.dict USING btree (code);
