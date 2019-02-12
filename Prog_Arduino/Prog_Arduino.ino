#include "Arduino.h"
#define pinStatus 1
#define pinAlarm 7

const int CN0 = A0;
const int CN1 = A1;
String estado = "Desligada";
String db = "null";
String buffSerial;
int tempoSalvar = 120;
double tempSet1 = 157;
double tempSet2 = 167;
double alarme1 = 10;
double alarme2 = 10;
double offSet1 = 0;
double offSet2 = 0;
String beforeRead = "";
String pc = "";

void setup() {
  // put your setup code here, to run once:
  pinMode(pinStatus, INPUT_PULLUP);
  pinMode(pinAlarm, OUTPUT);
  Serial.begin(9600);
  Serial.setTimeout(5);
}

void loop() {
  double tempZ1 = 0.0;
  double tempZ2 = 0.0;
  int contadorMemoria = 119;
  boolean watchdog1 = false;
  boolean watchdog2 = false;

  while (true) {
    while (Serial.available()) {
      contadorMemoria ++;
      pc = leituraPc();

      char pcRead[50] = "";
      pc.toCharArray(pcRead, 50);

      if ( pc != "" && pc != beforeRead ) {
        beforeRead = pc;
        int contLinha = 0;
        char * pch;

        pch = strtok (pcRead, " ,");

        while (pch != NULL)
        {
          switch (contLinha) {
            case 0:
             tempoSalvar = String (pch).toInt();
              break;
            case 1:
              tempSet1 = String (pch).toDouble();
              break;
            case 2:
              tempSet2 = String (pch).toDouble();
              break;
            case 3:
              alarme1 = String (pch).toDouble();
              break;
            case 4:
              alarme2 = String (pch).toDouble();
              break;
            case 5:
              offSet1 = String (pch).toDouble();
              break;
            case 6:
              offSet2 = String (pch).toDouble();
              break;
          }
         // Serial.println(pch);
          pch = strtok (NULL, " ,");
          contLinha++;
          delay(100);
        }
        contLinha = 0;
      }


      //Logica do Alarmes da temperatura

      double tempMax1 = tempSet1 + alarme1;
      double tempMin1 = tempSet1 - alarme1;
      double tempMax2 = tempSet2 + alarme2;
      double tempMin2 = tempSet2 - alarme2;

      //Leituras das entradas analogicas
      tempZ1 = ((double(analogRead(CN0)) * 5 / (1023)) / 0.01) + offSet1;
      tempZ2 = ((double(analogRead(CN1)) * 5 / (1023)) / 0.01) + offSet2;

      // Controle de Alarmes e Temperatura
      if (tempZ1 >= (tempMin1 + 1.0) && pinStatus == LOW ) {
        watchdog1 = true;
      }
      if (tempZ2 >= (tempMin2 + 1.0) && pinStatus == LOW) {
        watchdog2 = true;
      }
      if (estado == "Desligada" || estado == "Desligando...") {
        watchdog1 = false;
        watchdog2 = false;
        digitalWrite(pinAlarm, LOW);
      }

      if (((watchdog1 == true && (tempZ1 < tempMin1 || tempZ1 > tempMax1)) || (watchdog2 == true && (tempZ2 < tempMin2 || tempZ2 > tempMax2))) && estado != "Alarme Temperatura") {
        estado = "Alarme Temperatura";
        contadorMemoria = tempoSalvar;
        digitalWrite(pinAlarm, HIGH);
      }
      else {
        if (pinStatus == LOW && estado == "Desligada") {
          estado = "Ligando...";
          contadorMemoria = tempoSalvar;
        }
        else if (pinStatus == HIGH && estado == "Ligada") {
          estado = "Desligando...";
          contadorMemoria = tempoSalvar;
        }
      }

      // Programação para salvar o registro
      if (contadorMemoria > tempoSalvar) {
        db = "salvar";
        contadorMemoria = 0;
      }

      String buff1 = String(tempZ1);
      String buff2 = String(tempZ2);
      buffSerial = (buff1 + "," + buff2 + "," + estado + "," + db);
      Serial.println(buffSerial);
      db = "null";

      // Logica para resetar os estados

      if ( pinStatus == HIGH ) {
        estado = "Desligada";
      }
      else if (estado == "Ligando...") {
        estado = "Ligada";
      }

      delay(1000);
    }
  }
}


String leituraPc() {
  char charReceived = ' ';
  String newParameter = "";
  int contWhile  = 0;
  charReceived = char (Serial.read());

  if (charReceived == '{') {

    while (contWhile < 50 && charReceived != '}') {
      newParameter.concat(String (charReceived));
      contWhile++;
      delay(100);
      charReceived = char (Serial.read());
    }

    char newChar[50] = "";
    newParameter.toCharArray(newChar, 50);
    char * pch;
    pch = strtok (newChar, " {");
    newParameter = "";

    while (pch != NULL)
    {
      newParameter.concat(pch);
      pch = strtok (NULL, " {");
      delay(100);
    }
  }
  return newParameter;
}
