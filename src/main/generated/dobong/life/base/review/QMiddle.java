package dobong.life.base.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMiddle is a Querydsl query type for Middle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMiddle extends EntityPathBase<Middle> {

    private static final long serialVersionUID = 1695562691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMiddle middle = new QMiddle("middle");

    public final dobong.life.base.store.QDomain domain;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QKeyword keyword;

    public final QReview review;

    public QMiddle(String variable) {
        this(Middle.class, forVariable(variable), INITS);
    }

    public QMiddle(Path<? extends Middle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMiddle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMiddle(PathMetadata metadata, PathInits inits) {
        this(Middle.class, metadata, inits);
    }

    public QMiddle(Class<? extends Middle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.domain = inits.isInitialized("domain") ? new dobong.life.base.store.QDomain(forProperty("domain"), inits.get("domain")) : null;
        this.keyword = inits.isInitialized("keyword") ? new QKeyword(forProperty("keyword")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

