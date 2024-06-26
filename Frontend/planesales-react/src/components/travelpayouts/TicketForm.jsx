import React, { useState } from 'react';
import { TextField, Checkbox, FormControlLabel, Button, Grid, Snackbar } from "@mui/material";

function TicketForm({ onSubmit }) {
    const [origin, setOrigin] = useState('');
    const [destination, setDestination] = useState('');
    const [departureAt, setDepartureAt] = useState('');
    const [returnAt, setReturnAt] = useState('');
    const [oneWay, setOneWay] = useState(false);
    const [direct, setDirect] = useState(true);
    const [limit, setLimit] = useState(10);
    const [successSnackbarOpen, setSuccessSnackbarOpen] = useState(false);
    const [errorSnackbarOpen, setErrorSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onSubmit({
            origin,
            destination,
            departureAt,
            returnAt,
            oneWay,
            direct,
            limit
        }).then(() => {
            setSuccessSnackbarOpen(true);
            setSnackbarMessage('Билеты найдены!');
        }).catch((error) => {
            setErrorSnackbarOpen(true);
            setSnackbarMessage(`Error: ${error.message}`);
        });
    };

    const handleCloseSuccessSnackbar = () => {
        setSuccessSnackbarOpen(false);
    };

    const handleCloseErrorSnackbar = () => {
        setErrorSnackbarOpen(false);
    };

    return (
        <form onSubmit={handleSubmit}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        label="Вылет"
                        required
                        value={origin}
                        onChange={(e) => setOrigin(e.target.value)}
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        label="Прибытие"
                        required
                        value={destination}
                        onChange={(e) => setDestination(e.target.value)}
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        label="Дата вылета"
                        required
                        type="date"
                        value={departureAt}
                        onChange={(e) => setDepartureAt(e.target.value)}
                        fullWidth
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        label="Дата возвращения"
                        required
                        type="date"
                        value={returnAt}
                        onChange={(e) => setReturnAt(e.target.value)}
                        fullWidth
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <FormControlLabel
                        control={<Checkbox checked={oneWay} disabled onChange={(e) => setOneWay(e.target.checked)} />}
                        label="В одну сторону"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <FormControlLabel
                        control={<Checkbox checked={direct} onChange={(e) => setDirect(e.target.checked)} />}
                        label="Прямой"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        label="Количество вариантов"
                        type="number"
                        value={limit}
                        onChange={(e) => setLimit(e.target.value)}
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button type="submit" variant="contained" color="primary">Искать</Button>
                </Grid>
            </Grid>
            <Snackbar
                open={successSnackbarOpen}
                autoHideDuration={6000}
                onClose={handleCloseSuccessSnackbar}
                message={snackbarMessage}
            />
            <Snackbar
                open={errorSnackbarOpen}
                autoHideDuration={6000}
                onClose={handleCloseErrorSnackbar}
                message={snackbarMessage}
            />
        </form>
    );
}

export default TicketForm;