package com.wangyu.garage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.google.gson.Gson;
//import com.joygo.star.base.bean.ProjectContext;
//import com.joygo.star.base.bean.UserLoginBean;
//import com.joygo.star.bbm.model.ReportStatisticsModel;
//import com.joygo.star.bbm.service.IReportStatisticsService;
//import com.joygo.star.bbm.utils.Http;
//import com.joygo.star.bbm.utils.PropertyUtils;
//import com.joygo.star.common.BaseResponse;
//import com.joygo.star.common.Code;
//import com.joygo.star.common.DataResponse;
//import com.joygo.star.common.ListResponse;
//import com.joygo.star.constant.CommonConstants;
//import com.joygo.star.constant.MessageConstants;
//import com.joygo.star.constant.SessionAttributeConstants;
//import com.joygo.star.kit.Md5Kit;
//import com.joygo.star.kit.StringKit;
//import com.joygo.star.live.entity.page.LiveReportStatisticsQueryParameter;
//import com.joygo.star.live.entity.response.ConfigResponse;
//import com.joygo.star.prm.entity.response.UserLoginResponse;
//import com.joygo.star.prm.model.ProjectModel;

/**
 * 登录并进入首页的控制层
 * @author 	wangyu	wangyu@joygo.com 2016年10月21日 下午2:54:00
 *
 */
@Controller
public class HomeController extends BaseController{
	
	//登录服务访问入口
	private static final String LOGIN = "/login/login";
	
	//获取系统单位接口
	private static final String UNIT = "/config/list";
	
