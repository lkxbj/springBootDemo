package com.lakala.dao;

import com.lakala.domain.LearnResouce;
import com.lakala.util.Page;

import java.util.Map;

/**
 * <p>Title: ${FILE_NAME}</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.dao
 * <p>Description: ${todo}</p>
 * @date 2018/1/24 16:26
 */
public interface LearnDao {
    Page queryLearnResouceList(Map<String,Object> params);
    LearnResouce queryLearnResouceById(Long id);
    int add(LearnResouce learnResouce);
    int update(LearnResouce learnResouce);
    int deleteByIds(String ids);
}
