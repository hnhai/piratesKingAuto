package id.piratesking.autotool.service;

import id.piratesking.autotool.adapter.TelegramClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyService implements INotifyService {

    private final TelegramClient client;

    @Value("${telegram.token}")
    private String telegramToken;

    @Value("${telegram.chat-id}")
    private String chatId;

    @Value("${telegram.enable}")
    private Boolean enable;

    @Override
    public void sendMessage(String message) {
        if (enable) {
            client.sendMessage(telegramToken, chatId, message);
        }
    }
}
