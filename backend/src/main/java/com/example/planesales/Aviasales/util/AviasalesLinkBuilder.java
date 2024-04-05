package com.example.planesales.Aviasales.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

@UtilityClass
public class AviasalesLinkBuilder {
    @Value("${travelpayouts.referral-url}")
    private String referralUrl;

    //build url with link parametetr
    public String buildReferralFlightUrl(String link) {
        return referralUrl + link;
    }
}
