--RESET INICIO

BEGIN
  FOR cur_rec IN (SELECT object_name, object_type 
                  FROM   user_objects
                  WHERE  object_type IN ('TABLE', 'VIEW', 'PACKAGE', 'PROCEDURE', 'FUNCTION', 'SEQUENCE')) LOOP
    BEGIN
      IF cur_rec.object_type = 'TABLE' THEN
        EXECUTE IMMEDIATE 'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '" CASCADE CONSTRAINTS';
      ELSE
        EXECUTE IMMEDIATE 'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"';
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.put_line('FAILED: DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"');
    END;
  END LOOP;
END;
/

--RESET FIN

--TABLAS INICIO

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


CREATE TABLE audit
  (
    id          INTEGER NOT NULL ,
    fecha       DATE NOT NULL ,
    ip          VARCHAR2 (255) ,
    usuario_id  INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE audit ADD CONSTRAINT audit_PK PRIMARY KEY ( id ) ;


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


CREATE TABLE detalle
  (
    id          INTEGER NOT NULL ,
    rut         INTEGER ,
    rut_jefe    INTEGER ,
    nota_auto   INTEGER ,
    nota_jefe   INTEGER ,
    nota        INTEGER ,
    brecha      INTEGER ,
    periodo_id  INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE detalle ADD CONSTRAINT detalle_PK PRIMARY KEY ( id ) ;


CREATE TABLE encuesta
  (
    id          INTEGER NOT NULL ,
    usuario_id  INTEGER NOT NULL ,
    jefe_id     INTEGER NOT NULL ,
    fecha       DATE NOT NULL ,
    periodo_id  INTEGER NOT NULL ,
    creado      DATE NOT NULL ,
    modificado  DATE NOT NULL ,
    desactivado DATE ,
    activo      NUMBER (1) DEFAULT 1 NOT NULL
  ) ;
ALTER TABLE encuesta ADD CONSTRAINT encuesta_PK PRIMARY KEY ( id ) ;


CREATE TABLE evaluacion
  (
    id           INTEGER NOT NULL ,
    rut_evaluado INTEGER ,
    nota         INTEGER ,
    usuario_id   INTEGER NOT NULL ,
    periodo_id   INTEGER NOT NULL ,
    creado       DATE NOT NULL ,
    modificado   DATE NOT NULL ,
    desactivado  DATE ,
    activo       NUMBER (1) DEFAULT 1 NOT NULL
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

ALTER TABLE audit ADD CONSTRAINT audit_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE area_competencia ADD CONSTRAINT competencia_area_FK FOREIGN KEY ( area_id ) REFERENCES area ( id ) ;

ALTER TABLE competencia_nivel ADD CONSTRAINT competencia_nivel_FK FOREIGN KEY ( nivel_id ) REFERENCES nivel ( id ) ;

ALTER TABLE detalle ADD CONSTRAINT detalle_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo ( id ) ;

ALTER TABLE encuesta ADD CONSTRAINT encuesta_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo ( id ) ;

ALTER TABLE encuesta ADD CONSTRAINT encuesta_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_periodo_FK FOREIGN KEY ( periodo_id ) REFERENCES periodo ( id ) ;

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_usuario_FK FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id ) ;

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

CREATE SEQUENCE audit_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER audit_id_TRG BEFORE
  INSERT ON audit FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := audit_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE competencia_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER competencia_id_TRG BEFORE
  INSERT ON competencia FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := competencia_id_SEQ.NEXTVAL;
END;
/

CREATE SEQUENCE detalle_id_SEQ START WITH 1 NOCACHE ORDER ;
CREATE OR REPLACE TRIGGER detalle_id_TRG BEFORE
  INSERT ON detalle FOR EACH ROW WHEN (NEW.id IS NULL) BEGIN :NEW.id := detalle_id_SEQ.NEXTVAL;
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
-- CREATE TABLE                            17
-- CREATE INDEX                             0
-- ALTER TABLE                             35
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                          14
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
-- CREATE SEQUENCE                         14
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

--TABLAS FIN
create or replace PACKAGE PKG_ROL AS
-----JSON
 PROCEDURE SP_ROL_CREATE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                          p_TOKEN IN USUARIO.TOKEN%TYPE,
                          p_name IN ROL.NOMBRE%TYPE);
 PROCEDURE SP_ROL_READ_ALL(p_IDUSUARIO IN USUARIO.ID%TYPE,
                           p_TOKEN IN USUARIO.TOKEN%TYPE);
 PROCEDURE SP_ROL_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                           p_TOKEN IN USUARIO.TOKEN%TYPE,
                           p_IDROL IN ROL.ID%TYPE DEFAULT NULL);
 PROCEDURE SP_ROL_READ_NOMBRE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                               p_TOKEN IN USUARIO.TOKEN%TYPE,
                               p_nombre IN ROL.NOMBRE%TYPE DEFAULT NULL);
 PROCEDURE SP_ROL_UPDATE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                          p_TOKEN IN USUARIO.TOKEN%TYPE,
                          p_idrol IN ROL.ID%TYPE,
                          p_nombrerol IN ROL.NOMBRE%TYPE);
 PROCEDURE SP_ROL_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE,
                             p_idrol IN ROL.ID%TYPE);
-----XML 
 PROCEDURE SP_ROL_XREAD_ALL;
END PKG_ROL;
/
create or replace PACKAGE BODY PKG_ROL AS
--------PROCEDIMIENTO PARA CREAR EL ROL
PROCEDURE SP_ROL_CREATE(
p_IDUSUARIO IN USUARIO.ID%TYPE,p_TOKEN IN USUARIO.TOKEN%TYPE,p_name IN ROL.NOMBRE%TYPE)
IS
total number;
l_cursor SYS_REFCURSOR;
BEGIN
      IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then 
      total := 0;
      SELECT COUNT(*) INTO total FROM ROL WHERE NOMBRE LIKE UPPER(p_name);
      
      IF total = 0 THEN
        INSERT INTO ROL (NOMBRE,CREADO,MODIFICADO) VALUES (UPPER(p_name),SYSDATE,SYSDATE);
        COMMIT;
      END IF;
      OPEN l_cursor FOR
      select * from 
      rol  r,
      (select max(id) as id from rol)  m 
       where r.id = m.id;   
      APEX_JSON.open_object;
      APEX_JSON.write('rol', l_cursor);
      APEX_JSON.close_object;  
    
      ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;  
  
  END IF;
  
  
END SP_ROL_CREATE;
--------PROCEDIMIENTO PARA LEER TODOS LOS ROLES JSON
PROCEDURE SP_ROL_READ_ALL (p_IDUSUARIO IN USUARIO.ID%TYPE,p_TOKEN IN USUARIO.TOKEN%TYPE)AS
  l_cursor SYS_REFCURSOR;
BEGIN
  if FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
  OPEN l_cursor FOR
    SELECT *
    FROM   rol r;
 
  APEX_JSON.open_object;
  APEX_JSON.write('roles', l_cursor);
  APEX_JSON.close_object;
  ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;
  
  END IF;
END SP_ROL_READ_ALL;
 
--------PROCEDIMIENTO PARA LEER UN ROL POR ID
PROCEDURE SP_ROL_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,p_TOKEN IN USUARIO.TOKEN%TYPE,p_IDROL IN ROL.ID%TYPE DEFAULT NULL) AS
  l_cursor SYS_REFCURSOR;
BEGIN
  if FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
  OPEN l_cursor FOR
    SELECT *
    FROM   rol r
    WHERE  r.id = DECODE(p_IDROL, NULL, r.id, p_IDROL);
 
  APEX_JSON.open_object;
  APEX_JSON.write('rol', l_cursor);
  APEX_JSON.close_object;
  ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;
  
  END IF;
  
END SP_ROL_READ_ID;
 
 
--------PROCEDIMIENTO PARA LEER UN ROL POR NOMBRE
PROCEDURE SP_ROL_READ_NOMBRE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_nombre IN ROL.NOMBRE%TYPE DEFAULT NULL)
AS
  l_cursor SYS_REFCURSOR;
BEGIN
  if FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
  OPEN l_cursor FOR
    SELECT *
    FROM   rol r
    WHERE  r.nombre = DECODE(p_nombre, NULL, r.nombre, p_nombre);
 
      APEX_JSON.open_object;
      APEX_JSON.write('rol', l_cursor);
      APEX_JSON.close_object;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;
END SP_ROL_READ_NOMBRE;
--------PROCEDIMIENTO PARA ACTUALIZAR UN ROL POR ID
PROCEDURE SP_ROL_UPDATE(p_IDUSUARIO IN USUARIO.ID%TYPE,
                        p_TOKEN IN USUARIO.TOKEN%TYPE,
                        p_idrol IN ROL.ID%TYPE,
                        p_nombrerol IN ROL.NOMBRE%TYPE)
AS
l_cursor SYS_REFCURSOR;
total number;
BEGIN
  if FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
  total := 0;
  SELECT COUNT(*) INTO total FROM ROL WHERE ID = p_idrol;
  
  IF total = 1 THEN
    UPDATE ROL SET NOMBRE = UPPER(p_nombrerol), MODIFICADO = SYSDATE WHERE ID = p_idrol;
    COMMIT;
    OPEN l_cursor FOR
    SELECT *
    FROM   rol r
    WHERE  r.id = DECODE(p_IDROL, NULL, r.id, p_IDROL);
 
      APEX_JSON.open_object;
      APEX_JSON.write('rol', l_cursor);
      APEX_JSON.close_object;
  END IF; 
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;
  
