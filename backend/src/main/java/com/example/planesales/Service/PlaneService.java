package com.example.planesales.Service;


import com.example.planesales.Entity.Flight;
import com.example.planesales.Entity.Plane;
import com.example.planesales.Entity.TechnicalState;
import com.example.planesales.Entity.TechnicalStatus;
import com.example.planesales.Repository.PlaneRepository;
import com.example.planesales.Repository.TechnicalStatusRepository;
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
    private final TechnicalStatusRepository technicalStatusRepository;

    public List<Plane> getPlanes(){
        return (List<Plane>) planeRepository.findAll();
    }

    public List<Plane> getPlaneByRoute(String route){
        List<Plane> planes = new LinkedList<>();
        planeRepository.findAllByFlight_Route(route).forEach(planes::add);
        return planes;
    }



    /**
     * Generates a list of planes by filling them with flights and assigning random values to their properties.
     *
     * @return         	The list of generated planes.
     */
    public List<Plane> fillPlanes() {
        //flights are filled!
        Iterable<Flight> flights = flightService.getFlights();

        List<Plane> planes = new LinkedList<>(); // Create a list to store the generated planes

        for (Flight flight : flights) {
            Plane plane = new Plane(); // Create a new Plane object for each flight
            plane.setFlight(flight);
            Random random = new Random();

            plane.setCapacity(random.nextInt(100));
            plane.setModel("Model" + random.nextInt(100));
            plane.setCalSign(String.valueOf(1000 + random.nextInt(9000)));

            // Generate a random Date for maintenance
            long now = System.currentTimeMillis();
            long randomMaintenanceTime = now + ((long) random.nextInt(365) * 24 * 60 * 60 * 1000); // Random date within a year
            Date maintenanceDate = new Date(randomMaintenanceTime);
            plane.setMaintenance(maintenanceDate);

            plane.setTechnicalStatus(TechnicalState.OPERATIONAL);

            planeRepository.save(plane);

            planes.add(plane);
        }
        return planes;
    }



    @Deprecated
    public List<TechnicalStatus> initializeTechnicalStatus(){
        List<TechnicalStatus> technicalStatuses = new LinkedList<>();
        String[] statuses = {"Operational", "Repair", "Retired"};
        for(String status : statuses){
           TechnicalStatus technicalStatus = new TechnicalStatus();
           technicalStatus.setStatus(status);
           technicalStatuses.add(technicalStatus);
        }

        return (List<TechnicalStatus>) technicalStatusRepository.saveAll(technicalStatuses);
    }

}
