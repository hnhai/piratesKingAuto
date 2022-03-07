package id.piratesking.autotool.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TelegramClient", url = "${telegram.uri}")
public interface TelegramClient {

    @GetMapping("/{token}/sendMessage")
    void sendMessage(@PathVariable("token") String token,
        @RequestParam("chat_id") String chatId,
        @RequestParam("text") String text);
}
