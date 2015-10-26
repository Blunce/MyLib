package cn.blunce.qq_interface;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.blunce.mylib.MyApp;
import cn.blunce.mylib.R;


/**
 * 底部控制栏
 * Created by Blunce on 2015/10/26 0026.
 */
public class BottomControlPanel extends RelativeLayout implements
        OnClickListener {

    private Context mContext;

    private ImageText mMsgImageText;
    private ImageText mContantImageText;
    private ImageText mNewImageText;
    private ImageText mSettingImageText;

    private BottomPanelCallback mBottomCallback;

    private int DEFALUT_BACKGROUND_COLOR = Color.rgb(243, 243, 243);

    private List<ImageText> viewList = new ArrayList<ImageText>();

    public BottomControlPanel(Context context) {
        super(context);
        mContext = context;
        // TODO Auto-generated constructor stub
    }

    public BottomControlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    public interface BottomPanelCallback {
        public void onBottomPanelClick(int itemId);
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        mMsgImageText = (ImageText) findViewById(R.id.btn_message);
        mContantImageText = (ImageText) findViewById(R.id.btn_contacts);
        mNewImageText = (ImageText) findViewById(R.id.btn_news);
        mSettingImageText = (ImageText) findViewById(R.id.btn_setting);
        setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
        viewList.add(mMsgImageText);
        viewList.add(mContantImageText);
        viewList.add(mNewImageText);
        viewList.add(mSettingImageText);
    }

    public void initBottomPanel() {
        if (mMsgImageText != null) {
            mMsgImageText.setImage(R.drawable.message_unselected);
            mMsgImageText.setText("消息");
        }
        if (mContantImageText != null) {
            mContantImageText.setImage(R.drawable.contacts_unselected);
            mContantImageText.setText("联系人");
        }
        if (mNewImageText != null) {
            mNewImageText.setImage(R.drawable.news_unselected);
            mNewImageText.setText("新闻");
        }
        if (mSettingImageText != null) {
            mSettingImageText.setImage(R.drawable.setting_unselected);
            mSettingImageText.setText("设置");
        }
        setBtnListener();

    }

    private void setBtnListener() {
        // TODO Auto-generated method stub
        int num = this.getChildCount();
        for (int i = 0; i < num; i++) {
            View v = getChildAt(i);
            if (v != null) {
                v.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        initBottomPanel();
        int index = -1;
        switch (v.getId()) {
            case R.id.btn_message:
                index = MyApp.BTN_FLAG_MESSAGE;
                mMsgImageText.setChecked(MyApp.BTN_FLAG_MESSAGE);
                break;
            case R.id.btn_contacts:
                index = MyApp.BTN_FLAG_CONTACTS;
                mContantImageText.setChecked(MyApp.BTN_FLAG_CONTACTS);
                break;
            case R.id.btn_news:
                index = MyApp.BTN_FLAG_NEWS;
                mNewImageText.setChecked(MyApp.BTN_FLAG_NEWS);
                break;
            case R.id.btn_setting:
                index = MyApp.BTN_FLAG_SETTING;
                mSettingImageText.setChecked(MyApp.BTN_FLAG_SETTING);
                break;
            default:
                break;
        }
        if (mBottomCallback != null) {
            mBottomCallback.onBottomPanelClick(index);
        }
    }

    public void setBottomCallback(BottomPanelCallback bottomCallback) {
        mBottomCallback = bottomCallback;
    }

    public void defaultBtnChecked() {
        if (mMsgImageText != null) {
            mMsgImageText.setChecked(MyApp.BTN_FLAG_MESSAGE);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        // TODO Auto-generated method stub
        super.onLayout(changed, left, top, right, bottom);
        layoutItems(left, top, right, bottom);
    }

    /**
     * 最左边和最右边的view由母布局的padding进行控制位置。这里需对第2、3个view的位置重新设置
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    private void layoutItems(int left, int top, int right, int bottom) {
        int n = getChildCount();
        if (n == 0) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
//        Log.i("yanguoqi", "paddingLeft = " + paddingLeft + " paddingRight = "
//        +paddingRight);
        int width = right - left;
        int height = bottom - top;
//        Log.i("yanguoqi", "width = " + width + " height = " + height);
        int allViewWidth = 0;
        for (int i = 0; i < n; i++) {
            View v = getChildAt(i);
//            Log.i("yanguoqi", "v.getWidth() = " + v.getWidth());
            allViewWidth += v.getWidth();
        }
        int blankWidth = (width - allViewWidth - paddingLeft - paddingRight)
                / (n - 1);
//        Log.i("yanguoqi", "blankV = " + blankWidth);

        LayoutParams params1 = (LayoutParams) viewList.get(1).getLayoutParams();
        params1.leftMargin = blankWidth;
        viewList.get(1).setLayoutParams(params1);

        LayoutParams params2 = (LayoutParams) viewList.get(2).getLayoutParams();
        params2.leftMargin = blankWidth;
        viewList.get(2).setLayoutParams(params2);
    }
}
