#include <SoftwareSerial.h> //시리얼통신 라이브러리 호출

int blueRx = 7; //Rx (받는핀 설정)
int blueTx = 8; //Tx (보내는핀 설정)

SoftwareSerial BTSerial(blueTx, blueRx);  //시리얼 통신을 위한 객체선언

float val;
float len = 0;
int button = 2;
int encoder0PinA = 3;
int encoder0PinB = 4;
int encoder0Pos = 1; // 처음에 --가 붙은채로 시작하므로 1로 초기값 설정
int encoder0PinALast = LOW;
int n = LOW;
int ledPin = 12;

void setup() {
  pinMode (encoder0PinA, INPUT);
  pinMode (encoder0PinB, INPUT);
  pinMode (button, INPUT);
  pinMode (ledPin, OUTPUT);

  Serial.begin (9600); //시리얼 모니터
  BTSerial.begin(9600);// 블루투스 시리얼
}
int flag = 0;
void loop() {

  int btn = digitalRead(button);
  n = digitalRead(encoder0PinA);

  if(BTSerial.available()){
    digitalWrite(ledPin,HIGH);
   }
  
  if (btn == HIGH) { // 버튼이 눌렸을 때 상태값 1(high) 부여
    flag = 1;
  } else
    flag = 0;

  if ((encoder0PinALast == LOW) && (n == HIGH)) {
    if (digitalRead(encoder0PinB) == LOW) {
      encoder0Pos++;
    } else {
      encoder0Pos--;
    }
    val = encoder0Pos * 18; // 각도 값


    if (val <= 360) { // 첫바퀴
      len = 10.3 / 360.0 * val;
    }
    else if (val > 360 && val <= 720) { //second
      len = 20.3 / 720.0 * val;;
    }
    else if (val > 720 && val <= 1080) { //third
      len = 30.0 / 1080.0 * val;
    }
    else if (val > 1080 && val <= 1440) { //fourth
      len = 39.4 / 1440.0 * val;
    }
    else if (val > 1440 && val <= 1800) { //fifth
      len = 48.6 / 1800.0 * val;
    }
    else if (val > 1800 && val <= 2160) { //sixth
      len = 57.5 / 2160.0 * val;
    }
    else if (val > 2160 && val <= 2520) { //7
      len = 66.1 / 2520.0 * val;
    }
    else if (val > 2520 && val <= 2880) { //8
      len = 74.4 / 2880.0 * val;
    }
    else if (val > 2880 && val <= 3240) { //9
      len = 82.4 / 3240.0 * val;
    }
    else if (val > 3240 && val <= 3600) { //10
      len = 90.1 / 3600.0 * val;
    }
    else if (val > 3600 && val <= 3960) { //11
      len = 97.5 / 3960.0 * val;
    }
    else if (val > 3960 && val <= 4320) { //12
      len = 104.7 / 4320.0 * val;
    }
    else if (val > 4320 && val <= 4680) { //13
      len = 111.6 / 4680.0 * val;
    }
    else if (val > 4680 && val <= 5040) { //14
      len = 118.2 / 5040.0 * val;
    }
    else if (val > 5040 && val <= 5400) { //15
      len = 124.6 / 5400.0 * val;
    }
    else if (val > 5400 && val <= 5760) { //16
      len = 130.6 / 5760.0 * val;
    }
    else if (val > 5760 && val <= 6120) { //17
      len = 136.4 / 6120.0 * val;
    }
    else if (val > 6120 && val <= 6480) { //18
      len = 141.9 / 6480.0 * val;
    }
    else if (val > 6480 && val <= 6840) { //19
      len = 147.1 / 6840.0 * val;
    }
    
    if (flag == 1) {
      Serial.println(len); // 길이값 시리얼모니터에 출력
      BTSerial.println(len); // 길이값 블루투스 연결 기기에 출력
      delay(500);
    }
  }
  encoder0PinALast = n;
  
}