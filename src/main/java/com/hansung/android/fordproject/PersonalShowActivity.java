//조회에서 아이템 눌럿을 떄 나오는 화면
package com.hansung.android.fordproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class PersonalShowActivity extends AppCompatActivity {
    String name, age, weight, height, date, arms, legs, shoulders, chest, waist, hip, gender;
    TextView personalS, dateS, armsS, legsS, shouldersS, waistS, chestS, hipS;
    Button finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_show);


        finish = findViewById(R.id.finish_btn);
        personalS = findViewById(R.id.show_info_tv);
        dateS = findViewById(R.id.show_date_tv);
        armsS = findViewById(R.id.show_arms_tv);
        shouldersS = findViewById(R.id.show_shoulders_tv);
        chestS = findViewById(R.id.show_chest_tv);
        waistS = findViewById(R.id.show_waist_tv);
        hipS = findViewById(R.id.show_hip_tv);
        legsS = findViewById(R.id.show_legs_tv);


        final Intent intent = getIntent();
        if(intent.hasExtra("name")){
            //intent를 통해 넘겨받은 데이터 변수에 저장
            name = intent.getStringExtra("name");
            age = intent.getStringExtra("age");
            weight = intent.getStringExtra("weight");
            height = intent.getStringExtra("height");
            gender = intent.getStringExtra("gender");
            arms = intent.getStringExtra("arms");
            legs = intent.getStringExtra("legs");
            shoulders = intent.getStringExtra("shoulders");
            chest = intent.getStringExtra("chest");
            waist = intent.getStringExtra("waist");
            hip = intent.getStringExtra("hip");
            date = intent.getStringExtra("date");
        }

        //텍스트뷰에 해당하는 글 넣어주기
        personalS.setText(name + "("+age +", "+gender+", "+height+"cm, "+weight+"kg"+")");
        dateS.setText("예약날짜 : "+date);
        armsS.setText("팔길이 : "+arms + "cm");
        shouldersS.setText("어깨길이 : "+shoulders + "cm");
        chestS.setText("가슴둘레 : "+chest + "cm");
        waistS.setText("허리둘레 : "+waist + "cm");
        hipS.setText("엉덩이둘레 : "+hip + "cm");
        legsS.setText("다리길이 : "+legs + "cm");

        //돌아가기 누르면 main으로 돌아가기
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });


    }
}