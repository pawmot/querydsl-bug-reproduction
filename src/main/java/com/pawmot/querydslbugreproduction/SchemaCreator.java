package com.pawmot.querydslbugreproduction;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
final class SchemaCreator {
    void create(Connection connection) throws SQLException {
        try(val statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE dict (id SMALLINT PRIMARY KEY, entry VARCHAR(10) NOT NULL UNIQUE)");

            statement.execute(
                    "CREATE TABLE test (id SERIAL PRIMARY KEY, ref SMALLINT NULL REFERENCES dict(id), name VARCHAR(100), number1 SMALLINT, number2 INTEGER)");
        }
    }
}
