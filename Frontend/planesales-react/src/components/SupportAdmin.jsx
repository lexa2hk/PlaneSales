import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from "js-cookie";


const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/admin',
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


const SupportRequestAdmin = () => {
    const [supportRequests, setSupportRequests] = useState([]);
    const [newSupportRequest, setNewSupportRequest] = useState({ requestText: '', username: '' });
    const [editingRequest, setEditingRequest] = useState(null);

    useEffect(() => {
        fetchSupportRequests();
    }, []);

    const fetchSupportRequests = async () => {
        try {
            const response = await axiosInstance.get('/support-requests');
            setSupportRequests(response.data);
        } catch (error) {
            console.error('Error fetching support requests:', error);
        }
    };

    const handleCreateRequest = async () => {
        try {
            const response = await axiosInstance.post('/support-requests', newSupportRequest);
            setSupportRequests([...supportRequests, response.data]);
            setNewSupportRequest({ requestText: '', username: '' });
        } catch (error) {
            console.error('Error creating support request:', error);
        }
    };

    const handleUpdateRequest = async () => {
        try {
            const response = await axiosInstance.put(`/support-requests/${editingRequest.idSupportRequest}`, editingRequest);
            const updatedRequests = supportRequests.map((request) =>
                request.idSupportRequest === editingRequest.idSupportRequest ? response.data : request
            );
            setSupportRequests(updatedRequests);
            setEditingRequest(null);
        } catch (error) {
            console.error('Error updating support request:', error);
        }
    };

    const handleDeleteRequest = async (id) => {
        try {
            await axiosInstance.delete(`/api/support-requests/${id}`);
            const updatedRequests = supportRequests.filter((request) => request.idSupportRequest !== id);
            setSupportRequests(updatedRequests);
        } catch (error) {
            console.error('Error deleting support request:', error);
        }
    };

    return (
        <div>
            <h2>Support Requests</h2>
            <ul>
                {supportRequests.map((request) => (
                    <li key={request.idSupportRequest}>
                        {request.requestText}
                        <button onClick={() => handleDeleteRequest(request.idSupportRequest)}>Delete</button>
                        <button onClick={() => setEditingRequest(request)}>Edit</button>
                    </li>
                ))}
            </ul>
            <h3>Create New Support Request</h3>
            <input
                type="text"
                value={newSupportRequest.requestText}
                onChange={(e) => setNewSupportRequest({ ...newSupportRequest, requestText: e.target.value })}
                placeholder="Request Text"
            />
            <input
                type="text"
                value={newSupportRequest.username}
                onChange={(e) => setNewSupportRequest({ ...newSupportRequest, username: e.target.value })}
                placeholder="Username"
            />
            <button onClick={handleCreateRequest}>Create</button>
            {editingRequest && (
                <>
                    <h3>Edit Support Request</h3>
                    <input
                        type="text"
                        value={editingRequest.requestText}
                        onChange={(e) => setEditingRequest({ ...editingRequest, requestText: e.target.value })}
                        placeholder="Request Text"
                    />
                    <button onClick={handleUpdateRequest}>Update</button>
                    <button onClick={() => setEditingRequest(null)}>Cancel</button>
                </>
            )}
        </div>
    );
};

export default SupportRequestAdmin;