END SP_ROL_UPDATE;
--------PROCEDIMIENTO PARA ELIMINAR UN ROL POR ID
PROCEDURE SP_ROL_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_idrol IN ROL.ID%TYPE)
AS 
l_cursor SYS_REFCURSOR;
BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
        UPDATE ROL SET ACTIVO = 0, DESACTIVADO = SYSDATE WHERE ID = p_idrol;
        COMMIT;
        OPEN l_cursor FOR
        SELECT *
        FROM   rol r
        WHERE  r.id = DECODE(p_IDROL, NULL, r.id, p_IDROL);
 
          APEX_JSON.open_object;
          APEX_JSON.write('rol', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;      
    
END SP_ROL_DELETE_ID;
 
--------PROCEDIMIENTO PARA LEER TODOS LOS ROLES XML
PROCEDURE SP_ROL_XREAD_ALL AS
  l_clob CLOB;
BEGIN
  
  SELECT XMLELEMENT("roles",
           XMLAGG(
             XMLELEMENT("rol",
               XMLFOREST(r.id AS "id",
                         r.nombre AS "nombre",
                         r.creado AS "creado",
                         r.modificado AS "modificado",
                         r.desactivado AS "desactivado",
                         r.activo AS "activo"
               )
             )
           )
         ).getClobVal()
  INTO   l_clob
  FROM   rol r;
 
  OWA_UTIL.mime_header('text/xml');  
  HTP.print(l_clob);
 
END SP_ROL_XREAD_ALL;
END PKG_ROL;
/
---set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
---prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5576329206779453)
,p_name=>'rol'
);
 
end;
/
--prompt --application/restful_services/rol
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5576329206779453)
,p_name=>'rol'
,p_uri_prefix=>'rol/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>111
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5582976148977025)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5583065364003864)
,p_template_id=>wwv_flow_api.id(5582976148977025)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_ROL.SP_ROL_CREATE(:idusuario,:token,:name);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5607516739265097)
,p_handler_id=>wwv_flow_api.id(5583065364003864)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5615618474373840)
,p_handler_id=>wwv_flow_api.id(5583065364003864)
,p_name=>'name'
,p_bind_variable_name=>'name'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5607616168266183)
,p_handler_id=>wwv_flow_api.id(5583065364003864)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5584679075335174)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5584771633338054)
,p_template_id=>wwv_flow_api.id(5584679075335174)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_ROL.SP_ROL_DELETE_ID(:idusuario,:token,:idrol);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5619667474739813)
,p_handler_id=>wwv_flow_api.id(5584771633338054)
,p_name=>'idrol'
,p_bind_variable_name=>'idrol'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5584827258339365)
,p_handler_id=>wwv_flow_api.id(5584771633338054)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5619568755738789)
,p_handler_id=>wwv_flow_api.id(5584771633338054)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5576467049779453)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5576522551779453)
,p_template_id=>wwv_flow_api.id(5576467049779453)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'begin PKG_ROL.SP_ROL_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5614391973285251)
,p_handler_id=>wwv_flow_api.id(5576522551779453)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5614451984286475)
,p_handler_id=>wwv_flow_api.id(5576522551779453)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5576886398818049)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5576967722831717)
,p_template_id=>wwv_flow_api.id(5576886398818049)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_ROL.SP_ROL_READ_ID(:idusuario,:token,:idrol); END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5617109662542335)
,p_handler_id=>wwv_flow_api.id(5576967722831717)
,p_name=>'idrol'
,p_bind_variable_name=>'idrol'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5577004690835707)
,p_handler_id=>wwv_flow_api.id(5576967722831717)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5617025098540434)
,p_handler_id=>wwv_flow_api.id(5576967722831717)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5583688451279308)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/read_nombre'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5583714985283922)
,p_template_id=>wwv_flow_api.id(5583688451279308)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_ROL.SP_ROL_READ_NOMBRE(:idusuario,:token,:nombrerol); END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5618053136636959)
,p_handler_id=>wwv_flow_api.id(5583714985283922)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5583805425286782)
,p_handler_id=>wwv_flow_api.id(5583714985283922)
,p_name=>'nombrerol'
,p_bind_variable_name=>'nombrerol'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5618129281638027)
,p_handler_id=>wwv_flow_api.id(5583714985283922)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5584157831299976)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5584290991314906)
,p_template_id=>wwv_flow_api.id(5584157831299976)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_ROL.SP_ROL_UPDATE(:idusuario,:token,:idrol,:nombrerol);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5618971562696634)
,p_handler_id=>wwv_flow_api.id(5584290991314906)
,p_name=>'idrol'
,p_bind_variable_name=>'idrol'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5584369117318119)
,p_handler_id=>wwv_flow_api.id(5584290991314906)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5619028684698515)
,p_handler_id=>wwv_flow_api.id(5584290991314906)
,p_name=>'nombrerol'
,p_bind_variable_name=>'nombrerol'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5584576739323696)
,p_handler_id=>wwv_flow_api.id(5584290991314906)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5585283395390526)
,p_module_id=>wwv_flow_api.id(5576329206779453)
,p_uri_template=>'xml/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5585311252396349)
,p_template_id=>wwv_flow_api.id(5585283395390526)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'GET'
,p_require_https=>'NO'
,p_source=>'begin PKG_ROL.SP_ROL_XREAD_ALL;END;'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
---set verify on feedback on define on
---prompt  ...done

create or replace PACKAGE PKG_USUARIO AS 
PROCEDURE SP_USUARIO_CREATE(
                            p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_RUT      IN USUARIO.RUT%type ,
                            p_RUTJEFE IN USUARIO.RUT_JEFE%type DEFAULT 1,
                            p_CLAVE    IN USUARIO.CLAVE%type, 
                            p_NOMBRE   IN USUARIO.NOMBRE%type DEFAULT NULL ,
                            p_APELLIDO IN USUARIO.APELLIDO%type DEFAULT NULL ,
                            p_EMAIL    IN USUARIO.EMAIL%type DEFAULT NULL ,
                            p_SEXO     IN USUARIO.SEXO%type DEFAULT NULL,
                            p_FONO     IN USUARIO.FONO%type DEFAULT NULL ,
                            p_ROLID    IN USUARIO.ROL_ID%type
                            );
                            
PROCEDURE SP_USUARIO_READ_ALL(
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE
                             );
                             
PROCEDURE SP_USUARIO_READ_JEFES(
                                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN IN USUARIO.TOKEN%TYPE
                               );
                               
PROCEDURE SP_USUARIO_READ_ID(
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_id IN USUARIO.ID%TYPE
                            );

PROCEDURE SP_USUARIO_READ_ROL(
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_rolid IN USUARIO.ROL_ID%TYPE
                            );                             

PROCEDURE SP_USUARIO_READ_RUT(
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_RUT IN USUARIO.RUT%TYPE
                             );
                             
PROCEDURE SP_USUARIO_LOGIN (
                            p_RUT IN USUARIO.RUT%TYPE,
                            p_CLAVE IN USUARIO.CLAVE%type
                            );
                            
PROCEDURE SP_USUARIO_UPDATE(
                            p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN     IN USUARIO.TOKEN%TYPE,
                            p_ID        IN USUARIO.ID%TYPE,
                            p_RUT       IN USUARIO.RUT%type,
                            p_RUTJEFE   IN USUARIO.RUT_JEFE%type DEFAULT 1,
                            p_CLAVE     IN USUARIO.CLAVE%type, 
                            p_NOMBRE    IN USUARIO.NOMBRE%type DEFAULT NULL ,
                            p_APELLIDO  IN USUARIO.APELLIDO%type DEFAULT NULL ,
                            p_EMAIL     IN USUARIO.EMAIL%type DEFAULT NULL ,
                            p_SEXO      IN USUARIO.SEXO%type DEFAULT NULL,
                            p_FONO      IN USUARIO.FONO%type DEFAULT NULL ,
                            p_ROLID     IN USUARIO.ROL_ID%type
                            );
                            
PROCEDURE SP_USUARIO_DELETE_ID(
                               p_IDUSUARIO IN USUARIO.ID%TYPE,
                               p_TOKEN IN USUARIO.TOKEN%TYPE,
                               p_id IN USUARIO.ID%TYPE
                              );
                              
FUNCTION FN_HASH(
                 p_RUT in varchar2, 
                 p_CLAVE in varchar2) 
                 RETURN VARCHAR2;
                 
FUNCTION FN_TOKEN(
                  p_ID IN USUARIO.ID%TYPE,
                  p_TOKEN IN USUARIO.TOKEN%TYPE) 
                  RETURN INT;
                  
END PKG_USUARIO;
/
create or replace PACKAGE BODY PKG_USUARIO AS
--------PROCEDIMIENTO PARA CREAR EL USUARIO  
PROCEDURE SP_USUARIO_CREATE(
      p_IDUSUARIO IN USUARIO.ID%TYPE,
      p_TOKEN     IN USUARIO.TOKEN%TYPE,
      p_RUT       IN USUARIO.RUT%type ,
      p_RUTJEFE   IN USUARIO.RUT_JEFE%type DEFAULT 1,
      p_CLAVE     IN USUARIO.CLAVE%type, 
      p_NOMBRE    IN USUARIO.NOMBRE%type DEFAULT NULL ,
      p_APELLIDO  IN USUARIO.APELLIDO%type DEFAULT NULL ,
      p_EMAIL     IN USUARIO.EMAIL%type DEFAULT NULL ,
      p_SEXO      IN USUARIO.SEXO%type DEFAULT NULL,
      p_FONO      IN USUARIO.FONO%type DEFAULT NULL ,
      p_ROLID     IN USUARIO.ROL_ID%type
)
IS
total number;
l_cursor SYS_REFCURSOR;
BEGIN
IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
total :=0;
  
  SELECT COUNT(*) INTO total FROM USUARIO WHERE RUT = p_RUT;
  
  IF total = 0 THEN
   INSERT
    INTO USUARIO
      (
        RUT,RUT_JEFE,CLAVE,NOMBRE,APELLIDO,EMAIL,SEXO,FONO,ROL_ID,CREADO,MODIFICADO
      )
      VALUES
      (
        p_RUT,p_RUTJEFE,Fn_Hash(p_RUT,p_CLAVE),UPPER(p_NOMBRE),UPPER(p_APELLIDO),UPPER(p_EMAIL),p_SEXO,p_FONO,p_ROLID,SYSDATE,SYSDATE 
      );
      COMMIT;
  END IF;  
  OPEN l_cursor FOR
      SELECT u.id AS ID,
             u.rut AS RUT,
             u.rut_jefe AS RUT_JEFE,
             u.nombre AS NOMBRE,
             u.apellido AS APELLIDO,
             u.email AS EMAIL,
             u.sexo AS SEXO,
             u.fono AS FONO,
             u.rol_id AS ROL_ID,
             r.nombre AS ROL_NOMBRE,
             u.creado AS CREADO,
             u.modificado AS MODIFICADO,
             u.desactivado AS DESACTIVADO,
             u.activo AS ACTIVO
             
      FROM  usuario  u inner join 
      (select max(id) as id from usuario)  m 
       on  u.id = m.id
       inner join rol r
       on u.rol_id = r.id;   
      APEX_JSON.open_object;
      APEX_JSON.write('usuario', l_cursor);
      APEX_JSON.close_object;  
   ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;  
  
  END IF; 
  
