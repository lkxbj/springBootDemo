package com.lakala.domain;

/**
 * <p>Title: chapter1</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.domain
 * <p>Description: ${todo}</p>
 * @date 2018/1/19 10:57
 */

// CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
// USE `spring`;
// DROP TABLE IF EXISTS `learn_resource`;
//
// CREATE TABLE `learn_resource` (
// `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
// `author` varchar(20) DEFAULT NULL COMMENT '作者',
// `title` varchar(100) DEFAULT NULL COMMENT '描述',
// `url` varchar(100) DEFAULT NULL COMMENT '地址链接',
// PRIMARY KEY (`id`)
// ) ENGINE=MyISAM AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8;
//
// insert into `learn_resource`(`id`,`author`,`title`,`url`) values (999,'官方SpriongBoot例子','官方SpriongBoot例子','https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples');
// insert into `learn_resource`(`id`,`author`,`title`,`url`) values (1000,'龙果学院','Spring Boot 教程系列学习','http://www.roncoo.com/article/detail/124661');
// insert into `learn_resource`(`id`,`author`,`title`,`url`) values (1001,'嘟嘟MD独立博客','Spring Boot干货系列','http://tengj.top/');
// insert into `learn_resource`(`id`,`author`,`title`,`url`) values (1002,'后端编程嘟','Spring Boot视频教程','http://www.toutiao.com/m1559096720023553/');

public class LearnResouce {

    private Long id;

    private String author;

    private String title;

    private String url;

    public LearnResouce(){}

    public LearnResouce(String author,String title,String url){
        this.author = author;
        this.title = title;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
