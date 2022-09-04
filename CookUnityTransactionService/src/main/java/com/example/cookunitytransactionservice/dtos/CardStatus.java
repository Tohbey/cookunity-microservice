package com.example.cookunitytransactionservice.dtos;

public enum CardStatus {
    AVAILABLE(CardStatus.AVAILABLE_ID),
    BLOCKED(CardStatus.BLOCKED_ID);

    public static final int AVAILABLE_ID = 0;
    public static final int BLOCKED_ID = 1;


    private int id;
    private String name;

    CardStatus(int id){
        this.id = id;
    }

    CardStatus(int id, String name){
        this.id = id;
        this.name = name;
    }
}
