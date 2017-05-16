package com.caihui.zlzb_test.bean;

import android.text.TextUtils;
import android.util.Log;

import com.caihui.zlzb_test.tool.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Response;

/**
 * @auther by yushilei.
 * @time 2017/5/16-10:02
 * @desc
 */

/**
 * 描述一次网络请求数据信息
 */
public class NetData implements Serializable {

    private String name;

    private String netUrl;

    private long timeStart;

    private long timeEnd;
    //响应码
    private int responseCode;
    //
    private String requestJson;
    //接口返回的Json
    private String responseJson;
    //请求Headers
    private Map<String, List<String>> requestHeadersMap;
    //响应头
    private Map<String, List<String>> responseHeadersMap;
    //协议
    private String netProtocol;

    private boolean isHttps;

    private String netMethod;

    public NetData() {
    }

    public <T> NetData(String apiName, Response<Res<T>> response) {
        name = apiName;
        okhttp3.Response rawRes = response.raw();
        Request request = rawRes.request();
        timeStart = rawRes.sentRequestAtMillis();
        timeEnd = rawRes.receivedResponseAtMillis();
        netUrl = request.url().toString();
        responseCode = response.code();
        requestHeadersMap = request.headers().toMultimap();
        responseHeadersMap = response.headers().toMultimap();
        netProtocol = rawRes.protocol().toString();
        responseJson = JsonUtil.getPrettyPrintingStr(response.body());
        requestJson = JsonUtil.getPrettyPrintingStr(getResStr(request));

        isHttps = request.isHttps();
        netMethod = request.method();
    }

    private String getResStr(Request request) {
        String data = null;
        try {
            RequestBody requestBody = request.body();
            if (requestBody == null) {
                return null;
            }
            Buffer sink = new Buffer();
            requestBody.writeTo(sink);
            ByteString bs = sink.readByteString();
            data = bs.utf8();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getNetMethod() {
        return netMethod;
    }

    public void setNetMethod(String netMethod) {
        this.netMethod = netMethod;
    }

    public boolean isHttps() {
        return isHttps;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public long getDuration() {
        return timeEnd - timeStart;
    }

    public String getNetProtocol() {
        return netProtocol;
    }

    public void setNetProtocol(String netProtocol) {
        this.netProtocol = netProtocol;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public Map<String, List<String>> getRequestHeadersMap() {
        return requestHeadersMap;
    }

    public void setRequestHeadersMap(Map<String, List<String>> requestHeadersMap) {
        this.requestHeadersMap = requestHeadersMap;
    }

    public Map<String, List<String>> getResponseHeadersMap() {
        return responseHeadersMap;
    }

    public void setResponseHeadersMap(Map<String, List<String>> responseHeadersMap) {
        this.responseHeadersMap = responseHeadersMap;
    }
}
