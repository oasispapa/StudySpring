package com.studyspring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.studyspring.ioc.ApplicationContextProvider;
import com.studyspring.ioc.Encoder;
import com.studyspring.objectmapper.dto.Car;
import com.studyspring.objectmapper.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class StudySpringApplicationTests {

    @Test
    void iocTest() {

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

    @Test
    void objectMapperTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        System.out.println(user.toString());

        String json = objectMapper.writeValueAsString(user);

        System.out.println(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>(){});
        System.out.println("cars : " + _cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 13);
        System.out.println(objectNode.toPrettyString());
    }
}
