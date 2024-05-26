package com.example.planesales.Controller;

import com.example.planesales.DTO.SupportRequestDto;
import com.example.planesales.Entity.SupportRequest;
import com.example.planesales.Service.SupportRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/support-requests")
@CrossOrigin(origins = "http://registry.lexa2hk.ru")
@RequiredArgsConstructor
public class SupportRequestController {

    private final SupportRequestService supportRequestService;

    @GetMapping
    public ResponseEntity<List<SupportRequestDto>> findAll() {
        List<SupportRequestDto> supportRequests = supportRequestService.findAll();
        return ResponseEntity.ok(supportRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportRequest> findById(@PathVariable int id) {
        Optional<SupportRequest> supportRequest = supportRequestService.findById(id);
        return supportRequest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupportRequest> save(@RequestBody SupportRequestDto supportRequest) {
        SupportRequest savedSupportRequest = supportRequestService.save(supportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupportRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportRequest> update(@PathVariable int id, @RequestBody SupportRequestDto supportRequest) {
        Optional<SupportRequest> existingSupportRequest = supportRequestService.findById(id);
        if (existingSupportRequest.isPresent()) {
            SupportRequest updatedSupportRequest = supportRequestService.save(supportRequest,id);
            return ResponseEntity.ok(updatedSupportRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        Optional<SupportRequest> existingSupportRequest = supportRequestService.findById(id);
        if (existingSupportRequest.isPresent()) {
            supportRequestService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}