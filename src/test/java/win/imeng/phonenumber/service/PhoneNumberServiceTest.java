package win.imeng.phonenumber.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import win.imeng.phonenumber.model.PhoneNumberInfo;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberServiceTest {

    private PhoneNumberService phoneNumberService;

    @BeforeEach
    void setUp() {
        phoneNumberService = new PhoneNumberService();
    }

    @Test
    void parseValidChineseMobileNumber() {
        // Given
        String phoneNumber = "+8613800138000";
        String region = "CN";

        // When
        PhoneNumberInfo result = phoneNumberService.parsePhoneNumber(phoneNumber, region);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.isValid()).isTrue();
        assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(result.getCountryCode()).isEqualTo("86");
        assertThat(result.getCountryIsoCode()).isEqualTo("CN");
    }

    @Test
    void parseInvalidPhoneNumber() {
        // Given
        String phoneNumber = "invalid-number";
        String region = "CN";

        // When
        PhoneNumberInfo result = phoneNumberService.parsePhoneNumber(phoneNumber, region);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.isValid()).isFalse();
        assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void parseUSPhoneNumber() {
        // Given
        String phoneNumber = "+12125551234";
        String region = "US";

        // When
        PhoneNumberInfo result = phoneNumberService.parsePhoneNumber(phoneNumber, region);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.isValid()).isTrue();
        assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(result.getCountryCode()).isEqualTo("1");
        assertThat(result.getCountryIsoCode()).isEqualTo("US");
    }

    @Test
    void parseUKPhoneNumber() {
        // Given
        String phoneNumber = "+442071234567";
        String region = "GB";

        // When
        PhoneNumberInfo result = phoneNumberService.parsePhoneNumber(phoneNumber, region);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.isValid()).isTrue();
        assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(result.getCountryCode()).isEqualTo("44");
        assertThat(result.getCountryIsoCode()).isEqualTo("GB");
    }
}