CREATE TABLE Student(
ID INT NOT NULL,
FIRSTNAME VARCHAR(20) NOT NULL,
SECONDNAME VARCHAR(20)NOT NULL,
LASTNAME VARCHAR(20)NOT NULL,
DATEOFBIRTH DATETIME NOT NULL,
GROUPNUMBER VARCHAR(14) NOT NULL,
SPECIALTYCODE INT NOT NULL,
TELEPHONENUMBER VARCHAR(16) NOT NULL,
EMAILADDRESS VARCHAR(32) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (SPECIALTYCODE) REFERENCES Specialty_Code (SPECIALTYCODE)
);