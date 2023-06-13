package kr.ac.tukorea.kodg2002.sgp05;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.kodg2002.sgp05.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Handler handler;
    private boolean isFireVisible = false;
    private int score = 0; // Declare and initialize the score variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fire1.setVisibility(View.INVISIBLE);
        binding.fire2.setVisibility(View.INVISIBLE);
    }
    public void onBtnStart(View view) {
        if (score >= 20) {
            Intent intent = new Intent(this, CatActivity2.class);
            startActivityForResult(intent, 1);
        } else {
            Intent intent = new Intent(this, CatActivity.class);
            startActivityForResult(intent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            score = data.getIntExtra("score", 0);
            binding.score.setText("Recent Score: " + score);

            handler = new Handler();

            if (score >= 20) {
                Toast.makeText(this, "Score is 20 or higher! You can meet the hell's small kitties.", Toast.LENGTH_LONG).show();
                binding.btnStart.setBackgroundResource(R.drawable.btn_hard);
                binding.fire1.setVisibility(View.VISIBLE);
                toggleFireVisibility();
            }
        }
    }

    private void toggleFireVisibility() {
        handler.postDelayed(() -> {
            if (isFireVisible) {
                binding.fire1.setVisibility(View.INVISIBLE);
                binding.fire2.setVisibility(View.VISIBLE);
            } else {
                binding.fire1.setVisibility(View.VISIBLE);
                binding.fire2.setVisibility(View.INVISIBLE);
            }
            isFireVisible = !isFireVisible;
            toggleFireVisibility(); // Repeat the toggle after a delay
        }, 500); // Adjust the duration (in milliseconds) as needed
    }
}

