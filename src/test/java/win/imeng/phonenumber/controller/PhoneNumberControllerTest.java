package win.imeng.phonenumber.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import win.imeng.phonenumber.controller.PhoneNumberController.PhoneNumberRequest;
import win.imeng.phonenumber.service.PhoneNumberService;

@WebFluxTest(PhoneNumberController.class)
@Import(PhoneNumberService.class) // Import the service that the controller depends on
class PhoneNumberControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void parsePhoneNumberWithGetRequest() {
        // Test with a number that doesn't require + sign to avoid URL encoding issues
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/phone/parse")
                        .queryParam("number", "13800138000")
                        .queryParam("region", "CN")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.phoneNumber").isEqualTo("13800138000")
                .jsonPath("$.valid").isEqualTo(true)
                .jsonPath("$.countryCode").isEqualTo("86")
                .jsonPath("$.countryIsoCode").isEqualTo("CN");
    }

    @Test
    void parsePhoneNumberWithInvalidNumberGetRequest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/phone/parse")
                        .queryParam("number", "invalid-number")
                        .queryParam("region", "CN")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.phoneNumber").isEqualTo("invalid-number")
                .jsonPath("$.valid").isEqualTo(false);
    }

    @Test
    void parsePhoneNumberWithPostRequest() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        request.setNumber("+12125551234");
        request.setRegion("US");

        webTestClient.post()
                .uri("/api/phone/parse")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.phoneNumber").isEqualTo("+12125551234")
                .jsonPath("$.valid").isEqualTo(true)
                .jsonPath("$.countryCode").isEqualTo("1")
                .jsonPath("$.countryIsoCode").isEqualTo("US");
    }

    @Test
    void parsePhoneNumberWithInvalidNumberPostRequest() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        request.setNumber("invalid-number");
        request.setRegion("CN");

        webTestClient.post()
                .uri("/api/phone/parse")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.phoneNumber").isEqualTo("invalid-number")
                .jsonPath("$.valid").isEqualTo(false);
    }
}