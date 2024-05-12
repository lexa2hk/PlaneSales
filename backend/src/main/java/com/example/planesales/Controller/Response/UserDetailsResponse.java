package com.example.planesales.Controller.Response;

public record UserDetailsResponse( int idUser,
                                   String passportData,
                                   String name,
                                   String surname,
                                   String patronymic,
                                   String birthDate,
                                   String exemption,
                                   String email) {
}
