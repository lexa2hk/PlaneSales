import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import {
  Box,
  Button,
  TextField,
  Typography,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
} from '@mui/material';
import {BrowserRouter as Router, Switch, Route, Link, Routes} from 'react-router-dom';
import Login from "./components/Login";
import Flights from "./components/Flights";
import AdminController from "./components/AdminController";
import SupportRequestAdmin from "./components/SupportAdmin";
import AirportComponent from "./components/AirportComponent";
import TicketSearchPage from "./components/travelpayouts/TicketSearchPage";
import NavBar from "./components/basic/NavBar";
import Footer from "./components/basic/Footer";
import ReviewList from "./components/account/ReviewList";
import Account from "./components/account/Account";
import SupportForm from "./components/support/SupportForm";

// Registration Component
const RegistrationComponent = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('USER');
    const [error, setError] = useState('');

    const handleRegistration = async () => {
        try {
            const response = await axios.post('http://95.165.157.208:8080/api/v1/auth/register', {
                firstname: firstName,
                lastname: lastName,
                email,
                password,
                role,
            });

            const { access_token, refresh_token } = response.data;

            // Store the access token and refresh token in cookies
            Cookies.set('access_token', access_token);
            Cookies.set('refresh_token', refresh_token);

            // Handle successful registration
            console.log('Registration successful!');
        } catch (error) {
            setError('Registration failed');
        }
    };

    return (
        <Box>
            <Typography variant="h4">Register</Typography>
            {error && <Typography color="error">{error}</Typography>}
            <TextField
                label="First Name"
                variant="outlined"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Last Name"
                variant="outlined"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                fullWidth
                margin="normal"
            />
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
            <FormControl variant="outlined" fullWidth margin="normal">
                <InputLabel>Role</InputLabel>
                <Select value={role} onChange={(e) => setRole(e.target.value)}>
                    <MenuItem value="USER">User</MenuItem>
                    <MenuItem value="ADMIN">Admin</MenuItem>
                    <MenuItem value="MANAGER">Manager</MenuItem>
                </Select>
            </FormControl>
            <Button variant="contained" color="primary" onClick={handleRegistration}>
                Register
            </Button>
            <Link to="/login">Already have an account? Login</Link>
        </Box>
    );
};


function App() {


  return (
          <Box>
              <Router>
                  <NavBar/>
                  <Routes>
                      <Route path="/login" element={<Login/>} />
                      <Route path="/register" element={<RegistrationComponent/>}/>
                      <Route path="/" element={<TicketSearchPage/>} />
                      <Route path="/admin" element={<AdminController/>} />
                      <Route path="/admin/support" element={<SupportRequestAdmin/>} />
                      <Route path="/admin/airport" element={<AirportComponent/>} />
                      <Route path="/user/reviews" element={<Account/>} />

                      <Route path="/flights" element={<Flights/>} />
                      <Route path="/support" element={<SupportForm/>} />
                  </Routes>
                  <Footer/>
              </Router>
          </Box>
  );
}

export default App;
