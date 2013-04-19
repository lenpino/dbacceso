CREATE TABLE PERSONA (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, APELLIDO VARCHAR(255), APELLIDOMAT VARCHAR(255), DV VARCHAR(255), EMAIL VARCHAR(255), FECHANAC DATE, NOMBRE VARCHAR(255), NOMBREAD VARCHAR(255), RUT INTEGER, TELEFONO INTEGER, PRIMARY KEY (ID))
CREATE TABLE CONTACTO (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, CARGO VARCHAR(255), CELULAR INTEGER, EMAIL VARCHAR(255), persona_id INTEGER, UNIDAD_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE UNIDAD (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOMBRE VARCHAR(255), empresa_id INTEGER, unidad_padre_id INTEGER, PRIMARY KEY (ID))
CREATE TABLE EMPRESA (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, DV VARCHAR(255), NOMBREFANTASIA VARCHAR(255), RAZONSOCIAL VARCHAR(255), RUT INTEGER, empresa_padre_id INTEGER, PRIMARY KEY (ID))
CREATE TABLE COMUNA (comuna_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, comuna_nombre VARCHAR(255), comuna_provincia_id INTEGER, PRIMARY KEY (comuna_id))
CREATE TABLE PROVINCIA (provincia_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, provincia_nombre VARCHAR(255), provincia_region_id INTEGER, PRIMARY KEY (provincia_id))
CREATE TABLE REGION (region_id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, region_nombre VARCHAR(255), PRIMARY KEY (region_id))
CREATE TABLE DIRECCION (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, CIUDAD VARCHAR(255), DIRECCION1 VARCHAR(255), DIRECCION2 VARCHAR(255), COMUNA_comuna_id INTEGER, REGION_region_id INTEGER, PRIMARY KEY (ID))
CREATE TABLE EDIFICIO (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOMBRE VARCHAR(255), TIPO VARCHAR(255), cliente_id INTEGER, DIRECCION_ID INTEGER NOT NULL UNIQUE, DIRECTORIO_ID INTEGER NOT NULL UNIQUE, PROPIETARIO_ID INTEGER NOT NULL UNIQUE, PRIMARY KEY (ID))
CREATE TABLE CLIENTE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, ESTADO VARCHAR(255), FECHAINCORPORACION DATE, NUMEROUSUARIOS INTEGER, EMPRESA_ID INTEGER UNIQUE, EMPRESA_FACTURAR_ID INTEGER UNIQUE, PRIMARY KEY (ID))
CREATE TABLE USUARIO (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, CLAVE VARCHAR(255), ESTADO VARCHAR(255), FECHAINGRESO DATE, LOGIN VARCHAR(255), CONTACTO_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE ROL (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOMBRE VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE APLICACION (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOMBRE VARCHAR(255), NOMBRECONTEXTO VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE DEPENDENCIA (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, IDENTIFICADOR VARCHAR(255), PISO INTEGER, RESERVABLE SMALLINT DEFAULT 0, TIPO VARCHAR(255), TIPOUSUARIO VARCHAR(255), dependencia_padre_id INTEGER, EDIFICIO_ID INTEGER, USUARIOINMUEBLE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE RESERVA (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, ESTADO VARCHAR(255), FECHAEMISION DATE, FECHARESERVA DATE, DEPENDENCIA_ID INTEGER NOT NULL, EMISOR_ID INTEGER NOT NULL, VISITA_ID INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE ACCESO (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, FECHAINGRESO DATE, FECHASALIDA DATE, ANFITRION_ID INTEGER NOT NULL, DEPENDENCIA_ID INTEGER NOT NULL, VISITA_ID INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE DIRECTORIO (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, PRIMARY KEY (ID))
CREATE TABLE USUARIOINMUEBLE (ID INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL, NOMBRE VARCHAR(255), TIPOUSO VARCHAR(255), DIRECTORIO_ID INTEGER, EMPRESA_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CLIENTE_APLICACION (CLIENTE_ID INTEGER NOT NULL, APLICACIONES_ID INTEGER NOT NULL, PRIMARY KEY (CLIENTE_ID, APLICACIONES_ID))
CREATE TABLE CLIENTE_USUARIO (Cliente_ID INTEGER NOT NULL, usuarios_ID INTEGER NOT NULL, PRIMARY KEY (Cliente_ID, usuarios_ID))
CREATE TABLE USUARIO_ROL (USUARIO_ID INTEGER NOT NULL, ROLES_ID INTEGER NOT NULL, PRIMARY KEY (USUARIO_ID, ROLES_ID))
CREATE TABLE ROL_USUARIO (ROL_ID INTEGER NOT NULL, USUARIOS_ID INTEGER NOT NULL, PRIMARY KEY (ROL_ID, USUARIOS_ID))
CREATE TABLE APLICACION_CLIENTE (APLICACION_ID INTEGER NOT NULL, CLIENTES_ID INTEGER NOT NULL, PRIMARY KEY (APLICACION_ID, CLIENTES_ID))
ALTER TABLE CONTACTO ADD CONSTRAINT CONTACTOpersona_id FOREIGN KEY (persona_id) REFERENCES PERSONA (ID)
ALTER TABLE CONTACTO ADD CONSTRAINT CONTACTO_UNIDAD_ID FOREIGN KEY (UNIDAD_ID) REFERENCES UNIDAD (ID)
ALTER TABLE UNIDAD ADD CONSTRAINT NIDADunidadpadreid FOREIGN KEY (unidad_padre_id) REFERENCES UNIDAD (ID)
ALTER TABLE UNIDAD ADD CONSTRAINT UNIDAD_empresa_id FOREIGN KEY (empresa_id) REFERENCES EMPRESA (ID)
ALTER TABLE EMPRESA ADD CONSTRAINT MPRSAmpresapadreid FOREIGN KEY (empresa_padre_id) REFERENCES EMPRESA (ID)
ALTER TABLE COMUNA ADD CONSTRAINT CMNcmnaprovinciaid FOREIGN KEY (comuna_provincia_id) REFERENCES PROVINCIA (provincia_id)
ALTER TABLE PROVINCIA ADD CONSTRAINT PRVNCprvncregionid FOREIGN KEY (provincia_region_id) REFERENCES REGION (region_id)
ALTER TABLE DIRECCION ADD CONSTRAINT DRCCIONRGNregionid FOREIGN KEY (REGION_region_id) REFERENCES REGION (region_id)
ALTER TABLE DIRECCION ADD CONSTRAINT DRCCIONCMNcomunaid FOREIGN KEY (COMUNA_comuna_id) REFERENCES COMUNA (comuna_id)
ALTER TABLE EDIFICIO ADD CONSTRAINT DFICIOPRPIETARIOID FOREIGN KEY (PROPIETARIO_ID) REFERENCES EMPRESA (ID)
ALTER TABLE EDIFICIO ADD CONSTRAINT DIFICIODIRECCIONID FOREIGN KEY (DIRECCION_ID) REFERENCES DIRECCION (ID)
ALTER TABLE EDIFICIO ADD CONSTRAINT DIFICIODRECTORIOID FOREIGN KEY (DIRECTORIO_ID) REFERENCES DIRECTORIO (ID)
ALTER TABLE EDIFICIO ADD CONSTRAINT EDIFICIOcliente_id FOREIGN KEY (cliente_id) REFERENCES CLIENTE (ID)
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_EMPRESA_ID FOREIGN KEY (EMPRESA_ID) REFERENCES EMPRESA (ID)
ALTER TABLE CLIENTE ADD CONSTRAINT CLNTMPRSFACTURARID FOREIGN KEY (EMPRESA_FACTURAR_ID) REFERENCES EMPRESA (ID)
ALTER TABLE USUARIO ADD CONSTRAINT USUARIOCONTACTO_ID FOREIGN KEY (CONTACTO_ID) REFERENCES CONTACTO (ID)
ALTER TABLE DEPENDENCIA ADD CONSTRAINT DPENDENCIADFICIOID FOREIGN KEY (EDIFICIO_ID) REFERENCES EDIFICIO (ID)
ALTER TABLE DEPENDENCIA ADD CONSTRAINT DPNDNCdpndncpdreid FOREIGN KEY (dependencia_padre_id) REFERENCES DEPENDENCIA (ID)
ALTER TABLE DEPENDENCIA ADD CONSTRAINT DPNDNCASRNMUEBLEID FOREIGN KEY (USUARIOINMUEBLE_ID) REFERENCES USUARIOINMUEBLE (ID)
ALTER TABLE RESERVA ADD CONSTRAINT RSERVADPENDENCIAID FOREIGN KEY (DEPENDENCIA_ID) REFERENCES DEPENDENCIA (ID)
ALTER TABLE RESERVA ADD CONSTRAINT RESERVA_EMISOR_ID FOREIGN KEY (EMISOR_ID) REFERENCES USUARIO (ID)
ALTER TABLE RESERVA ADD CONSTRAINT RESERVA_VISITA_ID FOREIGN KEY (VISITA_ID) REFERENCES CONTACTO (ID)
ALTER TABLE ACCESO ADD CONSTRAINT ACCESOANFITRION_ID FOREIGN KEY (ANFITRION_ID) REFERENCES CONTACTO (ID)
ALTER TABLE ACCESO ADD CONSTRAINT ACCESO_VISITA_ID FOREIGN KEY (VISITA_ID) REFERENCES CONTACTO (ID)
ALTER TABLE ACCESO ADD CONSTRAINT CCESODEPENDENCIAID FOREIGN KEY (DEPENDENCIA_ID) REFERENCES DEPENDENCIA (ID)
ALTER TABLE USUARIOINMUEBLE ADD CONSTRAINT SROINMUEBLEMPRSAID FOREIGN KEY (EMPRESA_ID) REFERENCES EMPRESA (ID)
ALTER TABLE USUARIOINMUEBLE ADD CONSTRAINT SRNMUEBLEDRCTRIOID FOREIGN KEY (DIRECTORIO_ID) REFERENCES DIRECTORIO (ID)
ALTER TABLE CLIENTE_APLICACION ADD CONSTRAINT CLNTPLICACIONCLNTD FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID)
ALTER TABLE CLIENTE_APLICACION ADD CONSTRAINT CLNTPLCCIONPLCCNSD FOREIGN KEY (APLICACIONES_ID) REFERENCES APLICACION (ID)
ALTER TABLE CLIENTE_USUARIO ADD CONSTRAINT CLNTEUSUARIOsrosID FOREIGN KEY (usuarios_ID) REFERENCES USUARIO (ID)
ALTER TABLE CLIENTE_USUARIO ADD CONSTRAINT CLNTUSUARIOClnteID FOREIGN KEY (Cliente_ID) REFERENCES CLIENTE (ID)
ALTER TABLE USUARIO_ROL ADD CONSTRAINT SUARIOROLUSUARIOID FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (ID)
ALTER TABLE USUARIO_ROL ADD CONSTRAINT USUARIOROLROLES_ID FOREIGN KEY (ROLES_ID) REFERENCES ROL (ID)
ALTER TABLE ROL_USUARIO ADD CONSTRAINT ROL_USUARIO_ROL_ID FOREIGN KEY (ROL_ID) REFERENCES ROL (ID)
ALTER TABLE ROL_USUARIO ADD CONSTRAINT RLUSUARIOSUARIOSID FOREIGN KEY (USUARIOS_ID) REFERENCES USUARIO (ID)
ALTER TABLE APLICACION_CLIENTE ADD CONSTRAINT PLCCNCLIENTECLNTSD FOREIGN KEY (CLIENTES_ID) REFERENCES CLIENTE (ID)
ALTER TABLE APLICACION_CLIENTE ADD CONSTRAINT PLCCNCLIENTEPLCCND FOREIGN KEY (APLICACION_ID) REFERENCES APLICACION (ID)