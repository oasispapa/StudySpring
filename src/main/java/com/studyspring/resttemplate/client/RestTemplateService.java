package com.studyspring.resttemplate.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;

@Service
public class RestTemplateService {

    public UserResponseDto getHello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/getHello2")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        // Case 1
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println("CASE1 : " + result);

        // Case 2
        ResponseEntity<String> result2 = restTemplate.getForEntity(uri, String.class);
        System.out.println("CASE2 : ");
        System.out.println(result2.getStatusCode());
        System.out.println(result2.getBody());

        // Case 3
        ResponseEntity<UserResponseDto> result3 = restTemplate.getForEntity(uri, UserResponseDto.class);
        System.out.println("CASE3 : " + result3.getBody());

        // Case 4
        UserResponseDto result4 = restTemplate.getForObject(uri, UserResponseDto.class);
        System.out.println("CASE4 : " + result4);

        return result3.getBody();
    }

    public UserResponseDto postHello() {
        // http://localhost:8080/api/server/user/{age}/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/postHello/{age}/name/{userName}")
                .encode()
                .build()
                .expand(100, "owj")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequestDto req = new UserRequestDto();
        req.setName("sss");
        req.setAge(122);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponseDto> res = restTemplate.postForEntity(uri, req, UserResponseDto.class);

        System.out.println(res.getStatusCode());
        System.out.println(res.getHeaders());
        System.out.println(res.getBody());

        return res.getBody();
    }

    public UserResponseDto postExchange() {

        // http://localhost:8080/api/server/user/{age}/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/postExchange")
                .encode()
                .build()
                .expand(100, "owj")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequestDto req = new UserRequestDto();
        req.setName("sss");
        req.setAge(122);

        RequestEntity<UserRequestDto> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponseDto> response = restTemplate.exchange(requestEntity, UserResponseDto.class);
        return response.getBody();
    }

    public GenericReqDto<UserResponseDto> genericExchange() {

        // http://localhost:8080/api/server/user/{age}/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/genericExchange")
                .encode()
                .build()
                .expand(100, "owj")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequestDto userReq = new UserRequestDto();
        userReq.setName("sss");
        userReq.setAge(122);

        GenericReqDto<UserRequestDto> req = new GenericReqDto<>();
        req.setHeader(new GenericReqDto.Header());
        req.setRBody(userReq);


        RequestEntity<GenericReqDto<UserRequestDto>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "aaaa")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GenericReqDto<UserResponseDto>> response = restTemplate.exchange(requestEntity,
                new ParameterizedTypeReference<>(){});

        System.out.println(response.getBody().getHeader().getResponseCode());
        // return response.getBody().getRBody();
        return response.getBody();
    }
}
