package com.wangyu.system.constant;

/**
 * 通用常量类
 * @author 	wangyu	wangyu@joygo.com 2016年10月17日 上午11:32:03
 *
 */
public class CommonConstants {

	/**
	 * 管理员级别用户is_admin值
	 */
	public static final int USER_IS_ADMIN = 1;
	
	/********************通用状态值 	开始*****************/
	/**
	 * 正常状态
	 */
	public static final int COMMON_STATUS_NORMAL = 0;
	/**
	 * 不使用状态
	 */
	public static final int COMMON_STATUS_NOT_USE = 1;
	/********************通用状态值 	结束*****************/

	/********************数据状态值 	开始*****************/
	/**
	 * 正常状态
	 */
	public static final int DATA_STATUS_NORMAL = 0;
	/**
	 * 删除状态
	 */
	public static final int DATA_STATUS_DELETE = 1;
	/********************数据状态值 	结束*****************/
	
	
	/**
	 * redis微服务key
	 */
	public static final String STAR_BBM_REDIS = "sredis";
	/**
	 * prm微服务key
	 */
	public static final String STAR_BBM_PRM = "sprm";
	/**
	 * live微服务key
	 */
	public static final String STAR_BBM_LIVE = "slive";
	/**
	 * user微服务key
	 */
	public static final String STAR_BBM_USER = "suser";
	/**
	 * song微服务key
	 */
	public static final String STAR_BBM_SONG = "ssong";
	/**
	 * message微服务key
	 */
	public static final String STAR_BBM_MESSAGE = "smessage";
	/**
	 * account微服务key
	 */
	public static final String STAR_BBM_ACCOUNT = "saccount";
	
	/********************操作日志类型	开始*****************/
	/**
	 * 操作日志类型-登录
	 */
	public static final String LOG_TYPE_LOGIN = "login";
	/**
	 * 操作日志类型-登出
	 */
	public static final String LOG_TYPE_LOGOUT= "logout";
	/**
	 * 操作日志类型-查询
	 */
	public static final String LOG_TYPE_SELECT = "select";
	/**
	 * 操作日志类型-新增
	 */
	public static final String LOG_TYPE_INSERT = "insert";
	/**
	 * 操作日志类型-修改
	 */
	public static final String LOG_TYPE_UPDATE = "update";
	/**
	 * 操作日志类型-删除
	 */
	public static final String LOG_TYPE_DELETE = "delete";
	/**
	 * 操作日志类型-异常
	 */
	public static final String LOG_TYPE_EXCEPTION = "exception";
	/********************操作日志类型	结束*****************/

	
	/********************监播,场控			开始*****************/
	/**
	 * 管理员类型-场控
	 */
	public static final int ANCHOR_ADMIN_TYPE_LIVE_CONTROL = 1;
	/**
	 * 管理员类型-监播
	 */
	public static final int ANCHOR_ADMIN_TYPE_LIVE_MONITOR = 2;
	/**
	 * 配置类型-后台
	 */
	public static final int ANCHOR_ADMIN_CONFIG_TYPE_BBM = 0;
	/**
	 * 配置类型-APP
	 */	
	public static final int ANCHOR_ADMIN_CONFIG_TYPE_APP = 1;
	/********************监播,场控			结束*****************/
	

	/********************参数配置			开始*****************/
	/**
	 * 项目配置-单位的key
	 */
	public static final String CONFIG_UNIT_KEY = "c_key";
	/**
	 * 项目配置-钻石单位
	 */
	public static final String CONFIG_COINUNIT = "coinunit";
	/**
	 * 项目配置-荔枝单位
	 */
	public static final String CONFIG_GIFTUNIT = "giftunit";
	/**
	 * 项目配置-配置类型-特定接口
	 */
	public static final String CONFIG_TYPE = "1";
	/********************参数配置			结束*****************/

	
	/********************注册用户类型		开始*****************/
	/**
	 * 注册用户 1-注册用户  
	 */
	public static final int USER_TYPE_COMMON = 1;
	/**
	 * 注册用户 4-QQ
	 */
	public static final int USER_TYPE_QQ = 4;
	/**
	 * 注册用户 5-微信 
	 */
	public static final int USER_TYPE_WEIXIN = 5;
	/**
	 * 注册用户 6-新浪微博
	 */
	public static final int USER_TYPE_SINA_WEIBO = 6;
	/**
	 * 注册用户 9-机器人
	 */
	public static final int USER_TYPE_ROBOT = 9;
	/********************注册用户类型		结束*****************/

