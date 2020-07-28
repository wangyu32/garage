package com.wangyu.controller;

import com.wangyu.common.BaseResponse;
import com.wangyu.common.Code;
import com.wangyu.constant.MessageConstants;
import com.wangyu.constant.SessionAttributeConstants;
import com.wangyu.entity.UserLoginBean;
import com.wangyu.entity.parameter.UserLoginParameter;
import com.wangyu.response.UserLoginResponse;
import com.wangyu.service.ISysUserService;
import com.wangyu.util.Md5Util;
import com.wangyu.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * 登录并进入首页的控制层
 * @author 	wangyu
 *
 */
@Slf4j
@Controller
public class HomeController extends BaseController{
	
	 //保存cookie时的cookieName
    private final static String cookieDomainName = "garage";

    //设置cookie有效期是两个星期
    private final static int cookieMaxAge = 60 * 60 * 24 * 7 * 2;

    @Autowired
    private ISysUserService sysUserService;

//    @Autowired
//	@Qualifier("reportStatisticsService")
//	private IReportStatisticsService reportService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces="text/html;charset=utf-8")
	public String root(){
		return home();
	}	
	
	/**
	 * 主页面
	 * @return
	 */
	@RequestMapping(value = "/home", method=RequestMethod.GET, produces="text/html;charset=utf-8")
	public String home(){
		UserLoginResponse user = getCurrentUser();
		if(user == null){
			return "redirect:login";
		}
		request.setAttribute("menu", user.getMenus());
		return "/home";
	}
	
	/**
	 * 用户登录
	 * @return 登录成功返回home,登录失败返回login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces="text/html;charset=utf-8")
	public String login() {
		UserLoginResponse user = getCurrentUser();
		if(user == null){
			return "/login";
		}
		return home();
	}

	/**
	 * 返回登录页，并提示错误信息
	 * @param errorMessage - 错误信息
	 * @return 登录地址
	 */
	private String reLogin(String errorMessage){
		return toJson(renderError(Code.FAIL, errorMessage));
	}

	/**
	 * 主页面
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userlogin", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	public String userlogin(UserLoginBean userLoginBean){
		String username = userLoginBean.getUsername();//用户名
		String password =  userLoginBean.getPassword();//密码
		String checkcode = userLoginBean.getCheckcode();//验证码
		String recordAccount= userLoginBean.getRecordAccount();//是否记住密码
		request.setAttribute("recordAccount", recordAccount);
		//用户名不能为空
		if(StringUtil.isBlank(username)){
			return reLogin(MessageConstants.LOGNAME_CAN_NOT_BE_NULL);
		}

		request.setAttribute("username", username);

		//密码不能为空
		if(StringUtil.isBlank(password)){
			return reLogin(MessageConstants.PASSWORD_CAN_NOT_BE_NULL);
		}

		request.setAttribute("password", password);

		//验证码不能为空
		if(StringUtil.isBlank(checkcode)){
			return reLogin(MessageConstants.CHECKCODE_CAN_NOT_BE_NULL);
		}

		//判断验证码是否正确
		String checkCodeInSesssion = (String)request.getSession().getAttribute(SessionAttributeConstants.LOGINCHECKCODE);
		if(!checkcode.equals(checkCodeInSesssion)){
//			return reLogin(MessageConstants.CHECKCODE_ERROR);
		}

		//密码MD5加密
		String passwordMD5 = Md5Util.MD5Encode(password, null).toUpperCase();

        UserLoginParameter userLoginParameter = new UserLoginParameter();
        userLoginParameter.setLogname(username);
        userLoginParameter.setPassword(passwordMD5);

		UserLoginResponse result = sysUserService.login(userLoginParameter);

		if(Code.SUCCESS.equals(result.getCode())){
			//登录成功，返回用户对应的菜单信息
			request.getSession().setAttribute(SessionAttributeConstants.CURRENT_USER, result);
			request.setAttribute("menu", result.getMenus());

			if(StringUtil.notBlank(recordAccount) && "1".equals(recordAccount)){
				saveCookie(userLoginBean, cookieMaxAge);
			} else {
				saveCookie(userLoginBean, 0);
			}

//            addLog(null, "用户登录", UserLogTypeConstants.USER_LOGIN, getCurrentUserIdStr(), username, toJson(userLoginParameter));

			return toJson(new BaseResponse());
		} else {
			return reLogin(result.getMessage());
		}
	}

    /**
     * 用户登出
     * @return BaseResponse
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String logout() throws IOException {
    	UserLoginResponse userInfo = (UserLoginResponse)request.getSession().getAttribute(SessionAttributeConstants.CURRENT_USER);
    	if(userInfo != null){
    		request.getSession().removeAttribute(SessionAttributeConstants.CURRENT_USER);
    	}
    	response.sendRedirect("login");
    	return "/login";
    }

	/**
	 * 记住用户名
	 * @param user
	 */
	public void saveCookie(UserLoginBean user, int maxAge) {
		// 开始保存Cookie
		Cookie cookie = new Cookie(cookieDomainName, user.getUsername());
		cookie.setMaxAge(maxAge);//等于0时删除
		// 向客户端写入
		response.addCookie(cookie);
	}

//	/**
//	 * 刷新项目配置信息
//	 * @param username 用户名
//	 * @param password 密码
//	 * @return json
//	 */
//	@RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//    public String refresh(String username, String password){
//    	BaseResponse response = new BaseResponse();
//		if(StringUtil.isBlank(username)){
//			response.setCode(Code.FAIL).setMessage("用户名不能为空！");
//			return responseToJson(response);
//    	}
//
//		if(StringUtil.isBlank(password)){
//			response.setCode(Code.FAIL).setMessage("密码不能为空！");
//			return responseToJson(response);
//		}
//
//		//
//		if(username.trim().equals("admin") && password.trim().equals("1")){
//			ProjectContext.getInstance().refresh();
//			return responseToJson(response);
//		}else{
//			response.setCode(Code.FAIL).setMessage("用户名或密码错误！");
//			return responseToJson(response);
//		}
//    }
	
	/**
	 * 首页
	 * @return 首页页面
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
    public String index(){
		return "/index";
	}
	
	/**
	 * 维持Session的心跳
	 * @return json
	 */
	@RequestMapping(value = "/holdsession", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String holdSession(){
		if (null == request.getSession().getAttribute(SessionAttributeConstants.CURRENT_USER)) {
			return "{\"code\": \"400\"}";
		}else {
			return "{\"code\": \"200\"}";
		}
	}
	
}
