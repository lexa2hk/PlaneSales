-- Active: 1696323393835@@127.0.0.1@3306


# INSERTION ==================================================

INSERT INTO `mydb`.`User` (`passportData`, `name`, `surname`, `patronymic`, `birthDate`, `exemption`)
VALUES
    ('PassportData1', 'Name1', 'Surname1', 'Patronymic1', '2000-01-01', 'Exemption1'),
    ('PassportData2', 'Name2', 'Surname2', 'Patronymic2', '1995-05-15', 'Exemption2'),
    ('PassportData3', 'Name3', 'Surname3', 'Patronymic3', '1988-09-30', 'Exemption3'),
    ('PassportData4', 'Name4', 'Surname4', 'Patronymic4', '2002-07-10', 'Exemption4'),
    ('PassportData5', 'Name5', 'Surname5', 'Patronymic5', '1990-03-20', 'Exemption5');


INSERT INTO `mydb`.`SupportEmployee` (`responseStat`, `accesible`, `lastSeen`)
VALUES
    (1, 1, '2023-09-12'),
    (2, 1, '2023-09-11'),
    (0, 0, '2023-09-10'),
    (3, 1, '2023-09-09'),
    (1, 1, '2023-09-08');


INSERT INTO `mydb`.`SupportRequest` (`requestText`, `User_idUser`, `SupportEmployee_idSupportEmployee`)
VALUES
    ('Request1', 1, 1),
    ('Request2', 2, 2),
    ('Request3', 3, 3),
    ('Request4', 4, 4),
    ('Request5', 5, 5);


INSERT INTO `mydb`.`PaymentStatus` (`statusDetails`)
VALUES
    ('Paid'),
    ('Pending'),
    ('Failed'),
    ('Refunded'),
    ('Completed');


INSERT INTO `mydb`.`Reciept` (`total`, `closeTime`, `User_idUser`, `PaymentStatus_idPaymentStatus`)
VALUES
    (100, '2023-09-12', 1, 1),
    (75, '2023-09-11', 2, 2),
    (50, '2023-09-10', 3, 3),
    (125, '2023-09-09', 4, 4),
    (90, '2023-09-08', 5, 5);


INSERT INTO `mydb`.`Order` (`creationDate`, `paymentmethod`, `PaymentStatus_idPaymentStatus`, `User_idUser`)
VALUES
    ('2023-09-12', 'Credit Card', 1, 1),
    ('2023-09-11', 'PayPal', 2, 2),
    ('2023-09-10', 'WebMoney', 3, 3),
    ('2023-09-09', 'Crypto', 4, 4),
    ('2023-09-08', 'Free', 5, 5);


INSERT INTO `mydb`.`Company` (`companyName`, `mainInfo`, `park`, `since`, `annualPassTraffic`)
VALUES
    ('Company1', 'Info1', 'Park1', '1990', 100000),
    ('Company2', 'Info2', 'Park2', '1995', 80000),
    ('Company3', 'Info3', 'Park3', '2000', 120000),
    ('Company4', 'Info4', 'Park4', '1985', 75000),
    ('Company5', 'Info5', 'Park5', '1998', 95000);


INSERT INTO `mydb`.`CompanyMark` (`idCompanyMark`, `Company_companyName`, `userName`, `mark`, `markText`)
VALUES
    ('Mark1', 'Company1', 'User1', '5', 'Excellent service'),
    ('Mark2', 'Company2', 'User2', '4', 'Good service'),
    ('Mark3', 'Company3', 'User3', '3', 'Average service'),
    ('Mark4', 'Company4', 'User4', '2', 'Below average service'),
    ('Mark5', 'Company5', 'User5', '1', 'Poor service');


INSERT INTO `mydb`.`Flight` (`idFlight`, `route`, `passengerQty`, `duration`)
VALUES
    ('Flight1', 'Route1', 150, 180),
    ('Flight2', 'Route2', 200, 240),
    ('Flight3', 'Route3', 100, 120),
    ('Flight4', 'Route4', 180, 210),
    ('Flight5', 'Route5', 220, 270);


INSERT INTO `mydb`.`Flight_has_Company` (`Flight_idFlight`, `Company_companyName`)
VALUES
    ('Flight1', 'Company1'),
    ('Flight2', 'Company2'),
    ('Flight3', 'Company3'),
    ('Flight4', 'Company4'),
    ('Flight5', 'Company5');


INSERT INTO `mydb`.`TechnicalStatus` (`idTechnicalStatus`, `status`)
VALUES
    (1, 'Operational'),
    (2, 'Maintenance Required'),
    (3, 'Out of Service'),
    (4, 'In Repair'),
    (5, 'Scheduled Maintenance');


INSERT INTO `mydb`.`Plane` (`model`, `calSign`, `capacity`, `maintenance`, `TechnicalStatus_idTechnicalStatus`, `Flight_idFlight`)
VALUES
    ('Model1', 'Sign1', 150, '2023-09-12', 1, 'Flight1'),
    ('Model2', 'Sign2', 200, '2023-09-11', 2, 'Flight2'),
    ('Model3', 'Sign3', 100, '2023-09-10', 3, 'Flight3'),
    ('Model4', 'Sign4', 180, '2023-09-09', 4, 'Flight4'),
    ('Model5', 'Sign5', 220, '2023-09-08', 5, 'Flight5');

INSERT INTO `mydb`.`Place` (`row`, `class`, `Plane_idPlane`)
VALUES
    ('A1', 'E', 1),
    ('B2', 'B', 2),
    ('C3', 'E', 3),
    ('D4', 'B', 4),
    ('E5', 'E', 5);

INSERT INTO `mydb`.`Ticket` (`ticketLink`, `Place_idPlace`, `Order_idOrder`)
VALUES
    ('Link1', 1, 1),
    ('Link2', 2, 2),
    ('Link3', 3, 3),
    ('Link4', 4, 4),
    ('Link5', 5, 5);




## ================ SELECT


SELECT * FROM `mydb`.`User`;


SELECT * FROM `mydb`.`SupportEmployee`;


SELECT * FROM `mydb`.`SupportRequest`;


SELECT * FROM `mydb`.`PaymentStatus`;


SELECT * FROM `mydb`.`Reciept`;


SELECT * FROM `mydb`.`Order`;


SELECT * FROM `mydb`.`Company`;


SELECT * FROM `mydb`.`CompanyMark`;


SELECT * FROM `mydb`.`Flight`;


SELECT * FROM `mydb`.`Flight_has_Company`;


SELECT * FROM `mydb`.`TechnicalStatus`;


SELECT * FROM `mydb`.`Plane`;


SELECT * FROM `mydb`.`Place`;


SELECT * FROM `mydb`.`Ticket`;








