package win.imeng.phonenumber.model;

import lombok.Data;

@Data
public class PhoneNumberInfo {
    private String phoneNumber;
    private boolean valid;
    private String countryCode;
    private String countryIsoCode;
    private String nationalNumber;
    private String region;
    private String carrier;
    private String numberType;
}