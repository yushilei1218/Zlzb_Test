package com.caihui.zlzb_test.bean;

import com.caihui.zlzb_test.tool.TokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther by yushilei.
 * @time 2017/5/16-14:53
 * @desc
 */

/**
 * 已发布职位 请求参数描述类
 */
public class JobMiniListReq {
    /**
     * status	String	true	职位状态,多个以逗号分隔
     * pageIndex	int	true	页码,从1开始
     * pageSize	int	true	页容量
     */
    private int pageSize = 50, pageIndex, jobStyle;
    private String status;

    public Map<String, Object> getQueryMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("pageIndex", pageIndex);
        map.put("jobStyle", jobStyle);
        map.put("status", status);
        Map<String, String> tokenMap = TokenUtil.getTokenMap();
        if (tokenMap != null) {
            map.putAll(tokenMap);
        }
        return map;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getJobStyle() {
        return jobStyle;
    }

    public void setJobStyle(int jobStyle) {
        this.jobStyle = jobStyle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