	/********************注册用户		开始*****************/
	/**
	 * 注册用户是主播
	 */
	public static final int USER_PROJECT_IS_ANCHOR = 1;
	/**
	 * 注册用户不是主播
	 */
	public static final int USER_PROJECT_IS_NOT_ANCHOR = 0;
	/********************注册用户		结束*****************/
	
	
	/********************提现审核		开始*****************/
	/**
	 * 处理中
	 */
	public static final int ACCOUNT_CASH_STATUS_HANDLE = 0;
	/**
	 * 已付款
	 */
	public static final int ACCOUNT_CASH_STATUS_ACCOUNT_PAID = 1;
	/**
	 * 作废
	 */
	public static final int ACCOUNT_CASH_STATUS_INVALID = 2;
	/********************提现审核		结束*****************/

	
	/********************直播间			开始*****************/
	/**
	 * 直播状态-直播中
	 */
	public static final int LIVE_STATUS_LIVING = 1;
	/**
	 * 直播状态-回放
	 */
	public static final int LIVE_STATUS_REPLAY = 0;
	/**
	 * 直播状态-创建中
	 */
	public static final int LIVE_STATUS_CREATE = 2;

	/**
	 * 直播关闭状态，0-正常关闭
	 */
	public static final int LIVE_BE_STOP_STATUS_NORMAL = 0;

	/**
	 * 直播cds录制状态-成功
	 */
	public static final int LIVE_CDS_STATUS_SUCCESS = 0;
	
	/********************直播间			结束*****************/

	/**
	 * 拼接当日最后一秒的时间
	 */
	public static final String TIME_235959 = " 23:59:59";
	
	/**
	 * 菜单树根节点名称
	 */
	public static final String MENU_ROOT_NAME = "所有菜单";
	/**
	 * 菜单树根节点id
	 */
	public static final String MENU_ROOT_ID = "-1";
	/**
	 * 菜单类型-根节点
	 */
	public static final String MENU_TYPE_ROOT = "0";
	/**
	 * 菜单类型-模块
	 */
	public static final String MENU_TYPE_MODULE = "1";
	/**
	 * 菜单类型-菜单
	 */
	public static final String MENU_TYPE_MENU = "2";

	/**
	 * 主播请求直播类型-所有人
	 */
	public static final int LIVE_REQUESTLIVE_ALL = 0;
	
	/**
	 * 类型-所有(用于禁播管理，监播场控管理的被管类型选择)
	 */
	public static final int TYPE_ALL = 0;
	
	/**
	 * 类型-栏目(用于禁播管理，监播场控管理的被管类型选择)
	 */
	public static final int TYPE_CHANNEL = 1;
	
	/**
	 * 类型-主播(用于禁播管理，监播场控管理的被管类型选择)
	 */
	public static final int TYPE_ANCHOR = 2;
	
	
	/*	主播审核		begin	*/
	/**
	 * 审核状态-0-未审核
	 */
	public static final int USER_ANCHOR_APPLY_STATUS_NOT_APPLY = 0;
	/**
	 * 审核状态-1-审核中
	 */
	public static final int USER_ANCHOR_APPLY_STATUS_APPLYING = 1;
	/**
	 * 审核状态-2-审核通过
	 */
	public static final int USER_ANCHOR_APPLY_STATUS_PASS = 2;
	/**
	 * 审核状态-3-审核不通过
	 */
	public static final int USER_ANCHOR_APPLY_STATUS_NOT_PASS = 3;	
	/*	主播审核		end	*/
	