END SP_USUARIO_CREATE;
--------PROCEDIMIENTO DE LOGIN
PROCEDURE SP_USUARIO_LOGIN(
      
      p_RUT IN USUARIO.RUT%TYPE,
      p_CLAVE IN USUARIO.CLAVE%type)
IS
l_cursor SYS_REFCURSOR;
total number;
clave_guardada varchar2(4000);
hash_clave varchar2(4000);
BEGIN
SELECT COUNT(*) INTO total FROM USUARIO WHERE RUT = p_RUT;
IF total > 0 THEN     
      hash_clave := Fn_Hash(p_RUT,p_CLAVE);
      SELECT CLAVE INTO clave_guardada FROM USUARIO WHERE RUT = p_RUT;
      IF hash_clave = clave_guardada THEN                  
          UPDATE USUARIO SET TOKEN =(SELECT DBMS_RANDOM.string('X', 25) FROM dual)WHERE RUT = p_RUT;
          COMMIT;
          OPEN l_cursor FOR 
          SELECT ID,NOMBRE,TOKEN FROM USUARIO WHERE RUT = p_RUT;
          APEX_JSON.open_object;
          APEX_JSON.write('login', l_cursor);
          APEX_JSON.close_object; 
      ELSE
  
          OPEN l_cursor FOR
          SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
          APEX_JSON.open_object;
          APEX_JSON.write('RESPUESTA', l_cursor);
          APEX_JSON.close_object;  
      
      END IF;
ELSE
  
      OPEN l_cursor FOR
      SELECT 'USUARIO NO EXISTE' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
END IF;     
  
END SP_USUARIO_LOGIN;
--------PROCEDIMIENTO PARA LEER TODOS LOS USUARIOS  
PROCEDURE SP_USUARIO_READ_ALL(p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE) 
AS
l_cursor SYS_REFCURSOR;
BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      OPEN l_cursor FOR
      SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       ORDER BY ID;
 
      APEX_JSON.open_object;
      APEX_JSON.write('usuarios', l_cursor);
      APEX_JSON.close_object; 
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
  END IF;
 
END SP_USUARIO_READ_ALL;
--------PROCEDIMIENTO PARA LEER TODOS LOS JEFES  
PROCEDURE SP_USUARIO_READ_JEFES(p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN IN USUARIO.TOKEN%TYPE)
AS
l_cursor SYS_REFCURSOR;
BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      OPEN l_cursor FOR
        SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       WHERE U.ROL_ID = 2 ORDER BY ID;
 
      APEX_JSON.open_object;
      APEX_JSON.write('jefes', l_cursor);
      APEX_JSON.close_object;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
  END IF;    
END SP_USUARIO_READ_JEFES;
--------PROCEDIMIENTO PARA LEER USUARIO POR ID  
PROCEDURE SP_USUARIO_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_id IN USUARIO.ID%TYPE)
AS
l_cursor SYS_REFCURSOR;
BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      OPEN l_cursor FOR
      SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       WHERE U.ID = p_id;
 
      APEX_JSON.open_object;
      APEX_JSON.write('usuario', l_cursor);
      APEX_JSON.close_object;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
  END IF;     
END SP_USUARIO_READ_ID;

--------PROCEDIMIENTO PARA LEER USUARIOS POR ROL  
PROCEDURE SP_USUARIO_READ_ROL (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_rolid IN USUARIO.ROL_ID%TYPE)
AS
l_cursor SYS_REFCURSOR;
BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      OPEN l_cursor FOR
      SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       WHERE U.ROL_ID = p_rolid;
 
      APEX_JSON.open_object;
      APEX_JSON.write('usuario', l_cursor);
      APEX_JSON.close_object;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
  END IF;     
END SP_USUARIO_READ_ROL;


--------PROCEDIMIENTO PARA LEER USUARIO POR RUT
PROCEDURE SP_USUARIO_READ_RUT (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_RUT IN USUARIO.RUT%TYPE)
AS
l_cursor SYS_REFCURSOR;
BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
       OPEN l_cursor FOR
           SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
        WHERE U.RUT = p_RUT;
 
      APEX_JSON.open_object;
      APEX_JSON.write('usuario', l_cursor);
      APEX_JSON.close_object;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
      
  END IF;     
END SP_USUARIO_READ_RUT;
--------PROCEDIMIENTO PARA ELIMINAR USUARIO POR ID
PROCEDURE SP_USUARIO_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN IN USUARIO.TOKEN%TYPE,
                                p_id IN USUARIO.ID%TYPE)
AS 
l_cursor SYS_REFCURSOR;
BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
        UPDATE USUARIO SET ACTIVO = 0 , DESACTIVADO = SYSDATE WHERE ID = p_id;
        COMMIT;
        OPEN l_cursor FOR
       SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       WHERE U.ID = p_id;
 
          APEX_JSON.open_object;
          APEX_JSON.write('usuario', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;      
END SP_USUARIO_DELETE_ID;
 
--------PROCEDIMIENTO PARA ACTUALIZAR USUARIO POR ID  
PROCEDURE SP_USUARIO_UPDATE(
      p_IDUSUARIO IN USUARIO.ID%TYPE,
      p_TOKEN     IN USUARIO.TOKEN%TYPE,
      p_ID        IN USUARIO.ID%TYPE,
      p_RUT       IN USUARIO.RUT%Type,
      p_RUTJEFE   IN USUARIO.RUT_JEFE%type DEFAULT 1,
      p_CLAVE     IN USUARIO.CLAVE%type, 
      p_NOMBRE    IN USUARIO.NOMBRE%type DEFAULT NULL ,
      p_APELLIDO  IN USUARIO.APELLIDO%type DEFAULT NULL ,
      p_EMAIL     IN USUARIO.EMAIL%type DEFAULT NULL ,
      p_SEXO      IN USUARIO.SEXO%type DEFAULT NULL,
      p_FONO      IN USUARIO.FONO%type DEFAULT NULL ,
      p_ROLID     IN USUARIO.ROL_ID%type)
IS
total number;
l_cursor SYS_REFCURSOR;
BEGIN
total :=0;
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      SELECT COUNT(*) INTO total FROM USUARIO WHERE ID = p_ID;
  
      IF total > 0 THEN
           UPDATE USUARIO SET
    
          RUT       =p_RUT,
          RUT_JEFE  =p_RUTJEFE,
          CLAVE     =Fn_Hash(p_RUT,p_CLAVE),
          NOMBRE    =UPPER(p_NOMBRE),
          APELLIDO  =UPPER(p_APELLIDO),
          EMAIL     =UPPER(p_EMAIL),
          SEXO      =p_SEXO,
          FONO      =p_FONO,
          ROL_ID    =p_ROLID,
          MODIFICADO=SYSDATE
 
          WHERE ID = p_ID;
 
          COMMIT;
      END IF; 
  OPEN l_cursor FOR
      SELECT 
          U.ID AS ID,
          U.RUT AS RUT,
          U.RUT_JEFE AS RUT_JEFE,
          U.NOMBRE AS NOMBRE,
          U.APELLIDO AS APELLIDO,
          U.EMAIL AS EMAIL,
          U.SEXO AS SEXO,
          U.FONO AS FONO,
          U.ROL_ID AS ROL_ID,
          R.NOMBRE AS ROL_NOMBRE,
          U.CREADO AS CREADO,
          U.MODIFICADO AS MODIFICADO,
          U.DESACTIVADO AS DESACTIVADO,
          U.ACTIVO AS ACTIVO 
       FROM USUARIO U 
       INNER JOIN ROL R 
       ON U.ROL_ID = R.ID 
       WHERE U.ID = p_id;   
      APEX_JSON.open_object;
      APEX_JSON.write('usuario', l_cursor);
      APEX_JSON.close_object;  
   ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;  
  
  END IF;     
END SP_USUARIO_UPDATE; 
 
--------FUNCION PARA CREAR EL HASH DE LA CLAVE DEL USUARIO  
FUNCTION FN_HASH (p_RUT in varchar2, p_CLAVE in varchar2) RETURN VARCHAR2 is
   l_password VARCHAR2(4000);
   l_salt VARCHAR2(4000) := 'ISYmHMtSrjFmT2nEZUvEU5LA3jrV3i';
BEGIN
   l_password := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5(input_string => p_CLAVE || substr(l_salt,10,13) || p_RUT || substr(l_salt, 4,10)));
   RETURN l_password;
END;
 
--------FUNCION PARA VALIDAR TOKEN
FUNCTION FN_TOKEN(p_ID IN USUARIO.ID%TYPE,p_TOKEN IN USUARIO.TOKEN%TYPE) RETURN INT is
token varchar(255):=null;
res int(1):=0;
BEGIN
SELECT token INTO token FROM usuario WHERE id = p_ID;
IF token = p_token THEN 
res:=1;
 
END IF;
return res;
END;
 
END PKG_USUARIO;
/
--set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
--prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5593564122114746)
,p_name=>'usuario'
);
 
