CREATE TABLE `users` (
   `Id` INT AUTO_INCREMENT,
  `Name` VARCHAR(1024),
  `FirstName` VARCHAR(1024),
  `MemberSince` DATE,
  `MemberTill` DATE NULL,
  `Gender` VARCHAR(1024),
  PRIMARY KEY (Id)
);

CREATE TABLE `books` (
   `Id` INT AUTO_INCREMENT,
  `Title` VARCHAR(1024) ,
  `Author` VARCHAR(1024) NULL,
  `Genre` VARCHAR(1024) NULL,
  `Publisher` VARCHAR(1024) NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE `borrowed` (
   `Id` INT AUTO_INCREMENT,
  `Borrower` VARCHAR(1024),
  `Book` VARCHAR(1024),
  `BorrowedFrom` DATE,
  `BorrowedTo` DATE,
  PRIMARY KEY (Id)
);
