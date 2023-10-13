package services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SaldoService {

    @Value("${fabrick.api.key}")
    private String fabrickApiKey;

    @Value("${fabrick.api.baseUrl}")
    private String fabrickBaseUrl;

    private final RestTemplate restTemplate;

    public SaldoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getSaldo(Long accountId) {

        String apiUrl = fabrickBaseUrl + "/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";

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

    altrimenti, direttamente getSaldoByAccountId nella repository, in modo da passare al frontend il campo richiesto tramite hateos

    public Long getSaldo(Long accountId){

        Long saldo = contoRepository.getSaldoByAccountId(accountId);

        return saldo;
    }
     */
}
