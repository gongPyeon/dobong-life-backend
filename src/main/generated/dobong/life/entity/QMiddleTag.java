package dobong.life.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import dobong.life.domain.store.MiddleTag;


/**
 * QMiddleTag is a Querydsl query type for MiddleTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMiddleTag extends EntityPathBase<MiddleTag> {

    private static final long serialVersionUID = -1476911697L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMiddleTag middleTag = new QMiddleTag("middleTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReview review;

    public final QReviewTag reviewTag;

    public QMiddleTag(String variable) {
        this(MiddleTag.class, forVariable(variable), INITS);
    }

    public QMiddleTag(Path<? extends MiddleTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMiddleTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMiddleTag(PathMetadata metadata, PathInits inits) {
        this(MiddleTag.class, metadata, inits);
    }

    public QMiddleTag(Class<? extends MiddleTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.reviewTag = inits.isInitialized("reviewTag") ? new QReviewTag(forProperty("reviewTag")) : null;
    }

}

