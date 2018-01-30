package com.lakala.service.impl;

import com.lakala.dao.LearnDao;
import com.lakala.domain.LearnResource;
import com.lakala.service.LearnService;
import com.lakala.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>Title: ${FILE_NAME}</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.service.impl
 * <p>Description: ${todo}</p>
 * @date 2018/1/25 10:02
 */
@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnDao learnDao;

    @Override
    public Page queryLearnResourceList(Map<String, Object> params) {
        return this.learnDao.queryLearnResourceList(params);
    }

    @Override
    public LearnResource queryLearnResourceById(Long id) {
        return this.learnDao.queryLearnResourceById(id);
    }

    @Override
    public int add(LearnResource learnResource) {
        return this.learnDao.add(learnResource);
    }

    @Override
    public int update(LearnResource learnResource) {
        return this.learnDao.update(learnResource);
    }

    @Override
    public int deleteByIds(String ids) {
        return this.learnDao.deleteByIds(ids);
    }
}
