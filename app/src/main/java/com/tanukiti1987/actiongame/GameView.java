package com.tanukiti1987.actiongame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by tanukiti1987 on 2015/01/25.
 */
public class GameView extends View implements Droid.Callback {

    private static final int START_GROUND_HEIGHT = 50;
    private Ground ground;
    private Droid droid;

    public GameView(Context context) {
        super(context);
    }

    public void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (droid == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.droid);
            droid = new Droid(bitmap, 0, 0, this);
        }

        if (ground == null) {
            ground = new Ground(0, height - START_GROUND_HEIGHT, width, height);
        }

        droid.move();

        droid.draw(canvas);
        ground.draw(canvas);

        invalidate();
    }

    @Override
    public int getDistanceFromGround(Droid droid){
        return ground.rect.top - droid.rect.bottom;
    }
}
