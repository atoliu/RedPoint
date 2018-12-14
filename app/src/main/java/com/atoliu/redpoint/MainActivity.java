package com.atoliu.redpoint;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ppdai.redpoint.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import top.defaults.colorpicker.ColorPickerPopup;

public class MainActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3, tv4;
    private TLRedPointView rTv1, rTv2, rTv3, rTv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TLRedPointView label = findViewById(R.id.num_text);
        AppCompatSeekBar numseekBar = findViewById(R.id.text_num_seekBar);
        AppCompatSeekBar sizeseekBar = findViewById(R.id.text_size_seekbar);
        AppCompatSeekBar num10Seek = findViewById(R.id.text_num10_seekBar);

        AppCompatSeekBar zeroSeekBar = findViewById(R.id.text_zerosize_seekBar);

        AppCompatSeekBar topBar = findViewById(R.id.text_topmargin_seekBar);
        AppCompatSeekBar leftBar = findViewById(R.id.text_leftmargin_seekBar);


        tv1 = findViewById(R.id.tev1);
        tv2 = findViewById(R.id.tev2);
        tv3 = findViewById(R.id.tev3);
        tv4 = findViewById(R.id.tev4);

        rTv1 = findViewById(R.id.redtv1);
        rTv2 = findViewById(R.id.redtv2);
        rTv3 = findViewById(R.id.redtv3);
        rTv4 = findViewById(R.id.redtv4);
        rTv4.isPopular(true);
        rTv4.setmText("热门");


        topBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mRedLabel != null) {
                    mRedLabel.setPointViewMarginTop(i - 250);
                }
                if (mRedLabelText != null) {
                    mRedLabelText.setPointViewMarginTop(i - 250);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        leftBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mRedLabel != null) {
                    mRedLabel.setPointViewMarginRight(i - 250);
                }
                if (mRedLabelText != null) {
                    mRedLabelText.setPointViewMarginRight(i - 250);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        zeroSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                label.isShowZeroNumPoint(true);
                label.setZeroRadius(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        numseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                label.setmCount(i);
                label.isShowZeroNumPoint(false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sizeseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.e("size", i + "");
                label.setmTextSize(i);
                tv1.setTextSize(i);
                tv2.setTextSize(i);
                tv3.setTextSize(i);
                tv4.setTextSize(i);

                rTv1.setmTextSize(i);
                rTv2.setmTextSize(i);
                rTv3.setmTextSize(i);
                rTv4.setmTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        num10Seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                label.setmCount(i);
                label.isShowZeroNumPoint(false);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.redpoint_bg_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                label.setmBGColor(color);
                            }

                            @Override
                            public void onColor(int color, boolean fromUser) {

                            }
                        });

            }
        });


        findViewById(R.id.redpoint_text_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ColorPickerPopup.Builder(MainActivity.this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                label.setmTextColor(color);
                            }

                            @Override
                            public void onColor(int color, boolean fromUser) {

                            }
                        });

            }
        });


        findViewById(R.id.redpoint_set_dy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRed(findViewById(R.id.redpoint_bg_color));
                showRedText(findViewById(R.id.text));
            }
        });


    }

    private TLRedPointView mRedLabel;

    private TLRedPointView mRedLabelText;

    public String gifurl = "https://media.giphy.com/media/xThtama8b8ZGtnMeuQ/giphy.gif?value=123";

    private void showRed(View view) {
        if (mRedLabel == null) {

            mRedLabel = new TLRedPointView(MainActivity.this);
            mRedLabel.isPopular(true);
            mRedLabel.setmText("热门");
            mRedLabel.setTargetView(view);
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Log.e("date", dateFormat.format(new Date()));

        } catch (Exception e) {
            e.printStackTrace();
        }

//        mRedLabel.setmCount(10);
    }

    private void showRedText(View view) {
        if (mRedLabelText == null) {
            mRedLabelText = new TLRedPointView(MainActivity.this);
            mRedLabelText.setTargetView(view);
        }
        mRedLabelText.setmCount(10);
    }
}
