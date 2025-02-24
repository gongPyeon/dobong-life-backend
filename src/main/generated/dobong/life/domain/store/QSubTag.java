package dobong.life.domain.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubTag is a Querydsl query type for SubTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubTag extends EntityPathBase<SubTag> {

    private static final long serialVersionUID = -685652100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubTag subTag = new QSubTag("subTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath subTagName = createString("subTagName");

    public final QTag tag;

    public QSubTag(String variable) {
        this(SubTag.class, forVariable(variable), INITS);
    }

    public QSubTag(Path<? extends SubTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubTag(PathMetadata metadata, PathInits inits) {
        this(SubTag.class, metadata, inits);
    }

    public QSubTag(Class<? extends SubTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag"), inits.get("tag")) : null;
    }

}

