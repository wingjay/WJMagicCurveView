package com.wingjay.wjmagiccurvedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wjMagicCurveView = (Button) findViewById(R.id.wj_magic_curve_view);
        Button cjjArtLineView = (Button) findViewById(R.id.cjj_art_line_view);

        if (wjMagicCurveView != null) {
            wjMagicCurveView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, WJMagicCurveActivity.class));
                }
            });
        }

        if (cjjArtLineView != null) {
            cjjArtLineView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, CJJArtLineActivity.class));
                }
            });
        }

    }
}

