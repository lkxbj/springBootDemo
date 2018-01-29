package com.lakala.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: ${FILE_NAME}</p>
 *
 * @author lixiaojie lixiaojie@lakala.com
 * @version V1.0
 * @Package com.lakala.util
 * <p>Description: ${todo}</p>
 * @date 2018/1/25 10:19
 */
public class ServletUtil {

    private static String hostName = "";//服务器标识

    private static final String RESPONSE_CONTENTTYPE = "application/json";//响应的ContentType内容

    private static final String RESPONSE_CHARACTERENCODING = "utf-8"; //响应编码

    private static final String BIZ_NAME = "";//业务名称的缩写

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ServletUtil.class);

    static{
        try {
            InetAddress netAddress = InetAddress.getLocalHost();
            hostName = netAddress.getHostName();
        } catch (UnknownHostException e) {
            log.error("netAddress.getHostName failed", e);
        }
    }

    /**
     * <p>Title: ${enclosing_method}</p>
     * <p>Description: 生成成功报文</p>
     * ${tags} return
     */
    public static String createSuccessResponse(Integer httpCode, Object result, HttpServletResponse response){
        return createSuccessResponse(httpCode, result, SerializerFeature.WriteMapNullValue,null,response);
    }

    public static String createSuccessResponse(Integer httpCode,String message,Object result,HttpServletResponse response){
        return createSuccessResponse(httpCode,message,result, SerializerFeature.WriteMapNullValue,null,response);
    }

    public static String createSuccessResponse(Integer httpCode, Object result, SerializeFilter filter, HttpServletResponse response){

        return createSuccessResponse(httpCode, result, SerializerFeature.PrettyFormat,filter,response);

    }
    public static String createSuccessResponse(Integer httpCode, Object result, SerializerFeature serializerFeature, HttpServletResponse response){

        return createSuccessResponse(httpCode, result,serializerFeature,null,response);

    }

    public static String createSuccessResponse(Integer httpCode, Object result, SerializerFeature serializerFeature, SerializeFilter filter, HttpServletResponse response){
        PrintWriter printWriter = null;
        String jsonString = "";
        try {
            response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
            response.setContentType(RESPONSE_CONTENTTYPE);
            response.setStatus(httpCode);
            printWriter = response.getWriter();
            if(null != result){
                if(null!=filter){
                    jsonString = JSONObject.toJSONString(result,filter,serializerFeature);
                }else{
//					jsonString = JSONObject.toJSONString(result, serializerFeature);
                    jsonString = JSONObject.toJSONStringWithDateFormat(result,"yyyy-MM-dd HH:ss:mm",serializerFeature);
                }
                printWriter.write(jsonString);
            }
            printWriter.flush();

        } catch (Exception e) {
            log.error("createResponse failed", e);
        } finally {
            if(null!=printWriter)printWriter.close();
        }
        return jsonString;
    }

    public static String createSuccessResponse(Integer httpCode, String message, Object result, SerializerFeature serializerFeature, SerializeFilter filter, HttpServletResponse response){
        PrintWriter printWriter = null;
        String jsonString = "";
        try {

            response.setCharacterEncoding(RESPONSE_CHARACTERENCODING);
            response.setContentType(RESPONSE_CONTENTTYPE);
            response.setStatus(httpCode);
            printWriter = response.getWriter();
            SerializeConfig config = new SerializeConfig();
            config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
            Map<String, Object> map = new HashMap<>();
            if(null != result){
                map.put("res_code", httpCode);
                map.put("message", message);
                map.put("data",result);
                if(null!=filter){
                    jsonString = JSONObject.toJSONString(map,filter,serializerFeature);
                }else{
//					jsonString = JSONObject.toJSONString(map,config,serializerFeature);
                    jsonString = JSONObject.toJSONStringWithDateFormat(map,"yyyy-MM-dd");

                }
                printWriter.write(jsonString);
            }
            printWriter.flush();

        } catch (Exception e) {
            log.error("createResponse failed", e);
        } finally {
            if(null!=printWriter)printWriter.close();
        }
        return jsonString;
    }
}
