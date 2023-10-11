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
    private String apiKey;

    public ResponseEntity<?> getSaldo(Long accountId) {
        return null;
    }
}
