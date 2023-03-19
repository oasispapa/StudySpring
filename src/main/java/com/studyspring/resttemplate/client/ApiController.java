package com.studyspring.resttemplate.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/getHello")
    public UserResponseDto getHello() {
        return restTemplateService.getHello();
    }

    @PostMapping("/postHello")
    public UserResponseDto postHello() {
        return restTemplateService.postHello();
    }

    @PostMapping("/postExchange")
    public UserResponseDto postExchange() {
        return restTemplateService.postExchange();
    }

    @PostMapping("/genericExchange")
    public GenericReqDto<UserResponseDto> genericExchange() {
        return restTemplateService.genericExchange();
    }

}
