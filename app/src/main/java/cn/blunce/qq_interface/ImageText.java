package cn.blunce.qq_interface;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.blunce.mylib.MyApp;
import cn.blunce.mylib.R;


public class ImageText extends LinearLayout {

    private Context mContext;

    private ImageView mImageView;
    private TextView mTextView;

    private final int DEFAULT_IMAGE_WIDTH = 64;
    private final int DEFAULT_IMAGE_HEIGHT = 64;

    private final int CHECKED_COLOR = Color.rgb(29, 118, 199); // 选中蓝色
    private final int UNCHECKED_COLOR = Color.GRAY;// 自然灰色

    public ImageText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    public ImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_text_layout, this, true);
        mImageView = (ImageView) findViewById(R.id.image_iamge_text);
        mTextView = (TextView) findViewById(R.id.text_iamge_text);
    }

    /**
     * 设置图片
     *
     * @param id
     */
    public void setImage(int id) {
        if (mImageView != null) {
            mImageView.setImageResource(id);
            setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
        }
    }

    /**
     * 设置文字
     *
     * @param s
     */
    public void setText(String s) {
        if (mTextView != null) {
            mTextView.setText(s);
            mTextView.setTextColor(UNCHECKED_COLOR);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return true;
    }

    private void setImageSize(int w, int h) {
        if (mImageView != null) {
            ViewGroup.LayoutParams params = mImageView.getLayoutParams();
            params.width = w;
            params.height = h;
            mImageView.setLayoutParams(params);
        }
    }

    /**
     * @param itemID
     */
    public void setChecked(int itemID) {
        if (mTextView != null) {
            mTextView.setTextColor(CHECKED_COLOR);
        }
        int checkDrawableId = -1;
        switch (itemID) {
            case MyApp.BTN_FLAG_MESSAGE:
                checkDrawableId = R.drawable.message_selected;
                break;
            case MyApp.BTN_FLAG_CONTACTS:
                checkDrawableId = R.drawable.contacts_selected;
                break;
            case MyApp.BTN_FLAG_NEWS:
                checkDrawableId = R.drawable.news_selected;
                break;
            case MyApp.BTN_FLAG_SETTING:
                checkDrawableId = R.drawable.setting_selected;
                break;
            default:
                break;
        }
        if (mImageView != null) {
            mImageView.setImageResource(checkDrawableId);
        }
    }

}
