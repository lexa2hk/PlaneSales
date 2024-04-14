import React, {useEffect, useState} from 'react';

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
            <table>
                <thead>
                <tr>
                    <th>Flight Number</th>
                    <th>Link</th>
                    <th>Origin Airport</th>
                    <th>Destination Airport</th>
                    <th>Departure At</th>
                    <th>Airline</th>
                    <th>Destination</th>
                    <th>Return At</th>
                    <th>Origin</th>
                    <th>Price</th>
                    <th>Return Transfers</th>
                    <th>Duration</th>
                    <th>Duration To</th>
                    <th>Duration Back</th>
                    <th>Transfers</th>
                </tr>
                </thead>
                <tbody>
                {tickets.map((ticket, index) => (
                    <tr key={index}>
                        <td>{ticket.airline+ticket.flight_number}</td>
                        <td><a href={"https://aviasales.ru"+ticket.link}>Купить на Aviasales</a></td>
                        <td>{ticket.origin_airport}</td>
                        <td>{ticket.destination_airport}</td>
                        <td>{ticket.departure_at}</td>
                        <td>{convertedNames[ticket.airline]}</td>
                        <td>{ticket.destination}</td>
                        <td>{ticket.return_at}</td>
                        <td>{ticket.origin}</td>
                        <td>{ticket.price}</td>
                        <td>{ticket.return_transfers}</td>
                        <td>{ticket.duration}</td>
                        <td>{ticket.duration_to}</td>
                        <td>{ticket.duration_back}</td>
                        <td>{ticket.transfers}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default TicketList;