import axios from "axios";
import Cookies from "js-cookie";
import {useEffect, useState} from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
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



function ReviewList() {
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        // Fetch reviews when component mounts
        axiosInstance.get('/api/v1/review/user')
            .then(response => {
                setReviews(response.data);
            })
            .catch(error => {
                console.error('Error fetching reviews:', error);
            });
    }, []); // Empty dependency array to run effect only once on mount

    return (
        <div>
            <Typography variant="h2" display="flex" justifyContent="center" alignItems="center">Ваш список отзывов</Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Тип</TableCell>
                            <TableCell>Идентификатор</TableCell>
                            <TableCell>Текст отзыва</TableCell>
                            <TableCell>Оценка</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {reviews.map(review => (
                            <TableRow key={review.objectId}>
                                <TableCell>{review.reviewObject}</TableCell>
                                <TableCell>{review.objectId}</TableCell>
                                <TableCell>{review.reviewText}</TableCell>
                                <TableCell>{review.reviewRating}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default ReviewList;