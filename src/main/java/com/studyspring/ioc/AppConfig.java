package com.studyspring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("base64Encode")
    public Encoder base64Encode(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }
    @Bean("urlEncode")
    public Encoder urlEncode(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}
