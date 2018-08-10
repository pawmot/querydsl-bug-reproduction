package com.pawmot.querydslbugreproduction;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QDict is a Querydsl query type for QDict
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QDict extends com.querydsl.sql.RelationalPathBase<QDict> {

    private static final long serialVersionUID = 1332194703;

    public static final QDict dict = new QDict("dict");

    public final StringPath entry = createString("entry");

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final com.querydsl.sql.PrimaryKey<QDict> dictPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QTest> _testRefFkey = createInvForeignKey(id, "ref");

    public QDict(String variable) {
        super(QDict.class, forVariable(variable), "public", "dict");
        addMetadata();
    }

    public QDict(String variable, String schema, String table) {
        super(QDict.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QDict(String variable, String schema) {
        super(QDict.class, forVariable(variable), schema, "dict");
        addMetadata();
    }

    public QDict(Path<? extends QDict> path) {
        super(path.getType(), path.getMetadata(), "public", "dict");
        addMetadata();
    }

    public QDict(PathMetadata metadata) {
        super(QDict.class, metadata, "public", "dict");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(entry, ColumnMetadata.named("entry").withIndex(2).ofType(Types.VARCHAR).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.SMALLINT).withSize(5).notNull());
    }

}

