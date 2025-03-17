package dobong.life.base.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDomain is a Querydsl query type for Domain
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDomain extends EntityPathBase<Domain> {

    private static final long serialVersionUID = 61762777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDomain domain = new QDomain("domain");

    public final StringPath address = createString("address");

    public final QCategory category;

    public final StringPath day = createString("day");

    public final StringPath description = createString("description");

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath mapUrl = createString("mapUrl");

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final QTag tag;

    public QDomain(String variable) {
        this(Domain.class, forVariable(variable), INITS);
    }

    public QDomain(Path<? extends Domain> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDomain(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDomain(PathMetadata metadata, PathInits inits) {
        this(Domain.class, metadata, inits);
    }

    public QDomain(Class<? extends Domain> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.tag = inits.isInitialized("tag") ? new QTag(forProperty("tag")) : null;
    }

}

