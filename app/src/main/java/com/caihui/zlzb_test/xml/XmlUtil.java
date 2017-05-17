package com.caihui.zlzb_test.xml;

import android.util.Log;

import com.caihui.zlzb_test.tool.ToastUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.InputStream;

/**
 * @auther by yushilei.
 * @time 2017/5/17-14:01
 * @desc
 */

/**
 * XML 文件解析
 */
public class XmlUtil {
    private XmlUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(InputStream in, Class<T> tClass) {
        XStream xstream = new XStream(new XppDriver());
        xstream.processAnnotations(tClass);
        xstream.ignoreUnknownElements();
        T entity = null;
        try {
            entity = (T) xstream.fromXML(in);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.toast("XML反序列化失败！");
        }
        return entity;
    }
}
