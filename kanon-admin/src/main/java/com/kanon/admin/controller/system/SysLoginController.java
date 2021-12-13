package com.kanon.admin.controller.system;

import com.kanon.common.core.controller.BaseController;
import com.kanon.common.core.entity.AjaxResult;
import com.kanon.common.utils.ServletUtils;
import com.kanon.common.utils.StringUtils;
import com.kanon.framework.util.ShiroUtils;
import com.kanon.framework.system.entity.SystemUser;
import com.kanon.framework.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录验证
 */
@Controller
public class SysLoginController extends BaseController {

    ISysUserService userService;

    @Autowired
    public SysLoginController(ISysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        SystemUser user = ShiroUtils.getSysUser();
        if (user != null) {
            return "redirect:/index";
        }

        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";//admin模块自带的登录页面
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SystemUser user = ShiroUtils.getSysUser();
            ServletUtils.setCookieUid(user.getId().toString());
            Map<String, Object> returnMap = new HashMap<>();

            if (user != null) {
                user = userService.selectUserById(user.getId());
                Date lastDate = user.getLastLoginTime();
                if (lastDate == null) {
                    returnMap.put("todayLogin", true);//今天是第一次登录
                    return AjaxResult.success("登录成功!", returnMap);
                }
                if (!isToday(lastDate)) {
                    //不是今天的登录时间
                    returnMap.put("todayLogin", true);//今天是第一次登录
                    return AjaxResult.success("登录成功!", returnMap);
                } else {
                    returnMap.put("todayLogin", false);//今天是第n次登录
                }
            }


            return AjaxResult.success("登录成功!", returnMap);
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isToday(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
