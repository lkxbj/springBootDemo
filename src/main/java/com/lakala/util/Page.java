package com.lakala.util;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: Page</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.util
 * <p>Description: 分页</p>
 * @date 2018/1/24 17:14
 */
public class Page {
    //一页显示的记录数
    private int numPerPage;
    //记录总数
    private int totalRows;
    //总页数
    private int totalPages;
    //当前页码
    private int currentPage;
    //起始行数
    private int startIndex;
    //结束行数
    private int lastIndex;
    //结果集存放List
    private List<Map<String, Object>> resultList;

    /**
     * <p>Title: </p>
     * <p>Description: 分页构造函数</p>
     * @param sql 包含筛选条件的sql，但不包含分页相关约束，如mysql的limit
     * @param currentPage 当前页
     * @param numPerPage 每页记录数
     * @param jdbcTemplate jdbcTemplate实例
     * ${tags} return
     */
    public Page(String sql,int currentPage,int numPerPage,JdbcTemplate jdbcTemplate){
        if(jdbcTemplate==null){
            throw new IllegalArgumentException("Page.jdbcTemplate is null");
        }else if(sql==null || sql.equals("")){
            throw new IllegalArgumentException("Page.sql is empty");
        }
        setNumPerPage(numPerPage);//设置每页显示记录数
        setCurrentPage(currentPage);//设置要显示的页数
        StringBuffer sb = new StringBuffer("select count(*) from (");//计算总记录数
        sb.append(sql);
        sb.append(") totalTable");
        setTotalRows(jdbcTemplate.queryForObject(sb.toString(),Integer.class));//总记录数
        setTotalPages();//计算总页数
        setStartIndex();//计算起始行数
        setLastIndex();//计算结束行数
        StringBuffer stringBuffer = new StringBuffer();//使用mysql时直接使用limits
        stringBuffer.append(sql);
        stringBuffer.append(" limit "+startIndex+","+lastIndex);
        setResultList(jdbcTemplate.queryForList(stringBuffer.toString()));//装入结果集
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    //计算总页数
    public void setTotalPages() {
        if(totalRows % numPerPage==0){
            this.totalPages = totalRows / numPerPage;
        }else{
            this.totalPages = (totalRows / numPerPage) + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex() {
        this.startIndex = (currentPage - 1) * numPerPage;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    //计算结束时的索引
    public void setLastIndex() {
        if(totalRows < numPerPage){
            this.lastIndex = totalRows;
        }else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){
            this.lastIndex = currentPage * numPerPage;
        }else if(totalRows % numPerPage != 0 && currentPage == totalPages){//最后一页
            this.lastIndex = totalRows;
        }
    }

    public List<Map<String, Object>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }
}