end;
/
--prompt --application/restful_services/usuario
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5593564122114746)
,p_name=>'usuario'
,p_uri_prefix=>'usuario/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>70
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5593847413139788)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5594703007225921)
,p_template_id=>wwv_flow_api.id(5593847413139788)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_USUARIO.SP_USUARIO_CREATE(:idusuario,',
'                                    :token,',
'                                    :rut,',
'                                    :rutjefe,',
'                                    :clave,',
'                                    :nombre,',
'                                    :apellido,',
'                                    :email,',
'                                    :sexo,',
'                                    :fono,',
'                                    :rolid);END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595273066239246)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'apellido'
,p_bind_variable_name=>'apellido'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595029305236580)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'clave'
,p_bind_variable_name=>'clave'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595361385240251)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'email'
,p_bind_variable_name=>'email'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595583525242798)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'fono'
,p_bind_variable_name=>'fono'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5624748262714156)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595110301237658)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595603023245388)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'rolid'
,p_bind_variable_name=>'rolid'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5594882834233188)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'rut'
,p_bind_variable_name=>'rut'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5594963397235391)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'rutjefe'
,p_bind_variable_name=>'rutjefe'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5595450032241186)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'sexo'
,p_bind_variable_name=>'sexo'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5624891804715250)
,p_handler_id=>wwv_flow_api.id(5594703007225921)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594272306154528)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5631011881346441)
,p_template_id=>wwv_flow_api.id(5594272306154528)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_DELETE_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5631301942349670)
,p_handler_id=>wwv_flow_api.id(5631011881346441)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5631122943347669)
,p_handler_id=>wwv_flow_api.id(5631011881346441)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5631294781348797)
,p_handler_id=>wwv_flow_api.id(5631011881346441)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594189531152438)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/login'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5596894559624912)
,p_template_id=>wwv_flow_api.id(5594189531152438)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'YES'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_LOGIN(:rut,:clave); END; '
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5597017333629884)
,p_handler_id=>wwv_flow_api.id(5596894559624912)
,p_name=>'clave'
,p_bind_variable_name=>'clave'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5596942700626683)
,p_handler_id=>wwv_flow_api.id(5596894559624912)
,p_name=>'rut'
,p_bind_variable_name=>'rut'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5593614451114747)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5593772247114747)
,p_template_id=>wwv_flow_api.id(5593614451114747)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5625887339938304)
,p_handler_id=>wwv_flow_api.id(5593772247114747)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5625964070939511)
,p_handler_id=>wwv_flow_api.id(5593772247114747)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594497142161127)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5627698238130522)
,p_template_id=>wwv_flow_api.id(5594497142161127)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_READ_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5627986172133745)
,p_handler_id=>wwv_flow_api.id(5627698238130522)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5627769894132012)
,p_handler_id=>wwv_flow_api.id(5627698238130522)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5627869826132924)
,p_handler_id=>wwv_flow_api.id(5627698238130522)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594371404157960)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/read_jefes'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5626474804075754)
,p_template_id=>wwv_flow_api.id(5594371404157960)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_READ_JEFES(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5626585203077446)
,p_handler_id=>wwv_flow_api.id(5626474804075754)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5626649839078416)
,p_handler_id=>wwv_flow_api.id(5626474804075754)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594613071164897)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/read_rut'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5628992517221649)
,p_template_id=>wwv_flow_api.id(5594613071164897)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_USUARIO.SP_USUARIO_READ_RUT(:idusuario,:token,:rut);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5629079460223120)
,p_handler_id=>wwv_flow_api.id(5628992517221649)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5629205048225956)
,p_handler_id=>wwv_flow_api.id(5628992517221649)
,p_name=>'rut'
,p_bind_variable_name=>'rut'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5629181629225058)
,p_handler_id=>wwv_flow_api.id(5628992517221649)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5594075664150750)
,p_module_id=>wwv_flow_api.id(5593564122114746)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5629714381290753)
,p_template_id=>wwv_flow_api.id(5594075664150750)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_USUARIO.SP_USUARIO_UPDATE(:idusuario,',
'                                    :token,',
'                                    :id,',
'                                    :rut,',
'                                    :rutjefe,',
'                                    :clave,',
'                                    :nombre,',
'                                    :apellido,',
'                                    :email,',
'                                    :sexo,',
'                                    :fono,',
'                                    :rolid                                    ',
'                                   );END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630561547320741)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'apellido'
,p_bind_variable_name=>'apellido'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630306191308677)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'clave'
,p_bind_variable_name=>'clave'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630675410321935)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'email'
,p_bind_variable_name=>'email'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630851563324106)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'fono'
,p_bind_variable_name=>'fono'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630082000300780)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5629889822298434)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630420318319610)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630925769325608)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'rolid'
,p_bind_variable_name=>'rolid'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630178538302071)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'rut'
,p_bind_variable_name=>'rut'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630204329307161)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'rutjefe'
,p_bind_variable_name=>'rutjefe'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5630769657322791)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'sexo'
,p_bind_variable_name=>'sexo'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5629952902299794)
,p_handler_id=>wwv_flow_api.id(5629714381290753)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
--set verify on feedback on define on
--prompt  ...done

create or replace PACKAGE PKG_AREA AS 
 PROCEDURE SP_AREA_CREATE (
                           p_IDUSUARIO IN USUARIO.ID%TYPE,
                           p_TOKEN IN USUARIO.TOKEN%TYPE,
                           p_NOMBRE IN AREA.NOMBRE%type,
                           p_SIGLA IN AREA.SIGLA%type,
                           p_DESCRIP IN AREA.DESCRIPCION%TYPE
                           );
                           
 PROCEDURE SP_AREA_READ_ALL (
                             p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE
                            );
                            
 PROCEDURE SP_AREA_READ_ID (
                             p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE,
                             p_id IN AREA.ID%TYPE
                           );
                           
 PROCEDURE SP_AREA_READ_NOMBRE (
                                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN IN USUARIO.TOKEN%TYPE,
                                p_nombre IN AREA.NOMBRE%TYPE
                               );
                               
 PROCEDURE SP_AREA_UPDATE (
                           p_IDUSUARIO IN USUARIO.ID%TYPE,
                           p_TOKEN IN USUARIO.TOKEN%TYPE,
                           p_id IN AREA.ID%TYPE,
                           p_NOMBRE IN AREA.NOMBRE%TYPE,
                           p_SIGLA IN AREA.SIGLA%Type,
                           p_DESCRIP IN AREA.DESCRIPCION%TYPE
                          );
                          
 PROCEDURE SP_AREA_DELETE_ID (
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_id IN AREA.ID%TYPE);
 
END PKG_AREA;
/
create or replace PACKAGE BODY PKG_AREA AS
  PROCEDURE SP_AREA_CREATE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_NOMBRE IN AREA.NOMBRE%type,
                            p_SIGLA IN AREA.SIGLA%type,
                            p_DESCRIP IN AREA.DESCRIPCION%TYPE) IS
  total number;
  l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
        total := 0;
        SELECT COUNT(*) INTO total FROM AREA WHERE NOMBRE LIKE UPPER(p_NOMBRE);
  
        IF total = 0 THEN
            INSERT INTO AREA (NOMBRE,SIGLA,DESCRIPCION,CREADO,MODIFICADO) 
            VALUES (UPPER(p_NOMBRE),UPPER(p_SIGLA),UPPER(p_DESCRIP),SYSDATE,SYSDATE);
            COMMIT;
        END IF;
    OPEN l_cursor FOR
      select * from 
      area  a,
      (select max(id) as id from area)  m 
       where a.id = m.id;   
      APEX_JSON.open_object;
      APEX_JSON.write('rol', l_cursor);
      APEX_JSON.close_object;  
    
      ELSE
  
  OPEN l_cursor FOR
  SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
  APEX_JSON.open_object;
  APEX_JSON.write('RESPUESTA', l_cursor);
  APEX_JSON.close_object;  
  
  END IF;
    
  END SP_AREA_CREATE;
  PROCEDURE SP_AREA_READ_ALL (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE) AS
  l_cursor SYS_REFCURSOR;                            
  BEGIN
    
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
        OPEN l_cursor FOR 
        SELECT ID ,NOMBRE,SIGLA,DESCRIPCION,CREADO,MODIFICADO,ACTIVO FROM AREA ORDER BY ID;
        APEX_JSON.open_object;
        APEX_JSON.write('areas', l_cursor);
        APEX_JSON.close_object;
    ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF;
  END SP_AREA_READ_ALL;
  
  PROCEDURE SP_AREA_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE,
                             p_id IN AREA.ID%TYPE) AS
  l_cursor SYS_REFCURSOR;                                                       
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    OPEN l_cursor FOR 
    SELECT ID ,NOMBRE,SIGLA,DESCRIPCION,CREADO,MODIFICADO,ACTIVO  FROM AREA WHERE ID = p_id;
    APEX_JSON.open_object;
        APEX_JSON.write('areas', l_cursor);
        APEX_JSON.close_object;
  ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF;
  END SP_AREA_READ_ID;
  PROCEDURE SP_AREA_READ_NOMBRE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN IN USUARIO.TOKEN%TYPE,
                                 p_nombre IN AREA.NOMBRE%TYPE) AS
    l_cursor SYS_REFCURSOR;                                                       
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    OPEN l_cursor FOR 
    SELECT ID ,NOMBRE,SIGLA,DESCRIPCION,CREADO,MODIFICADO,ACTIVO  FROM AREA WHERE NOMBRE = p_nombre;
    APEX_JSON.open_object;
        APEX_JSON.write('areas', l_cursor);
        APEX_JSON.close_object;
  ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF;
  END SP_AREA_READ_NOMBRE;
  PROCEDURE SP_AREA_UPDATE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                           p_TOKEN IN USUARIO.TOKEN%TYPE,
                           p_id IN AREA.ID%TYPE,
                           p_NOMBRE IN AREA.NOMBRE%TYPE,
                           p_SIGLA IN AREA.SIGLA%Type,
                           p_DESCRIP IN AREA.DESCRIPCION%TYPE) 
  AS
  l_cursor SYS_REFCURSOR;
  total number;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
  total := 0;
  SELECT COUNT(*) INTO total FROM AREA WHERE ID = p_id;
  
  IF total = 1 THEN
    UPDATE AREA SET NOMBRE = UPPER(p_NOMBRE), SIGLA = UPPER(p_SIGLA),DESCRIPCION = UPPER(p_DESCRIP),MODIFICADO = SYSDATE WHERE ID = p_id;
    COMMIT;
    OPEN l_cursor FOR
    SELECT *
    FROM   AREA a
    WHERE  a.id = DECODE(p_ID, NULL, a.id, p_ID);
    APEX_JSON.open_object;
    APEX_JSON.write('rol', l_cursor);
    APEX_JSON.close_object;
  END IF;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;
  END SP_AREA_UPDATE;
  
  PROCEDURE SP_AREA_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE,
                              p_id IN AREA.ID%TYPE) AS
                              l_cursor SYS_REFCURSOR;
                              
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
 
   UPDATE AREA SET ACTIVO = 0, DESACTIVADO = SYSDATE WHERE ID = p_id;
   COMMIT;
   OPEN l_cursor FOR
        SELECT *
        FROM   area a
        WHERE  a.id = DECODE(p_ID, NULL, a.id, p_ID);
 
          APEX_JSON.open_object;
          APEX_JSON.write('area', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;
  END SP_AREA_DELETE_ID;
END PKG_AREA;
/
--set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
--prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5633178993449491)
,p_name=>'area'
);
 
