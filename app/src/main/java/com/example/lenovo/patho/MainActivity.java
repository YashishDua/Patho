package com.example.lenovo.patho;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buttonGenerate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int height = displaymetrics.heightPixels;
        final int width = displaymetrics.widthPixels;
        setContentView(new Node(this,width,height));
    }

     void setRandomPixel(int height, int width) {/*
         LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main);
         linearLayout.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
         for(int i=0;i<4;i++){

         double random = new Random().nextDouble();
         double heightVar, widthVar;
         heightVar = 0 + (random * (height));
         widthVar = 0 + (random * (width));
         if (!(heightVar < height && widthVar < width))
             heightVar = 0;
         if (heightVar != 0) {
             Button myButton = new Button(this);
             myButton.setText("X");
             Log.i("----", "Adding to X:" + widthVar + " Y:" + heightVar);
             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
             params.leftMargin = 320+ i*20; // (int) widthVar;
             params.topMargin = 320+ i*20;//(int) heightVar;
             linearLayout.addView(myButton, params);

         }

         }
         setContentView(linearLayout);
         linearLayout.setOrientation(LinearLayout.VERTICAL);
*/
         double random = new Random().nextDouble();
         double heightVar, widthVar;
         heightVar = 0 + (random * (height));
         widthVar = 0 + (random * (width));
         Log.i("----", "Adding to X:" + widthVar + " Y:" + heightVar);


     }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = (double)event.getX();
        double y = (double)event.getY();
        Toast.makeText(MainActivity.this,"X:"+x+" Y:"+y,Toast.LENGTH_SHORT).show();
        return false;
    }
}
