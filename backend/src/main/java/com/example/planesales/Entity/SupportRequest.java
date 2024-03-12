package com.example.planesales.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "support_request")
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupportRequest;

    private String requestText;

    @ManyToOne
    @JoinColumn(name = "user_id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "support_employee_id_support_employee")
    private SupportEmployee supportEmployee;



}