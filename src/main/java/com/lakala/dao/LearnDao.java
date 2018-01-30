package com.lakala.dao;

import com.lakala.domain.LearnResource;
import com.lakala.util.Page;

import java.util.Map;

/**
 * <p>Title: LearnDao</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.dao
 * <p>Description: 接口类</p>
 * @date 2018/1/24 16:26
 */
public interface LearnDao {
    Page queryLearnResourceList(Map<String,Object> params);
    LearnResource queryLearnResourceById(Long id);
    int add(LearnResource learnResource);
    int update(LearnResource learnResource);
    int deleteByIds(String ids);
}
