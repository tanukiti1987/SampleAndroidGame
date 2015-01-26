package com.tanukiti1987.actiongame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by tanukiti1987 on 2015/01/25.
 */
public class Droid {

    public interface Callback {
        public int getDistanceFromGround(Droid droid);
    }

    private final Callback callback;
    private static final float GRAVITY = 0.8f;
    private static final float WEIGHT = GRAVITY * 60;
    private float acceleration = 0;

    private final Paint paint = new Paint();
    private Bitmap bitmap;
    final Rect rect;

    public Droid(Bitmap bitmap, int left, int top, Callback callback) {
        this.rect = new Rect(left, top, left + bitmap.getWidth(), top + bitmap.getHeight());
        this.bitmap = bitmap;
        this.callback = callback;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
    }

    public void jump(float power) {
        acceleration = power * WEIGHT;
    }

    public void move() {
        acceleration -= GRAVITY;
        int distanceFromGround = callback.getDistanceFromGround(this);

        if (acceleration < 0 && acceleration < -distanceFromGround) {
            acceleration = -distanceFromGround;
        }

        rect.offset(0, -Math.round(acceleration));
    }

    public void shutdown() {
        acceleration = 0;
    }
}
