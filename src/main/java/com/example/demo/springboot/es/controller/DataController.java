package com.example.demo.springboot.es.controller;

import com.example.demo.springboot.es.entity.es.EsBlog;
import com.example.demo.springboot.es.entity.mysql.MysqlBlog;
import com.example.demo.springboot.es.repository.es.EsBlogRepository;
import com.example.demo.springboot.es.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class DataController {

    @Autowired
    private MysqlBlogRepository mysqlBlogRepository;

    @Autowired
    private EsBlogRepository esBlogRepository;


    @GetMapping("/blogs")
    public Object blog(){
        List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryAll();
        return mysqlBlogs;
    }

    @GetMapping("/blog/{id}")
    public Object getBlog(@PathVariable("id") Integer id){
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        MysqlBlog mysqlBlog = byId.get();


        return mysqlBlog;
    }


    @PostMapping("/search")
    public Object search(@RequestBody Param param){
        HashMap<String,Object> map = new HashMap<>();
        //统计耗时
        StopWatch watch = new StopWatch();
        watch.start();
        if ("mysql".equalsIgnoreCase(param.type)){
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryMysqlBlog(param.keyword);
            map.put("list",mysqlBlogs);
        }else if("es".equalsIgnoreCase(param.type)){
            /**
             *  POST /blog/_search
             * {
             *   "query": {
             *
             *       "bool": {
             *         "should": [
             *           {
             *             "match_phrase": {
             *               "title": "青"
             *             }
             *           },
             *           {
             *             "match_phrase": {
             *               "content": "青"
             *             }
             *           }
             *         ]
             *       }
             *   }
             * }
             *
             */

            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title",param.keyword));
            builder.should(QueryBuilders.matchPhraseQuery("content",param.keyword));
            String string = builder.toString();
            System.out.println(" ===>query dsl "+string);
            Iterable<EsBlog> search = esBlogRepository.search(builder);
            //强转成sql
            Page<EsBlog> list = (Page<EsBlog>) search;
            List<EsBlog> content = list.getContent();
            map.put("list",content);


        }else {
            return "i dont understand";
        }

        watch.stop();
        long totalTimeMillis = watch.getTotalTimeMillis();
        map.put("duration",totalTimeMillis);

        return map;
    }



    //如果是一个类被定义为static，那这个类只有一种可能就是静态内部类
    @Data
    public static class Param{
        // mysql es
        private String type;

        private String keyword;
    }

}
