-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2016-09-06 12:39:00 CLST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE Cargo
  (
    id_cargo           INTEGER NOT NULL ,
    nombre_cargo       VARCHAR2 (255) ,
    descripcion_cargo  VARCHAR2 (255) ,
    activo             CHAR (1) ,
    Eval_Histo_id_eval INTEGER NOT NULL
  ) ;
ALTER TABLE Cargo ADD CONSTRAINT Cargo_PK PRIMARY KEY ( id_cargo, Eval_Histo_id_eval ) ;


CREATE TABLE Cargo_User
  (
    id_usuario         INTEGER NOT NULL ,
    id_cargo           INTEGER NOT NULL ,
    Usuario_id_usuario INTEGER NOT NULL ,
    Cargo_id_cargo     INTEGER NOT NULL ,
    Cargo_id_eval      INTEGER NOT NULL
  ) ;
ALTER TABLE Cargo_User ADD CONSTRAINT Cargo_User_PK PRIMARY KEY ( id_usuario, id_cargo, Usuario_id_usuario, Cargo_id_cargo, Cargo_id_eval ) ;


CREATE TABLE Competencia
  (
    id_competencia     INTEGER NOT NULL ,
    nombre_competencia VARCHAR2 (255) ,
    desc_competencia   VARCHAR2 (255) ,
    sigla_competencia  CHAR (5) ,
    activo             CHAR (1) ,
    Eval_Histo_id_eval INTEGER NOT NULL
  ) ;
ALTER TABLE Competencia ADD CONSTRAINT Competencia_PK PRIMARY KEY ( id_competencia ) ;


CREATE TABLE Deta_Eval
  (
    id_detaeval              INTEGER NOT NULL ,
    id_periodo               INTEGER ,
    rut_evaluado             VARCHAR2 (255) ,
    rut_evaluador            VARCHAR2 (255) ,
    id_competencia           INTEGER ,
    nota_autoevaluacion      REAL ,
    nota_jefe                REAL ,
    nota_final               REAL ,
    brecha_nota              REAL ,
    id_cargo                 INTEGER ,
    Peri_Eval_id_peval       INTEGER NOT NULL ,
    Cargo_id_cargo           INTEGER NOT NULL ,
    Cargo_Eval_Histo_id_eval INTEGER NOT NULL
  ) ;
ALTER TABLE Deta_Eval ADD CONSTRAINT Deta_Eval_PK PRIMARY KEY ( id_detaeval ) ;


CREATE TABLE Encuesta_Detal
  (
    id_eval               INTEGER NOT NULL ,
    id_detaleval          INTEGER NOT NULL ,
    Eval_Histo_id_eval    INTEGER NOT NULL ,
    Deta_Eval_id_detaeval INTEGER NOT NULL
  ) ;
ALTER TABLE Encuesta_Detal ADD CONSTRAINT Encuesta_Detal_PK PRIMARY KEY ( id_eval, id_detaleval, Eval_Histo_id_eval ) ;