	/*	PRM(系统管理) user表参数		begin	*/
	/**
	 * 是否管理员，1表示管理员（不允许删除）
	 */
	public static final int PRM_USER_IS_ADMIN = 1;
	/*	PRM(系统管理) user表参数		end		*/

	
	/*	live_base库command表code常量		begin	*/
	
	/**
	 * 操作方法-增加
	 */
	public static final String PRM_COMMAND_METHOD_ADD = "add";
	/**
	 * 操作方法-修改
	 */
	public static final String PRM_COMMAND_METHOD_UPDATE = "update";
	/**
	 * 操作方法-删除
	 */
	public static final String PRM_COMMAND_METHOD_DELETE = "delete";
	/**
	 * 数据库-live库
	 * 
	 */
	public static final String PRM_COMMAND_DB_LIVE = "live";
	/**
	 * 数据库-live_base库
	 */
	public static final String PRM_COMMAND_DB_LIVE_BASE = "live_base";
	/**
	 * 数据库-song库
	 */
	public static final String PRM_COMMAND_DB_SONG = "live_song";
	/**
	 * 数据库-user库
	 */
	public static final String PRM_COMMAND_DB_USER = "live_user";
	/**
	 * job类型，=0 SLS接口使用
	 */
	public static final int PRM_COMMAND_TYPE_SLS = 0;
	/**
	 * job类型，=1 SUS使用
	 */
	public static final int PRM_COMMAND_TYPE_SUS = 1;
	/**
	 * job类型，=3 官方消息使用
	 */
	public static final int PRM_COMMAND_TYPE_OS = 3;
	/**
	 * 缓存code值-项目CDN
	 */
	public static final String PRM_COMMAND_CODE_PROJECT_CDN = "project_cdn";
	/**
	 * 缓存code值-充值产品
	 */
	public static final String PRM_COMMAND_CODE_GOODS = "goods";
	/**
	 * 缓存code值-直播配置
	 */
	public static final String PRM_COMMAND_CODE_CONFIG = "config";
	/**
	 * 缓存code值-栏目
	 */
	public static final String PRM_COMMAND_CODE_ANCHOR_CATEGORY = "anchor_category";
	/**
	 * 缓存code值-栏目扩展
	 */
	public static final String PRM_COMMAND_CODE_ANCHOR_CATEGORY_EXTEND = "anchor_category_extend";
	/**
	 * 缓存code值-广告
	 */
	public static final String PRM_COMMAND_CODE_ADVERTISE = "advertise";
	/**
	 * 缓存code值-礼物
	 */
	public static final String PRM_COMMAND_CODE_GIFT = "gift";
	/**
	 * 缓存code值-话题
	 */
	public static final String PRM_COMMAND_CODE_TOPIC = "topic";
	/**
	 * 缓存code值-场控,监播合并的视图
	 */
	public static final String PRM_COMMAND_CODE_V_ANCHOR_ADMIN 	= "v_anchor_admin";
//	/**
//	 * 缓存code值-场控所有人
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_CK_ALL 	= "v_anchor_ck_all";
//	/**
//	 * 缓存code值-场控自定义主播
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_CK_ANCHOR 	= "v_anchor_ck_anchor";
//	/**
//	 * 缓存code值-场控栏目
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_CK_CHANNEL = "v_anchor_ck_channel";
//	/**
//	 * 缓存code值-监播所有人
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_JB_ALL 	= "v_anchor_jb_all";
//	/**
//	 * 缓存code值-监播自定义主播
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_JB_ANCHOR 	= "v_anchor_jb_anchor";
//	/**
//	 * 缓存code值-监播栏目
//	 */
//	public static final String PRM_COMMAND_CODE_V_ANCHOR_JB_CHANNEL = "v_anchor_jb_channel";
//	/**
//	 * 缓存code值-禁播所有人
//	 */
//	public static final String PRM_COMMAND_CODE_V_LIVE_REQUEST_ALL 	= "v_live_request_all";
//	/**
//	 * 缓存code值-禁播自定义主播
//	 */
//	public static final String PRM_COMMAND_CODE_V_LIVE_REQUEST_ANCHOR 	= "v_live_request_anchor";
//	/**
//	 * 缓存code值-禁播栏目
//	 */
//	public static final String PRM_COMMAND_CODE_V_LIVE_REQUEST_CHANNEL = "v_live_request_channel";
	/**
	 * 缓存code值-禁播合并后视图
	 */
	public static final String PRM_COMMAND_CODE_V_LIVE_REQUEST = "v_live_request";
	/**
	 * 缓存code值-修改密码
	 */
//	public static final String PRM_COMMAND_CODE_CHANGE_PWD = "changepwd";//没有这个
	/**
	 * 缓存code值-注册用户
	 */
	public static final String PRM_COMMAND_CODE_USER = "user_project";
	/**
	 * 缓存code值-主播
	 */
	public static final String PRM_COMMAND_CODE_ANCHOR = "anchor";
	/**
	 * 缓存code值-直播
	 */
//	public static final String PRM_COMMAND_CODE_LIVE = "live";
	/**
	 * 缓存code值-回放
	 */
	public static final String PRM_COMMAND_CODE_LIVE_RECORD = "live_record";
	/**
	 * 缓存code值-主播推荐
	 */
	public static final String PRM_COMMAND_CODE_ANCHOR_RECOMMEND = "anchor_recommend";
	/**
	 * 缓存code值-项目
	 */
	public static final String PRM_COMMAND_CODE_PROJECT = "project";
	/**
	 * 缓存code值-主播申请审核
	 */
	public static final String PRM_COMMAND_CODE_USER_ANCHOR_APPLY = "user_anchor_apply";
	/**
	 * 缓存code值-官方消息
	 */
	public static final String PRM_COMMAND_CODE_OFFICIAL_MSG = "official_msg";
	/*	live_base库command表code常量		end	*/
	
	
	/********************消息推送 	开始*****************/
	/**
	 * 未读状态
	 */
	public static final int MESSAGE_STATUS_UNREAD = 0;
	/**
	 * 已读状态
	 */
	public static final int MESSAGE_STATUS_READ = 1;
	/**
	 * 消息发送设备类型-全部
	 */
	public static final int MESSAGE_SEND_TYPE_ALL = 0;
	/**
	 * 消息发送设备类型-android
	 */
	public static final int MESSAGE_SEND_TYPE_ANDROID = 1;
	/**
	 * 消息发送设备类型-ios
	 */
	public static final int MESSAGE_SEND_TYPE_IOS = 2;
	/**
	 * 消息跳转类型-APP
	 */
	public static final int MESSAGE_TYPE_APP = 0;
	/**
	 * 消息跳转类型-直播
	 */
	public static final int MESSAGE_TYPE_LIVE = 1;
	/**
	 * 消息跳转类型-文本
	 */
	public static final int MESSAGE_TYPE_TEXT = 2;
	/**
	 * 消息发送给对象-所有人
	 */
	public static final int MESSAGE_SEND_PERSON_ALL = 0;
	/**
	 * 消息发送给对象-单人
	 */
	public static final int MESSAGE_SEND_PERSON_SINGLE = 1;
	/********************消息推送 	结束*****************/
	
