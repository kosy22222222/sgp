package kr.ac.tukorea.kodg2002.sgp01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity { //class 자식 extends 부모

    @Override //부모와는 다른 개성(?) 부여
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //수퍼로 부모 클래스의 메소드 사용
        setContentView(R.layout.activity_main); //xml 내용대로 실행
    }
}