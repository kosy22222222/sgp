package kr.ac.tukorea.kodg2002.sgp05;

import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.ArrayList;

import kr.ac.tukorea.kodg2002.sgp05.AnimSprite;
import kr.ac.tukorea.kodg2002.sgp05.IRecyclable;

public class Cat extends AnimSprite implements IRecyclable {

    public Cat() {
        super(R.drawable.cat, 2.0f, 3.0f, 2.0f, 3.0f, 8, 1);
    }

    protected enum State {
        running, stop, clicked, COUNT
    }

    private static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 72 + (idx % 100) * 272;
            int t = 132 + (idx / 100) * 272;
            rects[i] = new Rect(l, t, l + 140, t + 140);}
        return rects;
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = makeRects(100, 101, 102, 103);
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    @Override
    public void onRecycle() {
    }
}
