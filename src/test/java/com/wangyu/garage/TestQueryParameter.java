package com.wangyu.garage;

import com.wangyu.common.page.PageQueryParameter;
import com.wangyu.garage.util.StringUtil;
import junit.framework.TestCase;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestQueryParameter extends TestCase {
	
	public String getClassName() throws Exception{
		String className = "";
		className = "com.wangyu.garage.parameter.StopRecordingQueryParameter";
//		className = "";
		return className;
	}
	
	public void testJs()  throws Exception{
		String className = "";
		className = "com.joygo.star.live.entity.page.RedpacketSendPageQueryParameter";
//		className = "";
		
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		
		/*
			$("#rs_type").val("");
			
			var rs_type = $("#rs_type").val();
			
			"rs_type": rs_type,
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			p("$(\"#" + name +"\").val(\"\");");
		}
		

		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			p("var " + name + " = $(\"#"  + name + "\").val();");
		}
		
		fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			p("\"" + name + "\": "  + name + ",");
		}
	}
	
	public void testSaveParameter() throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		
		/*
			paramsMap.put("aa_type", getValueForParamsMap(model.getAa_type()));
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			
			p("paramsMap.put(\"" + name + "\", getValueForParamsMap(model.get" + StringUtil.firstCharToUpperCase(name) + "()));");
		}
	}

	public void testNullForSave() throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		
		/*
		 if(model.getP_id() != null){
			return renderError(Code.FAIL, MessageConstants._CAN_NOT_BE_NULL);
		 }
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			if(type.getName().equals(String.class.getName())){
				p("if(StringUtil.notBlank(model.get" + StringUtil.firstCharToUpperCase(name) + "())){");
			} else if (type.getName().equals(Integer.class.getName())){
				p("if(model.get" + StringUtil.firstCharToUpperCase(name) + "() == null){");
//			} else if (type instanceof String[]){
//				p("if(model.get" + StringUtil.firstCharToUpperCase(name) + "() == null){");
			} else {
				continue;
			}
			p("return renderError(Code.FAIL, MessageConstants._CAN_NOT_BE_NULL);");
			p("}");
		}
	}
	
	public void testQueryParameter() throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		
		/*
		 if(parameter.getP_id() != null){
			paramsMap.put("p_id", String.valueOf(parameter.getP_id()));
		 }
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			
			if(type.getName().equals(String.class.getName())){
				p("if(StringUtil.notBlank(parameter.get" + StringUtil.firstCharToUpperCase(name) + "())){");
				p("paramsMap.put(\"" + name + "\",parameter.get" + StringUtil.firstCharToUpperCase(name) + "());");
			} else if (type.getName().equals(Integer.class.getName())){
				p("if(parameter.get" + StringUtil.firstCharToUpperCase(name) + "() != null){");
				p("paramsMap.put(\"" + name + "\",String.valueOf(parameter.get" + StringUtil.firstCharToUpperCase(name) + "()));");
			} else {
				continue;
			}
				
			p("}");
		}
	}
	
	public void testSave() throws Exception{
		String className = "com.joygo.star.live.model.LiveRequestLiveModel";
		className = "com.joygo.star.live.model.AnchorAdminModel";
		className = "com.joygo.star.live.model.TopicModel";
		className = "com.joygo.star.live.model.GoodsModel";
		className = "com.joygo.star.live.model.UserLogModel";
		className = "com.joygo.star.live.model.LiveRecordModel";
//		Class clazz = Class.forName(getClassName());
		Class clazz = Class.forName(className);
		Object o = clazz.newInstance();
		
		/*
		paramsMap.put("lr_id", model.getLr_id());
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			
			if(type.getName().equals(String.class.getName())){
				p("paramsMap.put(\"" + name + "\", model.get" + StringUtil.firstCharToUpperCase(name) + "());");
			} else if (type.getName().equals(Integer.class.getName())){
				p("paramsMap.put(\"" + name + "\", String.valueOf(model.get" + StringUtil.firstCharToUpperCase(name) + "()));");
			} else {
				p("paramsMap.put(\"" + name + "\", model.get" + StringUtil.firstCharToUpperCase(name) + "());");
				
			}
		}
	}
	
	public void testUnitSave() throws Exception{
		String className = "com.joygo.star.live.model.LiveRequestLiveModel";
//		Class clazz = Class.forName(getClassName());
		Class clazz = Class.forName(className);
		Object o = clazz.newInstance();
		
		/*
		param += "pc_name=项目cdn" + maxid + "&";
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			
			p("param += \"" + name + "=" + name + "\" + maxid + \"&\";");
		}
	}
	
	public void testSqlMap() throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		
		/*
			<if test="begintime != ''">
				and mr.mr_time &gt;= #{begintime}
			</if>
		
			<if test="endtime != ''">
				and mr.mr_time &lt;= #{endtime}
			</if>
				
			<if test="mr_type != null">
				and mr.mr_type = #{mr_type}
			</if>
			<if test="ref_live_title != ''">
				and l.live_title like CONCAT('%',#{ref_live_title},'%')
			</if>
		 */
		Field[] fields = clazz.getDeclaredFields();
		
		String prffex = "";
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
//			p(type.getName());
			String name = field.getName(); 
			
			if(type.getName().equals(String.class.getName())){
				System.out.print("<if test=\"" + name + " != null and " + name + " != ''\">");
				System.out.print("and " + prffex + name + " like CONCAT('%',#{" + name + "},'%')");
			} else if (type.getName().equals(Integer.class.getName())
                    || type.getName().equals(Long.class.getName())){
				System.out.print("<if test=\"" + name + " != null\">");
				System.out.print("and " + prffex + name + " = #{" + name + "}");
			} else {
				continue;
			}
			p("</if>");
		}
	}
	
	public void testJdbc_Query()throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		/*
			model.setLive_id(resultSet.getInt("live_id"));
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
			String typeStr = "";
			if(type.getName().equals("java.lang.String")){
				typeStr = "String";
			}
			if(type.getName().equals("java.lang.Integer")){
				typeStr = "Int";
			}
				
//			p(type.getName());
			String name = field.getName(); 
			
			p("model.set" + StringUtil.firstCharToUpperCase(name) + "(resultSet.get" + typeStr + "(\"" + name + "\"));");
		}
		
	}
	
	public void testJdbc_Insert()throws Exception{
		Class clazz = Class.forName(getClassName());
		Object o = clazz.newInstance();
		/*
			statement.setInt(++index, model.getSdl_id());
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
			String typeStr = "";
			if(type.getName().equals("java.lang.String")){
				typeStr = "String";
			}
			if(type.getName().equals("java.lang.Integer")){
				typeStr = "Int";
			}
			
//			p(type.getName());
			String name = field.getName(); 
			
			p("statement.set" + typeStr + "(++index, model.get" + StringUtil.firstCharToUpperCase(name) + "());");
		}
		
	}
	
	private void setAttr(Object object, Class clazz, Map map){
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Class type = field.getType();
			String fieldName = field.getName();
			String getMethodName = null;
			if(type.getName().equals("java.lang.Boolean")){
				getMethodName = "is" + StringUtil.firstCharToUpperCase(fieldName);
			}else{
				getMethodName = "get" + StringUtil.firstCharToUpperCase(fieldName);
			}
			
			Method[] methods = clazz.getMethods();
			Method getMethod = null;
			for (int j = 0; j < methods.length; j++) {
				Method md = methods[j];
				if (md.getName().equals(getMethodName) && md.getParameterTypes().length == 0) {
					getMethod= md;
				}
			}
			try {
				Object value = getMethod.invoke(object, null);
				map.put(fieldName, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		if(clazz.getClass().equals(PageQueryParameter.class.getName())){
			return;
		}
		
		Class superClass = clazz.getSuperclass();
		object = (PageQueryParameter)object;
//		object = superClass.cast(object);
		setAttr(object, superClass, map);
	}
	
	public void testValueConvert(){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		int a1 = 11111;
		Integer a2 = 22222;
		boolean b2 = true;
		Boolean b1 = false;
		String s1 = "12312321";
		String[] s2 = {"12312321" , "asdasda"};
		Date d = new Date();
		
		
		Object o =null;
		o =	getValueForParamsMap(a1);
		o =	getValueForParamsMap(a2);
		o =	getValueForParamsMap(b1);
		o =	getValueForParamsMap(b2);
		o =	getValueForParamsMap(s1);
		o =	getValueForParamsMap(s2);
		o =	getValueForParamsMap(d);
		p(1);
	}
	
	public Object getValueForParamsMap(Object value){
		if(value == null){
			return null;
		}
		
		if(value instanceof String || value instanceof String[]){
			return value;
		}
		
		if(value instanceof Integer
				|| value instanceof Boolean
				|| value instanceof Byte
				|| value instanceof Short
				|| value instanceof Long
				|| value instanceof Double
				|| value instanceof Float
				|| value instanceof Character
				){
			return String.valueOf(value);
		} else {
			throw new RuntimeException("data type error !!!");
		}
	}
	
	public void listDir(){
		String path = "E:/joygo/sts-bundle/workspace_sts/star-bbm-ui/src/main/webapp/component";
		
		File file = new File(path);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if(f.isDirectory()){
				p(f.getName());
			}
		}
	}

	public static void p(Object o){
		System.out.println(o);
	}
}
