package id.piratesking.autotool.controller;

import id.piratesking.autotool.service.IAutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/battles")
@RequiredArgsConstructor
public class BattleController {

    private final IAutoService service;

    @PostMapping
    public ResponseEntity<String> battle() {
        service.autoBattle();
        return ResponseEntity.ok("Done");
    }
}
