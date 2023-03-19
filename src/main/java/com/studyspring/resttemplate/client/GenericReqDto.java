package com.studyspring.resttemplate.client;

import lombok.Data;
import lombok.Getter;

@Data
public class GenericReqDto<T> {

    private Header header;

    private T rBody;

    @Data
    public static class Header {
        private int responseCode;
    }
}
