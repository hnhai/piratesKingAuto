package id.piratesking.autotool.utils;

import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class CommonUtil {

    public static Integer parseInteger(String textNumber) {
        try {
            return Integer.parseInt(textNumber);
        } catch (Exception e) {
            log.error("Parse number fail: {}", textNumber);
            return 0;
        }
    }

    public static String buildTelegramMsg(String startBusd, String startPkt, List<String> battles, String endBusd, String endPkt) {
        var message = new StringBuilder("Start BUSD: ").append(startBusd).append(System.lineSeparator());
        message.append("Start PKT: ").append(startPkt).append(System.lineSeparator());
        for (var battle : battles) {
            message.append(" - ").append(battle).append(System.lineSeparator());
        }
        message.append("END BUSD: ").append(endBusd).append(System.lineSeparator());
        message.append("END PKT: ").append(endPkt).append(System.lineSeparator());
        return message.toString();
    }
}
