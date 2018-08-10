package com.pawmot.querydslbugreproduction;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.postgresql.PostgreSQLQueryFactory;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.DriverManager;
import java.sql.SQLException;

import static com.pawmot.querydslbugreproduction.QDict.dict;
import static com.pawmot.querydslbugreproduction.QTest.test;
import static com.querydsl.sql.SQLExpressions.select;
import static org.assertj.core.api.Assertions.assertThat;

class Replication {

    static PostgreSQLContainer container;
    static PostgreSQLQueryFactory queryFactory;

    @BeforeAll
    public static void setup() throws SQLException {
        container = new PostgreSQLContainer("postgres:10-alpine");
        container.start();
        val conn = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        SchemaCreator.create(conn);
        SQLTemplates templates = PostgreSQLTemplates.builder().build();
        Configuration configuration = new Configuration(templates);
        queryFactory = new PostgreSQLQueryFactory(configuration, () -> conn);
        queryFactory.insert(dict).columns(dict.id, dict.entry).values(0, "A").execute();
        queryFactory.insert(dict).columns(dict.id, dict.entry).values(1, "B").execute();
    }

    @AfterAll
    public static void teardown() {
        container.stop();
    }

    @Test
    public void shouldWorkWhenReferenceIsNullAndNumber1IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(null, "Test", null, 1)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsNullAndNumber2IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(null, "Test", 1, null)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsDirectlySpecifiedAndNumber1IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(0, "Test", null, 1)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsDirectlySpecifiedAndNumber2IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(1, "Test", 1, null)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsSelectedAndNumber1IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(select(dict.id).from(dict).where(dict.entry.eq("A")), "Test", null, 1)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsSelectedAndNumber2IsNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(select(dict.id).from(dict).where(dict.entry.eq("A")), "Test", 1, null)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsSelectedAndBothNumbersAreNull() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.ref, test.name, test.number1, test.number2)
                .values(select(dict.id).from(dict).where(dict.entry.eq("A")), "Test", null, null)
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }

    @Test
    public void shouldWorkWhenReferenceIsSelectedAndBothNumbersAreNullAndReferenceIsLast() {
        val insertedCount = queryFactory.insert(test)
                .columns(test.name, test.number1, test.number2, test.ref)
                .values("Test", null, null, select(dict.id).from(dict).where(dict.entry.eq("A")))
                .execute();

        assertThat(insertedCount).isEqualTo(1);
    }
}
