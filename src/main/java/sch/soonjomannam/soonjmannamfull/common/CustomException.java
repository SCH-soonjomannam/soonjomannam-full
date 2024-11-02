package sch.soonjomannam.soonjmannamfull.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCustom errorCustom;

    // Constructor for CustomException with ErrorCustom and custom message
    public CustomException(ErrorCustom errorCustom, String message) {
        super(message);
        if (errorCustom == null) {
            throw new IllegalArgumentException("ErrorCustom cannot be null");
        }
        this.errorCustom = errorCustom;
    }

    // Constructor for CustomException with only ErrorCustom
    public CustomException(ErrorCustom errorCustom) {
        super(errorCustom != null ? errorCustom.getDescription() : null);
        if (errorCustom == null) {
            throw new IllegalArgumentException("ErrorCustom cannot be null");
        }
        this.errorCustom = errorCustom;
    }



    public ErrorCustom getErrorCustom() {
        return errorCustom;
    }


}
