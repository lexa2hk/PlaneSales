import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AirportComponent = () => {
    const [airports, setAirports] = useState([]);
    const [newAirport, setNewAirport] = useState({ name: '', city: '', country: '' });
    const [editingAirport, setEditingAirport] = useState(null);

    useEffect(() => {
        fetchAirports();
    }, []);

    const fetchAirports = async () => {
        try {
            const response = await axios.get('http://95.165.157.208:8080/api/airports');
            setAirports(response.data);
        } catch (error) {
            console.error('Error fetching airports:', error);
        }
    };

    const handleInputChange = (e) => {
        setNewAirport({ ...newAirport, [e.target.name]: e.target.value });
    };

    const handleCreate = async () => {
        try {
            await axios.post('http://95.165.157.208:8080/api/airports', newAirport);
            setNewAirport({ name: '', city: '', country: '' });
            fetchAirports();
        } catch (error) {
            console.error('Error creating airport:', error);
        }
    };

    const handleUpdate = async () => {
        try {
            await axios.put(`http://95.165.157.208:8080/api/airports/${editingAirport.id}`, editingAirport);
            setEditingAirport(null);
            fetchAirports();
        } catch (error) {
            console.error('Error updating airport:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://95.165.157.208:8080/api/airports/${id}`);
            fetchAirports();
        } catch (error) {
            console.error('Error deleting airport:', error);
        }
    };

    const handleEdit = (airport) => {
        setEditingAirport(airport);
    };

    const handleCancelEdit = () => {
        setEditingAirport(null);
    };

    return (
        <div>
            <h2>Airports</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {airports.map((airport) => (
                    <tr key={airport.id}>
                        <td>{editingAirport && editingAirport.id === airport.id ? (
                            <input
                                type="text"
                                name="name"
                                value={editingAirport.name}
                                onChange={(e) => setEditingAirport({ ...editingAirport, name: e.target.value })}
                            />
                        ) : (
                            airport.name
                        )}
                        </td>
                        <td>{editingAirport && editingAirport.id === airport.id ? (
                            <input
                                type="text"
                                name="city"
                                value={editingAirport.city}
                                onChange={(e) => setEditingAirport({ ...editingAirport, city: e.target.value })}
                            />
                        ) : (
                            airport.city
                        )}
                        </td>
                        <td>{editingAirport && editingAirport.id === airport.id ? (
                            <input
                                type="text"
                                name="country"
                                value={editingAirport.country}
                                onChange={(e) => setEditingAirport({ ...editingAirport, country: e.target.value })}
                            />
                        ) : (
                            airport.country
                        )}
                        </td>
                        <td>
                            {editingAirport && editingAirport.id === airport.id ? (
                                <>
                                    <button onClick={handleUpdate}>Save</button>
                                    <button onClick={handleCancelEdit}>Cancel</button>
                                </>
                            ) : (
                                <>
                                    <button onClick={() => handleEdit(airport)}>Edit</button>
                                    <button onClick={() => handleDelete(airport.id)}>Delete</button>
                                </>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <h3>Create Airport</h3>
            <input
                type="text"
                name="name"
                placeholder="Name"
                value={newAirport.name}
                onChange={handleInputChange}
            />
            <input
                type="text"
                name="city"
                placeholder="City"
                value={newAirport.city}
                onChange={handleInputChange}
            />
            <input
                type="text"
                name="country"
                placeholder="Country"
                value={newAirport.country}
                onChange={handleInputChange}
            />
            <button onClick={handleCreate}>Create</button>
        </div>
    );
};

export default AirportComponent;