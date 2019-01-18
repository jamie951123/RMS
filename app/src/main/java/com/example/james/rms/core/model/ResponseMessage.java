package com.example.james.rms.core.model;

/**
 * Created by jamie on 2017/5/7.
 */

public class ResponseMessage {
    private String message_request;
    private String message_response;
    private String message_status;
    private String message_content;
    private Integer message_count;

    public String getMessage_request() {
        return message_request;
    }
    public void setMessage_request(String message_request) {
        this.message_request = message_request;
    }
    public String getMessage_response() {
        return message_response;
    }
    public void setMessage_response(String message_response) {
        this.message_response = message_response;
    }
    public String getMessage_status() {
        return message_status;
    }
    public void setMessage_status(String message_status) {
        this.message_status = message_status;
    }
    public String getMessage_content() {
        return message_content;
    }
    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
    public Integer getMessage_count() {
        return message_count;
    }
    public void setMessage_count(Integer message_count) {
        this.message_count = message_count;
    }
    @Override
    public String toString() {
        return "ResponseMessage [message_request=" + message_request + ", message_response=" + message_response
                + ", message_status=" + message_status + ", message_content=" + message_content + ", message_count="
                + message_count + "]";
    }




}
