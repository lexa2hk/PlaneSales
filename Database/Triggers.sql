-- Active: 1696323393835@@127.0.0.1@3306@mydb
CREATE TRIGGER update_last_seen
AFTER UPDATE ON SupportRequest
FOR EACH ROW
BEGIN
    UPDATE SupportEmployee
    SET lastSeen = CURRENT_DATE()
    WHERE idSupportEmployee = NEW.SupportEmployee_idSupportEmployee;
END;


UPDATE `SupportRequest`
SET `SupportEmployee_idSupportEmployee` = 1 
WHERE `requestText` = 'Request2';



CREATE TRIGGER after_place_insert
AFTER INSERT ON `mydb`.`Place`
FOR EACH ROW
BEGIN
    -- Increment the passengerQty in Flight table
    UPDATE `mydb`.`Flight`
    SET `Flight`.`passengerQty` = `Flight`.`passengerQty` + 1
    WHERE `Flight`.`idFlight` = (SELECT Flight_idFlight FROM Plane WHERE idPlane=NEW.Plane_idPlane);
END;


CREATE TRIGGER after_place_delete
AFTER DELETE ON `mydb`.`Place`
FOR EACH ROW
BEGIN
    -- Decrease the passengerQty in Flight table
    UPDATE `mydb`.`Flight`
    SET `Flight`.`passengerQty` = `Flight`.`passengerQty` - 1
    WHERE `Flight`.`idFlight` = (SELECT Flight_idFlight FROM Plane WHERE idPlane=OLD.Plane_idPlane);
END 



CREATE TRIGGER update_maintenance_date
AFTER INSERT ON Ticket
FOR EACH ROW
BEGIN
    UPDATE Plane
    SET maintenance = DATE_ADD(NEW.Order_idOrder, INTERVAL 6 MONTH)
    WHERE idPlane = (SELECT Plane_idPlane FROM Place WHERE idPlace = NEW.Place_idPlace);
END;



CREATE TRIGGER after_supportrequest_insert
AFTER INSERT ON `mydb`.`SupportRequest`
FOR EACH ROW
BEGIN
    DECLARE support_employee_count INT;
    DECLARE random_employee_id INT;

    -- Get the count of accessible support employees
    SELECT COUNT(*) INTO support_employee_count
    FROM `mydb`.`SupportEmployee`
    WHERE `accesible` = 1;

    -- Generate a random number between 1 and the number of accessible employees
    SET random_employee_id = (
        SELECT `idSupportEmployee`
        FROM `mydb`.`SupportEmployee`
        WHERE `accesible` = 1
        ORDER BY RAND()
        LIMIT 1
    );

    -- Assign a random accessible support employee to the request
    UPDATE `mydb`.`SupportRequest`
    SET `SupportEmployee_idSupportEmployee` = random_employee_id
    WHERE `idSupportRequest` = NEW.idSupportRequest;
END


-- DROP PROCEDURE GetTicketDetails;
-- CREATE PROCEDURE GetTicketDetails(IN ticket_id INT)
-- BEGIN
--     -- Declare variables to store ticket details
--     DECLARE ticket_link VARCHAR(45);
--     DECLARE user_name VARCHAR(45);
--     DECLARE user_surname VARCHAR(45);
--     DECLARE flight_route VARCHAR(45);
--     DECLARE place_row VARCHAR(45);
--     DECLARE place_class VARCHAR(45);

--     -- Retrieve ticket details
--     SELECT t.ticketLink, u.name, u.surname, f.route, pl.row, pl.class
--     INTO ticket_link, user_name, user_surname, flight_route, place_row, place_class
--     FROM Ticket t
--     JOIN Place pl ON t.Place_idPlace = pl.idPlace
--     JOIN `Order` o ON t.Order_idOrder = o.idOrder
--     JOIN User u ON o.User_idUser = u.idUser
--     JOIN Flight_has_Company fc ON pl.Plane_idPlane = fc.Flight_idFlight
--     JOIN Flight f ON fc.Flight_idFlight = f.idFlight
--     WHERE t.idTicket = ticket_id;