end;
/
--prompt --application/restful_services/area
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5633178993449491)
,p_name=>'area'
,p_uri_prefix=>'area/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>33
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5633307634454082)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5634150039620302)
,p_template_id=>wwv_flow_api.id(5633307634454082)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_AREA.SP_AREA_CREATE(:idusuario,',
'                              :token,',
'                              :nombre,',
'                              :sigla',
'                             );END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5634259377622004)
,p_handler_id=>wwv_flow_api.id(5634150039620302)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5634430856624374)
,p_handler_id=>wwv_flow_api.id(5634150039620302)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5634501659625600)
,p_handler_id=>wwv_flow_api.id(5634150039620302)
,p_name=>'sigla'
,p_bind_variable_name=>'sigla'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5634353188623221)
,p_handler_id=>wwv_flow_api.id(5634150039620302)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5639283611343844)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5639380524347664)
,p_template_id=>wwv_flow_api.id(5639283611343844)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_AREA.SP_AREA_DELETE_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5639639506350660)
,p_handler_id=>wwv_flow_api.id(5639380524347664)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5639475739348753)
,p_handler_id=>wwv_flow_api.id(5639380524347664)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5639575031349776)
,p_handler_id=>wwv_flow_api.id(5639380524347664)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5635392765010348)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5635409880015140)
,p_template_id=>wwv_flow_api.id(5635392765010348)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_AREA.SP_AREA_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5635508705016703)
,p_handler_id=>wwv_flow_api.id(5635409880015140)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5635687037017712)
,p_handler_id=>wwv_flow_api.id(5635409880015140)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5636736817161972)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5636813721168173)
,p_template_id=>wwv_flow_api.id(5636736817161972)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_AREA.SP_AREA_READ_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5637120257172737)
,p_handler_id=>wwv_flow_api.id(5636813721168173)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5636934485169325)
,p_handler_id=>wwv_flow_api.id(5636813721168173)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5637098338170497)
,p_handler_id=>wwv_flow_api.id(5636813721168173)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5637640699244815)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/read_nombre'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5637775669251003)
,p_template_id=>wwv_flow_api.id(5637640699244815)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_AREA.SP_AREA_READ_NOMBRE(:idusuario,:token,:nombre);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5637885814252322)
,p_handler_id=>wwv_flow_api.id(5637775669251003)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5638045331254312)
,p_handler_id=>wwv_flow_api.id(5637775669251003)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5637977480253320)
,p_handler_id=>wwv_flow_api.id(5637775669251003)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5638574581307185)
,p_module_id=>wwv_flow_api.id(5633178993449491)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5638698562315727)
,p_template_id=>wwv_flow_api.id(5638574581307185)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_AREA.SP_AREA_UPDATE(:idusuario,:token,:id,:nombre,:sigla);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5638901034319013)
,p_handler_id=>wwv_flow_api.id(5638698562315727)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5638707546316919)
,p_handler_id=>wwv_flow_api.id(5638698562315727)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5639008687320051)
,p_handler_id=>wwv_flow_api.id(5638698562315727)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5639107328321600)
,p_handler_id=>wwv_flow_api.id(5638698562315727)
,p_name=>'sigla'
,p_bind_variable_name=>'sigla'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5638891121317902)
,p_handler_id=>wwv_flow_api.id(5638698562315727)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
--set verify on feedback on define on
--prompt  ...done
create or replace PACKAGE PKG_NIVEL AS 
 PROCEDURE SP_NIVEL_CREATE (
                            p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_NOTA IN NIVEL.NOTA%TYPE,
                            p_NOMBRE IN NIVEL.NOMBRE%TYPE,
                            p_descrip IN NIVEL.DESCRIPCION%TYPE
                           );
                           
 PROCEDURE SP_NIVEL_READ_ALL (
                              p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE
                             );
                             
 PROCEDURE SP_NIVEL_READ_ID (
                             p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE,
                             p_id IN NIVEL.ID%TYPE
                            );
                            
 PROCEDURE SP_NIVEL_READ_NOMBRE (
                                 p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN IN USUARIO.TOKEN%TYPE,
                                 p_nombre IN NIVEL.NOMBRE%TYPE
                                );
                                
 PROCEDURE SP_NIVEL_UPDATE (
                            p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_id IN NIVEL.ID%TYPE,
                            p_NOTA IN NIVEL.NOTA%TYPE,
                            p_NOMBRE IN NIVEL.NOMBRE%TYPE,
                            p_descrip IN NIVEL.DESCRIPCION%TYPE
                           );
                           
 PROCEDURE SP_NIVEL_DELETE_ID (
                               p_IDUSUARIO IN USUARIO.ID%TYPE,
                               p_TOKEN IN USUARIO.TOKEN%TYPE,
                               p_id IN NIVEL.ID%TYPE
                              );
 
END PKG_NIVEL;
/
create or replace PACKAGE BODY PKG_NIVEL AS
  PROCEDURE SP_NIVEL_CREATE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_NOTA IN NIVEL.NOTA%TYPE,
                            p_NOMBRE IN NIVEL.NOMBRE%TYPE,
                            p_descrip IN NIVEL.DESCRIPCION%TYPE) IS
  total number;
  l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
        total := 0;
        SELECT COUNT(*) INTO total FROM NIVEL WHERE NOMBRE LIKE UPPER(p_NOMBRE);
 
        IF total = 0 THEN
        INSERT INTO NIVEL (NOTA,NOMBRE,DESCRIPCION,CREADO,MODIFICADO) VALUES (p_NOTA,UPPER(p_NOMBRE),UPPER(p_DESCRIP),SYSDATE,SYSDATE);
        COMMIT;
        END IF;
        OPEN l_cursor FOR
      select * from 
      nivel  n,
      (select max(id) as id from nivel)  m 
       where n.id = m.id;   
      APEX_JSON.open_object;
      APEX_JSON.write('nivel', l_cursor);
      APEX_JSON.close_object;  
    
    ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  
  END IF;
  END SP_NIVEL_CREATE;
  PROCEDURE SP_NIVEL_READ_ALL ( p_IDUSUARIO IN USUARIO.ID%TYPE,
                              p_TOKEN IN USUARIO.TOKEN%TYPE) AS
  l_cursor SYS_REFCURSOR;                            
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    OPEN l_cursor FOR 
    SELECT ID ,NOTA,NOMBRE,DESCRIPCION,CREADO,MODIFICADO,ACTIVO FROM NIVEL ORDER BY ID;
    APEX_JSON.open_object;
        APEX_JSON.write('nivel', l_cursor);
        APEX_JSON.close_object;
  ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF;
  END SP_NIVEL_READ_ALL;
  PROCEDURE SP_NIVEL_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                             p_TOKEN IN USUARIO.TOKEN%TYPE,
                             p_id IN NIVEL.ID%TYPE) AS
  l_cursor SYS_REFCURSOR;                            
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    OPEN l_cursor FOR 
    SELECT ID ,NOTA,NOMBRE,DESCRIPCION,CREADO,MODIFICADO,ACTIVO FROM NIVEL WHERE ID = p_id;
    APEX_JSON.open_object;
        APEX_JSON.write('nivel', l_cursor);
        APEX_JSON.close_object;
  ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF;
  END SP_NIVEL_READ_ID;
  PROCEDURE SP_NIVEL_READ_NOMBRE (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN IN USUARIO.TOKEN%TYPE,
                                 p_nombre IN NIVEL.NOMBRE%TYPE) AS
  l_cursor SYS_REFCURSOR;                            
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    OPEN l_cursor FOR 
    SELECT ID ,NOTA,NOMBRE,DESCRIPCION,CREADO,MODIFICADO,ACTIVO FROM NIVEL WHERE NOMBRE = UPPER(p_nombre);
    APEX_JSON.open_object;
        APEX_JSON.write('nivel', l_cursor);
        APEX_JSON.close_object;
  ELSE
  
        OPEN l_cursor FOR
        SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
        APEX_JSON.open_object;
        APEX_JSON.write('RESPUESTA', l_cursor);
        APEX_JSON.close_object;
  
  END IF; 
  END SP_NIVEL_READ_NOMBRE;
  PROCEDURE SP_NIVEL_UPDATE ( p_IDUSUARIO IN USUARIO.ID%TYPE,
                            p_TOKEN IN USUARIO.TOKEN%TYPE,
                            p_id IN NIVEL.ID%TYPE,
                            p_NOTA IN NIVEL.NOTA%TYPE,
                            p_NOMBRE IN NIVEL.NOMBRE%TYPE,
                            p_descrip IN NIVEL.DESCRIPCION%TYPE) AS
  total number;
  l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
 
       total := 0;
       SELECT COUNT(*) INTO total FROM NIVEL WHERE ID = p_id;
  
      IF total = 1 THEN
        UPDATE 
        NIVEL SET NOTA = p_NOTA,
        NOMBRE = UPPER(p_NOMBRE),
        DESCRIPCION = UPPER(p_descrip),
        MODIFICADO = SYSDATE 
        WHERE ID = p_id;
        COMMIT;
        OPEN l_cursor FOR
        SELECT *
        FROM   nivel n
        WHERE  n.id = DECODE(p_ID, NULL, n.id, p_ID);
        APEX_JSON.open_object;
        APEX_JSON.write('nivel', l_cursor);
        APEX_JSON.close_object;
      END IF;
  ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF;    
  END SP_NIVEL_UPDATE;
  PROCEDURE SP_NIVEL_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                               p_TOKEN IN USUARIO.TOKEN%TYPE,
                               p_id IN NIVEL.ID%TYPE) AS
                               l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
 
       UPDATE NIVEL SET ACTIVO = 0, DESACTIVADO = SYSDATE WHERE ID = p_id;
       COMMIT;
       OPEN l_cursor FOR
        SELECT *
        FROM   nivel n
        WHERE  n.id = DECODE(p_ID, NULL, n.id, p_ID);
 
          APEX_JSON.open_object;
          APEX_JSON.write('nivel', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF; 
  END SP_NIVEL_DELETE_ID;
END PKG_NIVEL;
/
--set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
--prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5641327565684934)
,p_name=>'nivel'
);
 
