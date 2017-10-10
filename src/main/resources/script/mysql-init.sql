DROP TABLE IF EXISTS `test_demo`;
create table test_demo(
	id bigint unsigned primary key ,
	demo_str varchar(100) default "",
	demo_date datetime default null,
	demo_number int
);

-- ----------------------------
-- Table structure for act_de_model
-- ----------------------------
DROP TABLE IF EXISTS `act_de_model`;
CREATE TABLE `act_de_model` (
  `id` varchar(255) NOT NULL,
  `name` varchar(400) NOT NULL,
  `model_key` varchar(400) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `model_comment` varchar(4000) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `model_editor_json` longtext,
  `thumbnail` longblob,
  `model_type` int(11) DEFAULT NULL,
  `deployment_id` varchar(64) DEFAULT "",
  `process_def_id` varchar(64) DEFAULT "",
  PRIMARY KEY (`id`),
  KEY `idx_proc_mod_created` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for act_de_model_relation
-- ----------------------------
DROP TABLE IF EXISTS `act_de_model_relation`;
CREATE TABLE `act_de_model_relation` (
  `id` varchar(255) NOT NULL,
  `parent_model_id` varchar(255) DEFAULT NULL,
  `model_id` varchar(255) DEFAULT NULL,
  `relation_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_relation_parent` (`parent_model_id`),
  KEY `fk_relation_child` (`model_id`),
  CONSTRAINT `fk_relation_child` FOREIGN KEY (`model_id`) REFERENCES `act_de_model` (`id`),
  CONSTRAINT `fk_relation_parent` FOREIGN KEY (`parent_model_id`) REFERENCES `act_de_model` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bpm_node_set`;
create table bpm_node_set (
	id bigint(20) PRIMARY KEY,
	act_def_id varchar(127) DEFAULT '',
	node_id varchar(64) DEFAULT '',
	node_name varchar(64) DEFAULT '',
	form_type smallint(6) DEFAULT '1' COMMENT '1 在线表单 2 url表单 ',
	form_url varchar(255) DEFAULT '',
    jump_type varchar(32) DEFAULT '1' COMMENT '1 正常跳转 2 自由跳转',
	before_handle varchar(100) DEFAULT '',
	after_handle varchar(100) DEFAULT '',
	status smallint(6) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点设置表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL PRIMARY KEY,
  `full_name` varchar(127) DEFAULT NULL COMMENT '姓名',
  `account` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `isexpired` smallint(6) DEFAULT NULL COMMENT '是否过期',
  `islock` smallint(6) DEFAULT '1' COMMENT '是否锁定(-1 是,1 否)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话',
  `sex` varchar(2) DEFAULT NULL COMMENT '1=男 2=女',
  `picture` varchar(300) DEFAULT NULL,
  `sequence` decimal(18,0) DEFAULT NULL COMMENT '排序字段',
	`status` smallint(6) DEFAULT '1' COMMENT '-1 删除, 1正常'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';