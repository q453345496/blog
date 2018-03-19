/*博客表*/
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) NOT NULL ,
`type_id` int(11),
`key_word` varchar(255),
`summary` varchar(400),
`content` text,
`right_type` int(11) DEFAULT 0,
`click` int(11) DEFAULT 0,
`reply` int(11) DEFAULT 0,
`status` int(11) DEFAULT 0,
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
`name`  varchar(255) NOT NULL ,
`rank` int(11),
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*收入与支出分类*/
DROP TABLE IF EXISTS `t_journal_category`;
CREATE TABLE `t_journal_category` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NOT NULL ,
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
`name`  varchar(255) NOT NULL ,
`code` varchar(255) ,
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
`key`  varchar(255) NOT NULL ,
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
`name`  varchar(255) NOT NULL ,
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
`name`  varchar(255) NOT NULL ,
`code`  varchar(255) NOT NULL ,
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
VALUES ('1', '根栏目', 'root', '-1', '1', '1', '0', NOW(), NOW());

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

/*文件表*/
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
`id` int(11) NOT NULL AUTO_INCREMENT ,
`name` varchar(255) NOT NULL ,
`source_url` varchar(500) NULL ,
`path` varchar(500) NOT NULL ,
`height` int(11) NULL ,
`width` int(11) NULL ,
`size` bigint(20) NOT NULL ,
`type` varchar(255) NOT NULL ,
`biz_id` varchar(255) NOT NULL ,
`biz_type` varchar(255) NOT NULL ,
`create_time` datetime NOT NULL,
`modify_time` datetime NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;
