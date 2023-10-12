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

    private final RestTemplate restTemplate;
    @Value("${fabrick.api.baseUrl}")
    private final String fabrickBaseUrl;
    @Value("${fabrick.api.key}")
    private final String fabrickApiKey;

    @Autowired
    public TransazioneService(RestTemplate restTemplate, String fabrickBaseUrl, String fabrickApiKey) {
        this.restTemplate = restTemplate;
        this.fabrickBaseUrl = fabrickBaseUrl;
        this.fabrickApiKey = fabrickApiKey;
    }

    public ResponseEntity<?> getTransazioni(Long accountId, String fromAccountingDate, String toAccountingDate) {

        String apiUrl = fabrickBaseUrl + "/transazioni/getTransazioni?accountId=" + accountId
                + "&fromAccountingDate=" + fromAccountingDate
                + "&toAccountingDate=" + toAccountingDate;

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
}
