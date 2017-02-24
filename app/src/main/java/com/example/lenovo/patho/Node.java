package com.example.lenovo.patho;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

/**
 * Created by Lenovo on 29-11-2016.
 */

public class Node extends View {

    int pixelX,pixelY;
    int screenWidth,screenHeight;
    ArrayList<PixelData> pixelDataArrayList;
    Paint paint;
    int colorRED = RED;
    int colorYellow =YELLOW;
    private ArrayList<Integer> results;
    private ArrayList<Integer> userAnswer;
    private boolean showResultBoolean =false;

    public Node(Context context, int screenWidth, int screenHeight) {
        super(context);

        paint = new Paint();
        pixelDataArrayList = new ArrayList<>();
        userAnswer = new ArrayList<>();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        for(int i=0;i<4;i++) {

            Random random = new Random();
            do {
                int maxH = screenHeight-150;
                int maxW = screenWidth-150;
                pixelX = random.nextInt((maxW - 100) + 1) + 100;
                pixelY = random.nextInt((maxH - 100) + 1) + 100;
            }while (checkMinDistance(pixelX,pixelY));

            Log.i("----","Plotting at X:"+pixelX+" Y:"+pixelY+"With Space in X:"+screenWidth+" Y:"+screenHeight);
            pixelDataArrayList.add(new PixelData(pixelX,pixelY,colorRED,0,0));
        }
    }

    private void showResult() {
        int adjacency_matrix[][] = new int[pixelDataArrayList.size() + 1][pixelDataArrayList.size() + 1];
        for (int i = 0; i < pixelDataArrayList.size(); i++)
        {
            for (int j = 0; j <pixelDataArrayList.size(); j++)
            {
                adjacency_matrix[i][j] = (int)findDistance(pixelDataArrayList.get(i).getX(),
                        pixelDataArrayList.get(i).getY(),
                        pixelDataArrayList.get(j).getX(),
                        pixelDataArrayList.get(j).getY());
            }
        }
        for (int i = 0; i <pixelDataArrayList.size(); i++)
        {
            for (int j = 0; j <pixelDataArrayList.size(); j++)
            {
                if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
                {
                    adjacency_matrix[j][i] = 1;
                }
            }
        }
        TSPNearestNeighbor tspNearestNeighbour = new TSPNearestNeighbor();
        tspNearestNeighbour.tsp(adjacency_matrix);
        results = tspNearestNeighbour.getResult();
        showResultBoolean = true;
        invalidate();


    }

    boolean checkMinDistance(float X,float Y){
        for(PixelData p : pixelDataArrayList){
          float d = (float) Math.sqrt((pixelX-p.getX())*((pixelX-p.getX()))+(pixelY-p.getY())*(pixelY-p.getY()));
            if(d<100)
                return true;

        }
        return false;
    }
    float findDistance(float pixelX,float pixelY,float prevPixelX,float prevPixelY){
        return (float) Math.sqrt((pixelX-prevPixelX)*((pixelX-prevPixelX))+(pixelY-prevPixelY)*(pixelY-prevPixelY));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = pixelDataArrayList.size();
        for(int i=0;i<size;i++) {
            paint.setColor(pixelDataArrayList.get(i).getColor());
            canvas.drawCircle((float) (pixelDataArrayList.get(i).getX()),
                    (float)(pixelDataArrayList.get(i).getY()), 15, paint);
            paint.setColor(BLACK);
            paint.setTextSize(25);
            canvas.drawText(String.valueOf(i),(float) (pixelDataArrayList.get(i).getX()-30),
                    (float) (pixelDataArrayList.get(i).getY()),paint);
        }
        paint.setColor(BLUE);
        paint.setTextSize(25);
        canvas.drawText("Start",(float) (pixelDataArrayList.get(1).getX()-30),
                (float) (pixelDataArrayList.get(1).getY()+30),paint);
        /*if(!showResultBoolean)
        {
            String distanceInfo = "Distances: ";
            for(int i=0;i<size-1;i++){
                distanceInfo = distanceInfo + " "+String.valueOf(i) +"->"+String.valueOf(i+1)+"="+findDistance((float) (pixelDataArrayList.get(i).getX()),
                        (float) (pixelDataArrayList.get(i).getY()),(float) (pixelDataArrayList.get(i+1).getX()),
                        (float) (pixelDataArrayList.get(i+1).getY()))+"\n";
            }
            distanceInfo = distanceInfo + " "+String.valueOf(size) +"->"+"0"+"="+findDistance((float) (pixelDataArrayList.get(size-1).getX()),
                    (float) (pixelDataArrayList.get(size-1).getY()),(float) (pixelDataArrayList.get(0).getX()),
                    (float) (pixelDataArrayList.get(0).getY())) ;
            paint.setColor(BLACK);
            canvas.drawText(distanceInfo,25,50,paint);

        }*/
        //specifying distance btw them
        /*
        paint.setColor(GRAY);
        for(int i=0;i<size-1;i++){
            canvas.drawLine((float)pixelDataArrayList.get(i).getX(),
                    (float)pixelDataArrayList.get(i).getY(),
                    (float)pixelDataArrayList.get(i+1).getX(),
                    (float)pixelDataArrayList.get(i+1).getX(),paint);
        }
        canvas.drawLine((float)pixelDataArrayList.get(0).getX(),
                (float)pixelDataArrayList.get(0).getY(),
                (float)pixelDataArrayList.get(size-1).getX(),
                (float)pixelDataArrayList.get(size-1).getX(),paint);*/



        if(showResultBoolean)
        {
            paint.setColor(BLACK);
            paint.setTextSize(30);
            String useranswer = "User Answer: ";
            String solution="Solution: ";
            for(int i=0;i<results.size();i++)
                solution = solution + "->"+results.get(i);
            for(int i=0;i<userAnswer.size();i++)
                useranswer = useranswer + "->"+userAnswer.get(i);

            canvas.drawText(solution,50,50,paint);
            canvas.drawText(useranswer,50,100,paint);

            String text;
            if(results.equals(userAnswer)) {
                text = "Correct !";
                paint.setColor(GREEN);
            }
            else{
                text = "Incorrect !";
                paint.setColor(RED);
            }
            canvas.drawText(text,50,150,paint);


        }
        }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                checkInside(x,y);
                break;
            }
        return true;
    }

    void checkInside(float x,float y){

        Log.i("----","Checking inside or not ..");
        for(PixelData p : pixelDataArrayList)
        {
            double s = ((x - p.getX()) * (x - p.getX())) + ((y - p.getY()) * (y - p.getY())) - (15 * 15);
                if (s < 0) {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();

                       if(p.getColor()==YELLOW) {
                           int value = pixelDataArrayList.indexOf(p);
                           int index = userAnswer.indexOf(value);
                           userAnswer.remove(index);
                           p.setColor(RED);
                       }
                    else
                        p.setColor(colorYellow);
                     if(p.getColor()==YELLOW) {
                            userAnswer.add(pixelDataArrayList.indexOf(p));
                        }

                }
            }

        //checking if user completed his answer
        if(pixelDataArrayList.size()==userAnswer.size())
        {
            Log.i("----","Calling Result..");
            showResult();

        }
            invalidate();

    }
}
