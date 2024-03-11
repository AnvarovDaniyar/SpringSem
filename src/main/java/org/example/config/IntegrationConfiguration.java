package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Класс конфигурации интеграции
 */
@Configuration
public class IntegrationConfiguration {
    // Канал от входных данных до трансформера
    @Bean
    public MessageChannel messageChannelInput() {
        return new DirectChannel();
    }

    // Канал от трансформера до модуля файлового хранилища
    @Bean
    public MessageChannel messageChannelFileWriter() {
        return new DirectChannel();
    }

    // Трансформер, маршрутизирующий входящие данные в модуль файлового хранилища
    @Bean
    @Transformer(inputChannel = "messageChannelInput", outputChannel = "messageChannelFileWriter")
    public GenericTransformer<String, String> stringTransformer() {
        return message -> {
            return message.toUpperCase();
        };
    }

    // Модуль файлового хранилища "messages.txt"
    @Bean
    @ServiceActivator(inputChannel = "messageChannelFileWriter")
    public FileWritingMessageHandler fileWriter () {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File(".\\src\\main\\resources\\messages"));
        handler.setExpectReply(false); // повторяющиеся сообщения не нужны
        handler.setFileExistsMode(FileExistsMode.APPEND); // если файл уже существует, дописываем в него
        handler.setAppendNewLine(true);
        return handler;
    }
}
