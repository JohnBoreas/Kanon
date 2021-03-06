package com.kanon.admin.controller.system;

import com.kanon.admin.service.ISysConfigService;
import com.kanon.common.config.Global;
import com.kanon.common.core.controller.BaseController;
import com.kanon.common.utils.CookieUtils;
import com.kanon.common.utils.ServletUtils;
import com.kanon.common.utils.StringUtils;
import com.kanon.framework.system.entity.SystemMenu;
import com.kanon.framework.system.entity.SystemUser;
import com.kanon.framework.system.service.ISysMenuService;
import com.kanon.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页 业务处理
 */
@Controller
public class SysIndexController extends BaseController {

    private ISysMenuService menuService;

    private ISysConfigService configService;

    @Autowired
    public SysIndexController(ISysMenuService menuService, ISysConfigService configService) {
        this.menuService = menuService;
        this.configService = configService;
    }

    @RequestMapping("/admin")
    public String admin() {
        return "forward:/index";
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SystemUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SystemMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());

        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : Global.getMenuStyle();

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName())) {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        return webIndex;
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin() {
        return "skin";
    }

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response) {
        CookieUtils.setCookie(response, "nav-style", style);
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
