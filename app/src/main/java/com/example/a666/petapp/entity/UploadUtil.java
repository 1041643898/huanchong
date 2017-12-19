package com.example.a666.petapp.entity;

import android.content.Context;
import android.util.Log;

import com.example.a666.petapp.entity.ImageUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 666 on 2017/12/19.
 */

public class UploadUtil {

    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码

    private static Context CONTEXT = null;

    public static void init(Context context) {
        CONTEXT = context;
    }

    /**
     * android上传文件到服务器
     *
     * @param file
     *            �?要上传的文件
     * @param RequestURL
     *            请求的rul
     * @param sb
     * @return 返回响应的内�?
     */
    public static String uploadFile(File file, String RequestURL, String json) {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入�?
            conn.setDoOutput(true); // 允许输出�?
            conn.setUseCaches(false); // 不允许使用缓�?
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("contentType", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);

            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意�? name里面的�?�为服务器端�?要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后�?名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=" + json
                        + ";filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                Log.e("CXP", "==" + sb);
                is.close();

                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应�? 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                InputStream input = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        input, CHARSET));
                StringBuffer sb1 = new StringBuffer();
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb1.append(line);
                }
                result = sb1.toString();
                // 删除HuanHuan下所有的文件
               ImageUtils.deleteAllHuanHuanPhoto();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @描述 :多文件上�?
     * @方法名称 :uploadFile---->UploadUtil.java @作�?? :Android - csx
     * @创建日期 :2016�?4�?7�? 上午9:21:15
     * @param files
     * @param RequestURL
     * @param params
     * @return
     *
     */
    public static String uploadFile(Map<String, File> files, String RequestURL,
                                    Map<String, Object> params) {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入�?
            conn.setDoOutput(true); // 允许输出�?
            conn.setUseCaches(false); // 不允许使用缓�?
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("contentType", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            /**
             * 上传文件
             */
            for (Map.Entry<String, File> file : files.entrySet()) {
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意�? name里面的�?�为服务器端�?要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后�?名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data;name=\""
                        + file.getKey() + "\"; filename=\""
                        + file.getValue().getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file.getValue());
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                Log.e("CXP", "======update_pet====" + sb.toString());
            }

            // 首先组拼文本类型的参�?
            StringBuilder sb1 = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String json = CJSON.toJSONEntity(params.get(entry.getKey()));
                sb1.append(PREFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINE_END);
                sb1.append("Content-Disposition: form-data; name=\"" + json
                        + "\"" + LINE_END);
                sb1.append("Content-Type: text/plain; charset=" + CHARSET
                        + LINE_END);
                sb1.append("Content-Transfer-Encoding: 8bit" + LINE_END);
                sb1.append(LINE_END);
                sb1.append(LINE_END);
            }
            Log.e("CXP", "======update_pet====" + sb1.toString());
            dos.write(sb1.toString().getBytes());

            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                    .getBytes();
            dos.write(end_data);
            dos.flush();

            /**
             * 获取响应�? 200=成功 当响应成功，获取响应的流
             */
            StringBuilder sb3 = new StringBuilder();
            int res = conn.getResponseCode();
            InputStream input = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input,
                    CHARSET));
            String line = "";
            while ((line = in.readLine()) != null) {
                sb3.append(line);
            }
            result = sb3.toString();
            // 删除HuanHuan下所有的文件
            ImageUtils.deleteAllHuanHuanPhoto();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
