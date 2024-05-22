package com.example.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;
    private Path path;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        if (canvas != null) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawPath(path, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

        return true;
    }

    public void clearCanvas() {
        canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void setShapeType(int i) {
        // Implement shape type logic here
    }

    public void setStrokeWidth(int width) {
        paint.setStrokeWidth(width);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }
}
