//다리,팔 길이 추가
package com.hansung.android.fordproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class PersonalInputActivity extends AppCompatActivity {
    Button finish;
    Button arm_btn, shoulders_btn, chest_btn, waist_btn, hip_btn, legs_btn;
    EditText arms, legs, shoulders, waist, chest, hip;
    ArrayList<String> armsS =new ArrayList<String>();
    ArrayList<String> legsS =new ArrayList<String>();
    ArrayList<String> shouldersS =new ArrayList<String>();
    ArrayList<String> waistS =new ArrayList<String>();
    ArrayList<String> chestS =new ArrayList<String>();
    ArrayList<String> hipS =new ArrayList<String>();
    TinyDB tinyDB;
    private BluetoothSPP bt;
    SharedPreferences Input;
    public static final String PREFERENCES_GROUP = "LoginInfo";//값 저장될 파일의 이름 문자열 상수logininfo로 정의
    public static final String PREFERENCES_SIZE = "PersonalSize";//여기에 측정값 저장한다고 생각해

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_input);
        bt = new BluetoothSPP(this);
        tinyDB = new TinyDB(getBaseContext());
        Input = getSharedPreferences(PREFERENCES_GROUP, MODE_PRIVATE);

        arms = findViewById(R.id.arm_et);
        shoulders = findViewById(R.id.shoulders_et);
        chest = findViewById(R.id.chest_et);
        waist = findViewById(R.id.waist_et);
        hip = findViewById(R.id.hip_et);
        legs = findViewById(R.id.leg_et);

        arm_btn = findViewById(R.id.arm_btn);
        shoulders_btn = findViewById(R.id.shoulders_btn);
        chest_btn = findViewById(R.id.chest_btn);
        waist_btn = findViewById(R.id.waist_btn);
        hip_btn = findViewById(R.id.hip_btn);
        legs_btn = findViewById(R.id.legs_btn);



        finish = findViewById(R.id.finish_btn);
        arm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                arms.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        shoulders_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                shoulders.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        chest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                chest.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        waist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view4) {
                waist.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        hip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view5) {
                hip.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        legs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view6) {
                legs.setText(Input.getString(PREFERENCES_SIZE,""));
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //전에 담겨있던 db값 받아오기
                armsS = tinyDB.getListString("arms");
                legsS = tinyDB.getListString("legs");
                shouldersS = tinyDB.getListString("shoulders");
                chestS = tinyDB.getListString("chest");
                waistS = tinyDB.getListString("waist");
                hipS = tinyDB.getListString("hip");


                armsS.add(arms.getText().toString());
                legsS.add(legs.getText().toString());
                shouldersS.add(shoulders.getText().toString());
                chestS.add(chest.getText().toString());
                waistS.add(waist.getText().toString());
                hipS.add(hip.getText().toString());
                //db에 값들 추가하기
                tinyDB.putListString("arms",armsS);
                tinyDB.putListString("legs",legsS);
                tinyDB.putListString("shoulders",shouldersS);
                tinyDB.putListString("chest",chestS);
                tinyDB.putListString("waist",waistS);
                tinyDB.putListString("hip",hipS);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);


            }

        });

    }
}