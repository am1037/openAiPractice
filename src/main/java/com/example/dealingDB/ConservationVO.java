package com.example.dealingDB;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ConservationVO {
    private String id;
    private String object;
    private long created;
    private String model;
    private long prompt_tokens;
    private long completion_tokens;
    private long total_tokens;
    private String role;
    private String content;
    private String direction;
}
