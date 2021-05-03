package com.example.demo.springboot.es.repository.es;

import com.example.demo.springboot.es.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepository extends ElasticsearchRepository<EsBlog,Integer> {

}
