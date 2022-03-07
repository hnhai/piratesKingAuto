package id.piratesking.autotool.adapter.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceResponse extends BaseResponse {

    String busd;
    String pkt;
}
