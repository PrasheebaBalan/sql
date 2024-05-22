package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class GraphicsActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private Button btnLine, btnRectangle, btnCircle, btnColor, btnStrokeWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        ImageView imageView = findViewById(R.id.imageView3);
        Animation zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        imageView.startAnimation(zoomInAnimation);

        drawingView = findViewById(R.id.drawingView);

        btnLine = findViewById(R.id.btnLine);
        btnRectangle = findViewById(R.id.btnRectangle);
        btnCircle = findViewById(R.id.btnCircle);
        btnColor = findViewById(R.id.btnColor);
        btnStrokeWidth = findViewById(R.id.btnStrokeWidth);

        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setShapeType(0); // Line
            }
        });

        btnRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setShapeType(1); // Rectangle
            }
        });

        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setShapeType(2); // Circle
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setColor(Color.RED); // Change color to red
            }
        });

        btnStrokeWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.setStrokeWidth(10); // Change stroke width to 10
            }
        });
    }
}
