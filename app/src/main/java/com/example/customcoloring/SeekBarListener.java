package com.example.customcoloring;

import android.graphics.Color;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private CustomSurface customSurface;
    private TextView textDisplay;
    private MainActivity.sliderColor sliderColor;

    public SeekBarListener(TextView text, CustomSurface display,
                           MainActivity.sliderColor barColor) {
        this.textDisplay = text;
        this.customSurface = display;
        this.sliderColor = barColor;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {

        // Update the associated text value

        this.textDisplay.setText(""+progress);

        if (this.customSurface.getCurrentElement() != null) {

            // Get Color of current element

            int currentColor =
                    this.customSurface.getCurrentElement().getColor();

            // Split up the color into separate components. Only one will
            // be set per listener

            int red = Color.red(currentColor);
            int green = Color.green(currentColor);
            int blue = Color.blue(currentColor);

            // Change color component based on seek bar type.

            switch (sliderColor) {
                case RED:
                    red = progress;
                    break;
                case GREEN:
                    green = progress;
                    break;
                case BLUE:
                    blue = progress;
                    break;
                default:
                    break;
            }

            // Use the new color values to make set the color

            this.customSurface.getCurrentElement().setColor(
                    Color.rgb(red, green, blue)
            );
            this.customSurface.invalidate();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}