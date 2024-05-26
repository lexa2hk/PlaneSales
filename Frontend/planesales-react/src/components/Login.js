import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { Box, Button, TextField, Typography, Snackbar } from '@mui/material';
import { Navigate } from 'react-router-dom';

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

const LoginComponent = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [successSnackbarOpen, setSuccessSnackbarOpen] = useState(false);
    const [errorSnackbarOpen, setErrorSnackbarOpen] = useState(false);

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://95.165.157.208:8080/api/v1/auth/authenticate', {
                email,
                password,
            });
            const { access_token } = response.data;
            Cookies.set('access_token', access_token);
            localStorage.setItem('email', email);
            setIsLoggedIn(true); // Set isLoggedIn to true after successful login
            setSuccessSnackbarOpen(true); // Open success snackbar
        } catch (error) {
            setError('Invalid email or password');
            setErrorSnackbarOpen(true); // Open error snackbar
        }
    };

    const handleCloseSuccessSnackbar = () => {
        setSuccessSnackbarOpen(false);
    };

    const handleCloseErrorSnackbar = () => {
        setErrorSnackbarOpen(false);
    };

    if (isLoggedIn) {
        return <Navigate to="/" />; // Redirect to the root URL after successful login
    }

    return (
        <Box>
            <Typography variant="h4">Login</Typography>
            <TextField
                label="Email"
                variant="outlined"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Password"
                type="password"
                variant="outlined"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                fullWidth
                margin="normal"
            />
            <Button variant="contained" color="primary" onClick={handleLogin}>
                Login
            </Button>
            <Snackbar
                open={successSnackbarOpen}
                autoHideDuration={6000}
                onClose={handleCloseSuccessSnackbar}
                message="Login successful!"
            />
            <Snackbar
                open={errorSnackbarOpen}
                autoHideDuration={6000}
                onClose={handleCloseErrorSnackbar}
                message={error}
                action={
                    <Button color="secondary" size="small" onClick={handleCloseErrorSnackbar}>
                        Close
                    </Button>
                }
            />
        </Box>
    );
};

export default LoginComponent;
