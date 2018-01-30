package com.lakala.service;

import com.lakala.domain.LearnResource;
import com.lakala.util.Page;

import java.util.Map;

/**
 * <p>Title: LearnService</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.service
 * <p>Description: 资料业务逻辑接口类</p>
 * @date 2018/1/24 16:04
 */
public interface LearnService {
    Page queryLearnResourceList(Map<String,Object> params);
    LearnResource queryLearnResourceById(Long id);
    int add(LearnResource learnResource);
    int update(LearnResource learnResource);
    int deleteByIds(String ids);
}
