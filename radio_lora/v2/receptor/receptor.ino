#include <SPI.h>
#include <LoRa.h>

const long frequency = 915E6; // Frequência do LoRa. Deve ser a mesma do transmissor.

void setup() {
  Serial.begin(9600); // Inicia a comunicação serial para depuração
  while (!Serial);      // Aguarda a conexão serial

  // Configuração dos pinos LoRa para o TTGO com ESP32.
  // O SPI é fixo, e o TTGO já tem as conexões internas.
  SPI.begin(5, 19, 27, 18);
  LoRa.setPins(18, 14, 26);

  // Inicia o módulo LoRa com a frequência definida
  if (!LoRa.begin(frequency)) {
    Serial.println("Falha ao iniciar o LoRa!");
    while (1); // Trava o código se o LoRa não iniciar
  }
  Serial.println("Receptor LoRa iniciado com sucesso!");
}

void loop() {
  // LoRa.parsePacket() retorna o tamanho do pacote recebido ou 0 se nenhum pacote chegou.
  int packetSize = LoRa.parsePacket();
  if (packetSize) {
    // Um pacote foi recebido.
    Serial.print("Pacote recebido. Tamanho: ");
    Serial.print(packetSize);
    Serial.print(" bytes. Dados (Hex): ");
    
    // LoRa.available() retorna o número de bytes disponíveis para leitura.
    while (LoRa.available()) {
      // Lê cada byte do buffer e o imprime como hexadecimal.
      uint8_t receivedByte = LoRa.read();
      if (receivedByte < 0x10) {
        Serial.print("0"); // Adiciona um zero para melhor visualização
      }
      Serial.print(receivedByte, HEX);
      Serial.print(" ");
    }
    
    Serial.println();
  }
}

