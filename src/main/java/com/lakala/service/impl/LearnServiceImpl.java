package com.lakala.service.impl;

import com.lakala.dao.LearnDao;
import com.lakala.domain.LearnResouce;
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
    public Page queryLearnResouceList(Map<String, Object> params) {
        return this.learnDao.queryLearnResouceList(params);
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnDao.queryLearnResouceById(id);
    }

    @Override
    public int add(LearnResouce learnResouce) {
        return this.learnDao.add(learnResouce);
    }

    @Override
    public int update(LearnResouce learnResouce) {
        return this.learnDao.update(learnResouce);
    }

    @Override
    public int deleteByIds(String ids) {
        return this.learnDao.deleteByIds(ids);
    }
}
