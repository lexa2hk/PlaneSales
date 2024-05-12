import React, {useEffect, useState} from 'react';
import {
    Box,
    Button, Dialog, DialogActions, DialogContent, DialogTitle, MenuItem, Select,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow, TextField,
} from "@mui/material";
import ReviewRequest from "../../schema/ReviewRequest";
import axiosTools, {axiosInstance} from "../../util/AxiosTools";

function PurchasedTicketsTable({ purchasedTickets }) {

    const [convertedNames, setConvertedNames] = useState({});
    const  [openReview,setOpenReview] = useState(false);
    const [currentTicket,setCurrentTicket] = useState(null);

    const reviewTypes = ['PLANE', 'FLIGHT', 'AIRPORT','COMPANY'];
    const [selectedReviewType, setSelectedReviewType] = useState('FLIGHT');


    const leaveReview = (ticket) => {
        console.log('Leaving review for:', ticket);
        setCurrentTicket(ticket);
        setOpenReview(true);
    };

    useEffect(() => {
        // Function to convert IATA code to name for each ticket's airline
        const fetchConvertedNames = async () => {
            const newConvertedNames = {};
            for (const ticket of purchasedTickets) {
                try {
                    const response = await fetch(`http://localhost:8080/iata/airline?iata=${ticket.airline}`);
                    const data = await response.json();
                    newConvertedNames[ticket.airline] = data.name;
                } catch (error) {
                    console.error('Error fetching data:', error);
                    newConvertedNames[ticket.airline] = 'Error';
                }
            }
            setConvertedNames(newConvertedNames);
        };

        fetchConvertedNames().catch(error => {
            console.error('Error fetching converted names:', error);
        });
    }, [purchasedTickets]);


    return (
        <div>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Перелет</TableCell>
                        <TableCell>Авиакомпания</TableCell>
                        <TableCell>Аэропорт отправления</TableCell>
                        <TableCell>Аэропорт прибытия</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {purchasedTickets.map((ticket, index) => (
                        <TableRow key={index}>
                            <TableCell>{ticket.airline + ticket.flight_number}</TableCell>
                            <TableCell>{convertedNames[ticket.airline]}</TableCell>
                            <TableCell>{ticket.origin_airport}</TableCell>
                            <TableCell>{ticket.destination_airport}</TableCell>
                            <TableCell>
                                <Select
                                    value={selectedReviewType[ticket.id] || 'FLIGHT'}
                                    onChange={(e) => setSelectedReviewType({ ...selectedReviewType, [ticket.id]: e.target.value })}
                                    fullWidth
                                    variant="outlined"
                                >
                                    {reviewTypes.map((type, index) => (
                                        <MenuItem key={index} value={type}>{type}</MenuItem>
                                    ))}
                                </Select>
                            </TableCell>
                            <TableCell>
                                <Button onClick={() => leaveReview(ticket)} variant="contained" color="primary">
                                    Оставить отзыв
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>


            <Dialog open={openReview} onClose={() => setOpenReview(false)}>
                <form onSubmit={async (event) => {
                    event.preventDefault();
                    console.log("Review submitted for", currentTicket);

                    const formData = new FormData(event.target);
                    const rating = formData.get('rating');
                    const comment = formData.get('comment');


                    const reviewReq = new ReviewRequest();
                    reviewReq.objectId = currentTicket.airline+currentTicket.flight_number;
                    reviewReq.reviewRating = parseInt(rating, 10);
                    reviewReq.reviewText = comment;
                    reviewReq.reviewObject = selectedReviewType.undefined;

                    console.log(reviewReq);
                    try {
                        const response = await axiosInstance.post('/api/v1/review', reviewReq);
                        console.log(response);
                    } catch (error) {
                        console.error('Error fetching data:', error);
                    }

                    setOpenReview(false);


                }}>
                    <DialogContent>
                        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                            <TextField
                                label="Рейтинг"
                                type="number"
                                InputProps={{ inputProps: { min: 1, max: 5 } }}
                                name="rating"
                                required
                                variant="outlined"
                                fullWidth
                            />
                            <TextField
                                label="Комментарий"
                                multiline
                                rows={4}
                                name="comment"
                                required
                                variant="outlined"
                                fullWidth
                            />
                        </Box>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={() => setOpenReview(false)}>Отмена</Button>
                        <Button type="submit">Отправить</Button>
                    </DialogActions>
                </form>
            </Dialog>
        </div>
    );
}

export default PurchasedTicketsTable;