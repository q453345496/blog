/*博客表*/
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`title`  varchar(128) NOT NULL ,
`type_id` int(11),
`key_word` varchar(255),
`summary` varchar(400),
`thumb` varchar(255),
`content` text,
`click` int(11) DEFAULT 0,
`reply` int(11) DEFAULT 0,
`status` int(11) DEFAULT 0,
`source_url` varchar(255),
`author_id` int(11),
`author_name` varchar(255),
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*博客类型*/
DROP TABLE IF EXISTS `t_blog_type`;
CREATE TABLE `t_blog_type` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name` varchar(32) NOT NULL ,
`code` varchar(32) NOT NULL ,
`img_path` varchar(255) ,
`rank` int(11),
`parent_id` int(11) NOT NULL,
`is_parent` tinyint(1) DEFAULT false,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
INSERT INTO `t_blog_type` (`id`, `name`, `code`, `img_path`, `rank`, `parent_id`, `is_parent`, `create_time`, `modify_time`) 
VALUES ('1', '根分类', 'root', NULL, '1', '-1', '0', NOW(), NOW());

/*收入与支出分类*/
DROP TABLE IF EXISTS `t_journal_category`;
CREATE TABLE `t_journal_category` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) NOT NULL ,
`rank` int(11),
`parent_id` int(11) NOT NULL,
`is_parent` tinyint(1) DEFAULT false,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
INSERT INTO `t_journal_category` (`id`, `name`, `parent_id`, `rank`, `is_parent`, `create_time`, `modify_time`) 
VALUES ('1', '根分类', '-1', '1', '0', NOW(), NOW());

/*参数类型*/
DROP TABLE IF EXISTS `t_param_type`;
CREATE TABLE `t_param_type` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) NOT NULL ,
`code` varchar(32) ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*参数*/
DROP TABLE IF EXISTS `t_param`;
CREATE TABLE `t_param` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`key`  varchar(32) NOT NULL ,
`value` varchar(255) ,
`type_code` varchar(255) NOT NULL,
`summary` varchar(255) ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*专题*/
DROP TABLE IF EXISTS `t_special_topic`;
CREATE TABLE `t_special_topic` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) NOT NULL ,
`click` int(11) DEFAULT 0,
`relate_count` int(11) DEFAULT 0,
`rank` int(11),
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*专题关联博客*/
DROP TABLE IF EXISTS `t_special_topic_resource`;
CREATE TABLE `t_special_topic_resource` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`topic_id`  int(11) NOT NULL ,
`blog_id` int(11) NOT NULL ,
`rank` int(11) NOT NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*栏目*/
DROP TABLE IF EXISTS `t_column`;
CREATE TABLE `t_column` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(32) NOT NULL ,
`code`  varchar(32) NOT NULL ,
`parent_id` int(11) NOT NULL,
`status` int(11) NOT NULL,
`rank` int(11),
`is_parent` tinyint(1) DEFAULT false,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
INSERT INTO `t_column` (`id`, `name`, `code`, `parent_id`, `status`, `rank`, `is_parent`, `create_time`, `modify_time`) 
VALUES ('1', '根栏目', 'root', '-1', '1', '1', '1', NOW(), NOW());
INSERT INTO `t_column` (`id`, `name`, `code`, `parent_id`, `status`, `rank`, `is_parent`, `create_time`, `modify_time`) 
VALUES ('2', '今日推荐', 'today_recommend', '1', '1', '1', '0', NOW(), NOW());

/*栏目关联分类*/
DROP TABLE IF EXISTS `t_column_resource`;
CREATE TABLE `t_column_resource` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`column_id`  int(11) NOT NULL ,
`type_id` int(11) NOT NULL ,
`rank` int(11) NOT NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
/*栏目关联博客*/
DROP TABLE IF EXISTS `t_column_blog_relate`;
CREATE TABLE `t_column_blog_relate` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`column_id`  int(11) NOT NULL ,
`blog_id` int(11) NOT NULL ,
`rank` int(11) NOT NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*文件表*/
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`name` varchar(255) NOT NULL ,
`source_url` varchar(500) NULL ,
`path` varchar(500) NOT NULL ,
`height` int(11) NULL ,
`width` int(11) NULL ,
`size` int(11) NOT NULL ,
`type` varchar(32) NOT NULL ,
`biz_id` varchar(64) NOT NULL ,
`biz_type` varchar(32) NOT NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*网页源*/
DROP TABLE IF EXISTS `t_web_source`;
CREATE TABLE `t_web_source` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`name` varchar(255) NOT NULL ,
`code` varchar(32) NOT NULL ,
`title_pattern` varchar(255) NULL ,
`content_pattern` varchar(255) NULL ,
`label_pattern` varchar(255) NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*代理池*/
DROP TABLE IF EXISTS `t_proxy_server`;
CREATE TABLE `t_proxy_server` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`ip` varchar(32) NOT NULL ,
`port` int(11) NOT NULL,
`protocol` int(11) NOT NULL,
`type` int(11) NOT NULL,
`state` int(11) NOT NULL,
`fail_times` int(11) DEFAULT 0 ,
`success_times` int(11) DEFAULT 0 ,
`score` double(10,3) DEFAULT 0 ,
`respone_time` int(11) DEFAULT 0 ,
`last_check_time` datetime,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `index_ip` (`ip`) USING HASH
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
