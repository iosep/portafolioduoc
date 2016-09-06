-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2016-09-05 16:37:19 BRT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE area
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    descripcion VARCHAR2 (255 CHAR) ,
    sigla       VARCHAR2 (255 CHAR) ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE area ADD CONSTRAINT area_PK PRIMARY KEY ( id ) ;


CREATE TABLE area_competencia
  (
    id             INTEGER NOT NULL ,
    area_id        INTEGER NOT NULL ,
    competencia_id INTEGER NOT NULL ,
    activo         CHAR (1) ,
    creado         DATE ,
    modificado     DATE ,
    desactivado    DATE
  ) ;
ALTER TABLE area_competencia ADD CONSTRAINT area_competencia_PK PRIMARY KEY ( id ) ;


CREATE TABLE cargo
  (
    id          INTEGER NOT NULL ,
    usuario_id  INTEGER NOT NULL ,
    area_id     INTEGER NOT NULL ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE cargo ADD CONSTRAINT cargo_PK PRIMARY KEY ( id ) ;


CREATE TABLE competencia
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    descripcion VARCHAR2 (255 CHAR) ,
    sigla       VARCHAR2 (255 CHAR) ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE competencia ADD CONSTRAINT competencia_PK PRIMARY KEY ( id ) ;


CREATE TABLE competencia_nivel
  (
    id               INTEGER NOT NULL ,
    competencia_id   INTEGER NOT NULL ,
    nivel_id         INTEGER NOT NULL ,
    definicion_nivel VARCHAR2 (255 CHAR) ,
    comentario       VARCHAR2 (255 CHAR) ,
    activo           CHAR (1) ,
    creado           DATE ,
    modificado       DATE ,
    desactivado      DATE
  ) ;
ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_PK PRIMARY KEY ( id ) ;


CREATE TABLE encuesta
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    detalle     VARCHAR2 (255 CHAR) ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE encuesta ADD CONSTRAINT encuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE encuesta_detalle
  (
    id          INTEGER NOT NULL ,
    encuesta_id INTEGER NOT NULL ,
    orden       INTEGER ,
    pregunta_id INTEGER NOT NULL ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE encuesta_detalle ADD CONSTRAINT encuesta_detalle_PK PRIMARY KEY ( id ) ;


CREATE TABLE encuesta_log
  (
    id                  INTEGER NOT NULL ,
    usuario_encuesta_id INTEGER NOT NULL ,
    usuario_ip          VARCHAR2 (15 CHAR) ,
    registro_usuario    DATE
  ) ;
ALTER TABLE encuesta_log ADD CONSTRAINT encuesta_log_PK PRIMARY KEY ( id ) ;


CREATE TABLE evalua
  (
    id                   INTEGER NOT NULL ,
    usuario_encuesta_id  INTEGER NOT NULL ,
    parametros_evalua_id INTEGER NOT NULL ,
    total_usuario        INTEGER ,
    total_jefe           INTEGER ,
    promedio_usuario     INTEGER ,
    promedio_jefe        INTEGER ,
    total_evalua         INTEGER ,
    activo               CHAR (1) ,
    creado               DATE ,
    modificado           DATE ,
    desactivado          DATE
  ) ;
ALTER TABLE evalua ADD CONSTRAINT evalua_PK PRIMARY KEY ( id ) ;


CREATE TABLE nivel
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    nota        INTEGER ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE nivel ADD CONSTRAINT nivel_PK PRIMARY KEY ( id ) ;


CREATE TABLE parametros_evalua
  (
    id              INTEGER NOT NULL ,
    pondera_usuario INTEGER ,
    pondera_jefe    INTEGER ,
    fecha_inicio    DATE ,
    fecha_termino   DATE ,
    activo          CHAR (1) ,
    creado          DATE ,
    modificado      DATE ,
    desactivado     DATE
  ) ;
ALTER TABLE parametros_evalua ADD CONSTRAINT parametros_evalua_PK PRIMARY KEY ( id ) ;


CREATE TABLE pregunta
  (
    id             INTEGER NOT NULL ,
    pregunta       VARCHAR2 (255 CHAR) ,
    competencia_id INTEGER NOT NULL ,
    activo         CHAR (1) ,
    creado         DATE ,
    modificado     DATE ,
    desactivado    DATE
  ) ;
COMMENT ON COLUMN pregunta.id
IS
  '      ' ;
ALTER TABLE pregunta ADD CONSTRAINT pregunta_PK PRIMARY KEY ( id ) ;


CREATE TABLE respuesta
  (
    id          INTEGER NOT NULL ,
    pregunta_id INTEGER NOT NULL ,
    respuesta   VARCHAR2 (255 CHAR) ,
    letra       CHAR (1 CHAR) ,
    puntos      INTEGER ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE respuesta ADD CONSTRAINT respuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE rol
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (100 CHAR) ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE rol ADD CONSTRAINT rol_PK PRIMARY KEY ( id ) ;


CREATE TABLE sexo
  ( id INTEGER NOT NULL , sexo CHAR (1 CHAR)
  ) ;
ALTER TABLE sexo ADD CONSTRAINT sexo_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario
  (
    id          INTEGER NOT NULL ,
    rut         INTEGER NOT NULL ,
    dv          CHAR (1 CHAR) ,
    apaterno    VARCHAR2 (255 CHAR) ,
    amaterno    VARCHAR2 (255 CHAR) ,
    nombres     VARCHAR2 (255 CHAR) ,
    sexo_id     INTEGER ,
    direccion   VARCHAR2 (255 CHAR) ,
    fonoa       INTEGER ,
    fonob       INTEGER ,
    email       VARCHAR2 (255 CHAR) ,
    clave       VARCHAR2 (255 CHAR) ,
    jefe        INTEGER NOT NULL ,
    rol_id      INTEGER NOT NULL ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE usuario ADD CONSTRAINT usuario_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario_encuesta
  (
    id          INTEGER NOT NULL ,
    usuario_id  INTEGER NOT NULL ,
    encuesta_id INTEGER NOT NULL ,
    vigente     CHAR (1) ,
    activo      CHAR (1) ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE usuario_encuesta ADD CONSTRAINT usuario_encuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario_encuesta_detalle
  (
    id                   INTEGER NOT NULL ,
    usuario_encuesta_id  INTEGER NOT NULL ,
    encuesta_detalle_id  INTEGER NOT NULL ,
    respuesta_id_usuario INTEGER NOT NULL ,
    respuesta_id_jefe    INTEGER NOT NULL ,
    activo               CHAR (1) ,
    creado_usuario       DATE ,
    creado_jefe          DATE ,
    desactivado          DATE
  ) ;
ALTER TABLE usuario_encuesta_detalle ADD CONSTRAINT usuario_encuesta_detalle_PK PRIMARY KEY ( id ) ;


ALTER TABLE area_competencia ADD CONSTRAINT area_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE cargo ADD CONSTRAINT cargo_area_FK FOREIGN KEY ( area_id ) REFERENCES area ( id ) ;

ALTER TABLE cargo ADD CONSTRAINT cargo_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE area_competencia ADD CONSTRAINT competencia_area_FK FOREIGN KEY ( area_id ) REFERENCES area ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE encuesta_detalle ADD CONSTRAINT detalle_encuesta_FK FOREIGN KEY ( encuesta_id ) REFERENCES encuesta ( id ) ;

ALTER TABLE encuesta_detalle ADD CONSTRAINT detalle_pregunta_FK FOREIGN KEY ( pregunta_id ) REFERENCES pregunta ( id ) ;

ALTER TABLE usuario_encuesta_detalle ADD CONSTRAINT detalle_usuario_encuesta_FK FOREIGN KEY ( usuario_encuesta_id ) REFERENCES usuario_encuesta ( id ) ;

ALTER TABLE usuario_encuesta_detalle ADD CONSTRAINT encuesta_detalle_FK FOREIGN KEY ( encuesta_detalle_id ) REFERENCES encuesta_detalle ( id ) ;

ALTER TABLE usuario_encuesta_detalle ADD CONSTRAINT encuesta_detalle_respuesta_FK FOREIGN KEY ( respuesta_id_usuario ) REFERENCES respuesta ( id ) ;

ALTER TABLE encuesta_log ADD CONSTRAINT encuesta_log_usuario_FK FOREIGN KEY ( usuario_encuesta_id ) REFERENCES usuario_encuesta ( id ) ;

ALTER TABLE evalua ADD CONSTRAINT evalua_parametros_evalua_FK FOREIGN KEY ( parametros_evalua_id ) REFERENCES parametros_evalua ( id ) ;

ALTER TABLE evalua ADD CONSTRAINT evalua_usuario_encuesta_FK FOREIGN KEY ( usuario_encuesta_id ) REFERENCES usuario_encuesta ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT nivel_competencia_FK FOREIGN KEY ( nivel_id ) REFERENCES nivel ( id ) ;

ALTER TABLE pregunta ADD CONSTRAINT pregunta_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE respuesta ADD CONSTRAINT respuesta_pregunta_FK FOREIGN KEY ( pregunta_id ) REFERENCES pregunta ( id ) ;

ALTER TABLE usuario_encuesta ADD CONSTRAINT usuario_encuesta_encuesta_FK FOREIGN KEY ( encuesta_id ) REFERENCES encuesta ( id ) ;

ALTER TABLE usuario_encuesta ADD CONSTRAINT usuario_encuesta_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_rol_FK FOREIGN KEY ( rol_id ) REFERENCES rol ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_sexol_FK FOREIGN KEY ( rut ) REFERENCES sexo ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_usuario_FK FOREIGN KEY ( jefe ) REFERENCES usuario ( id ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            18
-- CREATE INDEX                             0
-- ALTER TABLE                             39
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
