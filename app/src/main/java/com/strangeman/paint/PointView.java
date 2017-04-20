package com.strangeman.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.View;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by panzhi on 2017/4/15.
 */

public class PointView extends View {

    Point point=new Point();
    public PointView(Context context,Point point) {
        super(context);
       this.point=point;

    }

    public PointView(Context context, @Nullable AttributeSet atr)
    {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(point.x, point.y, 8, paint);


    }
}
