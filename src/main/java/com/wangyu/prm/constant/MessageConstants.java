package com.wangyu.prm.constant;

/**
 * 提示信息常量类
 * @author wangyuwangyu@joygo.com 2016年10月30日 上午11:32:03
 *
 */
public class MessageConstants {

	/* 微服务字段属性验证公共部分	  start */
	public static final String ORDER_CAN_NOT_BE_NULL = "排序号不能为空";
	public static final String PRIMARY_KEY_CAN_NOT_BE_NULL = "主键ID不能为空";
	public static final String STATUS_CAN_NOT_BE_NULL = "状态不能为空";
	public static final String RECORD_EXIST = "记录已存在";
	public static final String TYPE_CAN_NOT_BE_NULL = "类型不能为空";
	public static final String NUMBER_CAN_NOT_BE_NULL = "数量不能为空";
	public static final String PARAMETER_ERROR = "参数错误";
	
	/* 微服务字段属性验证公共部分	  end */
	
	public static final String ALL_USER = "所有人";

	public static final String ANCHOR_ADMIN__TYPE__CAN_NOT_BE_NULL = "管理类型不能为空";
	public static final String ANCHOR_ADMIN__ADMIN_TYPE__CAN_NOT_BE_NULL = "被管理类型不能为空";
	public static final String ANCHOR_ADMIN__CONFIG_TYPE__CAN_NOT_BE_NULL = "配置类型不能为空";
	public static final String ANCHOR_ADMIN__LIVE_MONITOR__CAN_NOT_BE_NULL = "监播人不能为空";
	public static final String ANCHOR_ADMIN__LIVE_CONTROL__CAN_NOT_BE_NULL = "场控人不能为空";

	public static final String ANCHOR_CATEGORY_ID_CAN_NOT_BE_NULL = "栏目ID不能为空";
	public static final String ANCHOR_CATEGORY_CODE_CAN_NOT_BE_NULL = "栏目编码不能为空";
	public static final String ANCHOR_CATEGORY_NAME_CAN_NOT_BE_NULL = "栏目名称不能为空";
	public static final String ANCHOR_CATEGORY_ORDER_CAN_NOT_BE_NULL = "栏目排序号不能为空";
	public static final String ANCHOR_CATEGORY_ISDEFAULT_CAN_NOT_BE_NULL = "栏目是否默认不能为空";
	public static final String ANCHOR_CATEGORY_RELEVANCE_CAN_NOT_BE_NULL = "栏目关联主播不能为空";
	public static final String ANCHOR_CATEGORY_RELEVANCE_ID_CAN_NOT_BE_NULL = "请选择要关联的主播";
	public static final String ANCHOR_CATEGORY_CODE_EXIST = "栏目编码已存在";
	public static final String ANCHOR_CATEGORY_NAME_EXIST = "栏目名称已存在";
	
	public static final String ANCHOR_ID_CAN_NOT_BE_NULL = "主播ID不能为空";

	public static final String APP_EXIST = "APP已存在";

	public static final String CHECKCODE_CAN_NOT_BE_NULL = "验证码不能为空";
	public static final String CHECKCODE_ERROR = "验证码错误";

	public static final String COMMAND_ARG_ARRAY_CAN_NOT_BE_NULL = "批量操作内容不能为空";
	public static final String COMMAND_CODE_CAN_NOT_BE_NULL = "CODE不能为空";
	public static final String COMMAND_TYPE_CAN_NOT_BE_NULL = "JOB类型不能为空";
	public static final String COMMAND_DB_CAN_NOT_BE_NULL = "数据库名不能为空";

	public static final String CONFIG_KEY_CAN_NOT_BE_NULL = "KEY不能为空";
	
	public static final String GOODS_ID_EXIST = "产品编号已存在";
	public static final String GOODS_ID_CAN_NOT_BE_NULL = "产品编号不能为空";
	public static final String GOODS_MONEY_CAN_NOT_BE_NULL = "金额不能为空";
	public static final String GOODS_ATTR_CAN_NOT_BE_NULL = "属性不能为空";

	public static final String SONG_NAME_CAN_NOT_BE_NULL = "歌曲名称不能为空";
	public static final String SONG_STATUS_CAN_NOT_BE_NULL = "歌曲状态不能为空";
	
	
	public static final String ACCOUNT_WITHDRAW_CASH_RECORD_UID_CAN_NOT_BE_NULL = "用户ID不能为空";
	public static final String ACCOUNT_WITHDRAW_CASH_RECORD_STATUS_CAN_NOT_BE_NULL = "状态不能为空";
	public static final String ACCOUNT_WITHDRAW_CASH_RECORD_NOT_EXIST = "记录不存在";
	public static final String ACCOUNT_WITHDRAW_CASH_RECORD_ACCOUNT_PAID = "该条记录已提现成功";
	public static final String ACCOUNT_CASH_STATUS_EXAMINE = "该条记录已审核";
	public static final String ACCOUNT_WITHDRAW_CASH_RECORD_NOT_FREE_SETTING = "不要乱设置状态";
	
	
	public static final String HTTP_REQUEST_ERROR = "请求服务器错误";
	public static final String HTTP_REQUEST_PARAMETER_ERROR = "HTTP请求参数错误";

