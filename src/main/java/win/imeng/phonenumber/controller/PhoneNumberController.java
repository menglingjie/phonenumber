package win.imeng.phonenumber.controller;

import lombok.Data;
import win.imeng.phonenumber.model.PhoneNumberInfo;
import win.imeng.phonenumber.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/phone")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping("/parse")
    public Mono<PhoneNumberInfo> parsePhoneNumber(
            @RequestParam String number,
            @RequestParam(defaultValue = "CN") String region) {
        return Mono.just(phoneNumberService.parsePhoneNumber(number, region));
    }

    @PostMapping("/parse")
    public Mono<PhoneNumberInfo> parsePhoneNumberPost(@RequestBody PhoneNumberRequest request) {
        return Mono.just(phoneNumberService.parsePhoneNumber(request.getNumber(), request.getRegion()));
    }

    @Data
    public static class PhoneNumberRequest {
        private String number;
        private String region = "CN";
    }
}