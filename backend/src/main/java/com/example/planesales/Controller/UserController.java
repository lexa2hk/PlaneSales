package com.example.planesales.Controller;

import com.example.planesales.Aviasales.db.entity.FlightsForDates;
import com.example.planesales.Aviasales.db.repository.FlightsForDatesRepository;
import com.example.planesales.Controller.Response.UserDetailsResponse;
import com.example.planesales.Entity.User;
import com.example.planesales.Repository.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://registry.lexa2hk.ru")
@Hidden
public class UserController {

    private final UserRepository userRepository;
    private final FlightsForDatesRepository flightsForDatesRepository;

    public UserController(UserRepository userRepository, FlightsForDatesRepository flightsForDatesRepository) {
        this.userRepository = userRepository;
        this.flightsForDatesRepository = flightsForDatesRepository;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        updatedUser.setIdUser(id);
        return userRepository.save(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/flight")
    public boolean addFlight(@RequestBody String url, @AuthenticationPrincipal UserDetails userDetails){
        userRepository.findByEmail(userDetails.getUsername()).ifPresent(user -> {
//            List<FlightsForDates> flights = user.getFlightsList();
//            flights.add(flightsForDatesRepository.findByLink(url));
//            user.setFlightsList(flights);
//            userRepository.save(user);
        });

        return false;
    }

    @GetMapping("/account/email/{email}")
    public UserDetailsResponse getUserAccountData(@PathVariable String email){
        return userRepository.findByEmail(email).map(user -> new UserDetailsResponse(user.getIdUser(),
                user.getPassportData(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getBirthDate(),
                user.getExemption(),
                user.getEmail())).orElse(null);
    }

    @GetMapping("/account")
    public UserDetailsResponse getUserAccount(@AuthenticationPrincipal Principal principal){
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        return userRepository.findByEmail(principal.getName()).map(user -> new UserDetailsResponse(user.getIdUser(),
                user.getPassportData(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getBirthDate(),
                user.getExemption(),
                user.getEmail())).orElse(null);
    }
}