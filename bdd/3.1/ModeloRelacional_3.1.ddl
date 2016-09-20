-- Generado por Oracle SQL Developer Data Modeler 4.1.3.901
--   en:        2016-09-20 16:47:59 BRT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g




CREATE TABLE cargo
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    descripcion VARCHAR2 (255 CHAR) ,
    sigla       VARCHAR2 (255 CHAR) ,
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE cargo ADD CONSTRAINT cargo_PK PRIMARY KEY ( id ) ;


CREATE TABLE cargo_competencia
  (
    id             INTEGER NOT NULL ,
    cargo_id       INTEGER NOT NULL ,
    competencia_id INTEGER NOT NULL ,
    activo         NUMBER (1) DEFAULT 1 ,
    creado         DATE ,
    modificado     DATE ,
    desactivado    DATE
  ) ;
ALTER TABLE cargo_competencia ADD CONSTRAINT cargo_competencia_PK PRIMARY KEY ( id ) ;


CREATE TABLE competencia
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    descripcion VARCHAR2 (255 CHAR) ,
    sigla       VARCHAR2 (255 CHAR) ,
    activo      NUMBER (1) DEFAULT 1 ,
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
    activo           NUMBER (1) DEFAULT 1 ,
    creado           DATE ,
    modificado       DATE ,
    desactivado      DATE
  ) ;
ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_PK PRIMARY KEY ( id ) ;


CREATE TABLE evaluacion
  (
    id             INTEGER NOT NULL ,
    usuario_id     INTEGER NOT NULL ,
    puntos_usuario INTEGER ,
    jefe_id        INTEGER NOT NULL ,
    puntos_jefe    INTEGER ,
    competencia_id INTEGER NOT NULL ,
    total_evalua   INTEGER ,
    brecha         INTEGER ,
    activo         NUMBER (1) DEFAULT 1 ,
    creado         DATE ,
    modificado     DATE ,
    desactivado    DATE
  ) ;
ALTER TABLE evaluacion ADD CONSTRAINT evalua_PK PRIMARY KEY ( id ) ;


CREATE TABLE nivel
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (255 CHAR) ,
    nota        INTEGER ,
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE nivel ADD CONSTRAINT nivel_PK PRIMARY KEY ( id ) ;


CREATE TABLE periodo_pondera
  (
    id              INTEGER NOT NULL ,
    pondera_usuario INTEGER ,
    pondera_jefe    INTEGER ,
    fecha_inicio    DATE ,
    fecha_termino   DATE ,
    activo          NUMBER (1) DEFAULT 1 ,
    creado          DATE ,
    modificado      DATE ,
    desactivado     DATE
  ) ;
ALTER TABLE periodo_pondera ADD CONSTRAINT periodo_pondera_PK PRIMARY KEY ( id ) ;