	public static final String LIVE_ID_CAN_NOT_BE_NULL = "直播ID不能为空";
	public static final String LIVE_TITLE_CAN_NOT_BE_NULL = "直播标题不能为空";

	public static final String LIVE_BE_STOP_NOT_NORMAL = "直播非正常关闭";
	
	public static final String LIVE_REQUESTLIVE_ID_CAN_NOT_BE_NULL = "直播请求黑名单ID不能为空";
	
	public static final String LIVE_RECORD_ID_CAN_NOT_BE_NULL = "回放录制ID不能为空";
	public static final String LIVE_RECORD_ATTR_CAN_NOT_BE_NULL = "推荐状态不能为空";
	public static final String LIVE_RECORD_ASSIST_COUNT_CAN_NOT_BE_NULL = "点赞数不能为空";
	public static final String LIVE_RECORD_PLAY_COUNT_CAN_NOT_BE_NULL = "播放数不能为空";
	public static final String LIVE_RECORD_SHARE_COUNT_CAN_NOT_BE_NULL = "分享数不能为空";
	public static final String LIVE_RECORD_GIFT_COUNT_CAN_NOT_BE_NULL = "礼物数不能为空";
//	public static final String LIVE_RECORD_VISIBLE_CAN_NOT_BE_NULL = "禁用启用不能为空";
	public static final String LIVE_RECORD_UPDATE_TYPE_WRONG = "修改类型错误";
		
	public static final String LIVE_ACTIVITY_TIME_EXISTS = "已有一条活动的时间段与该时间段重合";
	public static final String LIVE_ACTIVITY_TIME_CAN_NOT_BE_NULL = "活动起止时间不能为空";
	public static final String LIVE_ACTIVITY_STATUS_CAN_NOT_BE_NULL = "活动状态不能为空";
	public static final String LIVE_ACTIVITY_NAME_CAN_NOT_BE_NULL = "活动名称不能为空";
	public static final String LIVE_ACTIVITY_TYPE_CAN_NOT_BE_NULL = "邀请类型不能为空";
	public static final String LIVE_ACTIVITY_BEINVITE_COUNT_CAN_NOT_BE_NULL = "被邀请者赠送数量不能为空";
	public static final String LIVE_ACTIVITY_GOODS_CAN_NOT_BE_NULL = "充值产品不能为空";

	public static final String LOGNAME_CAN_NOT_BE_NULL = "用户名不能为空";
	public static final String LOGNAME_PATTERN_NOT_RIGHT = "用户名格式不正确";
	
	
	public static final String MESSAGE_TITLE_CAN_NOT_BE_NULL = "标题不能为空";//类型 0=打开app,1=打开直播，2=打开文本信息	int(11)	(null)	NO
	public static final String MESSAGE_TYPE_CAN_NOT_BE_NULL = "消息类型不能为空";//类型 0=打开app,1=打开直播，2=打开文本信息	int(11)	(null)	NO
	public static final String MESSAGE_REF_UP_ID_CAN_NOT_BE_NULL = "接收人不能为空";
	public static final String MESSAGE_STATUS_CAN_NOT_BE_NULL = "消息状态不能为空";//m_status	状态 0=未读 1=已读	int(11)	(null)	NO
	public static final String MESSAGE_SEND_TYPE_CAN_NOT_BE_NULL = "发送设备不能为空";//m_send_type	发送设备 =0全部；=1 android; =2 ios	tinyint(4)	(null)	NO
	public static final String MESSAGE_SEND_PERSON_CAN_NOT_BE_NULL = "发送给对象不能为空";//m_send_person	发送给对象 =0所有人； =1单人	tinyint(4)	(null)	NO
	public static final String MESSAGE_SEND_DATA_CAN_NOT_BE_NULL = "跳转数据不能为空";
	public static final String MESSAGE_SEND_PERSON_IS_UNKNOWN = "发送给对象类型未知";//m_send_person	发送给对象 =0所有人； =1单人	tinyint(4)	(null)	NO
	
	public static final String OPERATE_ERROR = "操作失败";

	public static final String OFFICIAL_MSG_FROM = "来源不能为空";
	public static final String OFFICIAL_MSG_MSG = "消息不能为空";
	public static final String OFFICIAL_MSG_EXIST = "消息已存在";
	
	public static final String PASSWORD_CAN_NOT_BE_NULL = "密码不能为空";
	public static final String PASSWORD_OLD_CAN_NOT_BE_NULL = "原始密码不能为空";
	public static final String PASSWORD_OLD_ERROR = "原始密码错误";
	public static final String PASSWORD_NEW_CAN_NOT_BE_NULL = "新密码不能为空";
	public static final String PASSWORD_NEW_CONFIRM_CAN_NOT_BE_NULL = "确认密码不能为空";
	public static final String PASSWORD_NOT_EQUALS = "新密码与确认密码不相同";
	public static final String PASSWORD_CAN_NOT_EQUALS = "新密码与旧密码不能相同";
	public static final String PASSWORD_UPDATE_FAILED = "修改密码失败" ;

