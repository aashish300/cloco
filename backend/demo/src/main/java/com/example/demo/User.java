package com.example.demo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    private int  id;

    @Id
    @Column(name = "MARK")
    private int  mark;

    @Id
    @Column(name = "NAME")
    private String  name;
}
