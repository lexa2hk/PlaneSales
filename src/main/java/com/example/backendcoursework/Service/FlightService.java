package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Entity.Company;
//import com.example.backendcoursework.Entity.FlightCompany;
import com.example.backendcoursework.Repository.CompanyRepository;
import com.example.backendcoursework.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CompanyRepository companyRepository;
    private final Random random;

    @Autowired
    public FlightService(FlightRepository flightRepository, CompanyRepository companyRepository) {
        this.flightRepository = flightRepository;
        this.companyRepository = companyRepository;
        this.random = new Random();
    }

    public List<Flight> fillFlights(){
        List<Flight> flights = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            flights.add(addRandomFlight());
        }
        return flights;
    }

    public Flight createFlight(String route, int passengerQty, Integer duration) {
        Flight flight = new Flight();
        flight.setRoute(route);
        flight.setPassengerQty(passengerQty);
        flight.setDuration(duration);
        // may be not fast, there is not a lot of companies
        long companyCount = companyRepository.count();
        List<Company> companys = StreamSupport.stream(companyRepository.findAll().spliterator(), false)
                .toList();
        Company company = companys.get(random.nextInt(Math.toIntExact(companyCount)));
        flight.setCompanies(new LinkedList<>(List.of(company)));
        return flightRepository.save(flight);
    }



    public Flight addRandomFlight(){
        Flight flight = new Flight();

        String randomRoute = createFlightCode();
        flight.setRoute(randomRoute);

        int randomPassengerQty = generateRandomPassengerQty();
        flight.setPassengerQty(randomPassengerQty);

        Integer randomDuration = generateRandomDuration();
        flight.setDuration(randomDuration);

        return flightRepository.save(flight);
    }


    private String createFlightCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        code.append((char) (random.nextInt(26) + 'A'));
        code.append((char) (random.nextInt(26) + 'A'));
        for (int i = 0; i < 5; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }



    private int generateRandomPassengerQty() {
        return random.nextInt(200) + 1;
    }

    private Integer generateRandomDuration() {
        return random.nextInt(600) + 60;
    }
}