package com.caihui.zlzb_test.bean;

/**
 * @auther by yushilei.
 * @time 2017/5/15-12:52
 * @desc
 */

/**
 * 服务器Response common 部分
 *
 * @param <T> 接口具体返回的Json数据 实体类类型
 */
public class Res<T> {
    private int code;
    private String message;
    private boolean success;
    private String taskId;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
