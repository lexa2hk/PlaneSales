package com.example.backendcoursework.Service;

import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Entity.Company;
import com.example.backendcoursework.Entity.FlightCompany;
import com.example.backendcoursework.Repository.CompanyRepository;
import com.example.backendcoursework.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, CompanyRepository companyRepository) {
        this.flightRepository = flightRepository;
        this.companyRepository = companyRepository;
    }

    public Flight createFlight(String route, int passengerQty, Integer duration) {
        Flight flight = new Flight();

        // todo flight creation

        return flightRepository.save(flight);
    }

    public FlightCompany linkFlightToCompany(Flight flight, Company company) {
        FlightCompany flightCompany = new FlightCompany();

        //todo link flight to company

//        return flightCompanyRepository.save(flightCompany);
        return flightCompany;
    }

}