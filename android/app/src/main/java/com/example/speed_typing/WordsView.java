package com.example.speed_typing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class WordsView extends RelativeLayout {
    private Paint paint;
    private int id;
    private int height;

    public WordsView(Context context, int height) {
        super(context);

        this.height = height;

        paint = new Paint(Color.RED);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        id = ViewCompat.generateViewId();
        setId(id);

    }

    public int getId() { return id; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, height / 2, getWidth(), height / 2, paint);
    }

}
