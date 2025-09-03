//Bibliotecas para LoRa
#include <SPI.h>
#include <LoRa.h>
#include <DHT.h> // Biblioteca para o sensor de temperatura de 3 pinos

//Bibliotecas para o Display OLED
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>

// Definições de pinos para o TTGO LoRa32-OLED
#define SCK 5
#define MISO 19
#define MOSI 27
#define SS 18
#define RST 14
#define DIO0 26

// Frequência do LoRa
#define BAND 866E6

// Pinos do display OLED
#define OLED_SDA 4
#define OLED_SCL 15 
#define OLED_RST 16
#define SCREEN_WIDTH 128 // Largura em pixels
#define SCREEN_HEIGHT 64 // Altura em pixels

// Definições para o sensor de temperatura de 3 pinos
#define DHTPIN 4
#define DHTTYPE DHT11 // Troque para DHT22 se for o seu sensor

// Cria o objeto para o display OLED
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RST);

// Cria o objeto para o sensor DHT
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  // Inicializa o Monitor Serial
  Serial.begin(9600);
  
  // Reseta o display OLED via software
  pinMode(OLED_RST, OUTPUT);
  digitalWrite(OLED_RST, LOW);
  delay(20);
  digitalWrite(OLED_RST, HIGH);

  // Inicializa o display OLED
  Wire.begin(OLED_SDA, OLED_SCL);
  if(!display.begin(SSD1306_SWITCHCAPVCC, 0x3c, false, false)) {
    Serial.println(F("Falha na alocação do display SSD1306."));
    for(;;); // Trava se falhar
  }
  
  display.clearDisplay();
  display.setTextColor(WHITE);
  display.setTextSize(1);
  display.setCursor(0,0);
  display.println("Iniciando...");
  display.display();
  
  Serial.println("Iniciando Transmissor LoRa");

  // Pinos SPI e LoRa
  SPI.begin(5, 19, 27, 18); // SCLK, MISO, MOSI, SS (Chip Select)

  LoRa.setPins(18, 14, 26); // SS, RST, DIO0
  
  if (!LoRa.begin(BAND)) {
    Serial.println("Falha ao iniciar o LoRa!");
    while (1);
  }
  Serial.println("LoRa OK!");
  
  // Inicializa o sensor DHT
  dht.begin();
  Serial.println("Sensor DHT OK!");
  
  display.setCursor(0,20);
  display.println("LoRa OK!");
  display.setCursor(0,30);
  display.println("Sensor OK!");
  display.display();
  delay(2000);
}

void loop() {
  // A leitura do sensor pode levar alguns instantes.
  float temperature = dht.readTemperature();

  // Verifica se a leitura foi bem-sucedida
  if (isnan(temperature)) {
    Serial.println("Falha ao ler a temperatura!");
    
    // Mostra erro na tela OLED
    display.clearDisplay();
    display.setCursor(0,0);
    display.println("Erro na leitura");
    display.println("do sensor!");
    display.display();
    
    delay(5000);
    return;
  }

  // Converte a temperatura para uma string para envio e exibição
  String tempString = String(temperature, 2) + " C";

  Serial.print("Enviando pacote: ");
  Serial.println(tempString);

  // Envia o pacote LoRa para o receptor
  LoRa.beginPacket();
  LoRa.print(tempString);
  LoRa.endPacket();
  
  // Atualiza a tela OLED
  display.clearDisplay();
  display.setCursor(0,0);
  display.println("LORA SENDER");
  display.setCursor(0,20);
  display.println("Pacote enviado!");
  display.setCursor(0,40);
  display.print("Temp: ");
  display.println(tempString);
  display.display();

  // Pausa de 5 segundos antes da próxima leitura
  delay(5000);
}