package com.hansung.android.fordproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ByNameActivity extends AppCompatActivity {
    TinyDB tinyDB;
    ArrayList<String> nameS = new ArrayList<String>();
    ArrayList<String> ageS = new ArrayList<String>();
    ArrayList<String> heightS = new ArrayList<String>();
    ArrayList<String> weightS = new ArrayList<String>();
    ArrayList<String> genderS = new ArrayList<String>();
    ArrayList<String> dateS = new ArrayList<String>();
    ArrayList<String> armsS = new ArrayList<String>();
    ArrayList<String> legsS = new ArrayList<String>();
    ArrayList<String> shouldersS = new ArrayList<String>();
    ArrayList<String> chestS = new ArrayList<String>();
    ArrayList<String> waistS = new ArrayList<String>();
    ArrayList<String> hipS = new ArrayList<String>();
    ArrayList<Character> nameFirst = new ArrayList<Character>();
    ArrayList<String[]> person = new ArrayList<String[]>();
    String[] personTemporary = new String[100];
    Character nameFirstTemporary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_name);

        tinyDB = new TinyDB(getBaseContext());

        //데이터 db에서 받아오기
        nameS = tinyDB.getListString("name");
        ageS = tinyDB.getListString("age");
        heightS = tinyDB.getListString("height");
        weightS = tinyDB.getListString("weight");
        genderS = tinyDB.getListString("gender");
        armsS = tinyDB.getListString("arms");
        legsS = tinyDB.getListString("legs");
        shouldersS = tinyDB.getListString("shoulders");
        chestS = tinyDB.getListString("chest");
        waistS = tinyDB.getListString("waist");
        hipS = tinyDB.getListString("hip");
        dateS = tinyDB.getListString("date");

        //person이라는 리스트로 묶어주기
        for (int i = 0; i < nameS.size(); i++) {
            person.add(new String[]{nameS.get(i), ageS.get(i), heightS.get(i), weightS.get(i), genderS.get(i), armsS.get(i), legsS.get(i), shouldersS.get(i), chestS.get(i), waistS.get(i), hipS.get(i), dateS.get(i)});
            nameFirst.add(nameS.get(i).charAt(0));
            Log.e("asdf", String.valueOf(nameFirst.size()));
        }

        // 수열 이용하여 abc 순으로 정렬
        for (int i = 0; i < nameS.size(); i++) {
            for(int j = 0 ; j < nameS.size() -i -1 ; j ++) {

                if (nameFirst.get(j) > nameFirst.get(j+1)) {
                    personTemporary = person.get(j);
                    person.set(j, person.get(j+1));
                    person.set(j+1, personTemporary);
                    nameFirstTemporary = nameFirst.get(j);
                    nameFirst.set(j, nameFirst.get(j+1));
                    nameFirst.set(j+1, nameFirstTemporary);
                }
            }
        }

        //리스트뷰 선언
        ListView listView = (ListView)findViewById(R.id.name_list);
        //어댑터로 리스트 뷰 안에 값 보여주기
        final MyAdapter myAdapter = new MyAdapter(this,person);

        listView.setAdapter(myAdapter);

        //일정 아이템을 클릭했을 때 그 정보 보여주는 페이지로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Intent intent = new Intent(getBaseContext(), PersonalShowActivity.class);
                intent.putExtra("name", person.get(position)[0]);
                Log.e("name", person.get(position)[0]);
                intent.putExtra("age", person.get(position)[1]);
                Log.e("age", person.get(position)[1]);
                intent.putExtra("height",person.get(position)[2]);
                Log.e("height",person.get(position)[2]);
                intent.putExtra("weight",person.get(position)[3]);
                Log.e("weight",person.get(position)[3]);
                intent.putExtra("gender",person.get(position)[4]);
                Log.e("gender",person.get(position)[4]);
                intent.putExtra("arms",person.get(position)[5]);
                Log.e("arms",person.get(position)[5]);
                intent.putExtra("shoulders",person.get(position)[6]);
                Log.e("shoulders",person.get(position)[6]);
                intent.putExtra("chest",person.get(position)[7]);
                Log.e("chest",person.get(position)[7]);
                intent.putExtra("waist",person.get(position)[8]);
                Log.e("waist",person.get(position)[8]);
                intent.putExtra("hip",person.get(position)[9]);
                Log.e("hip",person.get(position)[9]);
                intent.putExtra("legs",person.get(position)[10]);
                Log.e("legs",person.get(position)[10]);
                intent.putExtra("date", person.get(position)[11]);
                Log.e("date", person.get(position)[11]);
                startActivity(intent);
            }
        });


    }
}