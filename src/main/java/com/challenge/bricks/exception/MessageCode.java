package com.challenge.bricks.exception;

public enum MessageCode implements GeneralMessageCode{

    ERROR_AT_PROCESS_JSON("Error at process json"),
    CATEGORY_NOT_FOUND("Error, Category not found"),
    PRODUCT_NOT_FOUND("Error, Product not found"),
    ERROR_JOB_UPDATE_CATEGORY_TABLE("Error at process update category table job"),
    ERROR_CALLING_UPDATE_CATEGORY_TABLE("Error at calling services update category table job"),

    CATEGORY_EXISTS_WITH_NAME("Error, Category already exist with this name"),
    ;

    private final String message;


    MessageCode(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public String getExceptionCode() {
        return this.name();
    }
}
