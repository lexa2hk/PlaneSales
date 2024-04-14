import React, {useEffect, useState} from 'react';
import {Table, TableBody, TableCell, TableHead, TableRow} from "@mui/material";

function TicketList({ tickets }) {
    const [convertedNames, setConvertedNames] = useState({});

    useEffect(() => {
        // Function to convert IATA code to name for each ticket's airline
        const fetchConvertedNames = async () => {
            const newConvertedNames = {};
            for (const ticket of tickets) {
                try {
                    const response = await fetch(`http://localhost:8080/iata/airline?iata=${ticket.airline}`);
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
                            <TableCell><a href={"https://aviasales.ru" + ticket.link}>Купить на Aviasales</a></TableCell>
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
        </div>
    );
}

export default TicketList;