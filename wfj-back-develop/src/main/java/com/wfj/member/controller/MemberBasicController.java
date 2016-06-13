package com.wfj.member.controller;

import com.wangfj.order.utils.CommonProperties;
import com.wangfj.order.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员基本信息展示
 * Created by shenjp on 2016/6/6.
 */
@Controller
@RequestMapping("/memBasic")
public class MemberBasicController {
    private static org.slf4j.Logger log =  LoggerFactory.getLogger(MemberBasicController.class);

    /**
     * 展示会员基本信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/getMemBasicInfo", method = { RequestMethod.POST, RequestMethod.GET })
    public String getMemBasicInfo(HttpServletRequest request,
                                      HttpServletResponse response) {
        log.info("======== getMemBasicInfo in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/getMemBasicInfo.do";
        String jsonString="";
        //获取每页显示多少条数据
        Integer pageSize = 0;
        //获取当前页
        Integer currPage =Integer.parseInt(request.getParameter("page"));
        pageSize = request.getParameter("pageSize") == null ? null
                : Integer.parseInt(request.getParameter("pageSize"));
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("currPage", String.valueOf(currPage));
        paraMap.put("pageSize", String.valueOf(pageSize));
        paraMap.put("cid", request.getParameter("cid"));
        paraMap.put("belongStore", request.getParameter("belongStore"));
        paraMap.put("mobile", request.getParameter("mobile"));
        paraMap.put("idType",request.getParameter("idType"));
        paraMap.put("identityNo",request.getParameter("identityNo"));
        paraMap.put("email",request.getParameter("email"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== getMemBasicInfo url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== getMemBasicInfo url "+url+ method+"  =========");
            String resJson = HttpUtil.HttpPost(url, method, paraMap);
            JSONObject resJsonObj= JSONObject.fromObject(resJson);
            String code=resJsonObj.getString("code");
            if(code==null||!"0".equals(code)){
                jsonString="{success :false}";
            }else{
                jsonString=resJsonObj.getJSONObject("object").toString();
            }
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }

    /**
     * 将支付密码重置的验证码发送到用户手机
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/sendPayCodeToPhone", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendPayCodeToPhone(HttpServletRequest request,
                                      HttpServletResponse response) {
        log.info("======== sendPayCodeToPhone in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/sendPayCodeToPhone.do";
        String jsonString="";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("mobile", request.getParameter("pay_mobile"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== sendPayCodeToPhone url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== sendPayCodeToPhone url "+url+ method+"  =========");
            jsonString = HttpUtil.HttpPost(url, method, paraMap);
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }

    /**
     * 将重置后的支付密码发送到用户手机
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/sendPayPwdToPhone", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendPayPwdToPhone(HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("======== sendPayPwdToPhone in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/sendPayPwdToPhone.do";
        String jsonString="";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("mobile", request.getParameter("pay_mobile"));
        paraMap.put("cid",request.getParameter("cid"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== sendPayPwdToPhone url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== sendPayPwdToPhone url "+url+ method+"  =========");
            jsonString = HttpUtil.HttpPost(url, method, paraMap);
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }

    /**
     * 将重置登录密码的验证码发送至邮箱
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/sendCodeToEmail", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendCodeToEmail(HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("======== sendCodeToEmail in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/sendCodeToEmail.do";
        String jsonString="";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("email", request.getParameter("email"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== sendCodeToEmail url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== sendCodeToEmail url "+url+ method+"  =========");
            jsonString = HttpUtil.HttpPost(url, method, paraMap);
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }

    /**
     * 将登录密码重置的验证码发送到用户手机
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/sendLoginCodeToPhone", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendLoginCodeToPhone(HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("======== sendLoginCodeToPhone in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/sendPayCodeToPhone.do";
        String jsonString="";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("mobile", request.getParameter("pay_mobile"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== sendLoginCodeToPhone url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== sendLoginCodeToPhone url "+url+ method+"  =========");
            jsonString = HttpUtil.HttpPost(url, method, paraMap);
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }

    /**
     * 将重置后的登录密码发送至用户手机或邮箱
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/sendLoginPwd", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendLoginPwd(HttpServletRequest request,
                                    HttpServletResponse response) {
        log.info("======== sendLoginPwd in  =========");
        response.setCharacterEncoding("utf-8");
        String method = "/memBasic/sendLoginPwd.do";
        String jsonString="";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("mobile", request.getParameter("mobile"));
        paraMap.put("email",request.getParameter("email"));
        paraMap.put("loginStatus",request.getParameter("loginStatus"));
        paraMap.put("cid",request.getParameter("cid"));
        try {
            String url = CommonProperties.get("member_ops_url");
            log.info("======== sendLoginPwd url " + url + "  =========");
            System.err.println("============== member_ops_url:" + url);
            System.err.println("=============method:"+method);
            System.err.println("======== sendLoginPwd url "+url+ method+"  =========");
            jsonString = HttpUtil.HttpPost(url, method, paraMap);
        } catch (Exception e) {
            jsonString = "{success :false}";
        }
        return jsonString;
    }
}