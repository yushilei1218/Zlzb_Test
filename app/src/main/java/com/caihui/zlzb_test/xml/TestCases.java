package com.caihui.zlzb_test.xml;

import com.caihui.zlzb_test.BaseApp;
import com.caihui.zlzb_test.R;
import com.caihui.zlzb_test.bean.JobMiniListReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @auther by yushilei.
 * @time 2017/5/17-13:47
 * @desc
 */

/**
 * 已发布职位列表Case 集合
 */
@XStreamAlias("TestCases")
public class TestCases {
    @XStreamImplicit(itemFieldName = "JobMiniListReq")
    private List<JobMiniListReq> data;

    /**
     * 通过 Raw存储的XML case文件 来加载Case
     *
     * @return TestCases or null
     */
    public static TestCases fromRaw() {
        return XmlUtil.fromXml(BaseApp.getRes().openRawResource(R.raw.test_cases), TestCases.class);
    }

    public List<JobMiniListReq> getData() {
        return data;
    }

    public void setData(List<JobMiniListReq> data) {
        this.data = data;
    }
}
