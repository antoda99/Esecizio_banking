package controller;

import request.BonificoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.BonificoService;

@RestController
@RequestMapping(path = "/bonifico")
public class BonificoController {

    @Autowired
    private BonificoService bonificoService;

    @PostMapping(path = "/effettuaBonifico")
    public ResponseEntity<?> effettuaBonifico(@RequestBody BonificoRequest bonificoRequest) {
        return ResponseEntity.ok().body(
                bonificoService.effettuaBonifico(bonificoRequest)
        );
    }
}
