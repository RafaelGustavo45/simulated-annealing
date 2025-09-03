#include <SPI.h>
#include <LoRa.h>
#include <DHT.h> // Biblioteca para o sensor de temperatura de 3 pinos

// Define o pino digital onde o sensor DHT estará conectado
#define DHTPIN 4
// Define o tipo do sensor (DHT11, DHT22, etc.)
#define DHTTYPE DHT11

// Define a frequência do LoRa. Deve ser a mesma do receptor.
const long frequency = 915E6; 

// Cria o objeto para o sensor DHT
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);
  while (!Serial); // Aguarda a conexão serial

  // Configuração dos pinos LoRa para o TTGO com ESP32.
  LoRa.setPins(18, 14, 26);

  if (!LoRa.begin(frequency)) {
    Serial.println("Falha ao iniciar o LoRa!");
    while (1);
  }
  Serial.println("LoRa iniciado com sucesso!");

  // Inicializa o sensor DHT
  dht.begin();
  Serial.println("Sensor DHT iniciado com sucesso!");
}

void loop() {
  // A leitura do sensor pode levar alguns instantes.
  // Atrasos entre leituras (2 segundos) são obrigatórios.
  delay(5000); 

  // Lê a temperatura do sensor em Celsius
  float temperature = dht.readTemperature();

  // Verifica se a leitura foi bem-sucedida
  if (isnan(temperature)) {
    Serial.println("Falha ao ler a temperatura do sensor!");
    return;
  }

  // Converte a temperatura para uma string
  String message = "Temp: " + String(temperature, 2) + " C";

  Serial.print("Enviando mensagem: ");
  Serial.println(message);
  
  // Inicia o pacote de dados a ser enviado
  LoRa.beginPacket();
  // Escreve a string no buffer de envio
  LoRa.print(message);
  // Envia o pacote de dados
  LoRa.endPacket();

  Serial.println("Mensagem enviada.");
}

