package cn.blunce.qq_interface;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import cn.blunce.mylib.MyApp;
import cn.blunce.mylib.R;

public class QQ_interface extends Activity implements BottomControlPanel.BottomPanelCallback {

    private BottomControlPanel bottomControlPanel;
    private HeadControlPanel headControlPanel;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private ContactFragment contactFragment;
    private MessageFragment messageFragment;
    private NewsFragment newsFragment;
    private SettingFragment settingFragment;

    private String currFragTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_interface);

        initView();
        fragmentManager = getFragmentManager();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        setTabSelection(MyApp.FRAGMENT_FLAG_MESSAGE);
        bottomControlPanel.defaultBtnChecked();
    }

    private void initView() {
        bottomControlPanel = (BottomControlPanel) findViewById(R.id.bottom_layout);
        if (bottomControlPanel != null) {
            bottomControlPanel.initBottomPanel();
            bottomControlPanel.setBottomCallback(QQ_interface.this);
        }
        headControlPanel = (HeadControlPanel) findViewById(R.id.head_layout);
        if (headControlPanel != null) {
            headControlPanel.initHeadPanel();
        }
    }

    @Override
    public void onBottomPanelClick(int itemId) {
        String tag = "";
        if ((itemId & MyApp.BTN_FLAG_MESSAGE) != 0) {
            tag = MyApp.FRAGMENT_FLAG_MESSAGE;
        } else if ((itemId & MyApp.BTN_FLAG_CONTACTS) != 0) {
            tag = MyApp.FRAGMENT_FLAG_CONTACTS;
        } else if ((itemId & MyApp.BTN_FLAG_NEWS) != 0) {
            tag = MyApp.FRAGMENT_FLAG_NEWS;
        } else if ((itemId & MyApp.BTN_FLAG_SETTING) != 0) {
            tag = MyApp.FRAGMENT_FLAG_SETTING;
        }
        setTabSelection(tag); //切换Fragment
        headControlPanel.setMiddleTitle(tag);//切换标题
    }

    /**
     * 设置选中的Tag
     *
     * @param tag
     */
    private void setTabSelection(String tag) {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (tag.equals(MyApp.FRAGMENT_FLAG_MESSAGE)) {
            if (messageFragment == null) {
                messageFragment = new MessageFragment();
            }
            fragmentTransaction.replace(R.id.fragement_content, messageFragment);
        } else if (tag.equals(MyApp.FRAGMENT_FLAG_CONTACTS)) {
            if (contactFragment == null) {
                contactFragment = new ContactFragment();
            }
            fragmentTransaction.replace(R.id.fragement_content, contactFragment);
        } else if (tag.equals(MyApp.FRAGMENT_FLAG_SETTING)) {
            if (settingFragment == null) {
                settingFragment = new SettingFragment();
            }
            fragmentTransaction.replace(R.id.fragement_content, settingFragment);
        } else if (tag.equals(MyApp.FRAGMENT_FLAG_NEWS)) {
            if (messageFragment == null) {
                newsFragment = new NewsFragment();
            }
            fragmentTransaction.replace(R.id.fragement_content, newsFragment);
        }
        fragmentTransaction.commit();
        headControlPanel.setMiddleTitle(tag);
    }
}
