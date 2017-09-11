/*博客表*/
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
`id`  bigint NOT NULL AUTO_INCREMENT ,
`title`  varchar(255) NOT NULL ,
`type_id` int(11),
`key_word` varchar(255),
`summary` varchar(400),
`content` text,
`right_type` int(11) DEFAULT 0,
`click` int(11) DEFAULT 0,
`reply` int(11) DEFAULT 0,
`create_time` datetime NOT NULL,
`last_update_time` datetime NOT NULL,
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
`last_update_time` datetime NOT NULL,
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
`type` int(11) NOT NULL,
`create_time` datetime NOT NULL
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

/*参数类型*/
DROP TABLE IF EXISTS `t_param_type`;
CREATE TABLE `t_param_type` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) NOT NULL ,
`code` varchar(255) ,
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
type_code varchar(255) NOT NULL,
summary varchar(255) ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;