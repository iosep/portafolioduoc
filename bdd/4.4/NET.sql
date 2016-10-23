CREATE TABLE AplicacionPerfil
  (
    IdApp       NUMBER(10,0) NOT NULL,
    IdPerfil    NUMBER(10,0) NOT NULL,
    UserCreated VARCHAR2(50) ,
    DateCreated DATE ,
    UserUpdated VARCHAR2(50) ,
    DateUpdated DATE ,
    PRIMARY KEY( IdApp,IdPerfil)
  );
  
CREATE TABLE Aplicacion
  (
    IdApp       NUMBER(10,0) NOT NULL,
    App         VARCHAR2(200) NOT NULL,
    NomApp      VARCHAR2(500) ,
    IdPadre     NUMBER(10,0) NOT NULL,
    Tipo        VARCHAR2(50) ,
    Url         VARCHAR2(500) ,
    Prioridad   NUMBER(10,0) ,
    Activo      CHAR(1) ,
    AccionFlujo VARCHAR2(50) ,
    UserCreated VARCHAR2(50) ,
    DateCreated DATE ,
    UserUpdated VARCHAR2(50) ,
    DateUpdated DATE ,
    PRIMARY KEY( IdApp )
  );
  
CREATE TABLE Sesion
  (
    sesID                   VARCHAR2(50) NOT NULL,
    usuID                   NUMBER(10,0) NOT NULL,
    ses_fecha               DATE NOT NULL,
    ses_remote_addr         VARCHAR2(100) ,
    ses_remote_user         VARCHAR2(100) ,
    ses_remote_host         VARCHAR2(100) ,
    ses_user_agent          VARCHAR2(250) ,
    ses_language            VARCHAR2(250) ,
    ses_referer             VARCHAR2(1000) ,
    ses_cookie              VARCHAR2(1000) ,
    ses_host                VARCHAR2(250) ,
    ses_estado              VARCHAR2(20) NOT NULL,
    ses_ultimo_ingreso      DATE ,
    ses_ultima_fecha_activa DATE ,
    ses_ultima_validacion   DATE ,
    ses_fecha_actividad     DATE ,
    ses_fecha_expiracion    DATE ,
    PRIMARY KEY( sesID )
  );
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('0','.0.','SistemaEncuesta','-1','Sitio','#','1','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('1','.0.1.','Administración','0','Menu','#','1','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('2','.0.2.','Encuesta','0','Menu','#','2','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('3','.0.3.','Reportes','0','Menu','#','3','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('11','.0.1.11.','Usuario','1','Menu','frm_usuario.aspx','1','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('12','.0.1.12.','Area','1','Menu','frm_area.aspx','2','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('13','.0.1.13.','Competencia','1','Menu','frm_competencia.aspx','3','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('14','.0.1.14.','Periodo','1','Menu','frm_Periodo.aspx','4','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('15','.0.1.15.','Nivel','1','Menu','frm_Nivel.aspx','5','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('16','.0.1.16.','Pregunta','1','Menu','frm_Pregunta.aspx','6','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('17','.0.1.17.','Respuesta','1','Menu','frm_Respuesta.aspx','7','A',null,null,null,null,null);
Insert into APLICACION (IDAPP,APP,NOMAPP,IDPADRE,TIPO,URL,PRIORIDAD,ACTIVO,ACCIONFLUJO,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('18','.0.1.18.','Observaciones','1','Menu','frm_Observaciones.aspx','8','A',null,null,null,null,null);

Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('0','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('0','2',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('1','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('1','2',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('2','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('2','2',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('3','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('3','2',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('11','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('12','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('13','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('14','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('15','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('16','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('17','1',null,null,null,null);
Insert into APLICACIONPERFIL (IDAPP,IDPERFIL,USERCREATED,DATECREATED,USERUPDATED,DATEUPDATED) values ('18','1',null,null,null,null);

create or replace PACKAGE PKG_NET  
AS   
PROCEDURE STI_S_AppPorPerfil  
(  
    v_Perfil NUMBER,  
    v_Tipo   VARCHAR2,  
    v_App    VARCHAR2  
);  
   
PROCEDURE STI_SESION  
(  
    v_Tipo               VARCHAR2,  
    v_usuario            VARCHAR2,  
    v_clave              VARCHAR2,	  
    v_ses_remote_addr    VARCHAR2,  
    v_ses_remote_user    VARCHAR2,  
    v_ses_remote_host    VARCHAR2,  
    v_ses_user_agent     VARCHAR2,  
    v_ses_language       VARCHAR2,  
    v_ses_referer        VARCHAR2,  
    v_ses_cookie         VARCHAR2,  
    v_ses_host           VARCHAR2,  
    v_sesID              VARCHAR2,  
    v_usuID              NUMBER,    
    v_usu_ultimo_ingreso DATE  
);  

FUNCTION FN_HASH
(
  v_usuario in varchar2, 
  v_clave in varchar2
) RETURN VARCHAR2;
  
END PKG_NET;  
 
/
create or replace PACKAGE BODY PKG_NET AS 
  
PROCEDURE STI_S_AppPorPerfil 
( 
    v_Perfil NUMBER, 
    v_Tipo   VARCHAR2, 
    v_App    VARCHAR2 
) 
is  
l_cursor SYS_REFCURSOR;  
BEGIN 
	--IF v_Perfil = '0' THEN 
		--v_Perfil := NULL ; 
	--END IF; 
	OPEN l_cursor FOR 
	SELECT a.IdApp , 
		  App , 
		  a.NomApp , 
		  IdPadre , 
		  a.Tipo , 
		  Url , 
		  IdPerfil  
	FROM Aplicacion a  
		JOIN AplicacionPerfil p ON a.IdApp = p.IdApp  
	WHERE a.Activo = 'A'  
		AND ( p.IdPerfil = v_Perfil )  
		AND ( App LIKE '%.' || v_App || '.%' OR v_App IS NULL )  
		AND ( a.Tipo = v_Tipo OR a.Tipo = 'Sitio' OR v_Tipo IS NULL )  
	ORDER BY Prioridad,IdApp ; 
	APEX_JSON.open_object;  
	APEX_JSON.write('Datos', l_cursor);  
	APEX_JSON.close_object; 
END STI_S_AppPorPerfil; 
  
PROCEDURE STI_SESION 
( 
    v_Tipo               VARCHAR2, 
    v_usuario            VARCHAR2, 
    v_clave              VARCHAR2,	 
    v_ses_remote_addr    VARCHAR2, 
    v_ses_remote_user    VARCHAR2, 
    v_ses_remote_host    VARCHAR2, 
    v_ses_user_agent     VARCHAR2, 
    v_ses_language       VARCHAR2, 
    v_ses_referer        VARCHAR2, 
    v_ses_cookie         VARCHAR2, 
    v_ses_host           VARCHAR2, 
    v_sesID              VARCHAR2, 
    v_usuID              NUMBER,   
    v_usu_ultimo_ingreso DATE
)  
is  
l_cursor SYS_REFCURSOR;
clave_hash varchar2(4000);
begin 
clave_hash:=Fn_Hash(v_usuario,v_clave);
	IF v_Tipo = 'Crear' THEN 
    DECLARE V_COUNT NUMBER(10,0); 
    v_usuID2 number(10, 0); 
    v_sesID2 raw(3000); 
		BEGIN 
      SELECT COUNT(1) INTO V_COUNT  
          FROM Usuario U 
          INNER JOIN ROL ON U.ROL_ID = ROL.ID 
          WHERE U.RUT = v_usuario 
					--AND 1 = pwdcompare(v_clave, usu_contrasena, 0) 
					AND U.CLAVE = clave_hash 
					AND U.ACTIVO = 1; 
			IF V_COUNT = 0 THEN 
				BEGIN 
				--registrar login fallido 
					INSERT INTO Sesion 
					( 
						sesID, 
						usuID, 
						ses_fecha, 
						ses_remote_addr, 
						ses_remote_user, 
						ses_remote_host, 
						ses_user_agent, 
						ses_language, 
						ses_referer, 
						ses_cookie, 
						ses_host, 
						ses_estado, 
						ses_ultimo_ingreso 
					) 
					VALUES 
					( 
						SYS_GUID(), 
						0, 
						SYSDATE, 
						v_ses_remote_addr, 
						v_ses_remote_user, 
						v_ses_remote_host, 
						v_ses_user_agent, 
						v_ses_language, 
						v_ses_referer, 
						v_ses_cookie, 
						v_ses_host, 
						'ERROR', 
						to_date(v_usu_ultimo_ingreso,'YYYY-MM-DD HH24:MI:SS') 
					); 
					OPEN l_cursor FOR 
					SELECT	'false' as isOK , 
							'Su usuario o clave no son correctos' as MensajeError  
					FROM DUAL; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object;    
					RETURN; 
				END; 
			END IF; 
			SELECT 	U.ID, 
            SYS_GUID()  
      into v_usuID2,  
          v_sesID2 
      FROM Usuario U 
			WHERE U.RUT = v_usuario 
				--AND 1 = pwdcompare(v_clave, usu_contrasena, 0) 
				AND U.CLAVE = clave_hash; 
			IF SQL%ROWCOUNT > 0 THEN 
				BEGIN 
					INSERT 
					INTO Sesion 
					( 
					  sesID, 
					  usuID, 
					  ses_fecha, 
					  ses_remote_addr, 
					  ses_remote_user, 
					  ses_remote_host, 
					  ses_user_agent, 
					  ses_language, 
					  ses_referer, 
					  ses_cookie, 
					  ses_host, 
					  ses_estado, 
					  ses_ultimo_ingreso 
					) 
					VALUES 
					( 
					  v_sesID2, 
					  v_usuID2, 
					  SYSDATE, 
					  v_ses_remote_addr, 
					  v_ses_remote_user, 
					  v_ses_remote_host, 
					  v_ses_user_agent, 
					  v_ses_language, 
					  v_ses_referer, 
					  v_ses_cookie, 
					  v_ses_host, 
					  'ACTIVO', 
					 to_date(v_usu_ultimo_ingreso,'YYYY-MM-DD HH24:MI:SS') 
					); 
					OPEN l_cursor FOR 
					SELECT	v_usuID2 as usuID, 
							v_sesID2 as sesID, 
							'true' as isOK , 
							'Acceso Autorizado' as Mensaje  
					FROM DUAL; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object;    
					RETURN; 
				END; 
			  END IF; 
		END; 
	END IF; 
	IF v_Tipo = 'Validar' THEN 
    DECLARE 
    v_FechaActividadB DATE := ''; 
    v_FechaExpiracionB DATE := ''; 
    v_MinutosActividadB NUMBER(10,0) := 45; 
    v_MinutosExpiracionB NUMBER(10,0) := 10; 
    V_COUNT NUMBER(10,0); 
		BEGIN 
      SELECT COUNT(1) INTO V_COUNT FROM Sesion WHERE sesID = v_sesID; 
			IF V_COUNT = 0 THEN 
				BEGIN 
					OPEN l_cursor FOR 
					SELECT	'false' as isOK , 
							'La sesión no existe.' as MensajeError  
					FROM DUAL; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object; 
					RETURN; 
				END; 
			END IF; 
			 
      SELECT to_char((to_date(ses_fecha_actividad,'YYYY-MM-DD HH24:MI:SS')+INTERVAL '45' MINUTE),'HH24:MI:SS'), 
            to_char((to_date(ses_fecha_expiracion,'YYYY-MM-DD HH24:MI:SS')+INTERVAL '10' MINUTE),'HH24:MI:SS') 
					INTO v_FechaActividadB, 
               v_FechaExpiracionB       
      FROM Sesion 
			WHERE sesID = v_sesID; 
       
			/*SELECT NVL(ses_fecha_actividad, utils.dateadd('MINUTE', NVL(v_MinutosActividadB, 5), SYSDATE)) , 
              NVL(ses_fecha_expiracion, utils.dateadd('MINUTE', NVL(v_MinutosExpiracionB, 45), SYSDATE)) 
					INTO v_FechaActividadB, 
               v_FechaExpiracionB 
			FROM Sesion 
			WHERE sesID = v_sesID;*/ 
			 
			IF ( SYSDATE >= v_FechaExpiracionB ) THEN 
				BEGIN 
					DBMS_OUTPUT.PUT_LINE(('PASARON 45 MINUTOS')); 
					OPEN l_cursor FOR 
					SELECT 	'false' AS isOK , 
							'Expiro el tiempo límite' AS MensajeError  
					FROM DUAL ; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object; 
					RETURN; 
				END; 
			END IF; 
			IF ( SYSDATE >= v_FechaActividadB ) THEN 
				BEGIN 
					DBMS_OUTPUT.PUT_LINE(('PASARON 5 MINUTOS')); 
					OPEN l_cursor FOR 
					SELECT 'false' AS isOK , 
							'La sesión fue expirada por que no hubo actividad' MensajeError  
					FROM DUAL ; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object; 
					RETURN; 
				END; 
			END IF; 
       
      --SELECT to_char((to_date(ses_fecha_actividad,'YYYY-MM-DD HH12:MI:SS')+INTERVAL '15' MINUTE),'HH12:MI:SS') FROM Sesion; 
			 
			UPDATE 	Sesion 
			SET 	ses_fecha_actividad = to_char((to_date(SYSDATE,'YYYY-MM-DD HH24:MI:SS')+INTERVAL '15' MINUTE),'HH24:MI:SS'), 
            ses_fecha_expiracion = to_char((to_date(SYSDATE,'YYYY-MM-DD HH24:MI:SS')+INTERVAL '45' MINUTE),'HH24:MI:SS') 
          --utils.dateadd('MINUTE', NVL(v_MinutosActividadB, 5), SYSDATE), 
					--NVL(ses_fecha_expiracion, utils.dateadd('MINUTE', NVL(v_MinutosExpiracionB, 45), SYSDATE)) 
					--into ses_fecha_actividad, ses_fecha_expiracion  
			WHERE 	sesID = v_sesID; 
       
      SELECT COUNT(1) INTO V_COUNT 
				FROM Sesion S 
					JOIN Usuario U ON S.usuID = U.id 
				WHERE sesID = v_sesID 
					AND U.activo = 0 
					AND S.ses_remote_addr = v_ses_remote_addr 
					AND S.ses_estado = 'ACTIVO'; 
       
			IF V_COUNT = 0 THEN 
				BEGIN 
					OPEN l_cursor FOR 
					SELECT 	'false' AS isOK , 
							'La sesión caducada.' AS MensajeError  
					FROM DUAL ; 
					APEX_JSON.open_object;  
					APEX_JSON.write('Datos', l_cursor);  
					APEX_JSON.close_object; 
					RETURN; 
				END; 
			END IF; 
			OPEN l_cursor FOR 
			SELECT 	'true' AS isOK , 
					' ' AS MensajeError , 
					sesID AS sesID , 
					S.usuID AS usuID , 
					u.rol_id AS perID , 
					LTRIM(RTRIM(NVL(U.nombre, ' '))) || ' ' || LTRIM(RTRIM(NVL(U.apellido, ' ')))  AS Nombre , 
					LTRIM(RTRIM(NVL(U.nombre, ' '))) || ' ' || LTRIM(RTRIM(NVL(U.apellido, ' '))) AS NombreCorto , 
					R.nombre PerNombre  
			FROM 	Sesion S  
				JOIN Usuario U ON ( S.usuID = U.id )  
				JOIN rol R ON ( U.rol_id = R.id )  
			WHERE sesID = v_sesID  
				AND U.activo = 0 
				AND S.ses_remote_addr = v_ses_remote_addr  
				AND S.ses_estado = 'ACTIVO' ; 
			APEX_JSON.open_object;  
			APEX_JSON.write('Datos', l_cursor);  
			APEX_JSON.close_object; 
       
			UPDATE Sesion  
				SET ses_ultima_validacion = SYSDATE  
			WHERE sesID = v_sesID; 
		END; 
	END IF; 
	IF v_Tipo = 'CerrarSesion' THEN 
		BEGIN 
			UPDATE Sesion  
				SET ses_estado = 'FINALIZADA'  
			WHERE sesID = v_sesID; 
			OPEN l_cursor FOR 
			SELECT v_sesID as sesID FROM DUAL ; 
			APEX_JSON.open_object;  
			APEX_JSON.write('Datos', l_cursor);  
			APEX_JSON.close_object; 
		END; 
	END IF; 
	IF v_Tipo = 'InactivaSesionModulo' THEN 
    declare v_count number(10, 0); 
		BEGIN 
		  UPDATE Sesion  
        SET ses_estado = 'INACTIVO'  
      WHERE sesID = v_sesID; 
       
      v_count := SQL%ROWCOUNT; 
       
      OPEN l_cursor FOR 
      SELECT v_count as Resultado  
      FROM DUAL; 
      APEX_JSON.open_object;  
      APEX_JSON.write('Datos', l_cursor);  
      APEX_JSON.close_object; 
		END; 
	END IF; 
END STI_SESION; 

--------FUNCION PARA CREAR EL HASH DE LA CLAVE DEL USUARIO  
FUNCTION FN_HASH (v_usuario in varchar2, v_clave in varchar2) RETURN VARCHAR2 is
   l_password VARCHAR2(4000);
   l_salt VARCHAR2(4000) := 'ISYmHMtSrjFmT2nEZUvEU5LA3jrV3i';
BEGIN
   l_password := utl_raw.cast_to_raw(dbms_obfuscation_toolkit.md5(input_string => v_clave || substr(l_salt,10,13) || v_usuario || substr(l_salt, 4,10)));
   RETURN l_password;
END;

END PKG_NET; 
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
   -- -- SET APPLICATION OFFSET
   wwv_flow_api.g_id_offset := nvl(wwv_flow_application_install.get_offset,0);
null;
end;
/
begin
wwv_flow_api.remove_restful_service(
 p_id=>wwv_flow_api.id(5987339235803154)
,p_name=>'net'
);
 
end;
/
--prompt --application/restful_services/net
begin
wwv_flow_api.create_restful_module(
 p_id=>wwv_flow_api.id(5987339235803154)
,p_name=>'net'
,p_uri_prefix=>'net/'
,p_parsing_schema=>'WFBS'
,p_items_per_page=>25
,p_status=>'PUBLISHED'
,p_row_version_number=>1
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5987963592910548)
,p_module_id=>wwv_flow_api.id(5987339235803154)
,p_uri_template=>'json/STI_SESION'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5988034815922247)
,p_template_id=>wwv_flow_api.id(5987963592910548)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'DECLARE',
'    v_Tipo               VARCHAR2(255):=NULL;',
'    v_usuario            VARCHAR2(255):=NULL;',
'    v_clave              VARCHAR2(255):=NULL;',
'    v_ses_remote_addr    VARCHAR2(255):=NULL;',
'    v_ses_remote_user    VARCHAR2(255):=NULL;',
'    v_ses_remote_host    VARCHAR2(255):=NULL;',
'    v_ses_user_agent     VARCHAR2(255):=NULL;',
'    v_ses_language       VARCHAR2(255):=NULL;',
'    v_ses_referer        VARCHAR2(255):=NULL;',
'    v_ses_cookie         VARCHAR2(255):=NULL;',
'    v_ses_host           VARCHAR2(255):=NULL;',
'    v_sesID              VARCHAR2(255):=NULL;',
'    v_usuID              NUMBER;  ',
'    v_usu_ultimo_ingreso DATE; ',
'BEGIN PKG_NET.STI_SESION',
'(',
'    :v_Tipo,               ',
'    :v_usuario,            ',
'    :v_clave,              ',
'    :v_ses_remote_addr,    ',
'    :v_ses_remote_user,    ',
'    :v_ses_remote_host,    ',
'    :v_ses_user_agent,     ',
'    :v_ses_language,       ',
'    :v_ses_referer,        ',
'    :v_ses_cookie,         ',
'    :v_ses_host,           ',
'    :v_sesID,              ',
'    :v_usuID,                ',
'    :v_usu_ultimo_ingreso ',
');',
'END;    '))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988145162945312)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_Tipo'
,p_bind_variable_name=>'v_Tipo'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988324008949397)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_clave'
,p_bind_variable_name=>'v_clave'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5989200476959776)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_sesID'
,p_bind_variable_name=>'v_sesID'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5989057572957734)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_cookie'
,p_bind_variable_name=>'v_ses_cookie'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5989183898958888)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_host'
,p_bind_variable_name=>'v_ses_host'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988852431955520)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_language'
,p_bind_variable_name=>'v_ses_language'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988918304956792)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_referer'
,p_bind_variable_name=>'v_ses_referer'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988491266950485)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_remote_addr'
,p_bind_variable_name=>'v_ses_remote_addr'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988636527952965)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_remote_host'
,p_bind_variable_name=>'v_ses_remote_host'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988592971951635)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_remote_user'
,p_bind_variable_name=>'v_ses_remote_user'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988730806954423)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_ses_user_agent'
,p_bind_variable_name=>'v_ses_user_agent'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5989324639960936)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_usuID'
,p_bind_variable_name=>'v_usuID'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'INT'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5989461906961934)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_usu_ultimo_ingreso'
,p_bind_variable_name=>'v_usu_ultimo_ingreso'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5988296285948195)
,p_handler_id=>wwv_flow_api.id(5988034815922247)
,p_name=>'v_usuario'
,p_bind_variable_name=>'v_usuario'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_template(
 p_id=>wwv_flow_api.id(5987486796805555)
,p_module_id=>wwv_flow_api.id(5987339235803154)
,p_uri_template=>'json/STI_S_AppPorPerfil'
,p_priority=>0
,p_etag_type=>'HASH'
);
wwv_flow_api.create_restful_handler(
 p_id=>wwv_flow_api.id(5987574749826272)
,p_template_id=>wwv_flow_api.id(5987486796805555)
,p_source_type=>'PLSQL'
,p_format=>'DEFAULT'
,p_method=>'POST'
,p_require_https=>'NO'
,p_source=>wwv_flow_utilities.join(wwv_flow_t_varchar2(
'DECLARE',
'    v_Perfil NUMBER:=0;',
'    v_Tipo   VARCHAR2(255):=NULL;',
'    v_App    VARCHAR2(255):=NULL;',
'    ',
'BEGIN PKG_NET.STI_S_AppPorPerfil',
'(',
'    :v_Perfil,',
'    :v_Tipo,',
'    :v_App',
');',
'END;'))
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5987848482838942)
,p_handler_id=>wwv_flow_api.id(5987574749826272)
,p_name=>'v_App'
,p_bind_variable_name=>'v_App'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5987687073828498)
,p_handler_id=>wwv_flow_api.id(5987574749826272)
,p_name=>'v_Perfil'
,p_bind_variable_name=>'v_Perfil'
,p_source_type=>'HEADER'
,p_access_method=>'IN'
,p_param_type=>'STRING'
);
wwv_flow_api.create_restful_param(
 p_id=>wwv_flow_api.id(5987726743829384)
,p_handler_id=>wwv_flow_api.id(5987574749826272)
,p_name=>'v_Tipo'
,p_bind_variable_name=>'v_Tipo'
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
