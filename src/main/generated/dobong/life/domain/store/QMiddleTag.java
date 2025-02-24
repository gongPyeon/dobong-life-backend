package dobong.life.domain.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMiddleTag is a Querydsl query type for MiddleTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMiddleTag extends EntityPathBase<MiddleTag> {

    private static final long serialVersionUID = 1366942787L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMiddleTag middleTag = new QMiddleTag("middleTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dobong.life.domain.review.QReview review;

    public final dobong.life.domain.review.QReviewTag reviewTag;

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
        this.review = inits.isInitialized("review") ? new dobong.life.domain.review.QReview(forProperty("review"), inits.get("review")) : null;
        this.reviewTag = inits.isInitialized("reviewTag") ? new dobong.life.domain.review.QReviewTag(forProperty("reviewTag")) : null;
    }

}

