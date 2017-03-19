#include <Servo.h>
#include <SoftwareSerial.h>

SoftwareSerial BTSerial(2, 3); // SoftwareSerial(RX, TX)
byte buffer[1024]; // 데이터 수신 버퍼
int bufferPosition; // 버퍼에 기록할 위치

Servo myservo_righthand; // 서보를 제어할 서보 오브젝트를 만듭니다.
Servo myservo_rightdownarm;
Servo myservo_rightuparm;
Servo myservo_lefthand;
Servo myservo_leftdownarm;
Servo myservo_leftuparm;
Servo myservo_righttoe;
Servo myservo_lefttoe;

int pos=0;
// 각 부품을 제어하기 위한 상태 값(이 값을 조절하는 걸로 부품을 제어한다.)

void setup (){
  myservo_righthand.attach(11); // 서보모터를 9번 핀에 연결
  myservo_rightdownarm.attach(10);
  myservo_rightuparm.attach(9);
  myservo_lefthand.attach(7);
  myservo_leftdownarm.attach(8);
  myservo_leftuparm.attach(5);
  myservo_righttoe.attach(12);
  myservo_lefttoe.attach(6);
  
  BTSerial.begin(9600);
  Serial.begin(9600);
  bufferPosition = 0; // 버퍼 초기화
}

void loop (){
  if (BTSerial.available()){ // 블루투스로 데이터 수신
    byte data = BTSerial.read();
    Serial.write(data); // 수신된 데이터 시리얼 모니터로 출력
    Serial.println();
    buffer[bufferPosition++] = data;
    
    // 각각 버튼 눌렀을시 그 버튼에 맞는 숫자(char형)가 전송 된다/
    // 이제부터 나오는 if문은 
    if(data == '1'){ // right to left swipe
    for(pos=90; pos<180; pos+=1){
        myservo_leftuparm.write(pos);
        delay(5);
      }
      for(pos=90; pos>0; pos-=1){
        myservo_rightuparm.write(pos);
        delay(5);
      }
      for(pos=90; pos<180; pos+=1){
        myservo_righthand.write(pos);
        myservo_lefthand.write(pos);
        delay(1);
      }
      for(pos=180; pos>0; pos-=1){
        myservo_righthand.write(pos);
        myservo_lefthand.write(pos);
        delay(1);
      }
      for(pos=0; pos<90; pos+=1){
        myservo_righthand.write(pos);
        myservo_lefthand.write(pos);
        delay(1);
      }
     for(pos=180; pos>90; pos-=1){
      myservo_leftuparm.write(pos);
     delay(5);
     }
    for(pos=0; pos<90; pos+=1){
     myservo_rightuparm.write(pos);
    delay(5);
    } 
    }
    
    else if(data == '2'){ // left to right swipe
      for(pos=90; pos>50; pos-=1){
        myservo_rightuparm.write(pos);
        delay(5);
      }
      for(pos=90; pos<130; pos+=1){
        myservo_leftuparm.write(pos);
        delay(5);
      }
      for(pos=90; pos>40; pos-=1){
        myservo_rightdownarm.write(pos);
        delay(5);
      }
      for(pos=90; pos<140; pos+=1){
        myservo_leftdownarm.write(pos);
        delay(5);
      }
      for(pos=40; pos<90; pos+=1){
        myservo_rightdownarm.write(pos);
        delay(5);
      }
      for(pos=140; pos>90; pos-=1){
        myservo_leftdownarm.write(pos);
        delay(5);
      }
      for(pos=50; pos<90; pos+=1){
        myservo_rightuparm.write(pos);
        delay(5);
      }
      for(pos=130; pos>90; pos-=1){
        myservo_leftuparm.write(pos);
        delay(5);
      }
    }
    
    
    else if(data == '3'){  // down to up swipe
      for(pos=90; pos<180; pos+=1){
        myservo_rightdownarm.write(pos);
        delay(3);
      }
      for(pos=90; pos>0; pos-=1){
        myservo_leftdownarm.write(pos);
        delay(3);
      }
      for(pos=180; pos>90; pos-=1){
        myservo_rightdownarm.write(pos);
        delay(3);
      }
      for(pos=0; pos<90; pos+=1){
        myservo_leftdownarm.write(pos);
        delay(3);
      }
    }
    
    else if(data == '4'){ // up to down swipe
      for(pos=90; pos<180; pos+=1){
        myservo_leftuparm.write(pos);
        myservo_rightuparm.write(pos);
        delay(5);
      }
      for(pos=180; pos>0; pos-=1){
        myservo_leftuparm.write(pos);
        myservo_rightuparm.write(pos);
        delay(5);
      }
      for(pos=0; pos<90; pos+=1){
        myservo_leftuparm.write(pos);
        myservo_rightuparm.write(pos);
        delay(5);
      }
      }
    
    else if(data == '5'){  // 텇했 경
        for(pos=90; pos<180; pos+=1){
          myservo_leftuparm.write(pos);
          delay(5);
        }
        for(pos=90; pos>40; pos-=1){
          myservo_righttoe.write(pos);
          delay(5);
        }
        for(pos=180; pos>90; pos-=1){
          myservo_leftuparm.write(pos);
          delay(5);
        }
        for(pos=40; pos<90; pos+=1){
          myservo_righttoe.write(pos);
          delay(5);
        }
    }
    
    else if(data == '6'){  // 긹 눌렀 경
      for(pos=90; pos>0; pos-=1){
          myservo_rightuparm.write(pos);
          delay(5);
        }
        for(pos=90; pos<140; pos+=1){
          myservo_lefttoe.write(pos);
          delay(5);
        }
        for(pos=0; pos<90; pos +=1){
          myservo_rightuparm.write(pos);
          delay(5);
        }
        for(pos=140; pos>90; pos-=1){
          myservo_lefttoe.write(pos);
          delay(5);
        }
    }
    
    else if(data == '7'){  // 흔들었을 경우
      for(pos=90; pos<180; pos+=1){
          myservo_righthand.write(pos);
          delay(1);
        }
        for(pos=90;pos>0;pos-=1){
          myservo_lefthand.write(pos);
          delay(1);
        }
        for(pos=180; pos>=0; pos-=1){
          myservo_righthand.write(pos);
          delay(1);
        }
        for(pos=0; pos<180; pos+=1){
          myservo_lefthand.write(pos);
          delay(1);
        }
        for(pos=0; pos<90; pos+=1){
          myservo_righthand.write(pos);
          delay(1);
        }
        for(pos=180; pos>90; pos-=1){
          myservo_lefthand.write(pos);
          delay(1);
        }
      
    }
    else{
    }
  }
  else{
  }
 }
