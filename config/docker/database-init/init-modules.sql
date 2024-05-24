-- Postgres Docker image automatically creates the "modules" database including "modules" role.

--DROP   DATABASE IF EXISTS modules;
--CREATE DATABASE           modules;
--
--DROP   ROLE IF EXISTS modules;
--CREATE ROLE           modules WITH
--	SUPERUSER
--	CREATEDB
--	CREATEROLE
--	INHERIT
--	LOGIN
--	REPLICATION
--	BYPASSRLS
--	CONNECTION LIMIT -1;

DROP   SCHEMA IF EXISTS item;
CREATE SCHEMA           item      AUTHORIZATION modules;

DROP   SCHEMA IF EXISTS inventory;
CREATE SCHEMA           inventory AUTHORIZATION modules;