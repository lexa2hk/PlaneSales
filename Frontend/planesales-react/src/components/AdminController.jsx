import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from "js-cookie";
import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
    TextField,
    Button,
    Typography,
} from "@mui/material";


const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
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


const AdminController = () => {
    const [flights, setFlights] = useState([]);
    const [newFlight, setNewFlight] = useState({
        route: '',
        passengerQty: 0,
        duration: 0,
    });
    const [editingFlight, setEditingFlight] = useState(null);

    const fetchData = async () => {
        try {
            const response = await axiosInstance.get('/api/v1/admin/flights');
            setFlights(response.data);
        } catch (error) {
            console.error('Error fetching flights:', error);
        }
    };

    useEffect(() => {
        fetchData().then(r => null );
    }, []);

    const handleInputChange = (e) => {
        setNewFlight({ ...newFlight, [e.target.name]: e.target.value });
    };

    const handleCreateFlight = async (e) => {
        try {
            setNewFlight({ route: '', passengerQty: 0, duration: 0 });
            const response = await axiosInstance.post('/api/v1/admin/flights', newFlight);
            setFlights([...flights, response.data]);
        } catch (error) {
            console.error('Error creating flight:', error);
        }
    };

    const handleUpdateFlight = async (flight) => {
        setEditingFlight(flight);
    };

    const handleSaveUpdate = async () => {
        try {
            const response = await axiosInstance.put(`/api/v1/admin/flights/${editingFlight.idFlight}`, editingFlight);
            setFlights(flights.map((flight) => (flight.idFlight === response.data.idFlight ? response.data : flight)));
            setEditingFlight(null);
        } catch (error) {
            console.error('Error updating flight:', error);
        }
    };

    const handleDeleteFlight = async (flightId) => {
        try {
            await axiosInstance.delete(`/api/v1/admin/flights/${flightId}`);
            setFlights(flights.filter((flight) => flight.idFlight !== flightId));
        } catch (error) {
            console.error('Error deleting flight:', error);
        }
    };

    // const useStyles = makeStyles({
    //     table: {
    //         minWidth: 650,
    //     },
    // });

    // const classes = useStyles();

    return (
        <div>
            <Typography variant="h4" gutterBottom>
                Flights
            </Typography>
            <TableContainer component={Paper}>
                <Table aria-label="flight table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Route</TableCell>
                            <TableCell>Passenger Qty</TableCell>
                            <TableCell>Duration</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {flights.map((flight) => (
                            <TableRow key={flight.idFlight}>
                                <TableCell>
                                    {editingFlight && editingFlight.idFlight === flight.idFlight ? (
                                        <TextField
                                            name="route"
                                            value={editingFlight.route}
                                            onChange={(e) => setEditingFlight({ ...editingFlight, route: e.target.value })}
                                        />
                                    ) : (
                                        flight.route
                                    )}
                                </TableCell>
                                <TableCell>
                                    {editingFlight && editingFlight.idFlight === flight.idFlight ? (
                                        <TextField
                                            type="number"
                                            name="passengerQty"
                                            value={editingFlight.passengerQty}
                                            onChange={(e) => setEditingFlight({ ...editingFlight, passengerQty: parseInt(e.target.value) })}
                                        />
                                    ) : (
                                        flight.passengerQty
                                    )}
                                </TableCell>
                                <TableCell>
                                    {editingFlight && editingFlight.idFlight === flight.idFlight ? (
                                        <TextField
                                            type="number"
                                            name="duration"
                                            value={editingFlight.duration}
                                            onChange={(e) => setEditingFlight({ ...editingFlight, duration: parseInt(e.target.value) })}
                                        />
                                    ) : (
                                        flight.duration
                                    )}
                                </TableCell>
                                <TableCell>
                                    {editingFlight && editingFlight.idFlight === flight.idFlight ? (
                                        <>
                                            <Button variant="contained" color="primary" onClick={handleSaveUpdate}>
                                                Save
                                            </Button>
                                            <Button variant="contained" onClick={() => setEditingFlight(handleCreateFlight())}>
                                                Cancel
                                            </Button>
                                        </>
                                    ) : (
                                        <>
                                            <Button variant="contained" color="primary" onClick={() => handleUpdateFlight(flight)}>
                                                Edit
                                            </Button>
                                            <Button variant="contained" color="secondary" onClick={() => handleDeleteFlight(flight.idFlight)}>
                                                Delete
                                            </Button>
                                        </>
                                    )}
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Typography variant="h5" gutterBottom>
                Create New Flight
            </Typography>
            <div>
                <TextField
                    label="Route"
                    name="route"
                    value={newFlight.route}
                    onChange={handleInputChange}
                    margin="normal"
                />
                <TextField
                    label="Passenger Qty"
                    type="number"
                    name="passengerQty"
                    value={newFlight.passengerQty}
                    onChange={handleInputChange}
                    margin="normal"
                />
                <TextField
                    label="Duration"
                    type="number"
                    name="duration"
                    value={newFlight.duration}
                    onChange={handleInputChange}
                    margin="normal"
                />
                <Button variant="contained" color="primary" onClick={null}>
                    Create
                </Button>
            </div>
        </div>
    );
};

export default AdminController;