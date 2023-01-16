package com.studyspring;

import com.studyspring.ioc.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class StudySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringApplication.class, args);

        String url = "www.naver.com/books/it?page=10?size=20";

        /*
        Encoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);
        System.out.println(result);

        Encoder encoder2 = new Encoder(new UrlEncoder());
        result = encoder2.encode(url);
        System.out.println(result);
        */

        ApplicationContext context = ApplicationContextProvider.getContext();

        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        //UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        //Encoder encoder = new Encoder(base64Encoder);
        Encoder encoder = context.getBean("urlEncode", Encoder.class);
        String result = encoder.encode(url);
        System.out.println(result);
        encoder = context.getBean("base64Encode", Encoder.class);
        result = encoder.encode(url);
        System.out.println(result);
    }

}