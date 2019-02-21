package com.gxc15090111.garage.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
* @author 作者 gxc15090111
* @version 创建时间：2018年10月12日 上午11:38:59
*/
public class StringUtil {
	/**
	 * 字符串为 null 或者为  "" 时返回 true
	 * @param str - 传入的字符串
	 * @return 是否为空
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * 字符串不为 null 而且不为  "" 时返回 true
	 * @param str - 传入的字符串
	 * @return 是否不为空
	 */
	public static boolean notBlank(String str) {
		return str != null && !"".equals(str.trim());
	}
	
	/**
	 * 判断N个字符串是否都不为空
	 * @param strings - 传入的N个字符串（可变参数个数）
	 * @return 有一个为空，则返回false
	 */
	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}
	
	/**
	 * 首字母变小写
	 * @param str - 转换前字符串
	 * @return 转换后字符串
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * 首字母变大写
	 * @param str - 转换前字符串
	 * @return 转换后字符串
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	/**
	 * 将以下划线分隔的字符串每个下划线后面的第一个字母大写
	 * @param stringWithUnderline -传入的字符串
	 * @return 处理后的字符串
	 */
	public static String toCamelCase(String stringWithUnderline) {
		if (stringWithUnderline.indexOf('_') == -1)
			return stringWithUnderline;
		
		stringWithUnderline = stringWithUnderline.toLowerCase();
		char[] fromArray = stringWithUnderline.toCharArray();
		char[] toArray = new char[fromArray.length];
		int j = 0;
		for (int i=0; i<fromArray.length; i++) {
			if (fromArray[i] == '_') {
				// 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
				i++;
				if (i < fromArray.length)
					toArray[j++] = Character.toUpperCase(fromArray[i]);
			}
			else {
				toArray[j++] = fromArray[i];
			}
		}
		return new String(toArray, 0, j);
	}
	
	/**
	 * 将字符串数组内所有的元素拼接一起
	 * @param stringArray - 传入的数组
	 * @return 拼接后的字符串
	 */
	public static String join(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		for (String s : stringArray)
			sb.append(s);
		return sb.toString();
	}
	
	/**
	 * 将字符串数组内所有的元素以某一个分隔符拼接一起
	 * @param stringArray - 传入的数组
	 * @param separator - 分隔符
	 * @return 拼接后的字符串
	 */
	public static String join(String[] stringArray, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<stringArray.length; i++) {
			if (i>0)
				sb.append(separator);
			sb.append(stringArray[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 将List内所有的元素以某一个分隔符拼接一起
	 * @param list - 传入的list
	 * @param separator - 分隔符
	 * @return 拼接后的字符串
	 */
	public static String join(List<Integer> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<list.size(); i++) {
			if (i>0)
				sb.append(separator);
			sb.append(String.valueOf(list.get(i)));
		}
		return sb.toString();
	}
	
	/** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&amp;”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
	public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
	/**
	 * 判断{from}是否包含{to}
	 * @param from
	 * @param to
	 * @return true=包含 false=不包含
	 */
	public static Boolean isContain(String from,String to) {
		if(from.indexOf(to)>-1){
			return true;
		}else
			return false;
    }
	
	/**
	 * 检查字符串是否是Email格式
	 * @return 是-true,否-false
	 */
	public static boolean isEmail(String str){
		if(isBlank(str)){
			return false;
		}
		
		String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		return str.matches(regex);
	}
	
	/**
	 * 将ids使用逗号分隔后存储
	 * @param ids
	 * @param clazz
	 * @return
	 */
	public static List getList(String ids, Class clazz){
		List list = new ArrayList();
		if(notBlank(ids)){
			String[] array = ids.split(",");
			for (int i = 0; i < array.length; i++) {
				if(Integer.class.getName().equals(clazz.getName())){
					list.add(Integer.valueOf(array[i]));
				} else if(String.class.getName().equals(clazz.getName())){
					list.add(array[i]);
				}
			}
		}
		return list;
	}
	
	/**
	 * 将Integer数组转成String数组
	 * @param array Integer数组
	 * @return String数组
	 */
	public static String[] integerToString(Integer[] array){
		if(array == null || array.length == 0){
			return null;
		}
		
		String[] array2 = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			array2[i] = String.valueOf(array[i]);
		}
		return array2;
	}

	/**
	 * 将String数组转成Integer数组
	 * @param array String数组
	 * @return Integer数组
	 */
	public static Integer[] stringToInteger(String[] array){
		if(array == null || array.length == 0){
			return null;
		}
		
		Integer[] array2 = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			array2[i] = Integer.valueOf(array[i]);
		}
		return array2;
	}
	
	/**
	 * 将List的每个对象以String数组方式返回
	 * @param list - 集合
	 * @return String数组
	 */
	public static String[] getStringValueFromList(List<Object> list){
		if(NullUtil.isNull(list)){
			return new String[]{};
		}
		
		String[] s = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			s[i] = String.valueOf(list.get(i));
		}
		return s;
	}
	
	/**
	 * 将map中的key值以String数组形式返回
	 * @param map map容器
	 * @return String数组
	 */
	public static String[] getStringKeyArrayFromMap(Map map){
		if(NullUtil.isNull(map)){
			return null;
		}
		//将id以逗号分隔
		String[] array = new String[map.size()];
		Iterator iterator = map.keySet().iterator();
		int index = 0;
		while(iterator.hasNext()){
			array[index++] = String.valueOf(iterator.next());
		}
		return array;
	}
	
	/**
	 * 字符串前补充字符
	 * @param src - 源字符串
	 * @param length - 补充后的总长度
	 * @param c - 使用什么字符补充
	 * @return 补充后的结果
	 */
	public static String addCharacterBefore(String src, int length, char c){
		int len = length - src.length();
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < len; i++) {
			s.append(c);
		}
		s.append(src);
		return s.toString();
	}
	
	/**
	 * 字符串后补充字符
	 * @param src - 源字符串
	 * @param length - 补充后的总长度
	 * @param c - 使用什么字符补充
	 * @return 补充后的结果
	 */
	public static String addCharacterAfter(String src, int length, char c){
		int len = length - src.length();
		StringBuffer s = new StringBuffer();
		s.append(src);
		for (int i = 0; i < len; i++) {
			s.append(c);
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(addCharacterBefore("123", 10, '0'));
		System.out.println(addCharacterAfter("123", 10, '9'));
	}
}
 