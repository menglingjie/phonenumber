package win.imeng.phonenumber.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberInfoTest {

    @Test
    void phoneNumberInfoGettersAndSetters() {
        // Given
        PhoneNumberInfo info = new PhoneNumberInfo();

        // When & Then
        info.setPhoneNumber("+8613800138000");
        assertThat(info.getPhoneNumber()).isEqualTo("+8613800138000");

        info.setValid(true);
        assertThat(info.isValid()).isTrue();

        info.setCountryCode("86");
        assertThat(info.getCountryCode()).isEqualTo("86");

        info.setCountryIsoCode("CN");
        assertThat(info.getCountryIsoCode()).isEqualTo("CN");

        info.setNationalNumber("13800138000");
        assertThat(info.getNationalNumber()).isEqualTo("13800138000");

        info.setRegion("China");
        assertThat(info.getRegion()).isEqualTo("China");

        info.setCarrier("China Mobile");
        assertThat(info.getCarrier()).isEqualTo("China Mobile");

        info.setNumberType("MOBILE");
        assertThat(info.getNumberType()).isEqualTo("MOBILE");
    }

    @Test
    void testToString() {
        // Given
        PhoneNumberInfo info = new PhoneNumberInfo();
        info.setPhoneNumber("+8613800138000");
        info.setValid(true);

        // When
        String toStringResult = info.toString();

        // Then
        assertThat(toStringResult).contains("+8613800138000");
        assertThat(toStringResult).contains("true");
    }
}