package com.example.a666.petapp.httpinf;

/**
 * Created by 666 on 2017/12/6.
 */

public interface Callbacks<T> {
    //失败的方法
    void Failure(String str);

    //成功的方法
    void Succeed(T str);
}
