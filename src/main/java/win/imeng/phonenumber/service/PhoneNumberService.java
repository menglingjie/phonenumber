package win.imeng.phonenumber.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import win.imeng.phonenumber.model.PhoneNumberInfo;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PhoneNumberService {

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private final PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();
    private final PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

    public PhoneNumberInfo parsePhoneNumber(String phoneNumber, String region) {
        PhoneNumberInfo info = new PhoneNumberInfo();
        info.setPhoneNumber(phoneNumber);

        try {
            Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(phoneNumber, region);
            boolean isValid = phoneNumberUtil.isValidNumber(parsedNumber);

            info.setValid(isValid);
            if (isValid) {
                info.setCountryCode(String.valueOf(parsedNumber.getCountryCode()));
                info.setNationalNumber(String.valueOf(parsedNumber.getNationalNumber()));
                info.setCountryIsoCode(phoneNumberUtil.getRegionCodeForNumber(parsedNumber));
                info.setRegion(geocoder.getDescriptionForNumber(parsedNumber, Locale.ENGLISH));
                info.setCarrier(carrierMapper.getNameForNumber(parsedNumber, Locale.ENGLISH));
                info.setNumberType(phoneNumberUtil.getNumberType(parsedNumber).toString());
            }
        } catch (NumberParseException e) {
            info.setValid(false);
        }

        return info;
    }
}