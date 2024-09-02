package com.example.demo.config;

import com.example.demo.util.IamPortKeys;
import com.siot.IamportRestClient.IamportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentConfig {

    private final IamPortKeys iamPortKeys;

    @Bean
    public IamportClient iamportClient(){
        return new IamportClient(iamPortKeys.getApiKey(),iamPortKeys.getSecretKey());
    }
}
