package com.example.customcoloring;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public enum sliderColor {RED, GREEN, BLUE}

    private TextView currentObject;

    private TextView redValue;
    private TextView greenValue;
    private TextView blueValue;

    private SeekBar redSlider;
    private SeekBar greenSlider;
    private SeekBar blueSlider;

    private CustomSurface drawingSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * External Citation
         *  Date:       2/15/19
         *  Problem:    Remove navigation/top bar
         *  Resource:   https://developer.android.com/training/system-ui/
         *               navigation#java
         *  Solution:   Used code from website and read possible view options
         *               to make app immersive
         */

        //Remove nav/top bar from app

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        // Start accessing the elements from the UI:

        currentObject = findViewById(R.id.currentElement);

        redValue = findViewById(R.id.redValue);
        greenValue = findViewById(R.id.greenValue);
        blueValue = findViewById(R.id.blueValue);

        redSlider = findViewById(R.id.redSlider);
        greenSlider = findViewById(R.id.greenSlider);
        blueSlider = findViewById(R.id.blueSlider);

        drawingSurface = findViewById(R.id.drawingSurface);

        // Set the listeners

        redSlider.setOnSeekBarChangeListener(
                new SeekBarListener(redValue, drawingSurface,
                                    sliderColor.RED)
        );
        greenSlider.setOnSeekBarChangeListener(
                new SeekBarListener(greenValue, drawingSurface,
                                    sliderColor.GREEN)
        );
        blueSlider.setOnSeekBarChangeListener(
                new SeekBarListener(blueValue, drawingSurface,
                                    sliderColor.BLUE)
        );

        drawingSurface.setOnTouchListener(
                new CustomSurfaceListener(drawingSurface, currentObject,
                                          redSlider, greenSlider, blueSlider)
        );

    }

    // Small addition to ensure that the bars are still gone when the app is
    // re-entered

    @Override
    protected void onResume() {
        super.onResume();

        //Remove nav/top bar from app
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
