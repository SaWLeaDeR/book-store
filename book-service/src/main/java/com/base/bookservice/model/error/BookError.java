package com.base.bookservice.model.error;

public final class BookError {
    public static final String TOP_ERROR_DOMAIN = "book-service-error";
    public static final String NOT_FOUND = "book-not-found";
    public static final String VALIDATOR_NOT_FOUND = "validator-not-found";
    public static final String EMPTY_REQUEST = "request-should-be-filled";
    public static final String NAME_INVALID = "name-should-be-filled";
    public static final String NAME_SIZE_EXCEEDS = "name-size-exceeded";
    public static final String ID_INVALID = "book-id-should-be-hex";
    public static final String ID_CAN_NOT_BE_EMPTY = "id-should-be-given";
    public static final String SELLER_INVALID = "seller-should-be-given";
    public static final String SELLER_SIZE_EXCEEDS = "seller-character-size-exceed";
    public static final String STOCK_INVALID = "stock-number-should-be-given";
    public static final String STOCK_SIZE_INVALID = "stock-number-should-be-greater-than-zero";
    public static final String BOOK_NOT_FOUND = "book-not-found";

    private BookError() {
    }


}
