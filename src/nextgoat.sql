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
                        dia_semana ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY') NOT NULL -- Día de la semana
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
INSERT INTO Rutina (posicion, dia_semana) VALUES ('GOALKEEPER', 'MONDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('GOALKEEPER', 'TUESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('GOALKEEPER', 'WEDNESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('GOALKEEPER', 'THURSDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('GOALKEEPER', 'FRIDAY');
-- Rutina para Defensa
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSE', 'MONDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSE', 'TUESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSE', 'WEDNESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSE', 'THURSDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('DEFENSE', 'FRIDAY');

-- Rutina para Mediocentro
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MIDFIELDER', 'MONDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MIDFIELDER', 'TUESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MIDFIELDER', 'WEDNESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MIDFIELDER', 'THURSDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('MIDFIELDER', 'FRIDAY');


-- Rutina para Delantero
INSERT INTO Rutina (posicion, dia_semana) VALUES ('FORWARD', 'MONDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('FORWARD', 'TUESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('FORWARD', 'WEDNESDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('FORWARD', 'THURSDAY');
INSERT INTO Rutina (posicion, dia_semana) VALUES ('FORWARD', 'FRIDAY');


-- Insertar Actividades para Portero --
-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (1, '10 min', 'Warm-up', 'Running around the field at a moderate pace to activate the body.', 1),
                                                                            (1, '30 min', 'Reflexes', 'Reflex exercises with ball throws to different areas of the goal.', 2),
                                                                            (1, '50 min', 'Match Simulation', 'Simulation of real situations in the goal area to improve decision-making.', 3);


-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (2, '10 min', 'Warm-up', 'Light jogging with pace changes and dynamic stretching.', 1),
                                                                            (2, '30 min', 'Clearance', 'Ball clearance exercises with different techniques (using the foot and hands).', 2),
                                                                            (2, '50 min', 'Penalty Practice', 'Exercises for saving penalties and 1v1 situations against the striker.', 3);


-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (3, '10 min', 'Warm-up', 'Light jogging with direction changes and specific stretches.', 1),
                                                                            (3, '30 min', 'Quick Reflexes', 'Reaction exercises with fast throws and direction changes.', 2),
                                                                            (3, '50 min', 'Play Simulation', 'Simulation of plays with the ball and one-on-one situations with the striker.', 3);


-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (4, '10 min', 'Warm-up', 'Jog around the field with changes of pace and dynamic stretches.', 1),
                                                                            (4, '30 min', 'Shot Stopping', 'Exercises to perform stops, including free kicks and penalties.', 2),
                                                                            (4, '50 min', 'Stops', 'A player will take penalty and free kick shots, which the goalkeeper must stop.', 3);


-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (5, '10 min', 'Warm-up', 'Jog around the field with changes of pace and dynamic stretches.', 1),
                                                                            (5, '30 min', 'Long Clears', 'Exercises for clearing the ball with the foot from the area and under pressure.', 2),
                                                                            (5, '50 min', 'Aerial Game', 'Training interventions in the aerial game, including heading stops and clearances in crosses.', 3);



-- Insertar Actividades para Defensa --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (6, '15 min', 'Warm-up', 'Light jog and dynamic stretches to activate the body.', 1),
                                                                            (6, '40 min', 'Marking', 'Marking exercises in 1v1 situations and covering in real game scenarios.', 2),
                                                                            (6, '35 min', 'Small-sided Game', 'Simulation of defensive situations in small-sided games (3v3).', 3);


-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (7, '15 min', 'Warm-up', 'Moderate jog and stretches to activate the muscles.', 1),
                                                                            (7, '40 min', 'Defensive Duels', 'Individual defensive duel exercises to improve 1v1 situations.', 2),
                                                                            (7, '35 min', 'Aerial Clearance', 'Ball clearance exercises with the head in aerial situations.', 3);


-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
                                                                            (8, '15 min', 'Warm-up', 'Jogging and stretches to prepare the muscles.', 1),
                                                                            (8, '40 min', 'Ball Recovery', 'Exercises to improve ball recovery in duels and attacks.', 2),
                                                                            (8, '35 min', 'Marking in the Area', 'Simulations of defensive situations within the defenders area.', 3);


-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(9, '15 min', 'Warm-up', 'Light jogging with direction changes and stretches to activate the body.', 1),
(9, '40 min', 'Defensive Covering', 'Defensive covering exercises in 2v2 and 3v3 situations.', 2),
(9, '35 min', 'Defense in Transitions', 'Training to improve defense in fast transitions and changes of orientation.', 3);


-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(10, '15 min', 'Warm-up', 'Continuous running with intermittent sprints and specific stretches for defending.', 1),
(10, '40 min', 'Clearance Under Pressure', 'Ball clearance exercises under pressure from opposing forwards.', 2),
(10, '35 min', 'Block Defense', 'Block defense simulations to practice controlling the area and the defensive line.', 3);



-- Insertar Actividades para Mediocentro --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(11, '20 min', 'Warm-up', 'Short and long passing exercises with control under pressure.', 1),
(11, '40 min', 'Distribution', 'Training on long passes and changes of orientation under pressure.', 2),
(11, '30 min', 'Tactics', 'Small-sided game with tactical movements to improve ball distribution.', 3);


-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(12, '20 min', 'Warm-up', 'Passing and control exercises in a small space.', 1),
(12, '40 min', 'Positioning and passing', 'Positioning exercises to receive and pass under pressure.', 2),
(12, '30 min', 'Recovery', 'Training on ball recovery and transitioning from defense to attack.', 3);


-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(13, '20 min', 'Warm-up', 'Passing and control exercises under pressure, focusing on changes of direction.', 1),
(13, '40 min', 'Game vision', 'Long passing and change of direction exercises to improve game vision.', 2),
(13, '30 min', 'Pressing tactics', 'Training on pressing the opponent and making quick transitions.', 3);


-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(14, '20 min', 'Warm-up', 'Passing and control exercises under pressure, focusing on short and long passes.', 1),
(14, '40 min', 'Long passes', 'Training on long passes with changes of orientation under pressure.', 2),
(14, '30 min', 'Recovery and transition', 'Exercises to improve ball recovery and quick transition from defense to attack.', 3);


-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(15, '20 min', 'Warm-up', 'Short passing and control exercises with dynamic movements to activate game vision.', 1),
(15, '40 min', 'Field positioning', 'Exercises to improve positioning on the field to receive and distribute the ball.', 2),
(15, '30 min', 'Possession games', 'Possession training in reduced space to improve ball circulation and control in pressure situations.', 3);



-- Insertar Actividades para Delantero --

-- Lunes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(16, '20 min', 'Warm-up', 'Light jogging with pace changes, followed by dynamic stretches.', 1),
(16, '40 min', 'Finishing', 'Training shots on goal from different positions in the area.', 2),
(16, '30 min', 'Off-the-ball movements', 'Exercises for off-the-ball runs and movements to create space.', 3);


-- Martes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(17, '20 min', 'Warm-up', 'Light jogging with direction changes and dynamic stretches.', 1),
(17, '40 min', 'Long-range shots', 'Training on long-distance shots, including placement and power.', 2),
(17, '30 min', 'Off-the-ball movements', 'Exercises to improve off-the-ball runs and work without the ball in attack.', 3);


-- Miércoles --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(18, '20 min', 'Warm-up', 'Light jogging with direction changes, followed by dynamic stretches.', 1),
(18, '40 min', 'Finishing at goal', 'Training on shots at goal from different angles and distances.', 2),
(18, '30 min', 'Off-the-ball runs and movements', 'Exercises to improve off-the-ball runs and work without the ball in attack.', 3);


-- Jueves --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(19, '20 min', 'Warm-up', 'Light jogging with rhythm changes and dynamic stretches to activate the body.', 1),
(19, '40 min', 'Finishing against the goalkeeper', 'Training on finishing in one-on-one situations with the goalkeeper, focused on decision-making.', 2),
(19, '30 min', 'Off-the-ball movements', 'Exercises to improve off-the-ball runs in the area and space creation in attack.', 3);


-- Viernes --
INSERT INTO Actividad (id_rutina, duracion, titulo, descripcion, orden) VALUES
(20, '20 min', 'Warm-up', 'Light jogging with direction changes and dynamic stretches to increase agility.', 1),
(20, '40 min', 'Running shots', 'Finishing training with running shots from different angles and distances.', 2),
(20, '30 min', 'Deep off-the-ball movements', 'Exercises to improve deep off-the-ball runs and reading spaces for passing.', 3);