CREATE TABLE Eval_Histo
  (
    id_eval            INTEGER NOT NULL ,
    rut_evaluado       VARCHAR2 (255) ,
    rut_evaluador      VARCHAR2 (255) ,
    id_cargo           INTEGER ,
    id_competencia     INTEGER ,
    id_nivel           INTEGER ,
    tipo_evaluacion    INTEGER ,
    fecha_evaluacion   DATE ,
    nota               REAL ,
    id_periodo         INTEGER ,
    Peri_Eval_id_peval INTEGER NOT NULL ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
CREATE UNIQUE INDEX Eval_Histo__IDX ON Eval_Histo
  (
    Peri_Eval_id_peval ASC
  )
  ;
ALTER TABLE Eval_Histo ADD CONSTRAINT Eval_Histo_PK PRIMARY KEY ( id_eval ) ;


CREATE TABLE Log
  (
    id_log             INTEGER NOT NULL ,
    id_usuario         INTEGER ,
    ip_user            VARCHAR2 (255) ,
    fecha_conexion     DATE ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
ALTER TABLE Log ADD CONSTRAINT Log_PK PRIMARY KEY ( id_log, Usuario_id_usuario ) ;


CREATE TABLE Menu
  (
    id_app    INTEGER NOT NULL ,
    app       VARCHAR2 (255) ,
    nom_app   VARCHAR2 (255) ,
    id_padre  INTEGER ,
    tipo      VARCHAR2 (255) ,
    url       VARCHAR2 (255) ,
    prioridad INTEGER ,
    activo    CHAR (1)
  ) ;
ALTER TABLE Menu ADD CONSTRAINT Menu_PK PRIMARY KEY ( id_app ) ;


CREATE TABLE Nivel
  (
    id_nivel                   INTEGER NOT NULL ,
    id_competencia             INTEGER ,
    nota_minima                REAL ,
    nota_maxima                REAL NOT NULL ,
    nombre_nivel               VARCHAR2 (255) ,
    activo                     CHAR (1) ,
    Competencia_id_competencia INTEGER NOT NULL ,
    Eval_Histo_id_eval         INTEGER NOT NULL
  ) ;
ALTER TABLE Nivel ADD CONSTRAINT Nivel_PK PRIMARY KEY ( id_nivel, Competencia_id_competencia ) ;


CREATE TABLE Perfil
  (
    id_perfil          INTEGER NOT NULL ,
    nombre_perfil      VARCHAR2 (255) ,
    descripcion_perfil VARCHAR2 (255) ,
    Activo             CHAR (1) ,
    Usuario_id_usuario INTEGER NOT NULL
  ) ;
CREATE UNIQUE INDEX Perfil__IDX ON Perfil
  (
    Usuario_id_usuario ASC
  )
  ;
ALTER TABLE Perfil ADD CONSTRAINT Perfil_PK PRIMARY KEY ( id_perfil ) ;


CREATE TABLE Perfil_Menu
  (
    id_perfil        INTEGER NOT NULL ,
    id_menu          INTEGER NOT NULL ,
    Perfil_id_perfil INTEGER NOT NULL ,
    Menu_id_app      INTEGER NOT NULL
  ) ;
ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_PK PRIMARY KEY ( id_perfil, id_menu, Perfil_id_perfil, Menu_id_app ) ;


CREATE TABLE Peri_Eval
  (
    id_peval            INTEGER NOT NULL ,
    fecha_inicio        DATE ,
    fecha_termino       DATE ,
    porc_autoevaluacion REAL ,
    por_evaljefe        REAL ,
    activo              CHAR (1)
  ) ;
ALTER TABLE Peri_Eval ADD CONSTRAINT Peri_Eval_PK PRIMARY KEY ( id_peval ) ;


CREATE TABLE Pregunta
  (
    id_pregunta                INTEGER NOT NULL ,
    id_competencia             INTEGER ,
    glosa_pregunta             VARCHAR2 (255) ,
    alternativa_a              VARCHAR2 (255) ,
    alternativa_b              VARCHAR2 (255) ,
    alternativa_c              VARCHAR2 (255) ,
    alternativa_d              VARCHAR2 (255) ,
    alternativa_e              VARCHAR2 (255) ,
    alternativa_f              VARCHAR2 (255) ,
    puntaje_a                  INTEGER ,
    puntaje_b                  INTEGER ,
    puntaje_c                  INTEGER ,
    puntaje_d                  INTEGER ,
    puntaje_e                  INTEGER ,
    puntaje_f                  INTEGER ,
    activo                     CHAR (1) ,
    Res_AltPeri_id_respaltern  INTEGER NOT NULL ,
    Competencia_id_competencia INTEGER NOT NULL
  ) ;
CREATE UNIQUE INDEX Pregunta__IDX ON Pregunta
  (
    Res_AltPeri_id_respaltern ASC
  )
  ;
ALTER TABLE Pregunta ADD CONSTRAINT Pregunta_PK PRIMARY KEY ( id_pregunta, Competencia_id_competencia ) ;


CREATE TABLE Res_AltPeri
  (
    id_respaltern          INTEGER NOT NULL ,
    id_respperuser         INTEGER ,
    id_pregunta            INTEGER ,
    alter_escogida         CHAR (1) ,
    Resp_PUser_id_respuser INTEGER NOT NULL
  ) ;
ALTER TABLE Res_AltPeri ADD CONSTRAINT Res_AltPeri_PK PRIMARY KEY ( id_respaltern ) ;


CREATE TABLE Resp_PUser
  (
    id_respuser        INTEGER NOT NULL ,
    id_peval           INTEGER ,
    id_usuario         INTEGER ,
    fecha_respuesta    DATE ,
    Peri_Eval_id_peval INTEGER NOT NULL
  ) ;
ALTER TABLE Resp_PUser ADD CONSTRAINT Resp_PUser_PK PRIMARY KEY ( id_respuser ) ;


CREATE TABLE Usuario
  (
    id_usuario             INTEGER NOT NULL ,
    rut                    VARCHAR2 (255) ,
    nombre                 VARCHAR2 (255) ,
    apellido_paterno       VARCHAR2 (255) ,
    apellido_materno       VARCHAR2 (255) ,
    sexo                   CHAR (1) ,
    id_perfil              INTEGER ,
    id_userjefe            INTEGER ,
    password               VARCHAR2 (255) ,
    fecha_ingreso          DATE ,
    fecha_creacion         DATE ,
    activo                 CHAR (1) ,
    Resp_PUser_id_respuser INTEGER NOT NULL
  ) ;
ALTER TABLE Usuario ADD CONSTRAINT Usuario_PK PRIMARY KEY ( id_usuario ) ;


ALTER TABLE Cargo ADD CONSTRAINT Cargo_Eval_Histo_FK FOREIGN KEY ( Eval_Histo_id_eval ) REFERENCES Eval_Histo ( id_eval ) ;

ALTER TABLE Cargo_User ADD CONSTRAINT Cargo_User_Cargo_FK FOREIGN KEY ( Cargo_id_cargo, Cargo_id_eval ) REFERENCES Cargo ( id_cargo, Eval_Histo_id_eval ) ;

ALTER TABLE Cargo_User ADD CONSTRAINT Cargo_User_Usuario_FK FOREIGN KEY ( Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Competencia ADD CONSTRAINT Competencia_Eval_Histo_FK FOREIGN KEY ( Eval_Histo_id_eval ) REFERENCES Eval_Histo ( id_eval ) ;

ALTER TABLE Deta_Eval ADD CONSTRAINT Deta_Eval_Cargo_FK FOREIGN KEY ( Cargo_id_cargo, Cargo_Eval_Histo_id_eval ) REFERENCES Cargo ( id_cargo, Eval_Histo_id_eval ) ;

ALTER TABLE Deta_Eval ADD CONSTRAINT Deta_Eval_Peri_Eval_FK FOREIGN KEY ( Peri_Eval_id_peval ) REFERENCES Peri_Eval ( id_peval ) ;

ALTER TABLE Encuesta_Detal ADD CONSTRAINT Encuesta_Detal_Deta_Eval_FK FOREIGN KEY ( Deta_Eval_id_detaeval ) REFERENCES Deta_Eval ( id_detaeval ) ;

ALTER TABLE Encuesta_Detal ADD CONSTRAINT Encuesta_Detal_Eval_Histo_FK FOREIGN KEY ( Eval_Histo_id_eval ) REFERENCES Eval_Histo ( id_eval ) ;

ALTER TABLE Eval_Histo ADD CONSTRAINT Eval_Histo_Peri_Eval_FK FOREIGN KEY ( Peri_Eval_id_peval ) REFERENCES Peri_Eval ( id_peval ) ;

ALTER TABLE Eval_Histo ADD CONSTRAINT Eval_Histo_Usuario_FK FOREIGN KEY ( Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Log ADD CONSTRAINT Log_Usuario_FK FOREIGN KEY ( Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Nivel ADD CONSTRAINT Nivel_Competencia_FK FOREIGN KEY ( Competencia_id_competencia ) REFERENCES Competencia ( id_competencia ) ;

ALTER TABLE Nivel ADD CONSTRAINT Nivel_Eval_Histo_FK FOREIGN KEY ( Eval_Histo_id_eval ) REFERENCES Eval_Histo ( id_eval ) ;

ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_Menu_FK FOREIGN KEY ( Menu_id_app ) REFERENCES Menu ( id_app ) ;

ALTER TABLE Perfil_Menu ADD CONSTRAINT Perfil_Menu_Perfil_FK FOREIGN KEY ( Perfil_id_perfil ) REFERENCES Perfil ( id_perfil ) ;

ALTER TABLE Perfil ADD CONSTRAINT Perfil_Usuario_FK FOREIGN KEY ( Usuario_id_usuario ) REFERENCES Usuario ( id_usuario ) ;

ALTER TABLE Pregunta ADD CONSTRAINT Pregunta_Competencia_FK FOREIGN KEY ( Competencia_id_competencia ) REFERENCES Competencia ( id_competencia ) ;

ALTER TABLE Pregunta ADD CONSTRAINT Pregunta_Res_AltPeri_FK FOREIGN KEY ( Res_AltPeri_id_respaltern ) REFERENCES Res_AltPeri ( id_respaltern ) ;

ALTER TABLE Res_AltPeri ADD CONSTRAINT Res_AltPeri_Resp_PUser_FK FOREIGN KEY ( Resp_PUser_id_respuser ) REFERENCES Resp_PUser ( id_respuser ) ;

ALTER TABLE Resp_PUser ADD CONSTRAINT Resp_PUser_Peri_Eval_FK FOREIGN KEY ( Peri_Eval_id_peval ) REFERENCES Peri_Eval ( id_peval ) ;

ALTER TABLE Usuario ADD CONSTRAINT Usuario_Resp_PUser_FK FOREIGN KEY ( Resp_PUser_id_respuser ) REFERENCES Resp_PUser ( id_respuser ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            16
-- CREATE INDEX                             3
-- ALTER TABLE                             37
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