	/********************友盟推送配置 	开始*****************/
	/**
	 * 友盟推送配置的模式-生产
	 */
	public static Integer UMENG_INIT_MODEL_PRODUCT = 1;
	
	/**
	 * 友盟推送配置的模式-开发测试
	 */
	public static Integer UMENG_INIT_MODEL_DEVELOP = 2;
	/********************友盟推送配置 	结束*****************/
	
	/**
	 * 弹出方式查询主播的打开者页面id属性
	 */
	public static final String ANCHOR_SELECT_OPENER = "opener";
	
	/**
	 * 栏目关联所有主播
	 */
	public static final int ANCHOR_CATEGORY_RELEVANCE_ALL_ANCHOR = 0;
	
	/**
	 * 栏目是系统栏目
	 */
	public static final int ANCHOR_CATEGORY_IS_SYS = 1;
	
	/**
	 * 栏目不是系统栏目
	 */
	public static final int ANCHOR_CATEGORY_IS_NOT_SYS = 0;

	/**
	 * 栏目是默认栏目
	 */
	public static final int ANCHOR_CATEGORY_IS_DEFAULT = 1;
	
	/**
	 * 栏目不是默认栏目
	 */
	public static final int ANCHOR_CATEGORY_IS_NOT_DEFAULT = 0;
	
