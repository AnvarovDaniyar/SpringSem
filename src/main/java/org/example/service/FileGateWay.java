package org.example.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс FileGateWay для работы с файлом
 * Интеграция с файловым хранилищем
 */
@MessagingGateway(defaultRequestChannel = "messageChannelInput")  // канал для входящих данных
public interface FileGateWay {
    // метод для записи в файл
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
