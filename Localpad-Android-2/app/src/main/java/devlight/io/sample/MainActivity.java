package devlight.io.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView txt_PadName,txt_Date,txt_Temperature;

    Date tDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }

    private void initUI() {
        final View viewProgress = findViewById(R.id.viewProgress);
        viewProgress.setOnClickListener(this);
//        final View btnHorizontalCoordinatorNtb = findViewById(R.id.btn_horizontal_coordinator_ntb);
//        btnHorizontalCoordinatorNtb.setOnClickListener(this);
//        final View btnTopHorizontalNtb = findViewById(R.id.btn_horizontal_top_ntb);
//        btnTopHorizontalNtb.setOnClickListener(this);
        final View btnVerticalNtb = findViewById(R.id.btn_vertical_ntb);
        btnVerticalNtb.setOnClickListener(this);

        long now = System.currentTimeMillis();
        tDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String getTime = simpleDate.format(tDate);

        txt_Date = findViewById(R.id.txt_Date);
        txt_Date.setText("현재시간\n"+getTime);
        txt_PadName = findViewById(R.id.txt_PadName);
        txt_Temperature = findViewById(R.id.txt_Temperature);


        final CircleProgressBar progressBar = findViewById(R.id.days_graph);
        progressBar.setMax(100);
        progressBar.setProgress(90);
        //progressBar.setProgressFormatter((progress, max) -> { final String DEFAULT_PATTERN = "%d퍼센트"; return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100)); });


//        final View btnSamplesNtb = findViewById(R.id.btn_samples_ntb);
//        btnSamplesNtb.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        ViewCompat.animate(v)
                .setDuration(200)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(new CycleInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {

                    }

                    @Override
                    public void onAnimationEnd(final View view) {

                        switch (v.getId()) {
//                            case R.id.viewProgress:
//                               startActivity(
//                                       new Intent(MainActivity.this, TopHorizontalNtbActivity.class)
//                               );
//                               break;
                            case R.id.viewProgress:
                                startActivity(
                                        new Intent(MainActivity.this, ProgressActivity.class)
                                );
                                break;
//                            case R.id.btn_horizontal_top_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, TopHorizontalNtbActivity.class)
//                                );
//                                break;
//                            case R.id.btn_horizontal_coordinator_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, HorizontalCoordinatorNtbActivity.class)
//                                );
//                                break;
                            case R.id.btn_vertical_ntb:
                                startActivity(
                                        new Intent(MainActivity.this, VerticalNtbActivity.class)
                                );
                                break;
//                            case R.id.btn_samples_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, SamplesNtbActivity.class)
//                                );
//                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onAnimationCancel(final View view) {

                    }
                })
                .withLayer()
                .start();
    }


    private class CycleInterpolator implements android.view.animation.Interpolator {

        private final float mCycles = 0.5f;

        @Override
        public float getInterpolation(final float input) {
            return (float) Math.sin(2.0f * mCycles * Math.PI * input);
        }
    }
}