CREATE TABLE pregunta
  (
    id             INTEGER NOT NULL ,
    pregunta       VARCHAR2 (255 CHAR) ,
    competencia_id INTEGER NOT NULL ,
    activo         NUMBER (1) DEFAULT 1 ,
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
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE respuesta ADD CONSTRAINT respuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE rol
  (
    id          INTEGER NOT NULL ,
    nombre      VARCHAR2 (100 CHAR) ,
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE rol ADD CONSTRAINT rol_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario
  (
    id          INTEGER NOT NULL ,
    rut         INTEGER NOT NULL ,
    dv          CHAR (1 CHAR) ,
    apaterno    VARCHAR2 (255 CHAR) ,
    amaterno    VARCHAR2 (255 CHAR) ,
    nombres     VARCHAR2 (255 CHAR) ,
    sexo        CHAR (1 CHAR) NOT NULL ,
    direccion   VARCHAR2 (255 CHAR) ,
    fono        INTEGER ,
    email       VARCHAR2 (255 CHAR) ,
    clave       VARCHAR2 (255 CHAR) ,
    jefe        INTEGER DEFAULT 1 NOT NULL ,
    rol_id      INTEGER NOT NULL ,
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE usuario ADD CONSTRAINT usuario_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario_cargo
  (
    id          INTEGER NOT NULL ,
    usuario_id  INTEGER NOT NULL ,
    cargo_id    INTEGER NOT NULL ,
    activo      NUMBER (1) DEFAULT 1 ,
    creado      DATE ,
    modificado  DATE ,
    desactivado DATE
  ) ;
ALTER TABLE usuario_cargo ADD CONSTRAINT usuario_cargo_PK PRIMARY KEY ( id ) ;


CREATE TABLE usuario_respuesta
  (
    id                INTEGER NOT NULL ,
    usuario_id        INTEGER NOT NULL ,
    respuesta_usuario INTEGER NOT NULL ,
    respuesta_jefe    INTEGER NOT NULL ,
    evalua_jefe       NUMBER (1) DEFAULT 0 ,
    periodo_id        INTEGER NOT NULL ,
    activo            NUMBER (1) DEFAULT 1 ,
    creado            DATE ,
    modificado        DATE ,
    desactivado       DATE
  ) ;
ALTER TABLE usuario_respuesta ADD CONSTRAINT usuario_respuesta_PK PRIMARY KEY ( id ) ;


ALTER TABLE cargo_competencia ADD CONSTRAINT cargo_competencia_FK FOREIGN KEY ( cargo_id ) REFERENCES cargo ( id ) ;

ALTER TABLE usuario_cargo ADD CONSTRAINT cargo_usuario_FK FOREIGN KEY ( cargo_id ) REFERENCES cargo ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE cargo_competencia ADD CONSTRAINT competencia_cargo_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT jefe_FK FOREIGN KEY ( jefe_id ) REFERENCES usuario ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT nivel_competencia_FK FOREIGN KEY ( nivel_id ) REFERENCES nivel ( id ) ;

ALTER TABLE pregunta ADD CONSTRAINT pregunta_competencia_FK FOREIGN KEY ( competencia_id ) REFERENCES competencia ( id ) ;

ALTER TABLE usuario_respuesta ADD CONSTRAINT respuesta_jefe_FK FOREIGN KEY ( respuesta_jefe ) REFERENCES respuesta ( id ) ;

ALTER TABLE usuario_respuesta ADD CONSTRAINT respuesta_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo_pondera ( id ) ;

ALTER TABLE respuesta ADD CONSTRAINT respuesta_pregunta_FK FOREIGN KEY ( pregunta_id ) REFERENCES pregunta ( id ) ;

ALTER TABLE usuario_respuesta ADD CONSTRAINT respuesta_usuario_FK FOREIGN KEY ( respuesta_usuario ) REFERENCES respuesta ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE usuario_cargo ADD CONSTRAINT usuario_cargo_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE usuario_respuesta ADD CONSTRAINT usuario_respuesta_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_rol_FK FOREIGN KEY ( rol_id ) REFERENCES rol ( id ) ;

ALTER TABLE usuario ADD CONSTRAINT usuario_sexol_FK FOREIGN KEY ( jefe ) REFERENCES usuario ( id ) ;

CREATE SEQUENCE cargo_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER cargo_id_TRG BEFORE
  INSERT ON cargo FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := cargo_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE cargo_competencia_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER cargo_competencia_id_TRG BEFORE
  INSERT ON cargo_competencia FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := cargo_competencia_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE competencia_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER competencia_id_TRG BEFORE
  INSERT ON competencia FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := competencia_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE competencia_nivel_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER competencia_nivel_id_TRG BEFORE
  INSERT ON competencia_nivel FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := competencia_nivel_id_SEQ.NEXTVAL;
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

CREATE SEQUENCE periodo_pondera_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER periodo_pondera_id_TRG BEFORE
  INSERT ON periodo_pondera FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := periodo_pondera_id_SEQ.NEXTVAL;
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

CREATE SEQUENCE usuario_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER usuario_id_TRG BEFORE
  INSERT ON usuario FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := usuario_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE usuario_cargo_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER usuario_cargo_id_TRG BEFORE
  INSERT ON usuario_cargo FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := usuario_cargo_id_SEQ.NEXTVAL;
END;
/


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             30
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                          12
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
-- CREATE SEQUENCE                         12
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
