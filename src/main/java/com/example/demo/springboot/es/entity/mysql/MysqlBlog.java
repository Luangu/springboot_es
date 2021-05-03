package com.example.demo.springboot.es.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 *
 * CREATE TABLE `es_blog`.`Untitled`  (
 *   `id` int(20) NOT NULL AUTO_INCREMENT,
 *   `title` varchar(60) NULL DEFAULT '',
 *   `author` varchar(60) NULL DEFAULT '',
 *   `content` mediumtext NULL,
 *   `create_time` datetime NULL,
 *   `update_time` datetime NULL,
 *   PRIMARY KEY (`id`)
 * );
 */
@Entity
@Data
@Table(name = "t_blog")
public class MysqlBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String author;

    @Column(columnDefinition = "mediumtext")
    private String content;

    private Date createTime;

    private Date updateTime;


}
