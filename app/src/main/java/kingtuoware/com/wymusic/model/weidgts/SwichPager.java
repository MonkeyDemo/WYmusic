package kingtuoware.com.wymusic.model.weidgts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by xww on 2017/8/21.
 */

public class SwichPager extends ViewPager {
    float viewWidth;
    float viewHeight;
    float r;
    int alpha = 20;
    Paint mPaint;
    public SwichPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAlpha(alpha);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getHeight();
        viewWidth = getWidth();
        r = (float) (Math.min(viewHeight,viewWidth)*0.5*(4.0f/6.0f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        mPaint.setAlpha(alpha);
//        canvas.drawRect(0,0,viewWidth,viewHeight,mPaint);
        mPaint.setAlpha(alpha);
        canvas.drawCircle(viewWidth/2,viewHeight/2,r,mPaint);
        super.onDraw(canvas);
    }
}
