package com.example.customcoloring;

import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class CustomSurfaceListener implements View.OnTouchListener {

    private TextView currentElement;

    private SeekBar red;
    private SeekBar green;
    private SeekBar blue;

    private CustomSurface display;

    // Constructor for the listener

    public CustomSurfaceListener(CustomSurface display, TextView currentElement,
                           SeekBar red, SeekBar green, SeekBar blue) {
        this.currentElement = currentElement;
        this.display = display;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // Find where on the canvas you touched through the event

        int xTouch = (int)event.getX();
        int yTouch = (int)event.getY();

        // Call the method to check to see if we hit something and update the
        // sliders and to make that the element to edit

        this.display.checkTouch(xTouch, yTouch,
                currentElement, red, green, blue);

        this.display.invalidate();
        return true;
    }

}
