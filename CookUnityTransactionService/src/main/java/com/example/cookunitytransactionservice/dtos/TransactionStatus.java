package com.example.cookunitytransactionservice.dtos;

public enum TransactionStatus {
    NONE(TransactionStatus.NONE_ID),
    FAILED(TransactionStatus.FAILED_ID),
    SUCCESS(TransactionStatus.SUCCESS_ID);

    public static final int NONE_ID = 0;
    public static final int FAILED_ID = 1;

    public static final int SUCCESS_ID = 2;

    private int id;
    private String name;

    TransactionStatus(int id){
        this.id = id;
    }

    TransactionStatus(int id, String name){
        this.id = id;
        this.name = name;
    }
}
