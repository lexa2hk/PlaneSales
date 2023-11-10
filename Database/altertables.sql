
-- LINKS!!!!!

ALTER TABLE `mydb`.`SupportRequest`
ADD FOREIGN KEY (`User_idUser`)
REFERENCES `mydb`.`User` (`idUser`)
ON DELETE CASCADE;

-- Add cascading delete to SupportEmployee_idSupportEmployee foreign key
ALTER TABLE `mydb`.`SupportRequest`
ADD FOREIGN KEY (`SupportEmployee_idSupportEmployee`)
REFERENCES `mydb`.`User` (`idUser`)
ON DELETE SET NULL;


-- Add ON UPDATE and ON DELETE actions to User_idUser foreign key
ALTER TABLE `mydb`.`Reciept`
DROP FOREIGN KEY `fk_Reciept_User`;

ALTER TABLE `mydb`.`Reciept`
ADD FOREIGN KEY (`User_idUser`)
REFERENCES `mydb`.`User` (`idUser`)
ON UPDATE CASCADE;

-- Add ON UPDATE and ON DELETE actions to PaymentStatus_idPaymentStatus foreign key
ALTER TABLE `mydb`.`Reciept`
DROP FOREIGN KEY `fk_Reciept_PaymentStatus1`;

ALTER TABLE `mydb`.`Reciept`
ADD FOREIGN KEY (`PaymentStatus_idPaymentStatus`)
REFERENCES `mydb`.`PaymentStatus` (`idPaymentStatus`)
ON UPDATE CASCADE;



-- Add ON UPDATE and ON DELETE actions to PaymentStatus_idPaymentStatus foreign key
ALTER TABLE `mydb`.`Order`
DROP FOREIGN KEY `fk_Order_PaymentStatus1`;

ALTER TABLE `mydb`.`Order`
ADD FOREIGN KEY (`PaymentStatus_idPaymentStatus`)
REFERENCES `mydb`.`PaymentStatus` (`idPaymentStatus`)
ON UPDATE CASCADE;

-- Add ON UPDATE and ON DELETE actions to User_idUser foreign key
ALTER TABLE `mydb`.`Order`
DROP FOREIGN KEY `fk_Order_User1`;

ALTER TABLE `mydb`.`Order`
ADD FOREIGN KEY (`User_idUser`)
REFERENCES `mydb`.`User` (`idUser`)
ON UPDATE CASCADE;




-- Add ON UPDATE and ON DELETE actions to Company_companyName foreign key
ALTER TABLE `mydb`.`CompanyMark`
DROP FOREIGN KEY `fk_CompanyMark_Company1`;

ALTER TABLE `mydb`.`CompanyMark`
ADD FOREIGN KEY (`Company_companyName`)
REFERENCES `mydb`.`Company` (`companyName`)
ON UPDATE CASCADE;

-- Add ON UPDATE and ON DELETE actions to Flight_idFlight foreign key
ALTER TABLE `mydb`.`Flight_has_Company`
DROP FOREIGN KEY `fk_Flight_has_Company_Flight1`;

ALTER TABLE `mydb`.`Flight_has_Company`
ADD FOREIGN KEY (`Flight_idFlight`)
REFERENCES `mydb`.`Flight` (`idFlight`)
ON UPDATE CASCADE
ON DELETE CASCADE;

-- Add ON UPDATE and ON DELETE actions to Company_companyName foreign key
ALTER TABLE `mydb`.`Flight_has_Company`
DROP FOREIGN KEY `fk_Flight_has_Company_Company1`;

ALTER TABLE `mydb`.`Flight_has_Company`
ADD FOREIGN KEY (`Company_companyName`)
REFERENCES `mydb`.`Company` (`companyName`)
ON UPDATE CASCADE
ON DELETE CASCADE;



-- Add ON UPDATE and ON DELETE actions to TechnicalStatus_idTechnicalStatus foreign key
ALTER TABLE `mydb`.`Plane`
DROP FOREIGN KEY `fk_Plane_TechnicalStatus1`;

ALTER TABLE `mydb`.`Plane`
ADD FOREIGN KEY (`TechnicalStatus_idTechnicalStatus`)
REFERENCES `mydb`.`TechnicalStatus` (`idTechnicalStatus`)
ON UPDATE CASCADE
ON DELETE CASCADE;

-- Add ON UPDATE and ON DELETE actions to Flight_idFlight foreign key
ALTER TABLE `mydb`.`Plane`
DROP FOREIGN KEY `fk_Plane_Flight1`;

ALTER TABLE `mydb`.`Plane`
ADD FOREIGN KEY (`Flight_idFlight`)
REFERENCES `mydb`.`Flight` (`idFlight`)
ON UPDATE CASCADE
ON DELETE CASCADE;



-- Add ON UPDATE and ON DELETE actions to Plane_idPlane foreign key
ALTER TABLE `mydb`.`Place`
DROP FOREIGN KEY `fk_Place_Plane1`;

ALTER TABLE `mydb`.`Place`
ADD FOREIGN KEY (`Plane_idPlane`)
REFERENCES `mydb`.`Plane` (`idPlane`)
ON UPDATE CASCADE
ON DELETE CASCADE;



-- Add ON UPDATE and ON DELETE actions to Place_idPlace foreign key
ALTER TABLE `mydb`.`Ticket`
DROP FOREIGN KEY `fk_Ticket_Place1`;

ALTER TABLE `mydb`.`Ticket`
ADD FOREIGN KEY (`Place_idPlace`)
REFERENCES `mydb`.`Place` (`idPlace`)
ON UPDATE CASCADE;

-- Add ON UPDATE and ON DELETE actions to Order_idOrder foreign key
ALTER TABLE `mydb`.`Ticket`
DROP FOREIGN KEY `fk_Ticket_Order1`;

ALTER TABLE `mydb`.`Ticket`
ADD FOREIGN KEY (`Order_idOrder`)
REFERENCES `mydb`.`Order` (`idOrder`)
ON UPDATE CASCADE
ON DELETE CASCADE;

