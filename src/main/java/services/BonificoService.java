package services;

import Request.BonificoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BonificoService {

    private final RestTemplate restTemplate;
    private final String fabrickBaseUrl;
    private final String fabrickApiKey;

    @Autowired
    public BonificoService(RestTemplate restTemplate,
                           @Value("${fabrick.api.baseUrl}") String fabrickBaseUrl,
                           @Value("${fabrick.api.key}") String fabrickApiKey) {
        this.restTemplate = restTemplate;
        this.fabrickBaseUrl = fabrickBaseUrl;
        this.fabrickApiKey = fabrickApiKey;
    }

    public ResponseEntity<?> effettuaBonifico(BonificoRequest bonificoRequest) {

        String apiUrl = fabrickBaseUrl + "/bonifico/eseguiBonifico";

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
}
