package com.clay.springcloud.entities;


import lombok.Data;

@Data
public class Payment {
    private Long id;

    private String serial;

    public Payment(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }
}