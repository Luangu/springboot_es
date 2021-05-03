package com.example.demo.springboot.es.controller;

import com.example.demo.springboot.es.entity.es.EsBlog;
import com.example.demo.springboot.es.entity.mysql.MysqlBlog;
import com.example.demo.springboot.es.repository.es.EsBlogRepository;
import com.example.demo.springboot.es.repository.mysql.MysqlBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;

@Controller
public class IndexController {

    // 不应该写到控制器里，应该写到实现类里
    @Autowired
    private MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;

    @RequestMapping("/")
    public String index(){
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        System.out.println(all.size());
        return "index.html";
    }


    @RequestMapping("/es")
    public void esTest(){
        Iterable<EsBlog> all = esBlogRepository.findAll();
        Iterator<EsBlog> iterator = all.iterator();
        EsBlog next = iterator.next();
        System.out.println("111111111111");
        System.out.println(next);
    }


}
