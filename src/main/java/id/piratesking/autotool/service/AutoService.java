package id.piratesking.autotool.service;

import id.piratesking.autotool.adapter.IPiratesKingClient;
import id.piratesking.autotool.adapter.model.BattleResponse;
import id.piratesking.autotool.utils.CommonUtil;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    private final String GET_ALL_PIRATES_ACTION = "get_all_pirate";
    private final String BATTLE_ACTION = "battle";

    @Value("${io.piratesking.wallet-id}")
    private String walletId;

    @Value("${io.piratesking.bot-id}")
    private Integer botId;

    private final Integer energy = 5;

    @Override
    @SneakyThrows
    public void autoBattle() {
        var startBalance = client.getBalance(walletId);
        var piratesResponse = client.getPirates(GET_ALL_PIRATES_ACTION, walletId);
        var pirates = piratesResponse.getResults();

        if (CollectionUtils.isEmpty(pirates)) {
            log.error("AutoService autoBattle get pirate fail");
            return;
        }

        var battles = new ArrayList<String>();

        for (var pirate : pirates) {
            var pirateEnergy = CommonUtil.parseInteger(pirate.getEnergy());
            if (pirateEnergy < energy) {
                log.info("pirateEnergy not enough");
                continue;
            }
            var turns = pirateEnergy / energy;

            int winCount = 0;
            int total = 0;
            for (int i = 0; i < turns; i++) {
                var battle = client.battle(BATTLE_ACTION, walletId, Integer.parseInt(pirate.getId()), botId);
                boolean winBattle = winBattle(battle);
                log.info("Pirate ID: {}, Rank: {}, win battle: {}", pirate.getId(), pirate.getChestCode(), winBattle);
                if (winBattle) {
                    winCount++;
                }
                total++;
                Thread.sleep(5000);
            }

            battles.add(String.format("Pirate Rank: %s, ID: %s, Win: %d/%d", pirate.getChestCode(), pirate.getId(), winCount, total));
        }
        var endBalance = client.getBalance(walletId);

        var message = CommonUtil.buildTelegramMsg(startBalance.getBusd(), startBalance.getPkt(), battles, endBalance.getBusd(), endBalance.getPkt());
        notifyService.sendMessage(message);
    }

    private boolean winBattle(BattleResponse response) {
        return response.getCode() == 200 && Objects.nonNull(response.getResults()) && Objects.nonNull(response.getResults().getCharacterWin()) && response.getResults().getCharacterWin() == 1;
    }
}
