package com.strangeman.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.View;

/**
 * Created by panzhi on 2017/4/15.
 */

public class LineView extends View {

    XYDate xyDate = new XYDate();
    private int type;
    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context,XYDate xyDate,int type) {
        super(context);
        this.xyDate=xyDate;
        this.type=type;
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(type==0){

            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(8.0f);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{8,10,8,10},0);
            p.setPathEffect(dashPathEffect);

            Path path = new Path();
            path.moveTo(xyDate.getX1(), xyDate.getY1());
            path.lineTo(xyDate.getX2(), xyDate.getY2());

            canvas.drawPath(path,p);

        }
        if(type==1) {

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(8);
            canvas.drawLine((float) xyDate.getX1(), (float) xyDate.getY1(), (float) xyDate.getX2(), (float) xyDate.getY2(), paint);
        }
    }

    public void setLinePoint(int x,int y,int typePaint){
        xyDate.setX2(x);
        xyDate.setY2(y);
        this.type=typePaint;
        invalidate();
    }
}
