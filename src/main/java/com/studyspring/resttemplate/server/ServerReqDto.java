package com.studyspring.resttemplate.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerReqDto<T> {

    private Header header;

    private T rBody;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header {
        private int responseCode;
    }

}
