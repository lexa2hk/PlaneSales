import React, {useEffect, useState} from 'react';
import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow, TextField
} from "@mui/material";
import ReviewRequest from "../../schema/ReviewRequest";
import axios from "axios";
import Cookies from "js-cookie";


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


function TicketList({ tickets }) {
    const [convertedNames, setConvertedNames] = useState({});

    const [open, setOpen] = useState(false);
    const [openReview, setOpenReview] = useState(false);

    const [currentTicket, setCurrentTicket] = useState(null);

    const [ticketBought, setTicketBought] = useState(false); // Состояние, отслеживающее, купил ли пользователь билет

    const [purchasedTickets, setPurchasedTickets] = useState([]);


    const requestReview = (ticket) => (event) => {
        // event.preventDefault();  // Prevent the default link behavior
        setCurrentTicket(ticket); // Set the current ticket
        setOpen(true);            // Open the dialog
    };

    useEffect(() => {
        // Function to convert IATA code to name for each ticket's airline
        const fetchConvertedNames = async () => {
            const newConvertedNames = {};
            for (const ticket of tickets) {
                try {
                    const response = await fetch(`http://95.165.157.208:8080/iata/airline?iata=${ticket.airline}`);
                    const data = await response.json();
                    console.log(data);
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
    }, [tickets]);

    useEffect(() => {
        localStorage.setItem('purchasedTickets', JSON.stringify(purchasedTickets));
    }, [purchasedTickets]);



    async function sendCompanyReviewRequest(reviewReq) {
        try {
            const response = await axiosInstance.post('/api/v1/review', reviewReq);
            console.log(response);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }

    return (
        <div>
            <h2>Ticket List</h2>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Код перелета</TableCell>
                        <TableCell>Ссылка</TableCell>
                        <TableCell>Аэропорт вылета</TableCell>
                        <TableCell>Аэропорт прибытия</TableCell>
                        <TableCell>Время вылета</TableCell>
                        <TableCell>Авиакомпания</TableCell>
                        <TableCell>Место прибытия</TableCell>
                        <TableCell>Время возвращения</TableCell>
                        <TableCell>Место вылета</TableCell>
                        <TableCell>Цена</TableCell>
                        <TableCell>Пересадки обратно</TableCell>
                        <TableCell>Общая длительность</TableCell>
                        <TableCell>Длительность туда</TableCell>
                        <TableCell>Длительность обратно</TableCell>
                        <TableCell>Пересадки туда</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tickets.map((ticket, index) => (
                        <TableRow key={index}>
                            <TableCell>{ticket.airline + ticket.flight_number}</TableCell>
                            <TableCell><a href={"https://aviasales.ru" + ticket.link} target={"_blank"}
                             onClick={requestReview(ticket)}>Купить на Aviasales</a></TableCell>
                            <TableCell>{ticket.origin_airport}</TableCell>
                            <TableCell>{ticket.destination_airport}</TableCell>
                            <TableCell>{ticket.departure_at}</TableCell>
                            <TableCell>{convertedNames[ticket.airline]}</TableCell>
                            <TableCell>{ticket.destination}</TableCell>
                            <TableCell>{ticket.return_at}</TableCell>
                            <TableCell>{ticket.origin}</TableCell>
                            <TableCell>{ticket.price}</TableCell>
                            <TableCell>{ticket.return_transfers}</TableCell>
                            <TableCell>{ticket.duration}</TableCell>
                            <TableCell>{ticket.duration_to}</TableCell>
                            <TableCell>{ticket.duration_back}</TableCell>
                            <TableCell>{ticket.transfers}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>

            <Dialog open={openReview} onClose={() => setOpenReview(false)}>
                <DialogTitle>Хотите оставить отзыв на авиакомпанию?</DialogTitle>
                <form onSubmit={(event) => {
                    event.preventDefault();
                    console.log("Review submitted for", currentTicket);

                    const formData = new FormData(event.target);
                    const rating = formData.get('rating');
                    const comment = formData.get('comment');


                    const reviewReq = new ReviewRequest();
                    reviewReq.objectId = currentTicket.airline;
                    reviewReq.reviewRating = parseInt(rating, 10);
                    reviewReq.reviewText = comment;
                    reviewReq.reviewObject = "COMPANY";

                    console.log(reviewReq);

                    sendCompanyReviewRequest(reviewReq);

                    setOpen(false);  // Close the dialog upon form submission
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


            <Dialog open={open} onClose={() => setOpen(false)}>
                <DialogTitle>Купили билет?</DialogTitle>
                <DialogContent>
                    <DialogActions>
                        <Button onClick={() => {setOpen(false);setOpenReview(true); setPurchasedTickets([...purchasedTickets, currentTicket]);}} variant="contained">Да</Button>
                        <Button onClick={() => setOpen(false)} variant="contained">Нет</Button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        </div>
    );

}

export default TicketList;