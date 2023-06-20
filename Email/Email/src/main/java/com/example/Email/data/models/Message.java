package com.example.Email.data.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Message {
    private String subject;
    private String body;
    private LocalDateTime timeStamp;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