end;
/
--prompt --application/restful_services/nivel
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5641327565684934)
,p_name=>'nivel'
,p_uri_prefix=>'nivel/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>37
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5641506799809304)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5641706199826938)
,p_template_id=>wwv_flow_api.id(5641506799809304)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_NIVEL.SP_NIVEL_CREATE(',
'                                :idusuario,',
'                                :token,',
'                                :nota,',
'                                :nombre,',
'                                :descrip);END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5642244997851032)
,p_handler_id=>wwv_flow_api.id(5641706199826938)
,p_name=>'descrip'
,p_bind_variable_name=>'descrip'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5641823134846230)
,p_handler_id=>wwv_flow_api.id(5641706199826938)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5642131051849429)
,p_handler_id=>wwv_flow_api.id(5641706199826938)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5642056594848407)
,p_handler_id=>wwv_flow_api.id(5641706199826938)
,p_name=>'nota'
,p_bind_variable_name=>'nota'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5641998674847250)
,p_handler_id=>wwv_flow_api.id(5641706199826938)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5647550411300141)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5647668842307698)
,p_template_id=>wwv_flow_api.id(5647550411300141)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_NIVEL.SP_NIVEL_DELETE_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5647937726310362)
,p_handler_id=>wwv_flow_api.id(5647668842307698)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5647712754308694)
,p_handler_id=>wwv_flow_api.id(5647668842307698)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5647863104309539)
,p_handler_id=>wwv_flow_api.id(5647668842307698)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5642771373887642)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5642816237892096)
,p_template_id=>wwv_flow_api.id(5642771373887642)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_NIVEL.SP_NIVEL_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5642918711893154)
,p_handler_id=>wwv_flow_api.id(5642816237892096)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5643070405893976)
,p_handler_id=>wwv_flow_api.id(5642816237892096)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5644332140015520)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5644573251163790)
,p_template_id=>wwv_flow_api.id(5644332140015520)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_NIVEL.SP_NIVEL_READ_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5644886881167433)
,p_handler_id=>wwv_flow_api.id(5644573251163790)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5644697109165338)
,p_handler_id=>wwv_flow_api.id(5644573251163790)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5644736250166432)
,p_handler_id=>wwv_flow_api.id(5644573251163790)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5645317479189719)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/read_nombre'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5645466504191733)
,p_template_id=>wwv_flow_api.id(5645317479189719)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_NIVEL.SP_NIVEL_READ_NOMBRE(:idusuario,:token,:nombre);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5645507801192872)
,p_handler_id=>wwv_flow_api.id(5645466504191733)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5645715420194878)
,p_handler_id=>wwv_flow_api.id(5645466504191733)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5645608561193797)
,p_handler_id=>wwv_flow_api.id(5645466504191733)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5646279554244544)
,p_module_id=>wwv_flow_api.id(5641327565684934)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5646366294254354)
,p_template_id=>wwv_flow_api.id(5646279554244544)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_NIVEL.SP_NIVEL_UPDATE(',
'                                :idusuario,',
'                                :token,',
'                                :id,',
'                                :nota,',
'                                :nombre,',
'                                :descrip);END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646972131260864)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'descrip'
,p_bind_variable_name=>'descrip'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646626346257267)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646421666255545)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646896738259512)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646774043258127)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'nota'
,p_bind_variable_name=>'nota'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5646585832256449)
,p_handler_id=>wwv_flow_api.id(5646366294254354)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
--set verify on feedback on define on
--prompt  ...done
create or replace PACKAGE PKG_PERIODO AS 
 PROCEDURE SP_PERIODO_CREATE (  p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                p_INICIO    IN VARCHAR2,
                p_FINAL     IN VARCHAR2,
                p_JEFEPORC IN PERIODO.JEFE_PORC%type,
                p_AUTOPORC IN PERIODO.AUTO_PORC%type
               );
                             
 PROCEDURE SP_PERIODO_READ_ALL (
                                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE
                               );
                               
 PROCEDURE SP_PERIODO_READ_ID (
                                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                                p_id IN PERIODO.ID%TYPE
                              );
                              
 PROCEDURE SP_PERIODO_UPDATE (  
                                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                p_id IN PERIODO.ID%TYPE,
                p_INICIO IN VARCHAR2,
                p_FINAL IN VARCHAR2,
                p_JEFEPORC IN PERIODO.JEFE_PORC%type,
                p_AUTOPORC IN PERIODO.AUTO_PORC%type
               );
                             
 PROCEDURE SP_PERIODO_DELETE_ID (
                                 p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN     IN USUARIO.TOKEN%TYPE,
                                 p_id IN PERIODO.ID%TYPE
                                 );
 
END PKG_PERIODO;
/
create or replace PACKAGE BODY PKG_PERIODO AS
  PROCEDURE SP_PERIODO_CREATE (
                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                p_INICIO    IN VARCHAR2,
                p_FINAL     IN VARCHAR2,
                p_JEFEPORC  IN PERIODO.JEFE_PORC%type,
                p_AUTOPORC  IN PERIODO.AUTO_PORC%type
               ) AS
  total number;
  l_cursor SYS_REFCURSOR;
  
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    total := 0;
      SELECT COUNT(*) INTO total FROM PERIODO WHERE INICIO = to_date(p_INICIO,'YYYY-MM-DD') AND FINAL = to_date(p_FINAL,'YYYY-MM-DD');
  
    IF total = 0 THEN
    INSERT INTO PERIODO (
      INICIO,FINAL,JEFE_PORC,AUTO_PORC,CREADO,MODIFICADO
    ) VALUES (
      to_date(p_INICIO,'YYYY-MM-DD'),to_date(p_FINAL,'YYYY-MM-DD'),p_JEFEPORC,p_AUTOPORC,SYSDATE,SYSDATE
    );
    COMMIT;
    OPEN l_cursor FOR
    SELECT p.id AS ID,
           TO_CHAR(p.inicio,'YYYY-MM-DD') AS INICIO,
           TO_CHAR(p.final,'YYYY-MM-DD') AS FINAL,
           p.jefe_porc AS JEFE_PORC,
           p.auto_porc AS AUTO_PORC,
           p.creado AS CREADO,
           p.modificado AS MODIFICADO,
           p.desactivado AS DESACTIVADO,
           p.activo AS ACTIVO
             
      FROM  periodo  p inner join 
      (select max(id) as id from periodo)  m 
       on  p.id = m.id;
  END IF;
       APEX_JSON.open_object;
       APEX_JSON.write('periodo', l_cursor);
       APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;
  END SP_PERIODO_CREATE;
  PROCEDURE SP_PERIODO_READ_ALL (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN     IN USUARIO.TOKEN%TYPE) AS
                                 l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
        OPEN l_cursor FOR 
        SELECT p.id AS ID,
           TO_CHAR(p.inicio,'YYYY-MM-DD') AS INICIO,
           TO_CHAR(p.final,'YYYY-MM-DD') AS FINAL,
           p.jefe_porc AS JEFE_PORC,
           p.auto_porc AS AUTO_PORC,
           p.creado AS CREADO,
           p.modificado AS MODIFICADO,
           p.desactivado AS DESACTIVADO,
           p.activo AS ACTIVO
             
        FROM  periodo  p
        ORDER BY p.ID;
        APEX_JSON.open_object;
        APEX_JSON.write('periodo', l_cursor);
        APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;    
        
  END SP_PERIODO_READ_ALL;
  
  PROCEDURE SP_PERIODO_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                                p_id IN PERIODO.ID%TYPE) AS
                                l_cursor SYS_REFCURSOR;
  BEGIN
     
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
        OPEN l_cursor FOR 
        SELECT p.id AS ID,
           TO_CHAR(p.inicio,'YYYY-MM-DD') AS INICIO,
           TO_CHAR(p.final,'YYYY-MM-DD') AS FINAL,
           p.jefe_porc AS JEFE_PORC,
           p.auto_porc AS AUTO_PORC,
           p.creado AS CREADO,
           p.modificado AS MODIFICADO,
           p.desactivado AS DESACTIVADO,
           p.activo AS ACTIVO
        FROM  periodo  p
        WHERE p.ID=p_id;
        APEX_JSON.open_object;
        APEX_JSON.write('periodo', l_cursor);
        APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;    
     
  END SP_PERIODO_READ_ID;
  PROCEDURE SP_PERIODO_UPDATE (
                p_IDUSUARIO IN USUARIO.ID%TYPE,
                                p_TOKEN     IN USUARIO.TOKEN%TYPE,
                p_id IN PERIODO.ID%TYPE,
                p_INICIO IN VARCHAR2,
                p_FINAL IN VARCHAR2,
                p_JEFEPORC IN PERIODO.JEFE_PORC%type,
                p_AUTOPORC IN PERIODO.AUTO_PORC%type
               ) AS
  total number;
  l_cursor SYS_REFCURSOR;
  
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    total := 0;
    l_cursor := null;
      SELECT COUNT(*) INTO total FROM PERIODO WHERE id = p_id;
  
    IF total > 0 THEN
    UPDATE PERIODO SET
      INICIO=to_date(p_INICIO,'YYYY-MM-DD'),
      FINAL=to_date(p_FINAL,'YYYY-MM-DD'),
      JEFE_PORC=p_JEFEPORC,
      AUTO_PORC=p_AUTOPORC,
      MODIFICADO=SYSDATE
      WHERE ID=p_id;
    COMMIT;
    OPEN l_cursor FOR
    SELECT id AS ID,
           TO_CHAR(inicio,'YYYY-MM-DD') AS INICIO,
           TO_CHAR(final,'YYYY-MM-DD') AS FINAL,
           jefe_porc AS JEFE_PORC,
           auto_porc AS AUTO_PORC,
           creado AS CREADO,
           modificado AS MODIFICADO,
           desactivado AS DESACTIVADO,
           activo AS ACTIVO
    FROM  periodo   
    WHERE periodo.id = p_id;
    APEX_JSON.open_object;
    APEX_JSON.write('periodo', l_cursor);
    APEX_JSON.close_object;       
  END IF;
       
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;
  END SP_PERIODO_UPDATE;
  PROCEDURE SP_PERIODO_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                 p_TOKEN     IN USUARIO.TOKEN%TYPE,
                                 p_id IN PERIODO.ID%TYPE) AS
                               l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    UPDATE PERIODO SET ACTIVO = 0, DESACTIVADO = SYSDATE WHERE ID = p_id;
    COMMIT;
    OPEN l_cursor FOR
        SELECT *
        FROM   periodo p
        WHERE  p.id = DECODE(p_ID, NULL, p.id, p_ID);
 
          APEX_JSON.open_object;
          APEX_JSON.write('periodo', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF; 
  END SP_PERIODO_DELETE_ID;
END PKG_PERIODO;
/
--set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
--prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5700690426225939)
,p_name=>'periodo'
);
 
