package com.example.a666.petapp.entity.cxp;

import android.os.Handler;
import android.os.Message;

import com.example.a666.petapp.entity.CloudUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CopyOnWriteArrayList;

public class PulldataHandler extends ProtocalHandler {
	private PushDataListener mListener;
	private boolean isPush = true;
	private String str;
	private String ids;
	private CopyOnWriteArrayList<CloudUser> mDataList = new CopyOnWriteArrayList<CloudUser>();

	public PulldataHandler(PushDataListener listener, String str) {
		this.mListener = listener;
		this.str = str;
		if (str.equals("create")) {
			create.start();
		} else if (str.equals("update")) {
			update.start();
		} else if (str.equals("delete")) {
			delete.start();
		}

	}

	/**
	 * 加入上传列表
	 * 
	 * @param bean
	 */
	public void addTask(CloudUser bean) {
		mDataList.add(bean);
	}

	public void removeTask(String str) {
		ids = str;
	}

	/**
	 * 销毁上传线程
	 */
	public void destroy() {
		isPush = false;
	}

	/**
	 * 获取上传单条数据
	 */
	@Override
	protected String getUserJSONString() {
		if (mDataList.size() > 0) {
			Gson gson = new Gson();
			CloudUser bean = mDataList.get(0);
			mDataList.remove(0);
			return gson.toJson(bean);
		} else if (!ids.equals("") && ids != null) {
			return ids;
		}
		return new String();
	}

	/**
	 * 接收上传数据结果
	 */
	Handler upHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				String result = (String) msg.obj;
				boolean succeed = false;
				String info = "";
				String id = "";
				try {
					JSONObject jobj = new JSONObject(result);
					int status = jobj.getInt("status");
					info = jobj.getString("info");
					id = jobj.getString("_id");
					if (status == 0) {
						succeed = false;
					} else {
						succeed = true;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				} finally {
					if (mListener != null) {
						mListener.onPushFinish(succeed, info, id);
					}
				}

			}
		}

	};
	/**
	 * 添加数据线程
	 */
	Thread create = new Thread() {
		@Override
		public void run() {
			while (isPush) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (mDataList.size() <= 0) {
					continue;
				}
				String result = getData(str);
				Message msg = new Message();
				msg.what = 1;
				msg.obj = result;
				upHandler.sendMessage(msg);

			}

		}
	};

	/**
	 * 更新数据线程
	 */
	Thread update = new Thread() {
		@Override
		public void run() {
			while (isPush) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (mDataList.size() <= 0) {
					continue;
				}
				String result = getData(str);
				Message msg = new Message();
				msg.what = 1;
				msg.obj = result;
				upHandler.sendMessage(msg);

			}

		}
	};

	/**
	 * 删除数据线程
	 */
	Thread delete = new Thread() {
		@Override
		public void run() {
			while (isPush) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String result = getData(str);
				Message msg = new Message();
				msg.what = 1;
				msg.obj = result;
				upHandler.sendMessage(msg);
			}

		}
	};


}
