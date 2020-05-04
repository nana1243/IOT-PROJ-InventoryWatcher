package devlight.io.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dinuscxj.progressbar.CircleProgressBar;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_main);
        initUI2();
    }

    private void initUI2() {

        CircleProgressBar progressBar1 = findViewById(R.id.ItemProgress1);
        progressBar1.setMax(100);
        progressBar1.setProgress(30);  final CircleProgressBar progressBar2 = findViewById(R.id.ItemProgress2);
        progressBar2.setMax(100);
        progressBar2.setProgress(50);  final CircleProgressBar progressBar3 = findViewById(R.id.ItemProgress3);
        progressBar3.setMax(100);
        progressBar3.setProgress(70);  final CircleProgressBar progressBar4 = findViewById(R.id.ItemProgress4);
        progressBar4.setMax(100);
        progressBar4.setProgress(90);


    }

}