--     -- Display ticket details
--     SELECT 'Ticket Link:', ticket_link;
--     SELECT 'User:', CONCAT(user_name, ' ', user_surname);
--     SELECT 'Flight Route:', flight_route;
--     SELECT 'Place Row:', place_row;
--     SELECT 'Place Class:', place_class;
-- END;

-- CALL GetTicketDetails(1);

-- DROP PROCEDURE CalculateAnnualPassTraffic;
-- CREATE PROCEDURE CalculateAnnualPassTraffic(IN company_name VARCHAR(45))
-- BEGIN
--     -- Declare variable to store the total annual pass traffic
--     DECLARE total_annual_pass_traffic INT;

--     -- Calculate total annual pass traffic for the given company
--     SELECT SUM(f.annualPassTraffic)
--     INTO total_annual_pass_traffic
--     FROM Flight_has_Company fc
--     JOIN Flight f ON fc.Flight_idFlight = f.idFlight
--     WHERE fc.Company_companyName = company_name;

--     -- Display the total annual pass traffic
--     SELECT CONCAT('Total Annual Pass Traffic for ', company_name, ': ', total_annual_pass_traffic);
-- END;

-- CALL CalculateAnnualPassTraffic("Company1");

CREATE PROCEDURE UpdateMaintenanceDateBasedOnStatus(IN plane_id INT)
BEGIN
    -- Declare variables to store maintenance date and status
    DECLARE maintenance_date DATE;
    DECLARE status VARCHAR(45);

    -- Retrieve maintenance date and status for the given plane
    SELECT p.maintenance, ts.status
    INTO maintenance_date, status
    FROM Plane p
    JOIN TechnicalStatus ts ON p.TechnicalStatus_idTechnicalStatus = ts.idTechnicalStatus
    WHERE p.idPlane = plane_id;

    -- Update maintenance date based on status
    IF status = 'Operational' THEN
        -- If status is 'Good', extend maintenance by 30 days
        UPDATE Plane
        SET maintenance = maintenance_date + INTERVAL 30 DAY
        WHERE idPlane = plane_id;
    ELSEIF status = 'Maintenance Required' THEN
        -- If status is 'Needs Maintenance', update maintenance to today's date
        UPDATE Plane
        SET maintenance = CURDATE()
        WHERE idPlane = plane_id;
    END IF;

    -- Display the updated maintenance date
    SELECT CONCAT('Updated maintenance date for Plane ', plane_id, ' to ', maintenance_date);
END 

CALL UpdateMaintenanceDateBasedOnStatus(1);

CREATE PROCEDURE UpdateExemptionBasedOnAge(IN user_id INT)
BEGIN
    -- Declare variables to store birth date and exemption
    DECLARE birth_date DATE;
    DECLARE exemption VARCHAR(45);
    DECLARE age INT;

    -- Retrieve birth date and current exemption for the given user
    SELECT u.birthDate, u.exemption
    INTO birth_date, exemption
    FROM User u
    WHERE u.idUser = user_id;

    -- Calculate the age of the user based on the birth date
    SET age = YEAR(CURDATE()) - YEAR(birth_date) - (DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(birth_date, '%m%d'));

    -- Update exemption based on age
    IF age < 18 THEN
        SET exemption = 'Minor Exemption';
    ELSEIF age >= 18 AND age < 60 THEN
        SET exemption = 'Adult Exemption';
    ELSE
        SET exemption = 'Senior Exemption';
    END IF;

    -- Update the exemption for the user
    UPDATE User
    SET exemption = exemption
    WHERE idUser = user_id;

    -- Display the updated exemption for the user
    SELECT CONCAT('Updated exemption for User ', user_id, ' to ', exemption);
