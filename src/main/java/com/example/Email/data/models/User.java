package com.example.Email.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Message> messages = new ArrayList<>();
}
