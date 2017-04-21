package com.strangeman.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panzhi on 2017/4/21.
 */

public class BezierView extends View {

    private final static String X_KEY = "Xpos";
    private final static String Y_KEY = "Ypos";
    private List<Map<String, Integer>> mListPoint = new ArrayList<Map<String,Integer>>();

    public BezierView(Context context) {
        super(context);

    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
           Paint mpaint= new Paint();
        mpaint.setColor(Color.BLUE);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(5);

        for(int index=0;index<mListPoint.size();index++)
        {
            if (index > 0)
            {
                canvas.drawLine(mListPoint.get(index-1).get(X_KEY), mListPoint.get(index-1).get(Y_KEY),
                        mListPoint.get(index).get(X_KEY), mListPoint.get(index).get(Y_KEY), mpaint);
                //canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
            }

        }


    }
    public void setBezierPoint(int curX, int curY)
    {
        Map<String, Integer> temp = new HashMap<String, Integer>();
        temp.put(X_KEY, curX);
        temp.put(Y_KEY, curY);
        mListPoint.add(temp);
        invalidate();
    }
}