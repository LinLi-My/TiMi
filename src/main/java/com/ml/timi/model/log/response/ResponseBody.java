package com.ml.timi.model.log.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * (ResponseBody)实体类
 *
 * @author Lin
 * @since 2021-08-30 09:42:34
 */

@Data
@Component
public class ResponseBody implements Serializable {

    private static final long serialVersionUID = 385157276387914894L;

    /** 业务主键 */
    private String naturalkey;
    /** 状态 */
    private String status;
    /** 信息 */
    private String message;
    /** 主键 */
    private Integer id;


    public static final class ResponseBodyBuilder {
        private String naturalkey;
        private String status;
        private String message;
        private Integer id;

        public ResponseBodyBuilder() {
        }

        public static ResponseBodyBuilder aResponseBody() {
            return new ResponseBodyBuilder();
        }

        public ResponseBodyBuilder setNaturalkey(String naturalkey) {
            this.naturalkey = naturalkey;
            return this;
        }

        public ResponseBodyBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ResponseBodyBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBodyBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ResponseBody build() {
            ResponseBody responseBody = new ResponseBody();
            responseBody.setNaturalkey(naturalkey);
            responseBody.setStatus(status);
            responseBody.setMessage(message);
            responseBody.setId(id);
            return responseBody;
        }
    }
}

