package cn.blunce.mylib;

import android.app.Application;

/**
 * Created by Blunce on 2015/10/26 0026.
 */
public class MyApp extends Application {

    // QQ interface Btn的标识
    public static final int BTN_FLAG_MESSAGE = 0x01;
    public static final int BTN_FLAG_CONTACTS = 0x02;
    public static final int BTN_FLAG_NEWS = 0x03;
    public static final int BTN_FLAG_SETTING = 0x04;

    // QQ interface Fragment的标识
    public static final String FRAGMENT_FLAG_MESSAGE = "消息";
    public static final String FRAGMENT_FLAG_CONTACTS = "联系人";
    public static final String FRAGMENT_FLAG_NEWS = "新闻";
    public static final String FRAGMENT_FLAG_SETTING = "设置";
    public static final String FRAGMENT_FLAG_SIMPLE = "simple";
}
