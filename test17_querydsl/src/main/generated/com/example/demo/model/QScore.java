package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScore is a Querydsl query type for Score
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScore extends EntityPathBase<Score> {

    private static final long serialVersionUID = -1702930509L;

    public static final QScore score1 = new QScore("score1");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final NumberPath<Long> scoreId = createNumber("scoreId", Long.class);

    public final NumberPath<Long> studentId = createNumber("studentId", Long.class);

    public final StringPath subject = createString("subject");

    public QScore(String variable) {
        super(Score.class, forVariable(variable));
    }

    public QScore(Path<? extends Score> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScore(PathMetadata metadata) {
        super(Score.class, metadata);
    }

}

