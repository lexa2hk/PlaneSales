package com.example.backendcoursework.Service;


import com.example.backendcoursework.Entity.Flight;
import com.example.backendcoursework.Entity.Plane;
import com.example.backendcoursework.Repository.FlightRepository;
import com.example.backendcoursework.Repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepository planeRepository;
    private final FlightService flightService;

    public List<Plane> getPlanes(){
        return (List<Plane>) planeRepository.findAll();
    }

    public List<Plane> getPlaneByRoute(String route){
        List<Plane> planes = new LinkedList<>();
        planeRepository.findAllByFlight_Route(route).forEach(planes::add);
        return planes;
    }



    private List<Plane> fillPlanes() {
        //flight and companies are created, now generate a plane for each flight
        Plane plane = new Plane();
        Iterable<Flight> flights = flightService.getFlights();
        for (Flight flight : flights) {
            plane.setFlight(flight);
            Random random = new Random();

            plane.setCapacity(random.nextInt(100));
            plane.setModel("Model" + random.nextInt(100));
            plane.setCalSign(String.valueOf(1000 + random.nextInt(9000)));
            // todo: fill plane with random data
            planeRepository.save(plane);
        }
        return null;
    }
}
