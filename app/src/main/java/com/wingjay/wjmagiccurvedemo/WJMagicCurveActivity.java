package com.wingjay.wjmagiccurvedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wingjay.wjmagiccurveview.WJMagicCurveView;

/**
 * Created by wingjay on 4/22/16.
 * You can choose in top-right menu for different existing curve.
 */
public class WJMagicCurveActivity extends AppCompatActivity {
    private EditText radiusAX;
    private EditText radiusAY;
    private EditText radiusBX;
    private EditText radiusBY;
    private EditText loopTotalCount;
    private EditText durationSeconds;
    private EditText speedOuterPoint;
    private EditText speedInnerPoint;
    private Button startTransformBtn;

    private WJMagicCurveView wjMagicCurveView;
    private TextView aboutAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wj_magic_curve);

        radiusAX = (EditText) findViewById(R.id.radius_ax);
        radiusAY = (EditText) findViewById(R.id.radius_ay);
        radiusBX = (EditText) findViewById(R.id.radius_bx);
        radiusBY = (EditText) findViewById(R.id.radius_by);
        loopTotalCount = (EditText) findViewById(R.id.loopTotalCount);
        durationSeconds = (EditText) findViewById(R.id.durationSeconds);
        speedOuterPoint = (EditText) findViewById(R.id.speedOuterPoint);
        speedInnerPoint = (EditText) findViewById(R.id.speedInnerPoint);
        startTransformBtn = (Button) findViewById(R.id.start_transform_btn);
        wjMagicCurveView = (WJMagicCurveView) findViewById(R.id.wj_magic_curve_view);
        aboutAuthor = (TextView) findViewById(R.id.about_author);

        startTransformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wjMagicCurveView.isDrawing()) {
                    wjMagicCurveView.stopDraw();
                    startTransformBtn.setText("Start Transform (开始)");
                } else {
                    startTransform();
                    startTransformBtn.setText("Stop Transform (停止)");
                }
            }
        });

        setupIntroduction();
    }

    private void startTransform() {
        wjMagicCurveView.setRadius(getIntValueFromEdittext(radiusAX), getIntValueFromEdittext(radiusAY),
                getIntValueFromEdittext(radiusBX), getIntValueFromEdittext(radiusBY))
                .setDurationSec(getIntValueFromEdittext(durationSeconds))
                .setLoopTotalCount(getIntValueFromEdittext(loopTotalCount))
                .setSpeed(getIntValueFromEdittext(speedOuterPoint), getIntValueFromEdittext(speedInnerPoint))
                .startDraw();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(WJMagicCurveActivity.this);
        menuInflater.inflate(R.menu.wingjay_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        wjMagicCurveView.stopDraw();
        startTransformBtn.setText("Start Transform (开始)");
        WJMagicCurveView.WJMagicCurveViewParameters parameters;
        switch (item.getItemId()) {
            case R.id.diamond:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.DIAMOND;
                break;
            case R.id.ring:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.RING;
                break;
            case R.id.pyramid:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.PYRAMID;
                break;
            case R.id.shell:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.SHELL;
                break;
            case R.id.flower:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.FLOWER;
                break;
            case R.id.leaf:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.LEAF;
                break;
            case R.id.airship:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.AIRSHIP;
                break;
            case R.id.default_key:
            default:
                parameters = WJMagicCurveView.WJMagicCurveViewParameters.DEFAULT;
        }
        wjMagicCurveView.setParameters(parameters);
        displayWjMagicCurveParameters(parameters);

        return super.onOptionsItemSelected(item);
    }

    private void displayWjMagicCurveParameters(WJMagicCurveView.WJMagicCurveViewParameters parameters) {
        radiusAX.setText(parameters.radiusAX > 0 ? String.valueOf((int) parameters.radiusAX) : null);
        radiusAY.setText(parameters.radiusAY > 0 ? String.valueOf((int) parameters.radiusAY) : null);
        radiusBX.setText(parameters.radiusBX > 0 ? String.valueOf((int) parameters.radiusBX) : null);
        radiusBY.setText(parameters.radiusBY > 0 ? String.valueOf((int) parameters.radiusBY) : null);
        speedOuterPoint.setText(parameters.speedOuterPoint > 0 ? String.valueOf(parameters.speedOuterPoint) : null);
        speedInnerPoint.setText(parameters.speedInnerPoint > 0 ? String.valueOf(parameters.speedInnerPoint) : null);
        loopTotalCount.setText(parameters.loopTotalCount > 0 ? String.valueOf(parameters.loopTotalCount) : null);
        durationSeconds.setText(parameters.durationSec > 0 ? String.valueOf(parameters.durationSec) : null);
    }

    private int getIntValueFromEdittext(@NonNull EditText editText) {
        String content = editText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            return Integer.parseInt(content);
        }
        return -1;
    }

    @Override
    protected void onStop() {
        super.onStop();
        wjMagicCurveView.stopDraw();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wjMagicCurveView.destory();
    }

    private void setupIntroduction() {
        String aboutAuthorString = "<u>Find me here: wingjay (https://github.com/wingjay)</u>";
        aboutAuthor.setText(Html.fromHtml(aboutAuthorString));
        aboutAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/wingjay"));
                startActivity(browserIntent);
            }
        });
    }

}
