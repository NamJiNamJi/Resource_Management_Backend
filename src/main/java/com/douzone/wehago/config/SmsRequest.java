package com.douzone.wehago.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class SmsRequest {
    @NotBlank
    private final String phoneNumber;

    @NotBlank
    private final String message;

    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
                      @JsonProperty("message") String message){
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

}
