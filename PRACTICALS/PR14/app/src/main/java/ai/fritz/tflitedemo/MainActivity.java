package ai.fritz.tflitedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ai.fritz.tflitedemo.ml.DigitsDetector;
import ai.fritz.tflitedemo.ui.PaintView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private static final int PIXEL_WIDTH = 28;
    private DigitsDetector mnistClassifier;


    @BindView(R.id.button_detect)
    View detectButton;

    @BindView(R.id.button_clear)
    View clearButton;

    @BindView(R.id.text_result)
    TextView mResultText;

    @BindView(R.id.paintView)
    PaintView paintView;

    @BindView(R.id.preview_image)
    ImageView previewImage;

    @BindView(R.id.inference_preview)
    LinearLayout inferencePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        ButterKnife.bind(this);

        mnistClassifier = new DigitsDetector(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetectClicked();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClicked();
            }
        });
    }

    private void onDetectClicked() {
        inferencePreview.setVisibility(View.VISIBLE);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(paintView.getBitmap(), PIXEL_WIDTH, PIXEL_WIDTH, false);
        int digit = mnistClassifier.classify(scaledBitmap);
        previewImage.setImageBitmap(scaledBitmap);
        if (digit >= 0) {
            Log.d(TAG, "Found Digit = " + digit);
            mResultText.setText(getString(R.string.found_digits, String.valueOf(digit)));
        } else {
            mResultText.setText(getString(R.string.not_detected));
        }
    }

    private void onClearClicked() {
        mResultText.setText("");
        paintView.clear();
    }
}

