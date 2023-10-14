package services;

import request.BonificoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BonificoService {

    @Value("${fabrick.api.key}")
    private String fabrickApiKey;

    @Value("${fabrick.api.baseUrl}")
    private String fabrickBaseUrl;

    private final RestTemplate restTemplate;

    public BonificoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> effettuaBonifico(BonificoRequest bonificoRequest) {

        String apiUrl = fabrickBaseUrl + "/api/gbs/banking/v4.0/accounts/" + bonificoRequest.getAccountId() + "/payments/money-transfers";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", fabrickApiKey);

        HttpEntity<BonificoRequest> requestEntity = new HttpEntity<>(bonificoRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response;
    }

    /*

    public void eseguiBonifico(BonificoRequest bonificoRequest) {

        String amount = bonificoRequest.getAmount();
            if (amount.startsWith("-")) {
                throw new IllegalArgumentException("L'importo non può essere negativo");
            }

        Bonifico bonifico = new Bonifico(bonificoRequest.getAccountId(), bonificoRequest.getReceiverName(), bonificoRequest.getDescription(), bonificoRequest.getCurrency(), Double.parseDouble(amount), bonificoRequest.getExecutionDate());

        bonificoRepository.save(bonifico);

    }

     */
}
