package com.caihui.zlzb_test.bean;

/**
 * @auther by yushilei.
 * @time 2017/5/17-17:39
 * @desc
 */

/**
 * 一次网络请求
 *
 * @param <T> 请求参数
 * @param <E> 响应参数
 */
public class NetJop<T extends Req, E> {
    private T req;
    private E res;

    public NetJop(T req) {
        this.req = req;
    }

    public T getReq() {
        return req;
    }

    public void setReq(T req) {
        this.req = req;
    }

    public E getRes() {
        return res;
    }

    public void setRes(E res) {
        this.res = res;
    }
}
