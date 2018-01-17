package com.zhuguang.jack.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhuguang.jack.dao.CommonMapper;
import com.zhuguang.jack.service.CommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/spring/spring-dispatcher.xml")
public class MyTest {
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    CommonMapper mapper;
    
    @Autowired
    CommonService service;
    
    private static final int threadNum = 3000;
    
    CountDownLatch cdl = new CountDownLatch(threadNum);
    
    @Autowired
    private CacheManager cm;
    
    @Test
    public void test3() {
        logger.info(cm.getCache("user").getName());
        cm.getCache("user").put("ert", "jack");
        Collection<String> cacheNames = cm.getCacheNames();
        for (String cacheName : cacheNames) {
            logger.info(cacheName);
        }
        logger.info(cm.getCache("user").get("ert").get().toString());
    }
    
    @Test
    public void test1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("psptId", "124234");
        logger.info(Thread.currentThread().getName() + "================>"
                + mapper.queryUserByPsptId(map));
    }
    
    @Test
    public void test2() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        cdl.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("psptId", "124234");
                    logger.info(Thread.currentThread().getName()
                            + "================>"
                            + service.queryUserByPsptId(map));
                }
            }).start();
            cdl.countDown();
        }
        
        try {
            Thread.currentThread().join();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
