package kr.ac.tukorea.kodg2002.sgp05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.kodg2002.sgp05.databinding.ActivityCatBinding;

public class CatActivity extends AppCompatActivity {
    private ActivityCatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}