	public static final String PROJECT_ID_CAN_NOT_BE_NULL = "项目ID不能为空";
	public static final String PROJECT_NOT_EXIST = "项目不存在";
	public static final String PROJECT_KEY_EXIST = "项目KEY已存在";
	
	public static final String PROJECTCDN_NOT_EXIST = "项目CDN不存在";
	public static final String PROJECTCDN_ID_CAN_NOT_BE_NULL = "项目CDN的ID不能为空";
	public static final String PROJECTCDN_NAME_CAN_NOT_BE_NULL = "项目CDN名称不能为空";
	public static final String PROJECTCDN_PUSH_CAN_NOT_BE_NULL = "推流地址不能为空";
	public static final String PROJECTCDN_PULL_RTMP_CAN_NOT_BE_NULL = "拉流地址1不能为空";
	public static final String PROJECTCDN_PULL_HLS_CAN_NOT_BE_NULL = "拉流地址2不能为空";
	public static final String PROJECTCDN_FROM = "来源不能为空";
	public static final String PROJECTCDN_LEVEL = "级别不能为空";
	
	public static final String ROBOT_ADD_U_ID_CAN_NOT_BE_NULL = "主播u_id不能为空";
	public static final String ROBOT_ADD_UP_ID_CAN_NOT_BE_NULL = "主播up_id不能为空";
	public static final String ROBOT_ADD_LIVE_ID_CAN_NOT_BE_NULL = "直播ID不能为空";
	public static final String ROBOT_ADD_BEGINTIME_CAN_NOT_BE_NULL = "开始时间不能为空";
	public static final String ROBOT_ADD_BEGINTIME_PATTERN_WRONG = "开始时间格式错误";
	public static final String ROBOT_ADD_NUMBER_ID_CAN_NOT_BE_NULL = "增加机器人数不能为空";
	public static final String ROBOT_ADD_SECOND_ID_CAN_NOT_BE_NULL = "完成时间不能为空";

	public static final String ROLE_CAN_NOT_BE_NULL = "角色不能为空";
	public static final String ROLE_NAME_EXIST = "角色名称已存在";
	public static final String ROLE_NAME_NOT_BE_NULL = "角色名称不能为空";

	public static final String REDPACKET_INVALID = "无效红包";
	
	public static final String SENSITIVE_WORD_ID_CAN_NOT_BE_NULL = "敏感词ID不能为空";
	public static final String SENSITIVE_WORD_CODE_CAN_NOT_BE_NULL = "敏感词编码不能为空";
	public static final String SENSITIVE_WORD_NAME_CAN_NOT_BE_NULL = "敏感词不能为空";
	public static final String SENSITIVE_WORD_EXIST = "敏感词已存在";
	public static final String SENSITIVE_WORD_FILE_CONTENT_IS_NULL = "敏感词文件内容为空或没有有效数据";
	public static final String SENSITIVE_WORD_FILE_PARSE_ERROR = "敏感词文件解析错误";
	public static final String SENSITIVE_WORD_SW_CODE_IS_NULL = "敏感词类型为空";
	
	public static final String STATUS_USER_OR_NOT_CAN_NOT_BE_NULL = "启用/禁用状态不能为空";
	
	public static final String TIME_BEGIN_CAN_NOT_BE_NULL = "开始时间不能为空";
	public static final String TIME_END_CAN_NOT_BE_NULL = "结束时间不能为空";
	public static final String TIME_PATTERN_IS_NOT_RIGHT = "时间格式不正确";

	public static final String TITLE_CAN_NOT_BE_NULL = "标题不能为空";

	public static final String TOPIC_ID_CAN_NOT_BE_NULL = "话题ID不能为空";
	public static final String TOPIC_NAME_EXIST = "话题名称已存在";
	public static final String TOPIC_NAME_CAN_NOT_BE_NULL = "话题名称不能为空";
	public static final String TOPIC_ORDER_CAN_NOT_BE_NULL = "话题排序号不能为空";
	
	public static final String PRM_USER_ID_CAN_NOT_BE_NULL = "用户ID不能为空";
	public static final String USER_CAN_NOT_BE_NULL = "用户不能为空";
	public static final String USER_LOG_NAME_CAN_NOT_BE_NULL = "用户登录名不能为空";
	public static final String USER_NAME_EXIST = "用户名已存在";
	public static final String USER_NOT_EXIST = "用户不存在";
	public static final String USER_PHONE_CAN_NOT_BE_NULL = "手机号码不能为空";
	public static final String USER_PASSWORD_CAN_NOT_BE_NULL = "密码不能为空";
	public static final String USER_PASSWORD_ERROR = "用户名或密码错误";

	public static final String USER_ID_CAN_NOT_BE_NULL = "注册用户ID不能为空";

	public static final String USER_IMPEACH_TYPE_TYPE_CAN_NOT_BE_NULL = "举报类型不能为空";
	
	
}
