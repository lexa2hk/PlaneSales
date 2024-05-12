import React, { useState } from 'react';
import {TextField, Button, Snackbar, Alert} from '@mui/material';
import {axiosInstance} from "../../util/AxiosTools";

function SupportRequestForm() {
    const [requestText, setRequestText] = useState('');
    const [username, setUsername] = useState('');
    const [response, setResponse] = useState('');
    const [openSnackbar, setOpenSnackbar] = useState(false);

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axiosInstance.post('/api/v1/admin/support-requests', {
                requestText,
                username
            });

            setResponse(JSON.stringify(response.data, null, 2));
            setOpenSnackbar(true);
        } catch (error) {
            console.error('Error submitting support request:', error);
            setResponse('Error occurred while submitting support request.');
        }
    };

    const handleCloseSnackbar = () => {
        setOpenSnackbar(false);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Текст запроса"
                    variant="outlined"
                    fullWidth
                    multiline
                    rows={4}
                    margin="normal"
                    value={requestText}
                    onChange={(e) => setRequestText(e.target.value)}
                />
                <TextField
                    label="Email"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    type="email"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <Button variant="contained" type="submit">
                    Submit
                </Button>
            </form>
            <Snackbar
                open={openSnackbar}
                autoHideDuration={6000}
                onClose={handleCloseSnackbar}
            >
                <Alert onClose={handleCloseSnackbar} severity="success">
                    Support request submitted successfully!
                </Alert>
            </Snackbar>
        </div>
    );
}

export default SupportRequestForm;