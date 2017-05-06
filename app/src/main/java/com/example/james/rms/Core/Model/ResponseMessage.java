package com.example.james.rms.Core.Model;

/**
 * Created by jamie on 2017/5/7.
 */

public class ResponseMessage {
        private String request;
        private String response;
        private String status;
        private String message;
        public String getRequest() {
            return request;
        }
        public void setRequest(String request) {
            this.request = request;
        }
        public String getResponse() {
            return response;
        }
        public void setResponse(String response) {
            this.response = response;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return "ResponseMessage [request=" + request + ", response=" + response + ", status=" + status + ", message="
                    + message + "]";
        }

}
