package id.piratesking.autotool.adapter;

import id.piratesking.autotool.adapter.model.BalanceResponse;
import id.piratesking.autotool.adapter.model.BattleResponse;
import id.piratesking.autotool.adapter.model.PiratesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PiratesKingClient", url = "${io.piratesking.uri}")
public interface IPiratesKingClient {

    @GetMapping("/api/battle-win")
    BattleResponse battle(@RequestParam("id_wallet") String idWallet, @RequestParam("id_pirate") Integer idPirate, @RequestParam("id_boss") Integer idBoss);

    @GetMapping("/api/list-pirates")
    PiratesResponse getPirates(@RequestParam("action") String action, @RequestParam("id_wallet") String idWallet);

    @GetMapping("/api/balance")
    BalanceResponse getBalance(@RequestParam("id_wallet") String idWallet);
}
