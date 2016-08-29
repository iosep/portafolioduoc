-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2016-08-28 20:07:22 CLT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE
  TABLE Alternativa
  (
    id_alternativa        INTEGER NOT NULL ,
    letra                 CHAR (1) ,
    id_pregunta           INTEGER ,
    glosa_alternativa     VARCHAR2 (255) ,
    activo                INTEGER ,
    Preguntas_id_pregunta INTEGER NOT NULL
  ) ;
ALTER TABLE Alternativa ADD CONSTRAINT Alternativa_PK PRIMARY KEY (
id_alternativa ) ;


CREATE
  TABLE Area
  (
    id_area            INTEGER NOT NULL ,
    nombre_area        VARCHAR2 (255) ,
    descripcion_area   VARCHAR2 (255) ,
    Activo             INTEGER ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
ALTER TABLE Area ADD CONSTRAINT Area_PK PRIMARY KEY ( id_area ) ;


CREATE
  TABLE Cargo
  (
    id_cargo           INTEGER NOT NULL ,
    nombre_cargo       VARCHAR2 (255) ,
    descripcion_cargo  VARCHAR2 (255) ,
    activo             INTEGER ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
ALTER TABLE Cargo ADD CONSTRAINT Cargo_PK PRIMARY KEY ( id_cargo ) ;


CREATE
  TABLE Competencia
  (
    id_competencia          INTEGER NOT NULL ,
    nombre_competencia      VARCHAR2 (255) ,
    descripcion_competencia VARCHAR2 (255) ,
    sigla                   CHAR (4) ,
    activo                  INTEGER ,
    Nivel_id_nivel          INTEGER NOT NULL
  ) ;
ALTER TABLE Competencia ADD CONSTRAINT Competencia_PK PRIMARY KEY (
id_competencia ) ;


CREATE
  TABLE Menu
  (
    id_menu     INTEGER NOT NULL ,
    url_menu    VARCHAR2 (255) ,
    nombre_menu VARCHAR2 (255)
  ) ;
ALTER TABLE Menu ADD CONSTRAINT Menu_PK PRIMARY KEY ( id_menu ) ;


CREATE
  TABLE Nivel
  (
    id_nivel               INTEGER NOT NULL ,
    id_competencia         INTEGER ,
    nota_minima            REAL ,
    nota_maxima            REAL ,
    nombre_nivel           VARCHAR2 (255) ,
    activo                 INTEGER ,
    Resultado_id_resultado INTEGER NOT NULL
  ) ;
ALTER TABLE Nivel ADD CONSTRAINT Nivel_PK PRIMARY KEY ( id_nivel ) ;


CREATE
  TABLE Perfil
  (
    id_perfil          INTEGER NOT NULL ,
    nombre_perfil      VARCHAR2 (255) ,
    descripcion_perfil VARCHAR2 (255) ,
    Activo             INTEGER ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
ALTER TABLE Perfil ADD CONSTRAINT Perfil_PK PRIMARY KEY ( id_perfil ) ;


CREATE
  TABLE Perfil_Menu
  (
    id_perfil        INTEGER NOT NULL ,
    id_menu          INTEGER NOT NULL ,
    Perfil_id_perfil INTEGER NOT NULL ,
    Menu_id_menu     INTEGER NOT NULL
  ) ;
ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_PK PRIMARY KEY ( id_perfil,
id_menu ) ;


CREATE
  TABLE Preguntas
  (
    id_pregunta                INTEGER NOT NULL ,
    id_competencia             INTEGER ,
    descripcion_pregunta       VARCHAR2 (255) ,
    preguntas                  VARCHAR2 (255) ,
    puntos                     INTEGER ,
    activo                     INTEGER ,
    Competencia_id_competencia INTEGER NOT NULL
  ) ;
ALTER TABLE Preguntas ADD CONSTRAINT Preguntas_PK PRIMARY KEY ( id_pregunta ) ;


CREATE
  TABLE Prueba
  (
    id_prueba    INTEGER NOT NULL ,
    fecha_inicio DATE ,
    fecha_fin    DATE ,
    activo       INTEGER
  ) ;
ALTER TABLE Prueba ADD CONSTRAINT Prueba_PK PRIMARY KEY ( id_prueba ) ;


CREATE
  TABLE Prueba_Comp
  (
    id_prueba                  INTEGER NOT NULL ,
    id_competencia             INTEGER NOT NULL ,
    Competencia_id_competencia INTEGER NOT NULL ,
    Prueba_id_prueba           INTEGER NOT NULL
  ) ;
ALTER TABLE Prueba_Comp ADD CONSTRAINT Prueba_Competencia_PK PRIMARY KEY (
id_prueba, id_competencia ) ;


CREATE
  TABLE Resultado
  (
    id_resultado       INTEGER NOT NULL ,
    id_prueba          INTEGER ,
    id_usuario         INTEGER ,
    id_nivel           INTEGER ,
    calificacion       REAL ,
    fecha_resultado    DATE ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
ALTER TABLE Resultado ADD CONSTRAINT Resultado_PK PRIMARY KEY ( id_resultado )
;


CREATE
  TABLE Usuario
  (
    id_usuario       INTEGER NOT NULL ,
    rut              VARCHAR2 (255) ,
    nombre           VARCHAR2 (255) ,
    apellido_paterno VARCHAR2 (255) ,
    apellido_materno VARCHAR2 (255) ,
    sexo             CHAR (1) ,
    id_cargo         INTEGER ,
    id_area          INTEGER ,
    id_userjefe      INTEGER ,
    password         VARCHAR2 (255) ,
    fecha_ingreso    DATE ,
    fecha_insercion  DATE ,
    Activo           INTEGER
  ) ;
ALTER TABLE Usuario ADD CONSTRAINT Usuario_PK PRIMARY KEY ( id_usuario ) ;


ALTER TABLE Alternativa ADD CONSTRAINT Alternativa_Preguntas_FK FOREIGN KEY (
Preguntas_id_pregunta ) REFERENCES Preguntas ( id_pregunta ) ;

ALTER TABLE Area ADD CONSTRAINT Area_Usuario_FK FOREIGN KEY (
Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Cargo ADD CONSTRAINT Cargo_Usuario_FK FOREIGN KEY (
Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Competencia ADD CONSTRAINT Competencia_Nivel_FK FOREIGN KEY (
Nivel_id_nivel ) REFERENCES Nivel ( id_nivel ) ;

ALTER TABLE Nivel ADD CONSTRAINT Nivel_Resultado_FK FOREIGN KEY (
Resultado_id_resultado ) REFERENCES Resultado ( id_resultado ) ;

ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_Menu_FK FOREIGN KEY (
Menu_id_menu ) REFERENCES Menu ( id_menu ) ;

ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_Perfil_FK FOREIGN KEY (
Perfil_id_perfil ) REFERENCES Perfil ( id_perfil ) ;

ALTER TABLE Perfil ADD CONSTRAINT Perfil_Usuario_FK FOREIGN KEY (
Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Preguntas ADD CONSTRAINT Preguntas_Competencia_FK FOREIGN KEY (
Competencia_id_competencia ) REFERENCES Competencia ( id_competencia ) ;

ALTER TABLE Prueba_Comp ADD CONSTRAINT Prueba_Comp_Competencia_FK FOREIGN KEY (
Competencia_id_competencia ) REFERENCES Competencia ( id_competencia ) ;

ALTER TABLE Prueba_Comp ADD CONSTRAINT Prueba_Comp_Prueba_FK FOREIGN KEY (
Prueba_id_prueba ) REFERENCES Prueba ( id_prueba ) ;

ALTER TABLE Resultado ADD CONSTRAINT Resultado_Usuario_FK FOREIGN KEY (
Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             25
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
