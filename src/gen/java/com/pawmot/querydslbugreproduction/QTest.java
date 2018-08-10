package com.pawmot.querydslbugreproduction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTest is a Querydsl query type for QTest
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTest extends com.querydsl.sql.RelationalPathBase<QTest> {

    private static final long serialVersionUID = 1332668011;

    public static final QTest test = new QTest("test");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Short> number1 = createNumber("number1", Short.class);

    public final NumberPath<Integer> number2 = createNumber("number2", Integer.class);

    public final NumberPath<Short> ref = createNumber("ref", Short.class);

    public final com.querydsl.sql.PrimaryKey<QTest> testPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QDict> testRefFkey = createForeignKey(ref, "id");

    public QTest(String variable) {
        super(QTest.class, forVariable(variable), "public", "test");
        addMetadata();
    }

    public QTest(String variable, String schema, String table) {
        super(QTest.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTest(String variable, String schema) {
        super(QTest.class, forVariable(variable), schema, "test");
        addMetadata();
    }

    public QTest(Path<? extends QTest> path) {
        super(path.getType(), path.getMetadata(), "public", "test");
        addMetadata();
    }

    public QTest(PathMetadata metadata) {
        super(QTest.class, metadata, "public", "test");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(100));
        addMetadata(number1, ColumnMetadata.named("number1").withIndex(4).ofType(Types.SMALLINT).withSize(5));
        addMetadata(number2, ColumnMetadata.named("number2").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(ref, ColumnMetadata.named("ref").withIndex(2).ofType(Types.SMALLINT).withSize(5));
    }

}

