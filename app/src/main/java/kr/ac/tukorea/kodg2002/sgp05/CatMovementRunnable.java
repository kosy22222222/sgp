package kr.ac.tukorea.kodg2002.sgp05;

import android.widget.ImageView;

public class CatMovementRunnable implements Runnable {
    private ImageView catImageView;
    private final int originalY;
    private final int targetY;

    public CatMovementRunnable(ImageView catImageView) {
        this.catImageView = catImageView;
        this.originalY = (int) catImageView.getY();
        this.targetY = 300;
    }

    @Override
    public void run() {
        // 아래로 이동
        while (catImageView.getY() < targetY) {
            try {
                Thread.sleep(1);
                catImageView.setY(catImageView.getY() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 1초 지연
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 원래 좌표로 천천히 이동
        while (catImageView.getY() > originalY) {
            try {
                Thread.sleep(1);
                catImageView.setY(catImageView.getY() - 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
