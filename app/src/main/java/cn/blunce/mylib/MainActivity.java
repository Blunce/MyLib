package cn.blunce.mylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.blunce.view_utils.ChartView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLine();
    }

    private void showLine() {
        ChartView mChartView =(ChartView)findViewById(R.id.show_line);

        mChartView.setInfo(
                new String[]{"1","2","3","4","5","6"},
                new String[]{"0","5","10"},
                new String[]{"3","4","7","9","5","1"},
                "test"
        );
    }
}
