create table Clientes(
    id          int(5)       primary key,
    nombre      varchar(25)     not null,
    email       varchar(50)     not null,
    contraseña  varchar(100)    not null
);

create table Cuentas(
    id          int(5)         primary key,
    codCliente  int(5)         not null,
    saldo       double(6,2)    not null,
    tipoCuenta  varchar(13)    not null,
    foreign key (codCliente) references Clientes(id)
);

create table Movimientos(
    id         int(5)         primary key,
    codCuenta  int(5)         not null,
    tipo       enum('INGRESO','RETIRADA','TRANSFERENCIA')    not null,
    cantidad   int(10)        not null,
    fecha      date           not null,
    foreign key (codCuenta) references Cuentas(id)
);