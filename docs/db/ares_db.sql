CREATE DATABASE IF NOT EXISTS `ares` DEFAULT CHARACTER SET utf8 collate utf8_general_ci;
USE `ares`;

CREATE TABLE  `ares_cache_template`(
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `key` VARCHAR(255) NOT NULL COMMENT 'key模板',
    `desc` VARCHAR(500) NOT NULL COMMENT '描述信息',
    `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    INDEX idx_key(`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'key模板表';
