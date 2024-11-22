-- Creacion y uso de la BBDD--
DROP database IF EXISTS nextgoat;
CREATE database IF NOT EXISTS nextgoat;
USE nextgoat;

-- Tabla Usuario--
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
                              contrsena VARCHAR(100) NOT NULL
);
-- Tabla de Mejora Fisica, se almacena el progreso del usuario--
CREATE TABLE MejoraFisica (
                              id INT auto_increment PRIMARY KEY,
                              id_usuario INT NOT NULL UNIQUE,
                              ritmo int default 0,
                              regate int default 0,
                              fisico int default 0,
                              tiro int default 0,
                              pase int default 0,
                              defensa int default 0,
                              media int default 0,
                              FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Tabla Rutina --
CREATE TABLE Rutina (
                        id_rutina INT AUTO_INCREMENT PRIMARY KEY,
                        posicion VARCHAR(20) NOT NULL, -- Posición del jugador (ejemplo: PORTERO, DEFENSA)
                        dia_semana ENUM('LUNES', 'MARTES', 'MIÉRCOLES', 'JUEVES', 'VIERNES') NOT NULL -- Día de la semana
);

-- Tabla Actividad (relacionada con Rutina) --
CREATE TABLE Actividad (
                           id_actividad INT AUTO_INCREMENT PRIMARY KEY,
                           id_rutina INT NOT NULL,
                           duracion VARCHAR(20) NOT NULL, -- Ejemplo: '10 min', '1h'
                           titulo VARCHAR(100) NOT NULL, -- Título de la actividad
                           descripcion TEXT NOT NULL, -- Descripción de la actividad
                           orden INT NOT NULL, -- Para definir el orden de las actividades
                           FOREIGN KEY (id_rutina) REFERENCES Rutina(id_rutina) ON DELETE CASCADE
);


-- Rutina para Portero
INSERT INTO Rutina (posicion, dia_semana) VALUES ('PORTERO', 'LUNES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('PORTERO', 'MARTES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('PORTERO', 'MIÉRCOLES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('PORTERO', 'JUEVES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('PORTERO', 'VIERNES');

-- Rutina para Defensa
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSA', 'LUNES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSA', 'MARTES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSA', 'MIÉRCOLES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSA', 'JUEVES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSA', 'VIERNES');

-- Rutina para Mediocentro
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MEDIOCENTRO', 'LUNES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MEDIOCENTRO', 'MARTES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MEDIOCENTRO', 'MIÉRCOLES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MEDIOCENTRO', 'JUEVES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MEDIOCENTRO', 'VIERNES');

-- Rutina para Delantero
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DELANTERO', 'LUNES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DELANTERO', 'MARTES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DELANTERO', 'MIÉRCOLES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DELANTERO', 'JUEVES');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DELANTERO', 'VIERNES');

-- Insertar Actividades para Portero --
-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (1, '10 min', 'Calentamiento', 'Correr alrededor del campo a un ritmo moderado para activar el cuerpo.', 1),
                                                                            (1, '30 min', 'Reflejos', 'Ejercicios de reflejos con lanzamientos de balón a distintas zonas del área.', 2),
                                                                            (1, '50 min', 'Simulación de partido', 'Simulación de situaciones reales en el área para mejorar la toma de decisiones.', 3);

-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (2, '10 min', 'Calentamiento', 'Trote suave con cambios de ritmo y estiramientos dinámicos.', 1),
                                                                            (2, '30 min', 'Despeje', 'Ejercicios de despeje de balón con diferentes técnicas (de pierna y con las manos).', 2),
                                                                            (2, '50 min', 'Práctica de penalti', 'Ejercicios de atajamiento de penales y situaciones de 1v1 contra el delantero.', 3);

-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (3, '10 min', 'Calentamiento', 'Trote ligero con cambios de dirección y estiramientos específicos.', 1),
                                                                            (3, '30 min', 'Reflejos rápidos', 'Ejercicios de reacción con lanzamientos rápidos y cambios de dirección.', 2),
                                                                            (3, '50 min', 'Simulación de jugadas', 'Simulación de jugadas con el balón y situaciones de uno contra uno con el delantero.', 3);

-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (4, '10 min', 'Calentamiento', 'Correr alrededor del campo con cambios de ritmo y estiramientos dinámicos.', 1),
                                                                            (4, '30 min', 'Paradas de tiros', 'Ejercicisos para realizar paradas, de faltas y penaltis', 2),
                                                                            (4, '50 min', 'Paradas', 'Un jugador realizara los tiros de los penaltis y faltas, en las que el portero debera de frenarlas', 3);

-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (5, '10 min', 'Calentamiento', 'Correr alrededor del campo con cambios de ritmo y estiramientos dinámicos.', 1),
                                                                            (5, '30 min', 'Despejes largos', 'Ejercicios de despeje de balón con el pie desde el área y bajo presión.', 2),
                                                                            (5, '50 min', 'Juego aéreo', 'Entrenamiento de intervenciones en el juego aéreo, incluyendo paradas de cabeza y despejes en centros.', 3);


-- Insertar Actividades para Defensa --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (6, '15 min', 'Calentamiento', 'Trote ligero y estiramientos dinámicos para activar el cuerpo.', 1),
                                                                            (6, '40 min', 'Marcaje', 'Ejercicios de marcaje en 1v1 y cobertura en situaciones de juego real.', 2),
                                                                            (6, '35 min', 'Partido reducido', 'Simulación de situaciones de defensa en partidos reducidos (3v3).', 3);

-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (7, '15 min', 'Calentamiento', 'Trote moderado y estiramientos para activar los músculos.', 1),
                                                                            (7, '40 min', 'Duelos defensivos', 'Ejercicios de duelos defensivos individuales para mejorar el 1v1.', 2),
                                                                            (7, '35 min', 'Despeje aéreo', 'Ejercicios de despeje de balón de cabeza en situaciones aéreas.', 3);

-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (8, '15 min', 'Calentamiento', 'Trote y estiramientos para preparar los músculos.', 1),
                                                                            (8, '40 min', 'Recuperación de balón', 'Ejercicios para mejorar la recuperación del balón en duelos y ataques.', 2),
                                                                            (8, '35 min', 'Marcaje en el área', 'Simulaciones de situaciones defensivas dentro del área del defensor.', 3);

-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (9, '15 min', 'Calentamiento', 'Trote suave con cambios de dirección y estiramientos para activar el cuerpo.', 1),
                                                                            (9, '40 min', 'Cobertura defensiva', 'Ejercicios de cobertura defensiva en situaciones de 2v2 y 3v3.', 2),
                                                                            (9, '35 min', 'Defensa en transiciones', 'Entrenamiento para mejorar la defensa en transiciones rápidas y cambios de orientación.', 3);

-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (10, '15 min', 'Calentamiento', 'Carrera continua con sprints intercalados y estiramientos específicos para defender.', 1),
                                                                            (10, '40 min', 'Despeje bajo presión', 'Ejercicios de despeje de balón bajo presión de los delanteros contrarios.', 2),
                                                                            (10, '35 min', 'Defensa en bloque', 'Simulaciones de defensa en bloque para practicar el control del área y la línea defensiva.', 3);


-- Insertar Actividades para Mediocentro --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (11, '20 min', 'Calentamiento', 'Ejercicios de pase corto y largo con control bajo presión.', 1),
                                                                            (11, '40 min', 'Distribución', 'Entrenamiento de pases a larga distancia y cambios de orientación con presión.', 2),
                                                                            (11, '30 min', 'Tácticas', 'Juego en espacio reducido con movimientos tácticos para mejorar la distribución del balón.', 3);

-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (12, '20 min', 'Calentamiento', 'Ejercicios de pase y control en espacio reducido.', 1),
                                                                            (12, '40 min', 'Posicionamiento y pase', 'Ejercicios de posicionamiento para recibir y pasar bajo presión.', 2),
                                                                            (12, '30 min', 'Recuperación', 'Entrenamiento en la recuperación de balón y la transición de defensa a ataque.', 3);

-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (13, '20 min', 'Calentamiento', 'Ejercicios de pase y control bajo presión, con enfoque en cambios de orientación.', 1),
                                                                            (13, '40 min', 'Visión de juego', 'Ejercicios de pase largo y cambios de orientación para mejorar la visión de juego.', 2),
                                                                            (13, '30 min', 'Tácticas de presión', 'Entrenamiento en presionar al contrario y realizar transiciones rápidas.', 3);

-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (14, '20 min', 'Calentamiento', 'Ejercicios de pase y control bajo presión, con enfoque en el pase corto y largo.', 1),
                                                                            (14, '40 min', 'Pases a larga distancia', 'Entrenamiento de pases largos con cambios de orientación bajo presión.', 2),
                                                                            (14, '30 min', 'Recuperación y transición', 'Ejercicios para mejorar la recuperación del balón y transición rápida de defensa a ataque.', 3);

-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (15, '20 min', 'Calentamiento', 'Ejercicios de pase corto y control con movimientos dinámicos para activar la visión de juego.', 1),
                                                                            (15, '40 min', 'Posicionamiento en el campo', 'Ejercicios para mejorar el posicionamiento en el campo para recibir y distribuir el balón.', 2),
                                                                            (15, '30 min', 'Juegos de posesión', 'Entrenamiento de posesión en espacio reducido para mejorar la circulación del balón y el control en situaciones de presión.', 3);


-- Insertar Actividades para Delantero --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (16, '20 min', 'Calentamiento', 'Trote suave con cambios de ritmo, seguido de estiramientos dinámicos.', 1),
                                                                            (16, '40 min', 'Finalización', 'Entrenamiento de tiros a puerta desde distintas posiciones del área.', 2),
                                                                            (16, '30 min', 'Desmarques', 'Ejercicios de desmarque y movimientos sin balón para crear espacio.', 3);

-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (17, '20 min', 'Calentamiento', 'Trote suave con cambios de dirección y estiramientos dinámicos.', 1),
                                                                            (17, '40 min', 'Tiros desde fuera del área', 'Entrenamiento en tiros desde larga distancia, incluyendo colocación y potencia.', 2),
                                                                            (17, '30 min', 'Movimientos sin balón', 'Ejercicios para mejorar el desmarque y el trabajo sin balón en el ataque.', 3);

-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (18, '20 min', 'Calentamiento', 'Trote suave con cambios de dirección, seguido de estiramientos dinámicos.', 1),
                                                                            (18, '40 min', 'Finalización a puerta', 'Entrenamiento en tiros a puerta desde diferentes ángulos y distancias.', 2),
                                                                            (18, '30 min', 'Desmarques y movimientos', 'Ejercicios para mejorar el desmarque y el trabajo sin balón en el ataque.', 3);

-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (19, '20 min', 'Calentamiento', 'Trote suave con cambios de ritmo y estiramientos dinámicos para activar el cuerpo.', 1),
                                                                            (19, '40 min', 'Definición frente al portero', 'Entrenamiento de finalización en situaciones de uno contra uno con el portero, enfocado en la toma de decisiones.', 2),
                                                                            (19, '30 min', 'Movimientos de desmarque', 'Ejercicios para mejorar el desmarque en el área y la creación de espacios en el ataque.', 3);

-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (20, '20 min', 'Calentamiento', 'Trote suave con cambios de dirección y estiramientos dinámicos para aumentar la agilidad.', 1),
                                                                            (20, '40 min', 'Tiros en carrera', 'Entrenamiento de finalización con tiros en carrera desde diferentes ángulos y distancias.', 2),
                                                                            (20, '30 min', 'Desmarques en profundidad', 'Ejercicios para mejorar los desmarques en profundidad y la lectura de los espacios para el pase.', 3);

