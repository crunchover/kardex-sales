package com.pipecode.kardexsales.model.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SimpleErrorMessage {
    private String location;
    private String errorType;
    private String errorTitle;
    private List<SimpleErrorMessageDetail> errorDetails;

    public SimpleErrorMessage(
            String location,
            String errorType,
            String errorTitle,
            String detailName,
            String detailReason) {
        this(location, errorType, errorTitle, List.of(new SimpleErrorMessageDetail(detailName, detailReason)));
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleErrorMessageDetail {
        private String name;
        private String reason;
    }
}
