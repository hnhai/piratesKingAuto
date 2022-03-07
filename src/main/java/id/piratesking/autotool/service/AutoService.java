package id.piratesking.autotool.service;

import id.piratesking.autotool.adapter.IPiratesKingClient;
import id.piratesking.autotool.utils.CommonUtil;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
@Slf4j
public class AutoService implements IAutoService {

    private final IPiratesKingClient client;
    private final INotifyService notifyService;

    private final String ACTION = "get_all_pirate";

    @Value("${io.piratesking.wallet-id}")
    private String walletId;

    @Value("${io.piratesking.bot-id}")
    private Integer botId;

    @Value("${io.piratesking.energy}")
    private Integer energy;

    @Override
    public void autoBattle() {
        var startBalance = client.getBalance(walletId);
        var piratesResponse = client.getPirates(ACTION, walletId);
        var pirates = piratesResponse.getResults();

        if (CollectionUtils.isEmpty(pirates)) {
            log.error("AutoService autoBattle get pirate fail");
            return;
        }

        var battles = new ArrayList<String>();
        int winCount = 0;
        int total = 0;
        int pirateEnery = 0;
        for (var pirate : pirates) {
            energy = CommonUtil.parseInteger(pirate.getEnergy());
            if (pirateEnery < energy) {
                total = 0;
                winCount = 0;
                battles.add(String.format("Pirate Rank: %s, ID: %s, Win: %d/%d", pirate.getChestCode(), pirate.getChestTokenId(), winCount, total));
                continue;
            }

            var battle = client.battle(walletId, Integer.parseInt(pirate.getId()), botId);
            // Todo check battle result
            if (battle.getCode() == 200) {
                winCount++;
            }
            total++;
            pirateEnery -= energy;
        }
        var endBalance = client.getBalance(walletId);

        var message = CommonUtil.buildTelegramMsg(startBalance.getBusd(), startBalance.getPkt(), battles, endBalance.getBusd(), endBalance.getPkt());
        notifyService.sendMessage(message);
    }
}
