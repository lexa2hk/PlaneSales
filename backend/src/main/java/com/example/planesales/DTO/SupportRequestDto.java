package com.example.planesales.DTO;

import com.example.planesales.Entity.SupportRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Setter
@Getter
@Builder
public class SupportRequestDto {
    String requestText;
    String username;


}