	 //保存cookie时的cookieName
    private final static String cookieDomainName = "garage";
    //设置cookie有效期是两个星期
    private final static int cookieMaxAge = 60 * 60 * 24 * 7 * 2;
    
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
//		if(ProjectContext.getInstance().isDevelopMode()){
//			String jsonString = Http.get(PropertyUtils.getValue(CommonConstants.STAR_BBM_PRM)+"/user/querymodulemenubyuserid?userid=1");
//			if (null == jsonString) {
//				return "redirect:/#";
//			}else {
//				Gson gson = new Gson();
//				ListResponse listResponse = gson.fromJson(jsonString, ListResponse.class);
//				if (listResponse.getCode().equals(Code.SUCCESS)) {
//					request.setAttribute("menu", listResponse.getList());
//					return "home";
//				}else {
//					return "redirect:/#";
//				}
//			}
//		} else {
//			UserLoginResponse user = getCurrentUser();
//			if(user == null){
//				return "redirect:login";
//			}
//			request.setAttribute("menu", user.getMenus());
//			return "/home";
//		}
		return "/home";
	}
	
	/**
	 * 用户登录
	 * @return 登录成功返回home,登录失败返回login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces="text/html;charset=utf-8")
	public String login() throws IOException {
//		UserLoginResponse user = getCurrentUser();
//		if(user == null){
			return "/login";
//		}
//		return home();
	}
//
//	/**
//	 * 返回登录页，并提示错误信息
//	 * @param errorMessage - 错误信息
//	 * @param request - HttpServletRequest
//	 * @return 登录地址
//	 */
////	private String reLogin(String errorMessage){
////		return responseToJson(renderError(Code.FAIL, errorMessage));
////	}
//
//	/**
//	 * 主页面
//	 * @return
//	 */
//	@RequestMapping(value = "/userlogin", method=RequestMethod.POST, produces="text/html;charset=utf-8")
//	@ResponseBody
//	public String userlogin(UserLoginBean userLoginBean,Model model) throws IOException{
//		String username = userLoginBean.getUsername();//用户名
//		String password =  userLoginBean.getPassword();//密码
//		String checkcode = userLoginBean.getCheckcode();//验证码
//		String recordAccount= userLoginBean.getRecordAccount();//是否记住密码
//		request.setAttribute("recordAccount", recordAccount);
//		//用户名不能为空
//		if(StringKit.isBlank(username)){
//			return reLogin(MessageConstants.LOGNAME_CAN_NOT_BE_NULL);
//		}
//
//		request.setAttribute("username", username);
//
//		//密码不能为空
//		if(StringKit.isBlank(password)){
//			return reLogin(MessageConstants.PASSWORD_CAN_NOT_BE_NULL);
//		}
//
//		request.setAttribute("password", password);
//
//		//验证码不能为空
//		if(StringKit.isBlank(checkcode)){
//			return reLogin(MessageConstants.CHECKCODE_CAN_NOT_BE_NULL);
//		}
//
//		//判断验证码是否正确
//		String checkCodeInSesssion = (String)request.getSession().getAttribute(SessionAttributeConstants.LOGINCHECKCODE);
//		if(!checkCodeInSesssion.equals(checkcode)){
//			return reLogin(MessageConstants.CHECKCODE_ERROR);
//		}
//
//		String[] userDomain = username.split("@");
//		if(userDomain.length != 2){
//			return reLogin(MessageConstants.LOGNAME_PATTERN_NOT_RIGHT);
//		}
//
//		//用户登录名
//		String logname = userDomain[0];
//		//项目域名, POST方式传值@符号会自动转换，所以先分隔出域名，再调登录服务
//		String domain = userDomain[1];
//
//		//密码MD5加密
//		String passwordMD5 = Md5Kit.MD5Encode(password, null).toUpperCase();
//
//		Map<String, String > paramsMap = new HashMap<String, String>();
//		paramsMap.put("logname", logname);
//		paramsMap.put("domain", domain);
//		paramsMap.put("password", passwordMD5);
//
//		String json = null;
//		try {
//			//调登录服务
//			json = Http.post(PropertyUtils.getValue(CommonConstants.STAR_BBM_PRM) + LOGIN, paramsMap);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return reLogin(MessageConstants.HTTP_REQUEST_ERROR);
//		}
//
//		Gson gson = new Gson();
//		UserLoginResponse result = (UserLoginResponse)gson.fromJson(json, UserLoginResponse.class);
//
//		if(Code.SUCCESS.equals(result.getCode())){
//			//登录成功，返回用户对应的菜单信息
//			request.getSession().setAttribute(SessionAttributeConstants.CURRENT_USER, result);
//			request.setAttribute("menu", result.getMenus());
//			ProjectModel project = ProjectContext.getInstance().getProjectModel(result.getRef_p_id());
//
//			//获取单位
//			paramsMap.clear();
//			paramsMap.put(CommonConstants.CONFIG_UNIT_KEY, CommonConstants.CONFIG_COINUNIT);
//			paramsMap.put("c_type", CommonConstants.CONFIG_TYPE);
//			String coinUnitJson = Http.get(ProjectContext.getInstance().getServiceUrlForLive(request) + UNIT, paramsMap);
//			ConfigResponse coinConfig = new Gson().fromJson(coinUnitJson, ConfigResponse.class);
//			if (Code.SUCCESS.equals(coinConfig.getCode())) {
//				result.setU_coinunit(coinConfig.getList().get(0).getC_val());
//			}
//			paramsMap.put(CommonConstants.CONFIG_UNIT_KEY, CommonConstants.CONFIG_GIFTUNIT);
//			String giftUnitJson = Http.get(ProjectContext.getInstance().getServiceUrlForLive(request) + UNIT, paramsMap);
//			ConfigResponse giftConfig = new Gson().fromJson(giftUnitJson, ConfigResponse.class);
//			if (Code.SUCCESS.equals(giftConfig.getCode())) {
//				result.setU_giftunit(giftConfig.getList().get(0).getC_val());
//			}
//			//给页面使用
//			request.getSession().setAttribute(SessionAttributeConstants.CURRENT_APP, project);
//
//			if(StringKit.notBlank(recordAccount) && "1".equals(recordAccount)){
//				saveCookie(userLoginBean, cookieMaxAge);
//			} else {
//				saveCookie(userLoginBean, 0);
//			}
//
//			return responseToJson(new BaseResponse());
//		} else {
//			return reLogin(result.getMessage());
//		}
//	}
//
//    /**
//     * 用户登出
//     * @param logname - 用户名
//     * @param pid - 项目id
//     * @return BaseResponse
//     * @throws IOException
//     */
//    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//    public String logout() throws IOException {
//    	UserLoginResponse userInfo = (UserLoginResponse)request.getSession().getAttribute(SessionAttributeConstants.CURRENT_USER);
//    	if(userInfo != null){
//    		request.getSession().removeAttribute(SessionAttributeConstants.CURRENT_USER);
//    	}
//    	response.sendRedirect("login");
//    	return "/login";
//    }
//
//	/**
//	 * 记住用户名
//	 * @param user
//	 * @param response
//	 */
//	public void saveCookie(UserLoginBean user, int maxAge) {
//		// 开始保存Cookie
//		Cookie cookie = new Cookie(cookieDomainName, user.getUsername());
//		cookie.setMaxAge(maxAge);//等于0时删除
//		// 向客户端写入
//		response.addCookie(cookie);
//	}
//
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
//		if(StringKit.isBlank(username)){
//			response.setCode(Code.FAIL).setMessage("用户名不能为空！");
//			return responseToJson(response);
//    	}
//
//		if(StringKit.isBlank(password)){
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
	
	

