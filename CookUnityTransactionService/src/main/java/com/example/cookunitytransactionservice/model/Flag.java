package com.example.cookunitytransactionservice.model;

public enum Flag {
    NONE(Flag.NONE_ID),
    FAILED(Flag.FAILED_ID),
    SUCCESS(Flag.SUCCESS_ID);

    public static final int NONE_ID = 0;
    public static final int FAILED_ID = 1;

    public static final int SUCCESS_ID = 2;

    private int id;
    private String name;

    Flag(int id){
        this.id = id;
    }

    Flag(int id, String name){
        this.id = id;
        this.name = name;
    }
}
