import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const NavBar = () => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" style={{ flexGrow: 1 }}>
                    <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>PlaneSales</Link>
                </Typography>
                <div>
                    <Button color="inherit" component={Link} to="/">Искать билеты</Button>
                    <Button color="inherit" component={Link} to="/about">О нас</Button>
                    <Button color="inherit" component={Link} to="/contact">Контакты</Button>
                    <Button color="inherit" component={Link} to="/user/reviews">Личный кабинет</Button>
                    <Button color="inherit" component={Link} to="/support">Поддержка</Button>
                </div>
            </Toolbar>
        </AppBar>
    );
};

export default NavBar;