//	/**
//	 * 维持Session的心跳
//	 * @return json
//	 */
//	@RequestMapping(value = "/holdsession", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String holdSession(){
//		if (null == request.getSession().getAttribute(SessionAttributeConstants.CURRENT_USER)) {
//			return "{\"code\": \"400\"}";
//		}else {
//			return "{\"code\": \"200\"}";
//		}
//	}
	
	/**
	 * 
	* @Title: reportList
	* @Description: 报表数据
	* @param @return    参数
	* @return String    返回类型
	* @author renchenyang
	* @throws
	 */
//	@RequestMapping(value = "/reportlist", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String reportList(){
//		Integer ref_p_id = getCurrentProjectId(request);
//		DataResponse dataResponse = new DataResponse();
//		Gson gson = new Gson();
//		dataResponse.setCode(Code.SUCCESS);
//		ReportStatisticsModel model = new ReportStatisticsModel();
//		model.setAddusercount(String.valueOf(reportService.newAddUser(ref_p_id, request)));
//		model.setAddanchorcount(String.valueOf(reportService.newAddAnchor(ref_p_id, request)));
//		model.setRecharge(String.valueOf(reportService.todayRecharge(ref_p_id, request)));
//		model.setConsumption(String.valueOf(reportService.todayConsumption(ref_p_id, request)));
//		model.setLivecount(String.valueOf(reportService.liveCount(ref_p_id, request)));
//		model.setLiveduration(String.valueOf(reportService.todayLiveDuration(ref_p_id, request)));
//		model.setUsertotalbalance(String.valueOf(reportService.userTotalBalance(ref_p_id, request)));
//		model.setTotaluser(String.valueOf(reportService.userCount(ref_p_id, request)));
//		model.setTotalanchor(String.valueOf(reportService.anchorCount(ref_p_id, request)));
//		//model.setCashapply(String.valueOf(reportService.todayCash(ref_p_id, request)));
//		//model.setAddanchorapply(String.valueOf(reportService.anchorApply(ref_p_id, request)));
//		dataResponse.setData(model);
//		return gson.toJson(dataResponse);
//	}
//
//	/**
//	 *
//	* @Title: newAddUser7
//	* @Description: 7日新增用户走势
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/newaddusers", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String newAddUsers(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.newAddUsers(parameter, request);
//	}
//
//	/**
//	* @Title: newAddAnchors
//	* @Description: 近几日新增主播走势
//	* @param @param ref_p_id
//	* @param @param request
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	*/
//	@RequestMapping(value = "/newaddanchors", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String newAddAnchors(LiveReportStatisticsQueryParameter parameter) {
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.newAddAnchors(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: totalLiveDuration
//	* @Description: 近几日每日直播时长
//	* @param @param ref_p_id
//	* @param @param request
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/totalliveduration", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String totalLiveDuration(LiveReportStatisticsQueryParameter parameter) {
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.totalLiveDuration(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: userRecharge
//	* @Description: 7日用户充值走势
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/userrecharge", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String userRecharge(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.userRecharge(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: rechargeProportion
//	* @Description: 近几日充值占比
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/rechargeproportion", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String rechargeProportion(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.rechargeProportion(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: userConsumption
//	* @Description: 7日用户消费走势
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/userconsumption", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String userConsumption(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.userConsumption(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: userConsumptionRanking
//	* @Description:   (近几日消费排行)
//	* @param @param parameter
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/userconsumptionranking", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String userConsumptionRanking(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.userConsumptionRanking(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: hotLive
//	* @Description: 获取7日热门直播数据
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/hotlive", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String hotLive(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.hotLive(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: hotRecord
//	* @Description: 获取7日热门回放数据
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/hotrecord", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String hotRecord(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.hotRecord(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: anchorRanking
//	* @Description: 30日主播直播时长排名
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/anchorranking", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String anchorRanking(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.anchorRanking(parameter, request);
//	}
//
//	/**
//	 *
//	* @Title: anchorincomeranking
//	* @Description: 30日主播收入排名
//	* @param @return    参数
//	* @return String    返回类型
//	* @author renchenyang
//	* @throws
//	 */
//	@RequestMapping(value = "/anchorincomeranking", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
//	@ResponseBody
//	public String anchorIncomeRanking(LiveReportStatisticsQueryParameter parameter){
//		parameter.setRef_p_id(getCurrentProjectId(request));
//		return reportService.anchorIncomeRanking(parameter, request);
//	}
}
