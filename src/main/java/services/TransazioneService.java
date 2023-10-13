package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransazioneService {

    @Value("${fabrick.api.key}")
    private String fabrickApiKey;

    @Value("${fabrick.api.baseUrl}")
    private String fabrickBaseUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public TransazioneService(RestTemplate restTemplate, String fabrickBaseUrl, String fabrickApiKey) {
        this.restTemplate = restTemplate;
        this.fabrickBaseUrl = fabrickBaseUrl;
        this.fabrickApiKey = fabrickApiKey;
    }

    public ResponseEntity<?> getTransazioni(Long accountId, String fromAccountingDate, String toAccountingDate) {

        String apiUrl = fabrickBaseUrl + "/api/gbs/banking/v4.0/accounts/" + accountId + "/transactions";

        String queryString = "?fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;

        apiUrl += queryString;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", fabrickApiKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        return response;
    }

    /*
    public List<Transazione> getListTransazioni(Long accountId, String fromAccountingDate, String toAccountingDate){

        la query nella repo tirerebbe fuori direttamente le transazioni nel range temporale fromAccountingDate --> toAccountingDate
        List<Transazione> listaTransazioni = transazioniRepository.getListTransazioniByAccountId(accountId, fromAccountingDate, toAccountingDate);

        if (listaTransazioni.isEmpty()) {
            throw new IllegalStateException("Nessuna transazione trovata nel range di tempo inserito");
        }

        return listaTransazioni;
    }
     */
}
