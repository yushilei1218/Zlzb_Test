package com.caihui.zlzb_test.bean;

import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/5/16-15:30
 * @desc
 */

/**
 * 已发布职位列表接口响应
 */
public class JobDtoBeanRes {
    private List<JobDtoBean> list;

    public List<JobDtoBean> getList() {
        return list;
    }

    public void setList(List<JobDtoBean> list) {
        this.list = list;
    }
}
