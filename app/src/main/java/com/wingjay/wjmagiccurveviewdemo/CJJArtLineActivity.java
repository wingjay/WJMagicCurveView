package com.wingjay.wjmagiccurveviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by wingjay on 4/22/16.
 */
public class CJJArtLineActivity extends AppCompatActivity {
    private CJJArtLineView mArtLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cjj_art_line);
        mArtLine = (CJJArtLineView) findViewById(R.id.art);
        line1();
//        line2();
//        line3();
//        line4();
//        line5();
//        line6();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cjj_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mArtLine != null) {
            mArtLine.destory();
        }
        switch (item.getItemId()) {
            case R.id.art_line_1:
                line1();
                break;
            case R.id.art_line_2:
                line2();
                break;
            case R.id.art_line_3:
                line3();
                break;
            case R.id.art_line_4:
                line4();
                break;
            case R.id.art_line_5:
                line5();
                break;
            case R.id.art_line_6:
            default:
                line6();
        }

        return super.onOptionsItemSelected(item);
    }

    public void line1(){
        mArtLine.setColor("#90ff00ff");
        mArtLine.setTime(40*1000);
        mArtLine.setSpeedA((float) 0.76);
        mArtLine.setAngleB((float)0.03);
        mArtLine.setaXR(200);
        mArtLine.setaYR(200);
        mArtLine.setbXR(300);
        mArtLine.setbYR(300);
    }

    public void line2(){
        mArtLine.setColor("#904CAF50");
        mArtLine.setTime(20*1000);
        mArtLine.setSpeedA((float)0.12);
        mArtLine.setAngleB((float)0.25);
        mArtLine.setaXR(300);
        mArtLine.setaYR(120);
        mArtLine.setbXR(120);
        mArtLine.setbYR(220);
    }

    public void line3(){
        mArtLine.setColor("#90fff700");
        mArtLine.setTime(10*1000);
        mArtLine.setSpeedA((float) 0.26);
        mArtLine.setAngleB((float)0.005);
        mArtLine.setaXR(320);
        mArtLine.setaYR(20);
        mArtLine.setbXR(20);
        mArtLine.setbYR(320);
    }

    public void line4(){
        mArtLine.setColor("#902196F3");
        mArtLine.setTime(40*1000);
        mArtLine.setSpeedA((float) 0.16);
        mArtLine.setAngleB((float)0.005);
        mArtLine.setaXR(300);
        mArtLine.setaYR(300);
        mArtLine.setbXR(300);
        mArtLine.setbYR(60);
    }

    public void line5(){
        mArtLine.setColor("#90ff00ff");
        mArtLine.setTime(30*1000);
        mArtLine.setSpeedA((float) 0.06);
        mArtLine.setAngleB((float)0.003);
        mArtLine.setaXR(120);
        mArtLine.setaYR(20);
        mArtLine.setbXR(30);
        mArtLine.setbYR(320);
    }


    public void line6(){
        mArtLine.setColor("#90ff00ff");
        mArtLine.setTime(30*1000);
        mArtLine.setSpeedA((float) 0.06);
        mArtLine.setAngleB((float)0.003);
        mArtLine.setaXR(320);
        mArtLine.setaYR(200);
        mArtLine.setbXR(200);
        mArtLine.setbYR(320);
    }

}
