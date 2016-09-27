package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * A custom view that draws a circle with a border.
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class CircleView extends View {

    // The paint for the circle
    private Paint paint;
    private Paint borderPaint;


    // The position of the circle
    private RectF position;


    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initialized the view with default colors.
     */
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(3);

        setColor(220, 150, 86);
    }

    /**
     * Get the circle's color
     * @return The circle's color
     */
    public int getColor() {
        return paint.getColor();
    }

    /**
     * Set the circle's color using a resource ID.
     * @param resId
     */
    public void setColor(int resId) {
        paint.setColor(resId);

        // force a redraw
        invalidate();
    }

    /**
     * Set the circle's color using RGB
     * @param red The red component (0-255)
     * @param green The green component (0-255)
     * @param blue The blue component (0-255)
     */
    public void setColor(int red, int green, int blue) {
        paint.setColor(Color.rgb(red, green, blue));

        // force a redraw
        invalidate();
    }

    /**
     * Set the circle's color using RGB
     * @param alpha The alpha component (0-255)
     * @param red The red component (0-255)
     * @param green The green component (0-255)
     * @param blue The blue component (0-255)
     */
    public void setColor(int alpha, int red, int green, int blue) {
        paint.setColor(Color.argb(alpha, red, green, blue));

        // force a redraw
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(position, paint);
        canvas.drawOval(position, borderPaint);
    }


    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        float xPadding = (float) (getPaddingLeft() + getPaddingRight());
        float yPadding = (float) (getPaddingTop() + getPaddingBottom());
        float actualWidth = (float) width - xPadding;
        float actualHeight = (float) height - yPadding;

        float diameter = Math.min(actualWidth, actualHeight);

        // Fix the position of the circle offset by top/left padding
        position = new RectF(0.0f, 0.0f, diameter, diameter);
        position.offset(getPaddingLeft(), getPaddingTop());
    }

}
