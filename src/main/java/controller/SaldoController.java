package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.SaldoService;

@RestController
@RequestMapping(path = "/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping(path = "/getSaldo")
    public ResponseEntity<?> getSaldo(Long accountId) {
        return ResponseEntity.ok().body(
                saldoService.getSaldo(accountId)
        );
    }
}