END;

DELIMITER //
DROP PROCEDURE GetFlightsForRoute;
CREATE PROCEDURE GetFlightsForRoute(
    IN Froute VARCHAR(45)
)
BEGIN
    DECLARE flight_count INT;

    -- Check if there are flights for the given route and date
    SELECT COUNT(*) INTO flight_count
    FROM Flight
    WHERE `route` = Froute AND `duration` > 0;

    -- If flights are found, retrieve flight details
    IF flight_count > 0 THEN
        -- Retrieve flights for the given route and date
        SELECT idFlight, route, passengerQty, duration
        FROM Flight
        WHERE `route` = Froute AND `duration` > 0;
    ELSE
        SELECT 'No available flights for the given route and date.';
    END IF;
END;
//

DELIMITER ;



CREATE FUNCTION CalculateAveragePassengerQuantityForRoute(route VARCHAR(45)) RETURNS FLOAT
DETERMINISTIC
READS SQL DATA
BEGIN
    -- Declare variable to store the average passenger quantity
    DECLARE avg_passenger_qty FLOAT;

    -- Calculate the average passenger quantity for the given route
    SELECT AVG(f.passengerQty)
    INTO avg_passenger_qty
    FROM Flight f
    WHERE f.route = route;

    -- Return the average passenger quantity for the route
    RETURN avg_passenger_qty;
END;

CREATE FUNCTION CalculateAverageCompanyRating(companyName VARCHAR(45)) RETURNS FLOAT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE avgRating FLOAT;

    SELECT AVG(CAST(`mark` AS FLOAT))
    INTO avgRating
    FROM `mydb`.`CompanyMark`
    WHERE `Company_companyName` = companyName;

    IF avgRating IS NULL THEN
        SET avgRating = 0.0;
    END IF;

    RETURN avgRating;
END;

SELECT CalculateAverageCompanyRating("Company1") AS avgrat;



CREATE FUNCTION CalculateFlightRevenue(flightID VARCHAR(18)) RETURNS INT
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE totalRevenue INT;

    SELECT SUM(`total`)
    INTO totalRevenue
    FROM `mydb`.`Reciept`
    WHERE `User_idUser` IN (
        SELECT `User_idUser`
        FROM `mydb`.`Ticket`
        WHERE `Place_idPlace` IN (
            SELECT `idPlace`
            FROM `mydb`.`Place`
            WHERE `Plane_idPlane` IN (
                SELECT `idPlane`
                FROM `mydb`.`Plane`
                WHERE `Flight_idFlight` = flightID
            )
        )
    );

    IF totalRevenue IS NULL THEN
        SET totalRevenue = 0;
    END IF;

    RETURN totalRevenue;
END;






-- CREATE FUNCTION CalculateTicketPrice (class VARCHAR(45), duration INT)
-- RETURNS INT
-- BEGIN
--     DECLARE basePrice INT;
    
--     IF class = 'Business' THEN
--         SET basePrice = 1000;
--     ELSE
--         SET basePrice = 500;
--     END IF;
    
--     RETURN basePrice * duration;
-- END;



-- CREATE FUNCTION GetFlightRoute (flightId VARCHAR(18))
-- RETURNS VARCHAR(45)
-- BEGIN
--     DECLARE route VARCHAR(45);
    
--     SELECT route INTO route
--     FROM Flight
--     WHERE idFlight = flightId;
    
--     RETURN route;
-- END;


-- CREATE FUNCTION CheckTicketAvailability (flightId VARCHAR(18), class VARCHAR(45))
-- RETURNS INT
-- BEGIN
--     DECLARE availableSeats INT;
    
--     SELECT COUNT(*) INTO availableSeats
--     FROM Place
--     WHERE Plane_idPlane = (SELECT idPlane FROM Plane WHERE Flight_idFlight = flightId) AND class = class;
    
--     RETURN availableSeats;
-- END;


