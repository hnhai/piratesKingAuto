package id.piratesking.autotool.adapter.model;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PiratesResponse extends BaseResponse {

    List<PirateResponse> results;

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PirateResponse {

        String chestCode;
        String chestName;
        String energy;
        String id;
        String chestTokenId;
    }
}