end;
/
--prompt --application/restful_services/periodo
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5700690426225939)
,p_name=>'periodo'
,p_uri_prefix=>'periodo/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>35
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5700782446227144)
,p_module_id=>wwv_flow_api.id(5700690426225939)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5700876891238988)
,p_template_id=>wwv_flow_api.id(5700782446227144)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_PERIODO.SP_PERIODO_CREATE(:idusuario,',
'                                    :token,',
'                                    :inicio,',
'                                    :final,',
'                                    :jefeporc,',
'                                    :autoporc);END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5701553018248031)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'autoporc'
,p_bind_variable_name=>'autoporc'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5701251582242646)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'final'
,p_bind_variable_name=>'final'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5700917550239978)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5701128743241765)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'inicio'
,p_bind_variable_name=>'inicio'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5701406819246822)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'jefeporc'
,p_bind_variable_name=>'jefeporc'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5701044674240876)
,p_handler_id=>wwv_flow_api.id(5700876891238988)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5710021385837390)
,p_module_id=>wwv_flow_api.id(5700690426225939)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5710117530839422)
,p_template_id=>wwv_flow_api.id(5710021385837390)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_PERIODO.SP_PERIODO_DELETE_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5710488932843170)
,p_handler_id=>wwv_flow_api.id(5710117530839422)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5710204425840938)
,p_handler_id=>wwv_flow_api.id(5710117530839422)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5710384782842169)
,p_handler_id=>wwv_flow_api.id(5710117530839422)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5705430524540461)
,p_module_id=>wwv_flow_api.id(5700690426225939)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5705555883544753)
,p_template_id=>wwv_flow_api.id(5705430524540461)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_PERIODO.SP_PERIODO_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5705609967546977)
,p_handler_id=>wwv_flow_api.id(5705555883544753)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5705760981548609)
,p_handler_id=>wwv_flow_api.id(5705555883544753)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5706240648589395)
,p_module_id=>wwv_flow_api.id(5700690426225939)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5706383369591647)
,p_template_id=>wwv_flow_api.id(5706240648589395)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_PERIODO.SP_PERIODO_READ_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5706637665594384)
,p_handler_id=>wwv_flow_api.id(5706383369591647)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5706459914592627)
,p_handler_id=>wwv_flow_api.id(5706383369591647)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5706595896593475)
,p_handler_id=>wwv_flow_api.id(5706383369591647)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5707281444652760)
,p_module_id=>wwv_flow_api.id(5700690426225939)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5707397954657081)
,p_template_id=>wwv_flow_api.id(5707281444652760)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_PERIODO.SP_PERIODO_UPDATE(:idusuario,',
'                                    :token,',
'                                    :id,',
'                                    :inicio,',
'                                    :final,',
'                                    :jefeporc,',
'                                    :autoporc);END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5708050825664095)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'autoporc'
,p_bind_variable_name=>'autoporc'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707890712661730)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'final'
,p_bind_variable_name=>'final'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707618644659892)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707466437658039)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707752894660874)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'inicio'
,p_bind_variable_name=>'inicio'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707900179662750)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'jefeporc'
,p_bind_variable_name=>'jefeporc'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5707506285659061)
,p_handler_id=>wwv_flow_api.id(5707397954657081)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
--set verify on feedback on define on
--prompt  ...done
create or replace PACKAGE PKG_COMPETENCIA AS 
 PROCEDURE SP_COMPETENCIA_CREATE 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE,
            p_NOMBRE IN COMPETENCIA.NOMBRE%TYPE,
            p_DESCRIPCION IN COMPETENCIA.DESCRIPCION%TYPE,
            p_SIGLA IN COMPETENCIA.SIGLA%TYPE,
            p_NOPTIMO IN COMPETENCIA.N_OPTIMO%TYPE
           );
                                 
 PROCEDURE SP_COMPETENCIA_READ_ALL 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE
           );
 
 PROCEDURE SP_COMPETENCIA_READ_ID 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE,
            p_id IN COMPETENCIA.ID%TYPE
           );
 
 PROCEDURE SP_COMPETENCIA_READ_NOMBRE 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE,
            p_nombre IN COMPETENCIA.NOMBRE%TYPE
           );
 
 PROCEDURE SP_COMPETENCIA_READ_SIGLA 
           ( 
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE,
            p_SIGLA IN COMPETENCIA.SIGLA%TYPE
           );
                                     
 PROCEDURE SP_COMPETENCIA_UPDATE 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE, 
            p_id IN COMPETENCIA.ID%TYPE,
            p_NOMBRE IN COMPETENCIA.NOMBRE%TYPE,
            p_DESCRIPCION IN COMPETENCIA.DESCRIPCION%TYPE,
            p_SIGLA IN COMPETENCIA.SIGLA%TYPE,
            p_NOPTIMO IN COMPETENCIA.N_OPTIMO%TYPE
           );
 
 PROCEDURE SP_COMPETENCIA_DELETE_ID 
           (
            p_IDUSUARIO IN USUARIO.ID%TYPE,
            p_TOKEN IN USUARIO.TOKEN%TYPE, 
            p_id IN COMPETENCIA.ID%TYPE
           );
 
END PKG_COMPETENCIA;
/
create or replace PACKAGE BODY PKG_COMPETENCIA AS
  PROCEDURE SP_COMPETENCIA_CREATE (
                  p_IDUSUARIO IN USUARIO.ID%TYPE,
                                    p_TOKEN IN USUARIO.TOKEN%TYPE,
                  p_NOMBRE IN COMPETENCIA.NOMBRE%TYPE,
                  p_DESCRIPCION IN COMPETENCIA.DESCRIPCION%TYPE,
                  p_SIGLA IN COMPETENCIA.SIGLA%TYPE,
                                    p_NOPTIMO IN COMPETENCIA.N_OPTIMO%TYPE
                 ) IS
  total number; 
  l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
      total := 0;
      SELECT COUNT(*) INTO total FROM COMPETENCIA WHERE NOMBRE LIKE UPPER(p_NOMBRE) OR SIGLA = UPPER(p_SIGLA);
  
      IF total = 0 THEN
        INSERT INTO COMPETENCIA (NOMBRE,DESCRIPCION,SIGLA,N_OPTIMO,CREADO,MODIFICADO) VALUES (UPPER(p_NOMBRE),UPPER(p_DESCRIPCION),UPPER(p_SIGLA),p_NOPTIMO,SYSDATE,SYSDATE);
        COMMIT;
      END IF;  
      OPEN l_cursor FOR
      select * from 
      competencia  c,
      (select max(id) as id from competencia)  m 
       where c.id = m.id;   
      APEX_JSON.open_object;
      APEX_JSON.write('competencia', l_cursor);
      APEX_JSON.close_object;  
    
    ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  
  END IF;    
  END SP_COMPETENCIA_CREATE;
  PROCEDURE SP_COMPETENCIA_READ_ALL ( p_IDUSUARIO IN USUARIO.ID%TYPE,
                                      p_TOKEN IN USUARIO.TOKEN%TYPE) AS
  
  l_cursor SYS_REFCURSOR;
  BEGIN
    IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
        OPEN l_cursor FOR 
        SELECT *
        FROM competencia 
        ORDER BY ID;
        APEX_JSON.open_object;
        APEX_JSON.write('competencias', l_cursor);
        APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;    
  END SP_COMPETENCIA_READ_ALL;
  PROCEDURE SP_COMPETENCIA_READ_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                    p_TOKEN IN USUARIO.TOKEN%TYPE,
                                    p_id IN COMPETENCIA.ID%TYPE) AS
                                    l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    OPEN l_cursor FOR 
    SELECT ID ,
           NOMBRE,
           DESCRIPCION,
           SIGLA,
           N_OPTIMO,
           CREADO,
           MODIFICADO,
           ACTIVO  
    FROM COMPETENCIA 
    WHERE ID = p_id;
    APEX_JSON.open_object;
    APEX_JSON.write('competencia', l_cursor);
    APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;    
    
  END SP_COMPETENCIA_READ_ID;
  
  PROCEDURE SP_COMPETENCIA_READ_NOMBRE ( p_IDUSUARIO IN USUARIO.ID%TYPE,
                                         p_TOKEN IN USUARIO.TOKEN%TYPE,
                                         p_nombre IN COMPETENCIA.NOMBRE%TYPE) AS
                                         l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    OPEN l_cursor FOR 
    SELECT ID ,
           NOMBRE,
           DESCRIPCION,
           SIGLA,
           N_OPTIMO,
           CREADO,
           MODIFICADO,
           ACTIVO  
    FROM COMPETENCIA 
    WHERE NOMBRE = UPPER(p_nombre);
    APEX_JSON.open_object;
    APEX_JSON.write('competencia', l_cursor);
    APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;    
    
  
  END SP_COMPETENCIA_READ_NOMBRE;
  PROCEDURE SP_COMPETENCIA_READ_SIGLA (
                                      p_IDUSUARIO IN USUARIO.ID%TYPE,
                                      p_TOKEN IN USUARIO.TOKEN%TYPE,
                                      p_SIGLA IN COMPETENCIA.SIGLA%TYPE) AS
                                         l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    OPEN l_cursor FOR 
    SELECT ID ,
           NOMBRE,
           DESCRIPCION,
           SIGLA,
           N_OPTIMO,
           CREADO,
           MODIFICADO,
           ACTIVO  
    FROM COMPETENCIA 
    WHERE SIGLA = UPPER(p_sigla);
    APEX_JSON.open_object;
    APEX_JSON.write('competencia', l_cursor);
    APEX_JSON.close_object;  
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;                                      
  
  END SP_COMPETENCIA_READ_SIGLA;
  PROCEDURE SP_COMPETENCIA_UPDATE (
                  p_IDUSUARIO IN USUARIO.ID%TYPE,
                                    p_TOKEN IN USUARIO.TOKEN%TYPE, 
                  p_id IN COMPETENCIA.ID%TYPE,
                  p_NOMBRE IN COMPETENCIA.NOMBRE%TYPE,
                  p_DESCRIPCION IN COMPETENCIA.DESCRIPCION%TYPE,
                  p_SIGLA IN COMPETENCIA.SIGLA%TYPE,
                                    p_NOPTIMO IN COMPETENCIA.N_OPTIMO%TYPE) IS
  total number;
  l_cursor SYS_REFCURSOR;
  
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 then
    total := 0;
    l_cursor := null;
      SELECT COUNT(*) INTO total FROM COMPETENCIA WHERE id = p_id;
  
    IF total > 0 THEN
    UPDATE COMPETENCIA SET
           NOMBRE = UPPER(p_NOMBRE),
           DESCRIPCION = UPPER(p_DESCRIPCION),
           SIGLA = UPPER(p_SIGLA),
           N_OPTIMO = p_NOPTIMO,
           MODIFICADO=SYSDATE
      WHERE ID=p_id;
    COMMIT;
    OPEN l_cursor FOR
    SELECT *
    FROM  COMPETENCIA   
    WHERE id = p_id;
    APEX_JSON.open_object;
    APEX_JSON.write('competencia', l_cursor);
    APEX_JSON.close_object;       
  END IF;
       
   ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;  
  END IF;
  END SP_COMPETENCIA_UPDATE;
  
  PROCEDURE SP_COMPETENCIA_DELETE_ID (p_IDUSUARIO IN USUARIO.ID%TYPE,
                                     p_TOKEN IN USUARIO.TOKEN%TYPE, 
                                     p_id IN COMPETENCIA.ID%TYPE) AS
                                     l_cursor SYS_REFCURSOR;
  BEGIN
  IF FN_TOKEN(p_IDUSUARIO,p_TOKEN)=1 THEN
    UPDATE COMPETENCIA SET ACTIVO = 0, DESACTIVADO = SYSDATE WHERE ID = p_id;
    COMMIT;
    OPEN l_cursor FOR
        SELECT *
        FROM   COMPETENCIA c
        WHERE  c.id = DECODE(p_ID, NULL, c.id, p_ID);
 
          APEX_JSON.open_object;
          APEX_JSON.write('competencia', l_cursor);
          APEX_JSON.close_object;
     ELSE
  
      OPEN l_cursor FOR
      SELECT 'ACCESO NO AUTORIZADO' AS ADVERTENCIA FROM DUAL;
      APEX_JSON.open_object;
      APEX_JSON.write('RESPUESTA', l_cursor);
      APEX_JSON.close_object;
  
  END IF; 
 
  END SP_COMPETENCIA_DELETE_ID;
