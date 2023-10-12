package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.TransazioneService;

@RestController
//@RequestMapping(path = "/transazioni")
public class TransazioneController {

    @Autowired
    private TransazioneService transazioniService;

    @GetMapping(path = "/getTransazioni")
    public ResponseEntity<?> getTransazioni(@RequestParam Long accountId, @RequestParam String fromAccountingDate, @RequestParam String toAccountingDate) {
        return ResponseEntity.ok().body(
                transazioniService.getTransazioni(accountId, fromAccountingDate, toAccountingDate)
        );
    }
}
