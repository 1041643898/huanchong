package com.example.a666.petapp.entity;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.a666.petapp.utils.AppUtils;
import com.example.a666.petapp.utils.EntityToMap;
import com.example.a666.petapp.utils.FileUtil;
import com.example.a666.petapp.utils.MapUtils;
import com.example.a666.petapp.utils.SignUtil;
import com.example.a666.petapp.utils.TableUtils;
import com.example.a666.petapp.utils.TokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CJSON extends JSON {

	private static final String START_BRACES = "{";
	private static final String END_BRACES = "}";
	private static final String START_CONTENT = "\"";
	private static final String END_CONTENT = "\"";
	private static final String COMMA = ",";
	private static final String COLON = ":";

	/**
	 *
	 * @描述 : 获取JSON中的DESC
	 * @方法名称 : getDESC---->CJSON.java @作�?? : Android - yhq
	 * @创建日期 : 2016�???3�???31�??? 下午9:33:46
	 * @param json
	 * @return
	 */
	public static String getDESC(String json) {
		return CJSON.parseObject(json).getString("desc");
	}

	/**
	 *
	 * @描述 : 获取JSON中的DESC
	 * @方法名称 : getDESC---->CJSON.java @作�?? : Android - yhq
	 * @创建日期 : 2016�???3�???31�??? 下午9:33:46
	 * @param json
	 * @return
	 */
	public static String getRESULT(String json) {
		return CJSON.parseObject(json).getString("result");
	}

	/**
	 *
	 * @描述 : 获取JSON中的RET
	 * @方法名称 : getDESC---->CJSON.java @作�?? : Android - yhq
	 * @创建日期 : 2016�???3�???31�??? 下午9:33:46
	 * @param json
	 * @return
	 */
	public static Boolean getRET(String json) {
		return CJSON.parseObject(json).getBoolean("ret");
	}

	/**
	 * JSON�???
	 */
	public static final String HEADER = "header";
	/**
	 * data
	 */
	public static final String DATA = "data";
	/**
	 * JSON内容
	 */
	public static final String BODY = "body";
	/**
	 * 平台
	 */
	public static final String CHANNEL = "channel";
	private static StringBuffer json;

	/**
	 *
	 * @描述 :
	 * @方法名称 : toJSON---->CJSON.java @作�?? : Android - yhq
	 * @创建日期 : 2016�???3�???24�??? 上午11:30:06
	 * @param context
	 *            当前上下�???
	 * @param maps
	 *            body的参�???
	 * @param param
	 *            生成sign的参�???
	 * @return
	 */
	public static String toJSONMap(Map<String, Object> param) {
		buildJSON(AppUtils.getAppContext(), param);
		return json.toString();
	}

	public static <T> String toJSONEntity(T t) {
		try {
			buildJSON(AppUtils.getAppContext(), EntityToMap.ConvertObjToMap(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	private static void buildJSON(Context context, Map<String, Object> params) {
		params = MapUtils.sortMapByKey(params);
		json = new StringBuffer();
		json.append(START_BRACES + START_CONTENT);
		json.append(HEADER + END_CONTENT + COLON);
		json.append(START_BRACES);
		toContent(SignUtil.SIGN, SignUtil.getSign(params));
		json.append(COMMA);
		//toContent(ConnectionUtils.IP, ConnectionUtils.getIp(context));
		json.append(COMMA);
		toContent(CHANNEL, "android");
		json.append(COMMA);
		if (AppUtils.getUser() != null
				&& AppUtils.getUser().getUserId() != null) {
			toContent(TableUtils.UserInfo.USERID, AppUtils.getUser()
					.getUserId());
			json.append(COMMA);
		}
		toContent(TokenUtil.TOKEN, FileUtil.getToken());
		json.append(END_BRACES + COMMA);
		json.append(START_CONTENT + BODY + END_CONTENT);
		json.append(COLON);
		json.append(START_BRACES);
		if (params != null) {
			List<String> keys = new ArrayList<String>(params.keySet());
			int count = keys.size();
			for (int i = 0; i < count; i++) {
				if (params.get(keys.get(i)) != null) {
					if (params.get(keys.get(i)).equals("null")
							|| params.get(keys.get(i)).equals("0")) {
						count--;
						continue;
					}
					toContent(keys.get(i), params.get(keys.get(i)));
					if (!(i == count - 1))
						json.append(COMMA);
				}
			}
		}

		json.append(END_BRACES + END_BRACES);
	}

	/**
	 *
	 * @描述 : 生成body的内�???
	 * @方法名称 : toContent---->CJSON.java @作�?? : Android - yhq
	 * @创建日期 : 2016�???3�???24�??? 上午11:39:11
	 * @param str1
	 *            �???
	 * @param str2
	 *            �???
	 */
	private static void toContent(String str1, Object str2) {
		json.append(START_CONTENT);
		json.append(str1);
		json.append(END_CONTENT);
		json.append(COLON);
		if (str2 != null && str2 != "" && (str2 instanceof List)) {
			json.append(CJSON.toJSONString(str2));
		} else {
			json.append(START_CONTENT);
			json.append(str2);
			json.append(END_CONTENT);
		}
	}

}
