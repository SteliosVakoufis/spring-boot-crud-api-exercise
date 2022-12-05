package com.api.exercise.restapiexercisespring.data.dtos;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Builder;

@Builder
public class ErrorDTO {
    public String title;
    public String detail;
    public int status;
    public String errorType;
    public String errorCode;
    @Builder.Default
    public String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z Z"));
}
