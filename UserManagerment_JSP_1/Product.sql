CREATE DATABASE Product
USE Product
CREATE TABLE product (
    NO INT IDENTITY(1,1) PRIMARY KEY,
    ID varchar(10) NOT NULL  ,
    Name VARCHAR(100) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Quantity INT NOT NULL
);

INSERT INTO product (ID, Name, Price, Quantity) VALUES
('P01', 'Nhan vang 24K', 6500000, 10),
('P02', 'Day chuyen vang trang', 12500000, 5),
('P03', 'Lac tay vang 18K', 8300000, 7),
('P04', 'Bong tai vang Y', 5400000, 12),
('P05', 'Nhan cuoi doi', 9800000, 4),
('P06', 'Vong co kim cuong', 35000000, 2),
('P07', 'Lac chan vang tay', 4900000, 8),
('P08', 'Mat day chuyen da quy', 7200000, 6),
('P09', 'Nhan nam vang trang', 8800000, 3),
('P10', 'Khuyen tai vang hong', 5700000, 9);
DELETE  FROM Product;
SELECT * FROM Product;

CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    OrderDate DATETIME DEFAULT GETDATE(),
    Total MONEY
);

CREATE TABLE OrderDetail (
    DetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT FOREIGN KEY REFERENCES Orders(OrderID),
    ProductID VARCHAR(20),
    ProductName NVARCHAR(100),
    Quantity INT,
    Price MONEY
);


SELECT * FROM OrderDetail
