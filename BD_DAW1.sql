drop DATABASE alex_fiestas;
create database alex_fiestas;
use alex_fiestas;

create table personal
(
idPersonal bigint auto_increment primary key,
nombrePersonal varchar(100),
montoPersonal decimal(8,2),
rolPersonal varchar(100)
);

create table cliente
(
idCliente bigint auto_increment primary key,
nombreCliente varchar(100) not null,
correoCliente varchar(200) not null,
claveCliente varchar(100) not null,
telefonoCliente char(9) not null,
direccion varchar(150)
);

Create table Establecimiento 
(
idEstablecimiento bigint auto_increment primary key,
Nombre_establecimiento varchar(50),
Direccion varchar(100) not null,
Precio_alquiler decimal(10,2)
);

create table evento
(
idEvento bigint auto_increment primary key,
nombreEvento varchar(100) not null,
fechaEvento datetime,
horasEvento int,
montoEvento decimal(10,2),
idPersonal bigint,
idEstablecimiento bigint,
idCliente bigint,
FOREIGN KEY (idPersonal) REFERENCES personal(idPersonal),
FOREIGN KEY (idEstablecimiento) REFERENCES Establecimiento(idEstablecimiento),
FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

create table factura
(
idFactura bigint auto_increment primary key,
idEvento bigint unique,
descuento double,
fecha datetime default(now()),
precioFinal DECIMAL(10,2),
FOREIGN KEY (idEvento) REFERENCES evento(idEvento)
);

select * from factura


-- create table administrador
-- (
-- idAdministrador int auto_increment primary key,
-- nombreAdmin varchar(100) not null,
-- correoAdmin varchar(200) not null,
-- claveAdmin varchar(100) not null,
-- telefonoAdmin char(9) not null,
-- Fecha_Inico datetime not null,
-- Sueldo_admin double
-- direccion varchar(150)
-- );

select * from cliente;

Insert into Establecimiento (Nombre_establecimiento,Direccion,Precio_alquiler ) VALUES
('Parquiplex', 'Av. Mexico 3458, Lima', 105.20),
('Boulevard Punta Hermosa', 'Calle San Juan, Punta Hermosa', 234.15),
('Centro de Convenciones Maracana', 'C.C Marcamana, Miraflores', 318.10);

insert into personal (nombrePersonal, montoPersonal, rolPersonal) VALUES
('Juan Ramon', 456.60, 'Organizador'),
('Aaron Espinoza', 649.50, 'Organizador'),
('Juan Ramires', 329.60, 'Trabajador'),
('Maria Ramona', 659.59, 'Organizador');

select * from personal;
select * from Establecimiento;
select * from evento;
select * from factura;