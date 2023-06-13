package kr.ac.tukorea.kodg2002.sgp05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kr.ac.tukorea.kodg2002.sgp05.databinding.ActivityCat2Binding;

public class CatActivity2 extends AppCompatActivity {
    private ActivityCat2Binding binding;
    private List<ImageView> catImageViews;
    private List<ImageView> bigImageViews;
    private static final int ANIMATION_DELAY = 5000;
    private static final int SCORE_INCREMENT = 5;
    private static final int SCORE_DECREMENT = 10;
    private static final int ACTIVITY_FINISH_DELAY = 21000; // 20 seconds
    private Random random;
    private int score;
    private TextView scoreTextView;
    private TextView timeTextView;
    private CountDownTimer countDownTimer;
    private boolean isGameRunning = true;
    private Handler handler;
    private Runnable animationRunnable;
    private boolean isClickable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCat2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        catImageViews = new ArrayList<>();
        catImageViews.add(binding.cat1);
        catImageViews.add(binding.cat2);
        catImageViews.add(binding.cat3);
        catImageViews.add(binding.cat4);
        catImageViews.add(binding.cat5);
        catImageViews.add(binding.cat6);
        catImageViews.add(binding.cat7);
        catImageViews.add(binding.catt1);
        catImageViews.add(binding.catt2);
        catImageViews.add(binding.catt3);
        catImageViews.add(binding.catt4);
        catImageViews.add(binding.catt5);
        catImageViews.add(binding.catt6);
        catImageViews.add(binding.catt7);

        bigImageViews = new ArrayList<>();
        bigImageViews.add(binding.big1);
        bigImageViews.add(binding.big2);
        bigImageViews.add(binding.big3);
        bigImageViews.add(binding.big4);
        bigImageViews.add(binding.big5);
        bigImageViews.add(binding.big6);
        bigImageViews.add(binding.big7);
        bigImageViews.add(binding.bigg1);
        bigImageViews.add(binding.bigg2);
        bigImageViews.add(binding.bigg3);
        bigImageViews.add(binding.bigg4);
        bigImageViews.add(binding.bigg5);
        bigImageViews.add(binding.bigg6);
        bigImageViews.add(binding.bigg7);

        random = new Random();
        score = 0;

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
        timeTextView = findViewById(R.id.timeTextView);

        handler = new Handler();
        animationRunnable = new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    startCatAnimation();
                }
            }
        };

        for (int i = 0; i < catImageViews.size(); i++) {
            final int catIndex = i;
            ImageView catImageView = catImageViews.get(catIndex);
            ImageView bigImageView = bigImageViews.get(catIndex);

            catImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN && isClickable) {
                        // 클릭 가능 상태인 경우에만 처리
                        isClickable = false; // 클릭 비활성화

                        incrementScore();
                        Log.d("CatActivity", "Cat " + (catIndex + 1) + " clicked.");
                        bigImageView.setVisibility(View.VISIBLE);
                        bigImageView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bigImageView.setVisibility(View.INVISIBLE);

                                // 클릭 가능 상태로 변경
                                isClickable = true;
                            }
                        }, 200);
                    }
                    return true;
                }
            });
        }

        startTimer();
        startCatAnimation();
    }

    private void incrementScore() {
        score += SCORE_INCREMENT;
        scoreTextView.setText("Score: " + score);
    }
    private void decrementScore() {
        score -= SCORE_DECREMENT;
        scoreTextView.setText("Score: " + score);
    }

    private void startCatAnimation() {
        for (ImageView catImageView : catImageViews) {
            int delay = random.nextInt(ANIMATION_DELAY) + 4000;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isGameRunning) {
                        moveCat(catImageView);
                    }
                }
            }, delay);
        }
    }
    private void moveCat(ImageView catImageView) {
        int targetY = 300;
        int duration = 1000;
        catImageView.animate()
                .translationY(targetY)
                .setDuration(duration)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        catImageView.animate()
                                .translationY(0)
                                .setDuration(random.nextInt(duration))
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        int delay = random.nextInt(ANIMATION_DELAY);
                                        handler.postDelayed(animationRunnable, delay);
                                    }
                                })
                                .start();
                    }
                })
                .start();

        // 터치 이벤트를 처리할 수 있도록 터치 리스너 설정
        catImageView.setOnTouchListener(new View.OnTouchListener() {
            private boolean isTouched = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && isClickable && !isTouched) {
                    // 클릭 가능 상태인 경우에만 처리
                    isClickable = false; // 클릭 비활성화
                    isTouched = true; // 터치 이벤트 처리 플래그 설정

                    int catIndex = catImageViews.indexOf(catImageView);
                    if(catIndex <= 7) incrementScore();
                    else decrementScore();
                    Log.d("CatActivity", "Cat " + (catIndex + 1) + " clicked.");

                    ImageView bigImageView = bigImageViews.get(catIndex);
                    bigImageView.setVisibility(View.VISIBLE);
                    bigImageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bigImageView.setVisibility(View.INVISIBLE);

                            // 클릭 가능 상태로 변경
                            isClickable = true;
                        }
                    }, 200);

                    // 추가적인 터치 이벤트를 막기 위해 터치 리스너 제거
                    catImageView.setOnTouchListener(null);

                    // 클릭 가능 상태로 변경
                    isClickable = true;
                    isTouched = false; // 터치 이벤트 처리 플래그 초기화

                    return true;
                }
                return false;
            }
        });
    }



    private void startTimer() {
        countDownTimer = new CountDownTimer(ACTIVITY_FINISH_DELAY, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                timeTextView.setText("Time: " + secondsRemaining + "s");
            }

            @Override
            public void onFinish() {
                finishActivity();
            }
        }.start();
    }

    private void finishActivity() {
        isGameRunning = false;
        try {
            Intent intent = new Intent();
            intent.putExtra("score", score);
            setResult(RESULT_OK, intent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!isFinishing()) {
                finish();
            }
        }
    }
}
