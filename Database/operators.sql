USE mydb

SELECT * FROM Flight;

SELECT passportData FROM User WHERE exemption = 'Exemption3';

SELECT * FROM `SupportEmployee` WHERE responseStat >1;

SELECT * FROM Flight WHERE passengerQty > 100 ORDER BY `passengerQty` DESC;



DELETE FROM Flight WHERE `passengerQty` < 0;
DELETE FROM `SupportEmployee` WHERE responseStat <0;
DELETE FROM User WHERE exemption IS NULL;


UPDATE User
SET passportData = 'New Passport Data'
WHERE idUser = 123;

UPDATE Flight
SET route = 'New Route'
WHERE idFlight = 'FL1234';


UPDATE Company
SET mainInfo = 'New Main Information'
WHERE companyName = 'CompanyXYZ'; -- Replace CompanyXYZ with the desired company name





-- projection
SELECT name, surname FROM User;

SELECT companyName, mainInfo FROM Company;

SELECT ticketLink, Place_idPlace FROM Ticket;

-- Прямое произвеление
SELECT * FROM Flight, Company;

SELECT * FROM `SupportEmployee`,`SupportRequest`;

SELECT * From `Company`,`CompanyMark`;


-- Joins
SELECT Ticket.idTicket, Ticket.ticketLink, Place.row, Place.class
FROM Ticket
INNER JOIN Place ON Ticket.Place_idPlace = Place.idPlace;


SELECT Ticket.idTicket, Flight.route
FROM Ticket
INNER JOIN Place ON Ticket.Place_idPlace = Place.idPlace
INNER JOIN Plane ON Place.Plane_idPlane = Plane.idPlane
INNER JOIN Flight ON Plane.Flight_idFlight = Flight.idFlight;



-- intersection
SELECT Flight.idFlight, Flight.route
FROM Flight_has_Company AS FC1
INNER JOIN Flight AS Flight ON FC1.Flight_idFlight = Flight.idFlight
WHERE FC1.Company_companyName = 'Company1'
  AND FC1.Flight_idFlight IN (
    SELECT Flight_idFlight 
    FROM Flight_has_Company 
    WHERE Company_companyName = 'Company2'
  );


SELECT Place.row, Place.class
FROM Place
INNER JOIN Ticket ON Place.idPlace = Ticket.Place_idPlace
WHERE Ticket.idTicket = 1
INTERSECT
SELECT Place.row, Place.class
FROM Place
INNER JOIN Ticket ON Place.idPlace = Ticket.Place_idPlace
WHERE Ticket.idTicket = 2;

SELECT Flight.idFlight, Flight.route
FROM Flight_has_Company FC1
JOIN Flight_has_Company FC2 ON FC1.Flight_idFlight = FC2.Flight_idFlight
WHERE FC1.Company_companyName = 'Company1' AND FC2.Company_companyName = 'Company2';




-- Difference


-- Retrieve flights associated with Company1 but not with Company2
SELECT FC1.Flight_idFlight, FC1.Company_companyName
FROM Flight_has_Company FC1
WHERE FC1.Company_companyName = 'Company1'
EXCEPT
SELECT FC2.Flight_idFlight, FC2.Company_companyName
FROM Flight_has_Company FC2
WHERE FC2.Company_companyName = 'Company2';

-- Retrieve support requests that are not associated with any support employee.
SELECT SupportRequest.idSupportRequest, SupportRequest.requestText
FROM SupportRequest
LEFT JOIN SupportEmployee ON SupportRequest.SupportEmployee_idSupportEmployee = SupportEmployee.idSupportEmployee
WHERE SupportEmployee.idSupportEmployee IS NULL;






