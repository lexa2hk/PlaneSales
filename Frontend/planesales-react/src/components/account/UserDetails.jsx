import {Avatar, Box, Typography} from "@mui/material";
import {useEffect, useState} from "react";
import {axiosInstance} from "../../util/AxiosTools";

const UserDetails = () => {

    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axiosInstance.get('/api/v1/users/account/email/' + localStorage.getItem('email'));
                setUser(response.data);
                console.log(user);
            } catch (error) {
                console.error('Error fetching user:', error);
            }
        };

        fetchUser();
    }, []);


    // Function to get the first letter of the name for avatar
    const getFirstLetter = (str) => {
        return str.charAt(0).toUpperCase();
    };

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <Box display="flex" alignItems="center">
            <Avatar sx={{ bgcolor: '#3f51b5', marginRight: 2 }}>
                {getFirstLetter(user.name)}
            </Avatar>
            <Box>
                <Typography variant="h6">{`${user.name} ${user.surname}`}</Typography>
                <Typography variant="body1">{user.email}</Typography>
            </Box>
        </Box>
    );
};

export default UserDetails;