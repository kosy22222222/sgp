package kr.ac.tukorea.kodg2002.sgp05;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.kodg2002.sgp05.Cat;
import android.os.Bundle;

import java.util.ArrayList;

import kr.ac.tukorea.kodg2002.sgp05.databinding.ActivityCatBinding;

public class CatActivity extends AppCompatActivity {
    private ActivityCatBinding binding;
    private final Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public CatActivity() {
        cat = new Cat();
    }
}