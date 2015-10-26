package cn.blunce.qq_interface;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.blunce.mylib.MyApp;
import cn.blunce.mylib.R;


/**
 * Created by Blunce on 2015/10/26 0026.
 */
public class HeadControlPanel extends RelativeLayout {

    private Context mContext;
    private TextView mMidleTitle;
    private TextView mRightTitle;
    private final float middle_title_size = 20f;
    private final float right_title_size = 17f;
    private final int default_background_color = Color.rgb(23, 124, 202);

    public HeadControlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // TODO Auto-generated method stub
        mMidleTitle = (TextView) findViewById(R.id.midle_title);
        mRightTitle = (TextView) findViewById(R.id.right_title);
        setBackgroundColor(default_background_color);
    }

    public void initHeadPanel() {

        if (mMidleTitle != null) {
            setMiddleTitle(MyApp.FRAGMENT_FLAG_MESSAGE);
        }
    }

    public void setMiddleTitle(String s) {
        mMidleTitle.setText(s);
        mMidleTitle.setTextSize(middle_title_size);
    }
}

