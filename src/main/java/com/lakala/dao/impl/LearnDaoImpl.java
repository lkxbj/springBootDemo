package com.lakala.dao.impl;

import com.lakala.dao.LearnDao;
import com.lakala.domain.LearnResouce;
import com.lakala.util.Page;
import com.lakala.util.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: ${FILE_NAME}</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.dao.impl
 * <p>Description: ${todo}</p>
 * @date 2018/1/24 16:27
 */
@Repository
public class LearnDaoImpl implements LearnDao{


    private JdbcTemplate jdbcTemplate;

    @Override
    public Page queryLearnResouceList(Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from learn_resource where 1=1");
        if(!StringUtil.isNull((String)params.get("author"))){
            sb.append(" and author like '%").append((String)params.get("author")).append("'%");
        }
        if(!StringUtil.isNull((String)params.get("title"))){
            sb.append(" and title like '%").append((String)params.get("title")).append("%'");
        }
        Page page = new Page(sb.toString(),Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }

    /**
     * <p>Title: ${enclosing_method}</p>
     * <p>Description: 通过Id查询</p>
     * ${tags} return LearnResouce
     */
    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        List<LearnResouce> list = jdbcTemplate.query("select * from learn_resource where id = ?", new Object[]{id}, new BeanPropertyRowMapper(LearnResouce.class));
        if(list!=null&&list.size()>0){
            LearnResouce learnResouce = list.get(0);
            return learnResouce;
        }else {
            return null;
        }
    }

    @Override
    public int add(LearnResouce learnResource) {
        return jdbcTemplate.update("insert into learn_resource(author, title,url) values(?, ?, ?)",learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl());
    }

    @Override
    public int update(LearnResouce learnResource) {
        return jdbcTemplate.update("update learn_resource set author=?,title=?,url=? where id = ?",new Object[]{learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl(),learnResource.getId()});
    }

    @Override
    public int deleteByIds(String ids) {
        return jdbcTemplate.update("delete from learn_resource where id in("+ids+")");
    }
}
