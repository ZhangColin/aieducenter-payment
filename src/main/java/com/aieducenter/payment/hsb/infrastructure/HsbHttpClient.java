package com.aieducenter.payment.hsb.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class HsbHttpClient {

    private static final Logger log = LoggerFactory.getLogger(HsbHttpClient.class);

    private final HttpClient httpClient;

    public HsbHttpClient() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    public String postJson(String url, String json) {
        log.info("HSB HTTP POST: url={}", url);
        log.debug("HSB HTTP POST body: {}", json);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.ofSeconds(30))
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("HSB HTTP response: status={}", response.statusCode());
            log.debug("HSB HTTP response body: {}", response.body());

            if (response.statusCode() != 200) {
                throw new RuntimeException("建行接口返回 HTTP " + response.statusCode() + ": " + response.body());
            }
            return response.body();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("建行接口调用失败: " + e.getMessage(), e);
        }
    }
}
