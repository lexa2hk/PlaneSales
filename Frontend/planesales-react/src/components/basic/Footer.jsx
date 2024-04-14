import React from 'react';
import { Typography, Container } from '@mui/material';
import "./footer.css";

const Footer = () => {
    return (
        <footer className="footer mt-auto py-3 bg-light">
            <Container>
                <Typography variant="body2" color="textSecondary" align="center">
                    © 2024 PlaneSales
                </Typography>
                <Typography variant="body2" color="textSecondary" align="center">
                    Contact us: contact@planesale.ru
                </Typography>
            </Container>
        </footer>
    );
};

export default Footer;