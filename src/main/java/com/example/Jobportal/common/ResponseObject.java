package com.example.Jobportal.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseObject {
    private String message;
    private Object data;

    public ResponseObject(String message) {
        this.message = message;
    }

    public ResponseObject(Object data) {
        this.data = data;
    }

}