END PKG_COMPETENCIA;
/
--set define off verify off feedback off
--whenever sqlerror exit sql.sqlcode rollback
--------------------------------------------------------------------------------
--
-- ORACLE Application Express (APEX) export file
--
-- You should run the script connected to SQL*Plus as the Oracle user
-- APEX_050000 or as the owner (parsing schema) of the application.
--
-- NOTE: Calls to apex_application_install override the defaults below.
--
--------------------------------------------------------------------------------
begin
wwv_flow_api.import_begin (
 p_version_yyyy_mm_dd=>'2013.01.01'
,p_default_workspace_id=>100000
);
end;
/
--prompt  Set Application Offset...
begin
   -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5762526630276185)
,p_name=>'competencia'
);
 
end;
/
--prompt --application/restful_services/competencia
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5762526630276185)
,p_name=>'competencia'
,p_uri_prefix=>'competencia/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>41
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5762600314278055)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/create'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5767284600277045)
,p_template_id=>wwv_flow_api.id(5762600314278055)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_CREATE(:idusuario,:token,:nombre,:descripcion,:sigla,:noptimo);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5767378661301354)
,p_handler_id=>wwv_flow_api.id(5767284600277045)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5767472147302261)
,p_handler_id=>wwv_flow_api.id(5767284600277045)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771725559315857)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/delete'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5771864440321228)
,p_template_id=>wwv_flow_api.id(5771725559315857)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'DELETE'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_DELETE_ID',
'       (',
'        :idusuario,',
'        :token,',
'        :id',
'       );',
'END;',
''))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5774486274390573)
,p_handler_id=>wwv_flow_api.id(5771864440321228)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5774283468388570)
,p_handler_id=>wwv_flow_api.id(5771864440321228)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5774380264389626)
,p_handler_id=>wwv_flow_api.id(5771864440321228)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771253808308483)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/read_all'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5772059093333736)
,p_template_id=>wwv_flow_api.id(5771253808308483)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_READ_ALL(:idusuario,:token);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772148342334756)
,p_handler_id=>wwv_flow_api.id(5772059093333736)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772263894335684)
,p_handler_id=>wwv_flow_api.id(5772059093333736)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771371351309694)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/read_id'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5772367189341532)
,p_template_id=>wwv_flow_api.id(5771371351309694)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_READ_ID(:idusuario,:token,:id);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772648428345080)
,p_handler_id=>wwv_flow_api.id(5772367189341532)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772466049343116)
,p_handler_id=>wwv_flow_api.id(5772367189341532)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772516757344012)
,p_handler_id=>wwv_flow_api.id(5772367189341532)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771406032312328)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/read_nombre'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5772714641349286)
,p_template_id=>wwv_flow_api.id(5771406032312328)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_READ_NOMBRE(:idusuario,:token,:nombre);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772849479350230)
,p_handler_id=>wwv_flow_api.id(5772714641349286)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773019495352244)
,p_handler_id=>wwv_flow_api.id(5772714641349286)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5772982640351398)
,p_handler_id=>wwv_flow_api.id(5772714641349286)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771586405313238)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/read_sigla'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5773166969356306)
,p_template_id=>wwv_flow_api.id(5771586405313238)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_READ_SIGLA(:idusuario,:token,:sigla);END;'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773226690357173)
,p_handler_id=>wwv_flow_api.id(5773166969356306)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773405121358835)
,p_handler_id=>wwv_flow_api.id(5773166969356306)
,p_name=>'sigla'
,p_bind_variable_name=>'sigla'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773326881358002)
,p_handler_id=>wwv_flow_api.id(5773166969356306)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5771613239314981)
,p_module_id=>wwv_flow_api.id(5762526630276185)
,p_uri_template=>'json/update'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5771976661326208)
,p_template_id=>wwv_flow_api.id(5771613239314981)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'PUT'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'BEGIN PKG_COMPETENCIA.SP_COMPETENCIA_UPDATE',
'        (',
'         :idusuario,',
'         :token,',
'         :id,',
'         :nombre,',
'         :descripcion,',
'         :sigla,',
'         :noptimo',
'        );',
'END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773961458377064)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'descripcion'
,p_bind_variable_name=>'descripcion'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773760718375008)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'id'
,p_bind_variable_name=>'id'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773582528373097)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'idusuario'
,p_bind_variable_name=>'idusuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773841976375984)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'nombre'
,p_bind_variable_name=>'nombre'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5774185097379142)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'noptimo'
,p_bind_variable_name=>'noptimo'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5774023871378006)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'sigla'
,p_bind_variable_name=>'sigla'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5773648197374019)
,p_handler_id=>wwv_flow_api.id(5771976661326208)
,p_name=>'token'
,p_bind_variable_name=>'token'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
end;
/
begin
wwv_flow_api.import_end(p_auto_install_sup_obj => nvl(wwv_flow_application_install.get_auto_install_sup_obj, false));
commit;
end;
/
--set verify on feedback on define on
--prompt  ...done


CREATE UNIQUE INDEX "CONSTRAINT_USUARIO" ON "USUARIO" ("RUT");
CREATE UNIQUE INDEX "CONSTRAINT_ROL" ON "ROL" ("NOMBRE");
CREATE UNIQUE INDEX "AREA_NOMBRE" ON "AREA" ("NOMBRE");
CREATE UNIQUE INDEX "AREA_SIGLA" ON "AREA" ("SIGLA");
CREATE UNIQUE INDEX "COMP_NOM" ON "COMPETENCIA" ("NOMBRE");
CREATE UNIQUE INDEX "COMP_SIGLA" ON "COMPETENCIA" ("SIGLA");
ALTER TABLE "NIVEL" ADD CONSTRAINT "CONST_NIVEL" CHECK (nota BETWEEN 0 and 5) ENABLE;
Insert into ROL (ID,NOMBRE,CREADO,MODIFICADO,DESACTIVADO,ACTIVO) values ('1','ADMINISTRADOR',to_date('16/10/16','DD/MM/RR'),to_date('16/10/16','DD/MM/RR'),null,'1');
Insert into ROL (ID,NOMBRE,CREADO,MODIFICADO,DESACTIVADO,ACTIVO) values ('2','JEFE',to_date('16/10/16','DD/MM/RR'),to_date('16/10/16','DD/MM/RR'),null,'1');
Insert into ROL (ID,NOMBRE,CREADO,MODIFICADO,DESACTIVADO,ACTIVO) values ('3','FUNCIONARIO',to_date('16/10/16','DD/MM/RR'),to_date('16/10/16','DD/MM/RR'),null,'1');
Insert into USUARIO (ID,RUT,RUT_JEFE,CLAVE,TOKEN,NOMBRE,APELLIDO,EMAIL,SEXO,FONO,ROL_ID,CREADO,MODIFICADO,DESACTIVADO,ACTIVO) values ('1','14.181.477-K','1','B7E756AA2B61A06941CF227AED57F626',null,'VITORIO','CERDA','VI@TO.IO','M','987654321','1',to_date('16/10/16','DD/MM/RR'),to_date('16/10/16','DD/MM/RR'),null,'1');
