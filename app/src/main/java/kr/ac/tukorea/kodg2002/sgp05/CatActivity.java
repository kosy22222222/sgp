package kr.ac.tukorea.kodg2002.sgp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kr.ac.tukorea.kodg2002.sgp05.databinding.ActivityCatBinding;

public class CatActivity extends AppCompatActivity {
    private ActivityCatBinding binding;
    private List<ImageView> catImageViews;
    private Handler handler;
    private static final int ANIMATION_DELAY = 3000;
    private static final int SCORE_INCREMENT = 1;
    private Random random;
    private int score;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("d", "cat");

        binding = ActivityCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        catImageViews = new ArrayList<>();
        catImageViews.add(binding.cat1);
        catImageViews.add(binding.cat2);
        catImageViews.add(binding.cat3);
        catImageViews.add(binding.cat4);
        catImageViews.add(binding.cat5);
        catImageViews.add(binding.cat6);
        catImageViews.add(binding.cat7);

        random = new Random();
        handler = new Handler();
        score = 0;

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        for (ImageView catImageView : catImageViews) {
            catImageView.setOnClickListener(v -> incrementScore());
        }

        startCatAnimation();
    }

    private void incrementScore() {
        score += SCORE_INCREMENT;
        scoreTextView.setText("Score: " + score);
    }

    private void startCatAnimation() {
        for (ImageView catImageView : catImageViews) {
            int delay = random.nextInt(ANIMATION_DELAY);
            handler.postDelayed(() -> {
                Cat cat = new Cat(catImageView);
                cat.startAnimation();
                handler.postDelayed(this::startCatAnimation, ANIMATION_DELAY);
            }, delay);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private class Cat {
        private ImageView catImageView;

        public Cat(ImageView catImageView) {
            this.catImageView = catImageView;
        }

        public void startAnimation() {
            CatMovementRunnable catMovementRunnable = new CatMovementRunnable(catImageView);
            Thread catAnimationThread = new Thread(catMovementRunnable);
            catAnimationThread.start();
        }
    }
}
