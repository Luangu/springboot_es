package com.example.demo.springboot.es;

import com.example.demo.springboot.es.entity.es.EsBlog;
import com.example.demo.springboot.es.repository.es.EsBlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SpringbootEsApplicationTests {


    @Autowired
    private EsBlogRepository esBlogRepository;


    @Test
    void contextLoads() {
    }


    @Test
    public void testEs(){
        System.out.println("==========>");
        Iterable<EsBlog> all = esBlogRepository.findAll();
        Iterator<EsBlog> iterator = all.iterator();
        EsBlog next = iterator.next();
        System.out.println("111111111111");
        System.out.println(next);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
