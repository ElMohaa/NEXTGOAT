DROP database IF EXISTS nextgoat;
CREATE database IF NOT EXISTS nextgoat;
USE nextgoat;

CREATE TABLE Usuario (
    id_usuario INT auto_increment PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    apellidos_usuario VARCHAR(50) NOT NULL,
    dni VARCHAR(50) not null,
    fecha_nacimiento DATE,
    correo_usuario VARCHAR(100) UNIQUE NOT NULL,
    telefono_usuario VARCHAR(15),
    dirrecion_vivienda VARCHAR(255) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    contrsena VARCHAR(100) NOT NULL,
    edad_ususario INT (10)
);

CREATE TABLE ProgresoEntrenamiento (
    id INT auto_increment PRIMARY KEY,
    id_usuario INT,
    ejercicio VARCHAR(100),
    completado BOOLEAN,
    fecha DATE,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE MejoraFsica (
    id INT auto_increment PRIMARY KEY,
    id_usuario INT,
    ritmo int,
    regate int,
    fisico int,
    tiro int,
    pase int,
    defensa int,
    media int,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);


