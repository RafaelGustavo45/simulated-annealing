#include <SPI.h>
#include <LoRa.h>

const long frequency = 915E6; // Frequência do LoRa. Ajuste para a sua região (ex: 433E6, 868E6, 915E6)

void setup() {
  Serial.begin(9600);
  while (!Serial); // Aguarda a conexão serial

  // Configuração dos pinos LoRa para o Arduino Uno.
  // O SPI é fixo nos pinos 13(SCK), 12(MISO), 11(MOSI).
  SPI.begin(5, 19, 27, 18); // CS (Chip Select), RST (Reset), DIO0 (Interrupção)
  LoRa.setPins(18, 14, 26);

  if (!LoRa.begin(frequency)) {
    Serial.println("Falha ao iniciar o LoRa!");
    while (1);
  }
  Serial.println("LoRa iniciado com sucesso!");
}

void loop() {
  const char* message = "a";

  Serial.println("Enviando mensagem automaticamente...");
  
  // Inicia o pacote de dados a ser enviado
  LoRa.beginPacket();
  // Escreve a string no buffer de envio
  LoRa.print(message);
  // Envia o pacote de dados
  LoRa.endPacket();

  Serial.println("Mensagem enviada.");
  delay(2000); // Pausa por 2 segundos antes de enviar novamente
}
