-- Postgres Docker image automatically creates the "test" database including "test" role.

--DROP   DATABASE IF EXISTS test;
--CREATE DATABASE           test;
--
--DROP   ROLE IF EXISTS test;
--CREATE ROLE           test WITH
--	SUPERUSER
--	CREATEDB
--	CREATEROLE
--	INHERIT
--	LOGIN
--	REPLICATION
--	BYPASSRLS
--	CONNECTION LIMIT -1;

DROP   SCHEMA IF EXISTS test;
CREATE SCHEMA           test AUTHORIZATION test;