/**
 * @描述		:	
 * @作�??	    :Android - csx
 * @创建日期  :2016�?3�?12�? 上午11:18:12  
 *
 */
package com.example.a666.petapp.utils;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.widget.Toast;

/**
 * @描述 :Toast工具�?
 * @作�?? :Android - csx
 * @创建日期 :2016�?3�?12�? 上午11:18:12
 * 
 */
public class ToastUtil {

	// 全局的上下文
	private static Context context = null;
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	public static void makeText(Context mContext, String text) {
		try {
			mHandler.removeCallbacks(r);
			if (mToast != null)
				mToast.setText(text);
			else
				mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
			mHandler.postDelayed(r, 1000);

			mToast.show();
		} catch (Exception e) {
		}
	}

	public static void makeText(Context mContext, int resId) {
		try {
			makeText(mContext, mContext.getResources().getString(resId));
		} catch (NotFoundException e) {
		}
	}

	/**
	 * 
	 * @描述 :初始化方�?
	 * @方法名称 :init----&gt;ToastUtil.java
	 * @作�?? :Android - csx
	 * @创建日期 :2016�?3�?12�? 上午11:18:54
	 * 
	 */
	public static void init(Context mContext) {
		context = mContext;
	}

	/**
	 * 
	 * @描述 :文字提示
	 * @方法名称 :show----&gt;ToastUtil.java
	 * @作�?? :Android - csx
	 * @创建日期 :2016�?3�?12�? 上午11:25:12
	 * @param txt
	 * 
	 */
	public static void show(String txt) {
		makeText(context, txt);
	}

	/**
	 * 
	 * @描述 :id提示
	 * @方法名称 :show----&gt;ToastUtil.java
	 * @作�?? :Android - csx
	 * @创建日期 :2016�?3�?12�? 上午11:25:47
	 * @param resId
	 * 
	 */
	public static void show(int resId) {
		makeText(context, resId);
	}

}
