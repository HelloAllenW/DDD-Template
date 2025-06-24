package cn.helloallen.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}

