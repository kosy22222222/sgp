package kr.ac.tukorea.kodg2002.sgp01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //얘에 있는 온크리에이트도 쓰고 부모의 온크리에이트도 쓰겠다?
        setContentView(R.layout.activity_main); //xml내용대로 실행되게 함
    }
}