-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2016-11-26 23:48:58 CLST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE area
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255) NOT NULL ,
    sigla       VARCHAR2 (255) NOT NULL ,
    descripcion VARCHAR2 (255) ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE area ADD CONSTRAINT area_PK PRIMARY KEY ( id ) ;


CREATE TABLE area_competencia
  (
    area_id        INTEGER NOT NULL ,
    competencia_id INTEGER NOT NULL ,
    creado         DATE NOT NULL ,
    modificado     DATE NOT NULL ,
    desactivado    DATE ,
    activo         NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE area_competencia ADD CONSTRAINT area_competencia_PK PRIMARY KEY ( area_id, competencia_id ) ;


CREATE TABLE audit_log
  (
    id          INTEGER NOT NULL ,
    ip          VARCHAR2 (255) ,
    usuario_id  INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE audit_log ADD CONSTRAINT audit_log_PK PRIMARY KEY ( id ) ;


CREATE TABLE competencia
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255) NOT NULL ,
    descripcion VARCHAR2 (255) ,
    sigla       VARCHAR2 (255) ,
    n_optimo    INTEGER ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE competencia ADD CONSTRAINT competencia_PK PRIMARY KEY ( id ) ;


CREATE TABLE competencia_nivel
  (
    competencia_id INTEGER NOT NULL ,
    nivel_id       INTEGER NOT NULL ,
    creado         DATE NOT NULL ,
    modificado     DATE NOT NULL ,
    desactivado    DATE ,
    activo         NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_PK PRIMARY KEY ( competencia_id, nivel_id ) ;


CREATE TABLE encuesta
  (
    id          INTEGER NOT NULL ,
    usuario_id  INTEGER NOT NULL ,
    evaluado_id INTEGER NOT NULL ,
    periodo_id  INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE encuesta ADD CONSTRAINT encuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE evaluacion
  (
    id             INTEGER NOT NULL ,
    rut            VARCHAR2 (255) ,
    rut_jefe       VARCHAR2 (255) ,
    nota_auto      INTEGER ,
    nota_jefe      INTEGER ,
    nota           INTEGER ,
    brecha         INTEGER ,
    periodo_id     INTEGER NOT NULL ,
    competencia_id INTEGER NOT NULL ,
    creado         DATE NOT NULL ,
    modificado     DATE NOT NULL ,
    desactivado    DATE ,
    activo         NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_PK PRIMARY KEY ( id ) ;


CREATE TABLE nivel
  (
    id          INTEGER NOT NULL ,
    nota        INTEGER NOT NULL ,
    nombre      VARCHAR2 (255) ,
    descripcion VARCHAR2 (255) ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE nivel ADD CONSTRAINT nivel_PK PRIMARY KEY ( id ) ;


CREATE TABLE observacion
  (
    id             INTEGER NOT NULL ,
    nivel_inf      INTEGER ,
    nivel_sup      INTEGER ,
    msj_inf        VARCHAR2 (255) ,
    msj_sup        VARCHAR2 (255) ,
    competencia_id INTEGER NOT NULL ,
    creado         DATE NOT NULL ,
    modificado     DATE NOT NULL ,
    desactivado    DATE ,
    activo         NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE observacion ADD CONSTRAINT observacion_PK PRIMARY KEY ( id ) ;


CREATE TABLE periodo
  (
    id          INTEGER NOT NULL ,
    inicio      DATE NOT NULL ,
    final       DATE NOT NULL ,
    jefe_porc   INTEGER ,
    auto_porc   INTEGER ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE periodo ADD CONSTRAINT periodo_PK PRIMARY KEY ( id ) ;


CREATE TABLE pregunta
  (
    id             INTEGER NOT NULL ,
    pregunta       VARCHAR2 (255) NOT NULL ,
    competencia_id INTEGER NOT NULL ,
    creado         DATE NOT NULL ,
    modificado     DATE NOT NULL ,
    desactivado    DATE ,
    activo         NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE pregunta ADD CONSTRAINT pregunta_PK PRIMARY KEY ( id ) ;


CREATE TABLE respuesta
  (
    id          INTEGER NOT NULL ,
    respuesta   VARCHAR2 (255) ,
    puntos      INTEGER ,
    pregunta_id INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE respuesta ADD CONSTRAINT respuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE rol
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255) NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE rol ADD CONSTRAINT rol_PK PRIMARY KEY ( id ) ;


CREATE TABLE seleccion
  (
    id           INTEGER NOT NULL ,
    encuesta_id  INTEGER NOT NULL ,
    respuesta_id INTEGER NOT NULL ,
    creado       DATE NOT NULL ,
    modificado   DATE NOT NULL ,
    desactivado  DATE ,
    activo       NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE seleccion ADD CONSTRAINT seleccion_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario
  (
    id          INTEGER NOT NULL ,
    rut         VARCHAR2 (255) NOT NULL ,
    rut_jefe    VARCHAR2 (255) DEFAULT '1' NOT NULL ,
    clave       VARCHAR2 (4000) NOT NULL ,
    token       VARCHAR2 (255) ,
    nombre      VARCHAR2 (255) ,
    apellido    VARCHAR2 (255) ,
    email       VARCHAR2 (255) ,
    sexo        CHAR (1) ,
    fono        INTEGER ,
    rol_id      INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE usuario ADD CONSTRAINT usuario_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario_area
  (
    usuario_id  INTEGER NOT NULL ,
    area_id     INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE usuario_area ADD CONSTRAINT usuario_area_PK PRIMARY KEY ( usuario_id, area_id ) ;


ALTER TABLE area_competencia ADD CONSTRAINT area_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE usuario_area ADD CONSTRAINT area_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE audit_log ADD CONSTRAINT audit_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE area_competencia ADD CONSTRAINT competencia_area_FK FOREIGN KEY ( area_id ) REFERENCES area ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_FK FOREIGN KEY ( nivel_id ) REFERENCES nivel ( id ) ;

ALTER TABLE encuesta ADD CONSTRAINT encuesta_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo ( id ) ;

ALTER TABLE encuesta ADD CONSTRAINT encuesta_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT nivel_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE observacion ADD CONSTRAINT observacion_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE pregunta ADD CONSTRAINT pregunta_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE respuesta ADD CONSTRAINT respuesta_pregunta_FK FOREIGN KEY ( pregunta_id ) REFERENCES pregunta ( id ) ;

ALTER TABLE seleccion ADD CONSTRAINT seleccion_encuesta_FK FOREIGN KEY ( encuesta_id ) REFERENCES encuesta ( id ) ;

ALTER TABLE seleccion ADD CONSTRAINT seleccion_respuesta_FK FOREIGN KEY ( respuesta_id ) REFERENCES respuesta ( id ) ;

ALTER TABLE usuario_area ADD CONSTRAINT usuario_area_FK FOREIGN KEY ( area_id ) REFERENCES area ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_rol_FK FOREIGN KEY ( rol_id ) REFERENCES rol ( id ) ;

CREATE SEQUENCE area_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER area_id_TRG BEFORE
  INSERT ON area FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := area_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE audit_log_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER audit_log_id_TRG BEFORE
  INSERT ON audit_log FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := audit_log_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE competencia_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER competencia_id_TRG BEFORE
  INSERT ON competencia FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := competencia_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE encuesta_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER encuesta_id_TRG BEFORE
  INSERT ON encuesta FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := encuesta_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE evaluacion_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER evaluacion_id_TRG BEFORE
  INSERT ON evaluacion FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := evaluacion_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE nivel_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER nivel_id_TRG BEFORE
  INSERT ON nivel FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := nivel_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE observacion_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER observacion_id_TRG BEFORE
  INSERT ON observacion FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := observacion_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE periodo_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER periodo_id_TRG BEFORE
  INSERT ON periodo FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := periodo_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE pregunta_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER pregunta_id_TRG BEFORE
  INSERT ON pregunta FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := pregunta_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE respuesta_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER respuesta_id_TRG BEFORE
  INSERT ON respuesta FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := respuesta_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE rol_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER rol_id_TRG BEFORE
  INSERT ON rol FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := rol_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE seleccion_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER seleccion_id_TRG BEFORE
  INSERT ON seleccion FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := seleccion_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE usuario_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER usuario_id_TRG BEFORE
  INSERT ON usuario FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := usuario_id_SEQ.NEXTVAL;
END;
/


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            16
-- CREATE INDEX                             0
-- ALTER TABLE                             33
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                          13
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
-- CREATE SEQUENCE                         13
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
