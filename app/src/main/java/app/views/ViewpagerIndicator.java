package app.views;




import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ViewpagerIndicator extends View{
	private static final int X=30;
	private static final int Y=30;
	private static final int R=6;
	private static final int MOVE_LEN=3*R;
	private Paint paint;
	private Paint fullpaint;
	private float set;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			canvas.drawCircle(X+i*MOVE_LEN, Y, R, paint);
		}
		canvas.drawCircle(X+set, Y, R, fullpaint);
		super.onDraw(canvas);
	}

	public ViewpagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
//		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
		fullpaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		fullpaint.setColor(Color.RED);
		// TODO Auto-generated constructor stub
	}


	public void setPointVISE(int position, float positionOffset) {
		// TODO Auto-generated method stub
		set=position%3*MOVE_LEN+positionOffset;
		invalidate();
	}

}
