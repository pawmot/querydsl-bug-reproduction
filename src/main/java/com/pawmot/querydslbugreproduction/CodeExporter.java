package com.pawmot.querydslbugreproduction;

import com.querydsl.sql.codegen.MetaDataExporter;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
final class CodeExporter {
    void export(Connection connection) throws SQLException {
        MetaDataExporter exporter = new MetaDataExporter();
        exporter.setPackageName("com.pawmot.querydslbugreproduction");
        exporter.setTargetFolder(new File("src/gen/java"));
        exporter.export(connection.getMetaData());
    }
}
