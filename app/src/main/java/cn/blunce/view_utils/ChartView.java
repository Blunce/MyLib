package cn.blunce.view_utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 用来画折线图
 *
 * Created by Blunce on 2015/10/25 0025.
 */
public class ChartView extends View {
    /**
     * 当前view的宽度 (mm)
     */
    private float width = 0.0f;

    /**
     * 当前view的高度 (mm)
     */
    private float height = 0.0f;

    /**
     * 原点的X坐标
     */
    private float XPoint;

    /**
     * 原点的Y坐标
     */
    private float YPoint;

    /**
     * X的刻度长度
     */
    private float XScale;

    /**
     * Y的刻度长度
     */
    private float YScale;

    /**
     * X轴的长度
     */
    private float XLength;

    /**
     * Y轴的长度
     */
    private float YLength;

    /**
     * X的刻度
     */
    private String[] XLabel;

    /**
     * Y的刻度
     */
    private String[] YLabel;

    /**
     * 数据
     */
    private String[] Data;

    /**
     * 显示的标题
     */
    private String Title;

    /**
     * 标题的x坐标
     */
    private float titleXPoint;

    /**
     * 标题的y坐标
     */
    private float titleYPoint;

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param XLabels  x轴刻度
     * @param YLabels  y轴刻度
     * @param AllData  y的值
     * @param strTitle 标题
     */
    public void setInfo(String[] XLabels, String[] YLabels, String[] AllData,
                        String strTitle) {
        XLabel = XLabels;
        YLabel = YLabels;
        Data = AllData;
        Title = strTitle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);// 重写onDraw方法
        initData();

        // canvas.drawColor(Color.WHITE);//设置背景颜色
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(Color.BLUE);// 颜色

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);// 去锯齿
        paint1.setColor(Color.DKGRAY);
        paint.setTextSize(12); // 设置轴文字大小

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);// 去锯齿
        paint2.setColor(Color.BLUE);// 颜色
        paint2.setTextSize(30);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setAntiAlias(true);// 去锯齿
        paint3.setColor(Color.BLUE);// 颜色
        paint3.setTextSize(40);

        // 设置Y轴
        canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint); // y轴线
        for (int i = 0; i * YScale < YLength; i++) {
            canvas.drawLine(XPoint, YPoint - i * YScale, XPoint + 5, YPoint - i
                    * YScale, paint); // 刻度
            try {
                canvas.drawText(YLabel[i], XPoint - 22,
                        YPoint - i * YScale + 5, paint); // 文字
            } catch (Exception e) {
            }
        }
        canvas.drawLine(XPoint, YPoint - YLength, XPoint - 3, YPoint - YLength
                + 6, paint); // 箭头
        canvas.drawLine(XPoint, YPoint - YLength, XPoint + 3, YPoint - YLength
                + 6, paint);
        // 设置X轴
        canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint); // 轴线
        for (int i = 0; i * XScale < XLength; i++) {
            canvas.drawLine(XPoint + i * XScale, YPoint, XPoint + i * XScale,
                    YPoint - 5, paint); // 刻度
            try {
                canvas.drawText(XLabel[i], XPoint + i * XScale - 40,
                        YPoint + 20, paint); // 文字
                // 数据值
                if (i > 0 && YCoord(Data[i - 1]) != -999
                        && YCoord(Data[i]) != -999) // 保证有效数据
                    canvas.drawLine(XPoint + (i - 1) * XScale, YCoord(Data[i - 1]), XPoint + i * XScale,
                            YCoord(Data[i]), paint);
                canvas.drawCircle(XPoint + i * XScale,
                        YCoord(Data[i]), 2,
                        paint);
                canvas.drawText(Data[i], XPoint + i * XScale, YCoord(Data[i]),
                        paint2);
            } catch (Exception e) {
                Log.i("ChartView", "无效数据");
            }
        }
        canvas.drawLine(XPoint + XLength, YPoint, XPoint + XLength - 6,
                YPoint - 3, paint); // 箭头
        canvas.drawLine(XPoint + XLength, YPoint, XPoint + XLength - 6,
                YPoint + 3, paint);
        paint.setTextSize(20);
        canvas.drawText(Title, titleXPoint, titleYPoint, paint3);
    }

    /**
     * 初始化原点坐标，x轴和y轴刻度长度，x轴和y轴长度，标题的位置
     */
    private void initData() {
        // TODO Auto-generated method stub
        width = getWidth();
        height = getHeight();
        XScale = width / (XLabel.length + 3);
        YScale = height / (YLabel.length + 2);
        XPoint = XScale;
        YPoint = height - YScale;
        XLength = width - 2 * XScale;
        YLength = height - 2 * YScale;
        titleXPoint = width / 2;
        titleYPoint = (height - (YLabel.length + 1) * YScale) / 2;
    }

    /**
     * 计算绘制时的Y坐标，无数据时返回-999
     *
     * @param y0
     * @return
     */
    private float YCoord(String y0) //
    {
        float y;
        try {
            y = Float.valueOf(y0);
        } catch (Exception e) {
            return -999; // 出错则返回-999
        }
        try {
            return YPoint - y * YScale / Float.valueOf(YLabel[1]);
        } catch (Exception e) {
        }
        return y;
    }

}
