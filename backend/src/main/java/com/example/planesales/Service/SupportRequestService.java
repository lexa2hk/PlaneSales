package com.example.planesales.Service;


import com.example.planesales.DTO.SupportRequestDto;
import com.example.planesales.Entity.SupportRequest;
import com.example.planesales.Repository.SupportRequestRepository;
import com.example.planesales.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportRequestService {


    private final SupportRequestRepository supportRequestRepository;
    private final UserRepository userRepository;

    public List<SupportRequestDto> findAll() {
        List<SupportRequest> supportRequests = supportRequestRepository.findAll();
        List<SupportRequestDto> supportRequestDtos = new LinkedList<>();
        supportRequests.forEach(supportRequest -> supportRequestDtos.add(SupportRequestDto.builder()
                        .requestText(supportRequest.getRequestText())
                        .username(supportRequest.getUser().getUsername())
                .build()));
        return supportRequestDtos;
    }

    public Optional<SupportRequest> findById(int id) {
        return supportRequestRepository.findById(id);
    }

    public SupportRequest save(SupportRequestDto supportRequestDto) {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setRequestText(supportRequestDto.getRequestText());
//        supportRequest.setUser(userRepository.findByEmail(supportRequestDto.getUsername()).orElseThrow());
        supportRequest.setUser(userRepository.findById(1).orElseThrow());
        supportRequest.setSupportEmployee(null);
        return supportRequestRepository.save(supportRequest);
    }

    public SupportRequest save(SupportRequestDto supportRequestDto, int id) {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setIdSupportRequest(id);
        supportRequest.setRequestText(supportRequestDto.getRequestText());
        supportRequest.setUser(userRepository.findByEmail(supportRequestDto.getUsername()).orElseThrow());
        supportRequest.setSupportEmployee(null);
        return supportRequestRepository.save(supportRequest);
    }

    public void deleteById(int id) {
        supportRequestRepository.deleteById(id);
    }
}