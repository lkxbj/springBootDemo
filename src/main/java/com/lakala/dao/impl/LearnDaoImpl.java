package com.lakala.dao.impl;

import com.lakala.dao.LearnDao;
import com.lakala.domain.LearnResource;
import com.lakala.util.Page;
import com.lakala.util.StringUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: LearnDaoImpl</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.dao.impl
 * <p>Description: 资料信息接口实现</p>
 * @date 2018/1/24 16:27
 */
@Repository
public class LearnDaoImpl implements LearnDao{


    private JdbcTemplate jdbcTemplate;

    @Override
    public Page queryLearnResourceList(Map<String, Object> params) {
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
     * <p>Title: queryLearnResourceById</p>
     * <p>Description: 通过Id查询</p>
     * ${tags} return LearnResouce
     */
    @Override
    public LearnResource queryLearnResourceById(Long id) {
        List<LearnResource> list = jdbcTemplate.query("select * from learn_resource where id = ?", new Object[]{id}, new BeanPropertyRowMapper(LearnResource.class));
        if(list!=null&&list.size()>0){
            LearnResource learnResource = list.get(0);
            return learnResource;
        }else {
            return null;
        }
    }

    /**
     * <p>Title: add</p>
     * <p>Description: 添加</p>
     * ${tags} return int
     */
    @Override
    public int add(LearnResource learnResource) {
        return jdbcTemplate.update("insert into learn_resource(author, title,url) values(?, ?, ?)",learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl());
    }

    /**
     * <p>Title: update</p>
     * <p>Description: 更新</p>
     * ${tags} return int
     */
    @Override
    public int update(LearnResource learnResource) {
        return jdbcTemplate.update("update learn_resource set author=?,title=?,url=? where id = ?",new Object[]{learnResource.getAuthor(),learnResource.getTitle(),learnResource.getUrl(),learnResource.getId()});
    }

    /**
     * <p>Title: deleteByIds</p>
     * <p>Description: 删除</p>
     * ${tags} return int
     */
    @Override
    public int deleteByIds(String ids) {
        return jdbcTemplate.update("delete from learn_resource where id in("+ids+")");
    }
}
