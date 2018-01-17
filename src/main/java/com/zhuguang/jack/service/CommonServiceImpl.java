package com.zhuguang.jack.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zhuguang.jack.dao.CommonMapper;

@Service
public class CommonServiceImpl implements CommonService {
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    CacheService cs;
    
    @Autowired
    CommonMapper mapper;
    
    private static String CACHE_NAME = "user";
    
    public String queryUserByPsptId(Map param) {
        String cacheResult = cs.cacheResult(param.get("psptId").toString(),
                CACHE_NAME);
        if (cacheResult != null) {
            logger.info("=========query from redis===========");
            return cacheResult;
        }
        synchronized (logger) {
            cacheResult = cs.cacheResult(param.get("psptId").toString(),
                    CACHE_NAME);
            if (cacheResult != null) {
                logger.info("=========query from redis===========");
                return cacheResult;
            }
            logger.info("=========query from mysql===========");
            List<Map> lists = mapper.queryUserByPsptId(param);
            String resultStr = JSON.toJSONString(lists);
            cs.cachePut(param.get("psptId").toString(), resultStr, CACHE_NAME);
            return resultStr;
        }
    }
}
