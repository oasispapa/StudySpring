package com.studyspring.resttemplate.server;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/getHello")
    public String getHello() {
        return "hello server";
    }
    @GetMapping("/getHello2")
    public ServerUserDto getHello2(@RequestParam String name, @RequestParam int age) {
        ServerUserDto user = new ServerUserDto();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/postHello/{age}/name/{userName}")
    public ServerUserDto postHello(@RequestBody ServerUserDto user, @PathVariable int age, @PathVariable String userName) {
        log.info("age : {}  /  name : {}", age, userName);
        log.info("{}", user);

        return user;
    }

    @PostMapping("/postExchange")
    public ServerUserDto postExchange(@RequestBody ServerUserDto user,
                                      @RequestHeader("x-authorization") String authorization,
                                      @RequestHeader("custom-header") String customHeader){
        log.info("authorization : {}", authorization);
        log.info("customHeader : {}", customHeader);
        return user;
    }
    @PostMapping("/genericExchange")
    public ServerReqDto<ServerUserDto> genericExchange(@RequestBody ServerReqDto<ServerUserDto> reqDto,
                                      @RequestHeader("x-authorization") String authorization,
                                      @RequestHeader("custom-header") String customHeader){
        log.info("authorization : {}", authorization);
        log.info("customHeader : {}", customHeader);
        ServerReqDto<ServerUserDto> res = new ServerReqDto<>();
        res.setHeader(new ServerReqDto.Header(200));
        res.setRBody(reqDto.getRBody());
        return res;
    }

}
