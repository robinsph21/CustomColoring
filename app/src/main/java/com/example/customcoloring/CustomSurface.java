package com.example.customcoloring;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSurface extends SurfaceView {

    // ArrayList of all the things we are drawing
    private ArrayList<CustomElement> drawings = new ArrayList<>();

    // The reference to the current drawing we are going to change
    private CustomElement currentDrawing;

    // Dimensions of the view
    private final int WIDTH = 800;
    private final int HEIGHT = 1105;

    // Offset needed to balance
    private final int OFFSET = 325;

    // Vertical, symmetrical midpoint
    private final int MID_VERT = WIDTH/2+OFFSET;

    // Colors
    private final int PYRAMID_BASE_COLOR = 0xff997735;
    private final int PYRAMID_LOWER_COLOR = 0xffaf8833;
    private final int PYRAMID_HIGHER_COLOR = 0xffcc9f25;
    private final int PYRAMID_TOP_COLOR = 0xffeebb22;
    private final int OUTER_EYE_COLOR = 0xff227760;
    private final int INNER_EYE_COLOR = 0xff111111;

    // Pyramid dimensions
    private final int[] PYRAMID_BOTTOM = {HEIGHT, HEIGHT-(HEIGHT/6),
            HEIGHT-(HEIGHT*2/6), HEIGHT-(HEIGHT*3/6)};
    private final int[] PYRAMID_TOP = {HEIGHT-(HEIGHT/6), HEIGHT-(HEIGHT*2/6),
            HEIGHT-(HEIGHT*3/6), HEIGHT-(HEIGHT*4/6)};
    private final int[] PYRAMID_LEFT = {MID_VERT-WIDTH*4/5, MID_VERT-WIDTH*3/5,
            MID_VERT-WIDTH*2/5, MID_VERT-WIDTH/5};
    private final int[] PYRAMID_RIGHT = {MID_VERT+WIDTH*4/5, MID_VERT+WIDTH*3/5,
            MID_VERT+WIDTH*2/5, MID_VERT+WIDTH/5};

    // Eye dimensions
    private final int EYE_VERTICAL = HEIGHT-HEIGHT*5/6;
    private final int OUTER_EYE_RADIUS = 150;
    private final int INNER_EYE_RADIUS = 60;

    // Default constructors

    public CustomSurface(Context context) {
        super(context);
        init();
    }

    public CustomSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSurface(Context context, AttributeSet attrs,
                         int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setWillNotDraw(false);

        // Make the drawings based on the CustomElement Class

        CustomRect pyramidBase = new CustomRect("Pyramid Base",
            PYRAMID_BASE_COLOR, PYRAMID_LEFT[0], PYRAMID_TOP[0],
            PYRAMID_RIGHT[0], PYRAMID_BOTTOM[0]
        );
        CustomRect pyramidLowerTier = new CustomRect("Pyramid lower tier",
            PYRAMID_LOWER_COLOR, PYRAMID_LEFT[1], PYRAMID_TOP[1],
            PYRAMID_RIGHT[1], PYRAMID_BOTTOM[1]
        );
        CustomRect pyramidHigherTier = new CustomRect("Pyramid higher tier",
            PYRAMID_HIGHER_COLOR, PYRAMID_LEFT[2], PYRAMID_TOP[2],
            PYRAMID_RIGHT[2], PYRAMID_BOTTOM[2]
        );
        CustomRect pyramidTop = new CustomRect("Pyramid Top",
                PYRAMID_TOP_COLOR, PYRAMID_LEFT[3], PYRAMID_TOP[3],
                PYRAMID_RIGHT[3], PYRAMID_BOTTOM[3]
        );
        CustomCircle eyeOutside = new CustomCircle("Outer Eye",
                OUTER_EYE_COLOR, MID_VERT, EYE_VERTICAL,
                OUTER_EYE_RADIUS
        );
        CustomCircle eyeInside= new CustomCircle("Inner Eye",
                INNER_EYE_COLOR, MID_VERT, EYE_VERTICAL,
                INNER_EYE_RADIUS
        );

        // Add all the drawings to the array list

        this.drawings.add(pyramidBase);
        this.drawings.add(pyramidLowerTier);
        this.drawings.add(pyramidHigherTier);
        this.drawings.add(pyramidTop);
        this.drawings.add(eyeOutside);
        this.drawings.add(eyeInside);

        // Make the reference to the drawing we are going to edit null at first

        this.currentDrawing = null;
    }

    /**
     * External Citation
     *          *  Date:       2/18/19
     *          *  Problem:    How to determine if a user touched an object
     *                          and how to then change that object
     *          *  Resource:   Erik Torkelson
     *          *  Solution:   Listened to his explanation than implemented
     *                          code, made following tweaks in above methods.
     *                          Idea is not mine.
     */

    public void checkTouch(int x, int y, TextView currentText, SeekBar red,
                           SeekBar green, SeekBar blue) {

        // Check all elements and see if we hit any

        for (CustomElement element : this.drawings) {
            if (element.containsPoint(x, y)) {

                // Set the drawing that was tapped to be edited

                this.currentDrawing = element;
                currentText.setText(this.currentDrawing.getName());

                // Get the color of the thing that is tapped

                int redValue = Color.red(this.currentDrawing.getColor());
                int greenValue = Color.green(this.currentDrawing.getColor());
                int blueValue = Color.blue(this.currentDrawing.getColor());

                // Set the value of the slider

                red.setProgress(redValue);
                green.setProgress(greenValue);
                blue.setProgress(blueValue);
            }
        }
    }

    public CustomElement getCurrentElement() { return this.currentDrawing; }

    @Override
    protected void onDraw(Canvas canvas) {
        for(CustomElement element : this.drawings) {
            element.drawMe(canvas);
        }
    }
}
