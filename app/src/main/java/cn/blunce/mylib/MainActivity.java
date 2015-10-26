package cn.blunce.mylib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.blunce.qq_interface.QQ_interface;
import cn.blunce.view_utils.ChartView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startQQInterface();
//        showLine();
    }

    private void startQQInterface() {
        Intent intent = new Intent(this,QQ_interface.class);
        startActivity(intent);
        finish();
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
