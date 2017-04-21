package com.strangeman.paint;


import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener
{
      private  int type;
      private  int typePaint;
      View view;
      Stack<View> viewStack;
      Stack<View> temporaryStack;
      RelativeLayout layout ;

      PointView pointView;
      LineView  lineView;
      BezierView bezierView;

      XYDate xyDate;
      Point point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void  init(){

        Button point=(Button)findViewById(R.id.point);
        point.setOnClickListener(this);
        Button line=(Button)findViewById(R.id.line);
        line.setOnClickListener(this);
        Button bezier=(Button)findViewById(R.id.bezier) ;
        bezier.setOnClickListener(this);
        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        Button recovery =(Button)findViewById(R.id.recovery);
        recovery.setOnClickListener(this);

        layout=(RelativeLayout)findViewById(R.id.layout);
        layout.setOnTouchListener(this);

        type=0;
        typePaint=0;
        viewStack=new Stack<View>();
        temporaryStack =new Stack<View>();
        view= new View(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.point:
               type=0;
                break;

            case R.id.line:
                type=1;
                break;
            case R.id.bezier:
                type=2;
                break;
            case R.id.back:
                back();
                break;
            case R.id.recovery:
                recovery();
                break;

        }
    }


    public void back(){

      if(viewStack.empty()){
          Toast.makeText(this,"没有返回项了",Toast.LENGTH_SHORT).show();
      }
      else
      {  view =viewStack.pop();
        temporaryStack.push(view);
        view.setVisibility(view.INVISIBLE);
      }


    }

    public void recovery(){

        if(temporaryStack.empty()){
            Toast.makeText(this,"没用可恢复项",Toast.LENGTH_SHORT).show();
        }
        else {
            view=temporaryStack.pop();
            viewStack.push(view);
            view.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (type) {
            case 0:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        point = new Point();
                        int X = (int) event.getX();
                        int Y = (int) event.getY();
                        point.x = X;
                        point.y = Y;
                        pointView = new PointView(this, point);
                        viewStack.push(pointView);
                        layout.addView(pointView);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        int x1 = (int) event.getX();
                        int y1 = (int) event.getY();
                        pointView.setPointPoint(x1,y1);
                        break;
                    case MotionEvent.ACTION_UP:

                        int x2 = (int) event.getX();
                        int y2 = (int) event.getY();
                        pointView.setPointPoint(x2,y2);
                }
                break;
            case 1:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xyDate = new XYDate();
                        typePaint = 0;
                        int x1 = (int) event.getX();
                        int y1 = (int) event.getY();
                        xyDate.setX1(x1);
                        xyDate.setY1(y1);
                        xyDate.setX2(x1);
                        xyDate.setY2(y1);
                        lineView = new LineView(this, xyDate, typePaint);
                        layout.addView(lineView);
                        break;

                    case MotionEvent.ACTION_MOVE:

                        int x2 = (int) event.getX();
                        int y2 = (int) event.getY();
                        lineView.setLinePoint(x2,y2,0);
                        break;

                    case MotionEvent.ACTION_UP:

                        int x3 = (int) event.getX();
                        int y3 = (int) event.getY();
                        lineView.setLinePoint(x3,y3,1);
                        viewStack.push(lineView);
                        break;

                }
                break;
            case 2:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xyDate = new XYDate();
                        int x1 = (int) event.getX();
                        int y1 = (int) event.getY();
                        bezierView = new BezierView(this);
                        bezierView.setBezierPoint(x1,y1);
                        layout.addView(bezierView);
                        break;

                    case MotionEvent.ACTION_MOVE:

                        int x2 = (int) event.getX();
                        int y2 = (int) event.getY();
                        bezierView.setBezierPoint(x2,y2);
                        break;

                    case MotionEvent.ACTION_UP:

                        int x3 = (int) event.getX();
                        int y3 = (int) event.getY();
                        bezierView.setBezierPoint(x3,y3);
                        //lineView.setType(typePaint);
                       viewStack.push(bezierView);
                        break;

                }
                break;
        }
        return true;
    }

}