	/**
	 * 礼物交易类型-送礼(2017-05-26日新BBM上线前数据)
	 */
	public static final int GIFT_TRADE_TYPE_GIFT_0 = 0;
	/**
	 * 礼物交易类型-送礼(2017-05-26日新BBM上线后数据)
	 */
	public static final int GIFT_TRADE_TYPE_GIFT_2 = 2;
	
	/**
	 * 礼物交易类型-弹幕
	 */
	public static final int GIFT_TRADE_TYPE_DANMU = 1;
	
	/**
	 * 礼物交易类型-红包
	 */
//	public static final int GIFT_TRADE_TYPE_REDPACKE = 2;
	
	/**
	 * 回放-不推荐
	 */
	public static final int LIVE_RECORD_NOT_RECOMMEND = 0;

	/**
	 * 回放-推荐
	 */
	public static final int LIVE_RECORD_RECOMMEND = 1;
	
	/**
	 * 回放修改-标题
	 */
	public static final int LIVE_RECORD_UPDATE_TITLE = 0;
	
	/**
	 * 回放修改-
	 */
	public static final int LIVE_RECORD_UPDATE_ASSIST = 1;
	
	/**
	 * 回放修改-
	 */
	public static final int LIVE_RECORD_UPDATE_PLAY = 2;

	/**
	 * 回放修改-
	 */
	public static final int LIVE_RECORD_UPDATE_SHARE = 3;
	
	/**
	 * 充值类型-支付宝
	 */
	public static final int RECHARGE_TYPE_ALIPAY = 10;
	
	/**
	 * 充值类型-微信
	 */
	public static final int RECHARGE_TYPE_WECHAT = 11;
	
	/**
	 * 充值类型-苹果
	 */
	public static final int RECHARGE_TYPE_APPLE = 12;
	
	/**
	 * 充值类型-后台
	 */
	public static final int RECHARGE_TYPE_BACKSTAGE = 0;
	
	/**
	 * 充值来源-APP
	 */
	public static final String RECHARGE_FROM_APP = "1";
	
	/**
	 * 充值来源-PC
	 */
	public static final String RECHARGE_FROM_PC = "2";
	
	/**
	 * 充值来源-WAP
	 */
	public static final String RECHARGE_FROM_WAP = "3";
	
	/**
	 * 充值来源-公众号
	 */
	public static final String RECHARGE_FROM_PUBLIC = "4";

	/**
	 * 广告的跳转类型-默认
	 */
	public static final int ADVERTISE_AT_TYPE_DEFAULT = 0;
	/**
	 * 广告的跳转类型-主播
	 */
	public static final int ADVERTISE_AT_TYPE_ANCHOR = 1;
	/**
	 * 广告的跳转类型-网址
	 */
	public static final int ADVERTISE_AT_TYPE_URL = 2;
	
	/**
	 * 主播推荐最大排序值1000
	 */
	public static final int ANCHOR_RECOMMEND_MAX_ORDER = 1000;
	
	
}
