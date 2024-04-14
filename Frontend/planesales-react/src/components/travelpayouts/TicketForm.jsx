import React, { useState } from 'react';

function TicketForm({ onSubmit }) {
    const [origin, setOrigin] = useState('');
    const [destination, setDestination] = useState('');
    const [departureAt, setDepartureAt] = useState('');
    const [returnAt, setReturnAt] = useState('');
    const [oneWay, setOneWay] = useState(false);
    const [direct, setDirect] = useState(false);
    const [limit, setLimit] = useState(10);

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
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Origin:</label>
                <input type="text" value={origin} onChange={(e) => setOrigin(e.target.value)} />
            </div>
            <div>
                <label>Destination:</label>
                <input type="text" value={destination} onChange={(e) => setDestination(e.target.value)} />
            </div>
            <div>
                <label>Departure Date:</label>
                <input type="date" value={departureAt} onChange={(e) => setDepartureAt(e.target.value)} />
            </div>
            <div>
                <label>Return Date:</label>
                <input type="date" value={returnAt} onChange={(e) => setReturnAt(e.target.value)} />
            </div>
            <div>
                <label>One Way:</label>
                <input type="checkbox" checked={oneWay} onChange={(e) => setOneWay(e.target.checked)} />
            </div>
            <div>
                <label>Direct:</label>
                <input type="checkbox" checked={direct} onChange={(e) => setDirect(e.target.checked)} />
            </div>
            <div>
                <label>Limit:</label>
                <input type="number" value={limit} onChange={(e) => setLimit(e.target.value)} />
            </div>
            <button type="submit">Search</button>
        </form>
    );
}

export default TicketForm;