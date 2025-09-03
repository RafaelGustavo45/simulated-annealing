void setup() {
  // Inicia a comunicação serial a 9600 baud para a comunicação com o TTGO e o computador
  Serial.begin(9600);
}

void loop() {
  // Verifica se há dados disponíveis da porta serial
  if (Serial.available()) {
    // Lê a string completa enviada pelo TTGO
    String receivedString = Serial.readStringUntil('\n');
    
    // Imprime a string no monitor serial do computador para o Excel
    Serial.println(receivedString);
  }
}
