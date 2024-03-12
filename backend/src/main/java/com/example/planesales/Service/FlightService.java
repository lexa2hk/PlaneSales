package com.example.planesales.Service;

import com.example.planesales.Entity.Flight;
import com.example.planesales.Entity.Company;
//import com.example.planesales.Entity.FlightCompany;
import com.example.planesales.Repository.CompanyRepository;
import com.example.planesales.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CompanyRepository companyRepository;
    private final Random random;
    private final CompanyService companyService;

    @Autowired
    public FlightService(FlightRepository flightRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.flightRepository = flightRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.random = new Random();
    }


    public List<Flight> getFlights(){
        List<Flight> flights = new LinkedList<>();
        flightRepository.findAll().forEach(flights::add);
        return flights;
    }

    public Flight getFlightByRoute(String route){
        Flight flight = flightRepository.findAllByRoute(route).iterator().next();
        return flight;
    }


    /**
     * Generates a list of flights by filling it with random flights.
     *
     * @return         	The list of flights generated
     */
    public List<Flight> fillFlights(){
        //generate companies
        companyService.fillCompanies();
        List<Flight> flights = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            flights.add(addRandomFlight());
        }
        return flights;
    }

    public List<Flight> fillFlightsWithCompanies(){
        List<Flight> flights = fillFlights();
        defineCompaniesToFlights();
        return flights;

    }

    public void defineCompaniesToFlights(){
        long companyCount = companyRepository.count();

        List<Company> companys = StreamSupport.stream(companyRepository.findAll().spliterator(), false)
                .toList();
        Iterable<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            Company company = companys.get(random.nextInt(Math.toIntExact(companyCount)));
            flight.setCompanies(new LinkedList<>(List.of(company)));
            flightRepository.save(flight);
        }
    }

    /**
     * Creates a flight with the given route, passenger quantity, and duration.
     *
     * @param  route         the route of the flight
     * @param  passengerQty  the quantity of passengers
     * @param  duration      the duration of the flight in minutes
     * @return               the created Flight object
     */
    public Flight createFlight(String route, int passengerQty, Integer duration) {
        Flight flight = new Flight();
        flight.setRoute(route);
        flight.setPassengerQty(passengerQty);
        flight.setDuration(duration);
        return flightRepository.save(flight);
    }


    /**
     * Adds a random flight to the flight repository.
     *
     * @return         	The added flight.
     */
    public Flight addRandomFlight(){
        Flight flight = new Flight();

        String randomRoute = createFlightCode();
        flight.setRoute(randomRoute);

        int randomPassengerQty = generateRandomPassengerQty();
        flight.setPassengerQty(randomPassengerQty);

        Integer randomDuration = generateRandomDuration();
        flight.setDuration(randomDuration);

        flight.setCompanies(List.of());

        return flightRepository.save(flight);
    }

    /**
     * Generates a flight code consisting of two random uppercase letters followed by five random digits.
     *
     * @return  the generated flight code
     */
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