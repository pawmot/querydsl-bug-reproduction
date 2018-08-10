package com.pawmot.querydslbugreproduction;

import lombok.val;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.DriverManager;
import java.sql.SQLException;

class CreateQuerydslMetadataClasses {

    public static void main(String[] args) throws SQLException {
        try (val dbContainer = new PostgreSQLContainer("postgres:10-alpine")) {
            dbContainer.start();
            try (val connection = DriverManager.getConnection(dbContainer.getJdbcUrl(), dbContainer.getUsername(), dbContainer.getPassword())) {
                SchemaCreator.create(connection);
                CodeExporter.export(connection);
            }
            dbContainer.stop();
        }
    }
}
