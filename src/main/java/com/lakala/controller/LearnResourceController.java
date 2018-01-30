package com.lakala.controller;

import com.lakala.domain.LearnResource;
import com.lakala.domain.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: chapter1</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.controller
 * <p>Description: ${todo}</p>
 * @date 2018/1/19 10:52
 */
@Controller
public class LearnResourceController {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 Trace");
        logger.debug("日志输出 Debug");
        logger.info("日志输出 Info");
        logger.warn("日志输出 Warn");
        logger.error("日志输出 Error");
        Map<String,Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!username.equals("")&&password!=""){
            User user = new User(username,password);
            request.getSession().setAttribute("user",user);
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("/learn")
    public ModelAndView index(){
        List<LearnResource> learnResouceList = new ArrayList<>();
        LearnResource bean = new LearnResource("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnResouceList.add(bean);
        bean = new LearnResource("官方SpriongBoot例子","官方SpriongBoot例子","https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples");
        learnResouceList.add(bean);
        bean = new LearnResource("龙国学院","Spring Boot 教程系列学习","http://www.roncoo.com/article/detail/125488");
        learnResouceList.add(bean);
        bean =new LearnResource("CSDN——小当博客专栏","Sping Boot学习","http://blog.csdn.net/column/details/spring-boot.html");
        learnResouceList.add(bean);
        bean =new LearnResource("梁桂钊的博客","Spring Boot 揭秘与实战","http://blog.csdn.net/column/details/spring-boot.html");
        learnResouceList.add(bean);
        bean =new LearnResource("林祥纤博客系列","从零开始学Spring Boot ","http://412887952-qq-com.iteye.com/category/356333");
        learnResouceList.add(bean);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("learnResourceList",learnResouceList);
        return modelAndView;

    }
}
