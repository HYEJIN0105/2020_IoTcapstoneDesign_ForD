package com.hansung.android.fordproject;

public class Datas {
    private String name, age, height, weight, gender, arms, legs, shoulders, chest, waist, hip, date;
    //리스트뷰에 값 넣어줄때 필요한 data 클래스
    public Datas(String name, String age, String height, String weight, String gender, String arms, String shoulders, String chest, String legs, String waist, String hip, String date){
        this.age = age;
        this.arms = arms;
        this.legs = legs;
        this.shoulders = shoulders;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this. name = name;
        this.date = date;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getArms() {
        return arms;
    }

    public String getShoulders() {
        return shoulders;
    }

    public String getChest() {
        return chest;
    }

    public String getWaist() {
        return waist;
    }

    public String getHip() {
        return hip;
    }

    public String getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getLegs() {
        return legs;
    }

}
