package com.lakala.controller;

import com.alibaba.fastjson.JSONObject;
import com.lakala.domain.LearnResource;
import com.lakala.service.LearnService;
import com.lakala.util.Page;
import com.lakala.util.ServletUtil;
import com.lakala.util.StringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: LearnController</p>
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.controller
 * <p>Description: ${todo}</p>
 * @date 2018/1/24 15:02
 */

@Controller
@RequestMapping("/learn")
public class LearnController {

    @Autowired
    private LearnService learnService;

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String learn(){
        return "learn-resource";
    }

    /**
     * <p>Title: queryLearnList</p>
     * <p>Description: 查询</p>
     * ${tags} return
     */
    @RequestMapping(value = "/queryLearnList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryLearnList(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");// 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows");// 取得每页显示行数，,注意这是jqgrid自身的参数
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",rows);
        map.put("author",author);
        map.put("title",title);
        Page page1 = learnService.queryLearnResourceList(map);
        List<Map<String,Object>> list = page1.getResultList();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",list);
        jsonObject.put("total",page1.getTotalPages());
        jsonObject.put("records",page1.getTotalRows());
        ServletUtil.createSuccessResponse(200,jsonObject,response);
    }

    /**
     * <p>Title: addLearn</p>
     * <p>Description: 添加</p>
     * ${tags} return
     */
    public void addLearn(HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(StringUtil.isNull(author)){
            jsonObject.put("message","作者不能为空！");
            jsonObject.put("flag",false);
            ServletUtil.createSuccessResponse(200,jsonObject,response);
            return;
        }
        if(StringUtil.isNull(title)){
            jsonObject.put("message","书名不能为空");
            jsonObject.put("flag",false);
            ServletUtil.createSuccessResponse(200,jsonObject,response);
            return;
        }
        if(StringUtil.isNull(url)){
            jsonObject.put("message","地址不能为空");
            jsonObject.put("flag",false);
            ServletUtil.createSuccessResponse(200,jsonObject,response);
            System.out.println();
            return;
        }
        LearnResource learnResource = new LearnResource();
        learnResource.setAuthor(author);
        learnResource.setTitle(title);
        learnResource.setUrl(url);
        int index = learnService.add(learnResource);
        if(index>0){
            jsonObject.put("message","添加成功");
            jsonObject.put("flag",true);
        }else{
            jsonObject.put("message","添加失败");
            jsonObject.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,jsonObject,response);
    }

    /**
     * <p>Title: updateLearn</p>
     * <p>Description: 修改</p>
     * ${tags} return
     */
    public void updateLearn(HttpServletRequest request,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        String id = request.getParameter("id");
        LearnResource learnResource = learnService.queryLearnResourceById(Long.valueOf(id));
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(StringUtil.isNull(author)){
            jo.put("message","作者不能为空");
            jo.put("flag",false);
            ServletUtil.createSuccessResponse(200,jo,response);
            return;
        }
        if(StringUtil.isNull(title)){
            jo.put("message","标题不能为空");
            jo.put("flag",false);
            ServletUtil.createSuccessResponse(200,jo,response);
            return;
        }
        if(StringUtil.isNull(url)){
            jo.put("message","地址不能为空");
            jo.put("flag",false);
            ServletUtil.createSuccessResponse(200,jo,response);
            return;
        }
        learnResource.setAuthor(author);
        learnResource.setTitle(title);
        learnResource.setUrl(url);
        int index = learnService.update(learnResource);
        if(index>0){
            jo.put("message","信息修改成功");
            jo.put("flag",true);
        }else{
            jo.put("message","信息修改失败");
            jo.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,jo,response);
    }

    /**
     * <p>Title: deleteLearn</p>
     * <p>Description: 删除</p>
     * ${tags} return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteLearn(HttpServletRequest request,HttpServletResponse response){
        String ids = request.getParameter("ids");
        JSONObject object = new JSONObject();
        int index = learnService.deleteByIds(ids);
        if(index>0){
            object.put("message","删除成功");
            object.put("flag",true);
        }else{
            object.put("message","删除失败");
            object.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,object,response);
    }
}
