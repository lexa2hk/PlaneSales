import React, { useState } from 'react';
import TicketForm from './TicketForm';
import TicketList from "./TicketList";
import axios from "axios";

function TicketSearchPage() {
    const [tickets, setTickets] = useState([]);

    const handleSubmit = async (formData) => {
        // Make API request with formData
        // Update tickets state with response
        console.log('Form data:', formData);

        try {
            const response = await axios.get('http://95.165.157.208:8080/tickets', {
                params: {
                    origin: formData.origin,
                    destination: formData.destination,
                    departure_at: formData.departureAt,
                    return_at: formData.returnAt,
                    oneWay: formData.oneWay,
                    direct: formData.direct,
                    limit: formData.limit
                }
            });
            // Assuming the response.data has the same structure as the sample data
            setTickets(response.data[0].data);
            console.log(response.data[0].data);
        } catch (error) {
            console.error('Error fetching tickets:', error);
            // Handle error, show error message, etc.
        }
    };

    return (
        <div>
            <h1>Ticket Search</h1>
            <h2>ВНИМАНИЕ!</h2>
            <p>Поля вылет и прилет строго необходимо заполнить <a href="https://www.nationsonline.org/oneworld/IATA_Codes/airport_code_list.htm">IATA-кодами</a> аэропоров</p>
            <TicketForm onSubmit={handleSubmit} />
            <TicketList tickets={tickets} />
        </div>
    );
}

export default TicketSearchPage;