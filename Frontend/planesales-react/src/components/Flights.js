import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Box, Typography, List, ListItem, ListItemText } from '@mui/material';

const axiosInstance = axios.create({
    baseURL: 'http://95.165.157.208:8080',
});

axiosInstance.interceptors.request.use(
    (config) => {
        const accessToken = Cookies.get('access_token');
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const FlightsComponent = () => {
    const [flights, setFlights] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchFlights = async () => {
            try {
                const response = await axiosInstance.get('/api/v1/flights');
                setFlights(response.data);
            } catch (error) {
                setError('Failed to fetch flights');
            }
        };

        fetchFlights();
    }, []);

    return (
        <Box>
            <Typography variant="h4">Available Flights</Typography>
            {error && <Typography color="error">{error}</Typography>}
            <List>
                {flights.map((flight) => (
                    <ListItem key={flight.idFlight}>
                        <ListItemText
                            primary={`Route: ${flight.route}`}
                            secondary={`Duration: ${flight.duration} hours, Passenger Capacity: ${flight.passengerQty}`}
                        />
                    </ListItem>
                ))}
            </List>
        </Box>
    );
};

export default FlightsComponent;