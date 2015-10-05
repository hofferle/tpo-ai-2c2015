IF OBJECT_ID('DBAI.dbo.CONTRATO', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.CONTRATO;
IF OBJECT_ID('DBAI.dbo.CLIENTE', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.CLIENTE;
IF OBJECT_ID('DBAI.dbo.MEDIOPAGO', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.MEDIOPAGO;
IF OBJECT_ID('DBAI.dbo.VEHICULO', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.VEHICULO;
IF OBJECT_ID('DBAI.dbo.COCHERA', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.COCHERA;
IF OBJECT_ID('DBAI.dbo.CUENTACORRIENTE', 'U') IS NOT NULL
  DROP TABLE DBAI.dbo.CUENTACORRIENTE;

Create Table DBAI.dbo.VEHICULO (
patente varchar(6) not null primary key,
marca varchar (20),
modelo smallint,
tipo varchar (30)
);

Create Table DBAI.dbo.COCHERA
(
numeroCochera int not null primary key,
disponible bit not null
);

Create Table DBAI.dbo.CLIENTE
(
DNI int not null primary key,
nombre varchar(40),
domicilio varchar (40),
telefono int,
mail varchar(30),
medioPagoId int
);

Create Table DBAI.dbo.MEDIOPAGO
(
medioPagoId int Identity(1,1) not null primary key,
entidad varchar(40),
numero varchar(22),
fechaVencimiento date
);

Create Table DBAI.dbo.CUENTACORRIENTE
(
numeroCuentaCorriente int not null primary key,
saldo float
);

Create Table DBAI.dbo.CONTRATO
(
numeroContrato int  Identity (1,1) not null primary key, --el número de contrato es autoincremental
vehiculo varchar(6),
cochera int,
cuentaCorriente int,
cliente int,
monto float,
bloqueado bit
);
Alter Table DBAI.dbo.CONTRATO
add constraint VEHICULO_patente_fk
foreign key (vehiculo)
references DBAI.dbo.VEHICULO,
constraint COCHERA_numeroCochera_fk
foreign key (cochera)
references DBAI.dbo.COCHERA,
constraint CUENTACORRIENTE_cuentacCorriente_fk
foreign key (cuentaCorriente)
references DBAI.dbo.CUENTACORRIENTE,
constraint CLIENTE_DNI_fk
foreign key (cliente)
references DBAI.dbo.CLIENTE;

Alter Table DBAI.dbo.CLIENTE
add constraint MEDIOPAGO_medioPagoId_fk
foreign key (medioPagoId)
references DBAI.dbo.MEDIOPAGO
ON DELETE CASCADE;
