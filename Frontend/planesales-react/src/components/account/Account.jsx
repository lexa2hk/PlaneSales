import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import ReviewList from "./ReviewList";
import {useEffect, useState} from "react";
import PurchasedTicketsTable from "./PurchasedTicketsTable";
import UserDetails from "./UserDetails";

function Account(){

    const [purchasedTickets, setPurchasedTickets] = useState([]);

    useEffect(() => {
        const savedTickets = localStorage.getItem('purchasedTickets');
        if (savedTickets) {
            setPurchasedTickets(JSON.parse(savedTickets));
        }
    }, []);

    return (
        <div>
            <Typography variant="h2" display="flex" justifyContent="center" alignItems="center">Профиль</Typography>
            {localStorage.getItem('email') && <UserDetails/>}


            <Typography variant="h3" display="flex" justifyContent="center" alignItems="center">Купленные билеты:</Typography>
            <PurchasedTicketsTable purchasedTickets={purchasedTickets} />

            <ReviewList/>
        </div>
    );
}

export default Account;