package id.piratesking.autotool.schedule;

import id.piratesking.autotool.service.IAutoService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AutoScheduler {

    private final IAutoService service;

    @Scheduled(cron = "${io.piratesking.cron-time}", zone = "GMT+7:00")
    public void triggerAuto() {
        log.info("AutoScheduler.triggerAuto at: {} ", Instant.now().toString());
        service.autoBattle();
    }
}
