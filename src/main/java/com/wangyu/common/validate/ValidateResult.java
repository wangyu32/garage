package com.wangyu.common.validate;

import lombok.Data;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/5 1:20
 */
@Data
public class ValidateResult {

    public static ValidateResult VALID = new ValidateResult(true);
    public static ValidateResult INVALID = new ValidateResult(false);

    private boolean valid;

    private String errorMessage;

    public ValidateResult() {
    }

    public ValidateResult(boolean valid) {
        this.valid = valid;
    }

    public ValidateResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public ValidateResult setMessage(String message){
        this.errorMessage = message;
        return this;
    }

    public boolean isInvalid(){
        return !this.valid;
    }

}
