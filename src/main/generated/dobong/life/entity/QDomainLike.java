package dobong.life.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import dobong.life.domain.like.DomainLike;


/**
 * QDomainLike is a Querydsl query type for DomainLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDomainLike extends EntityPathBase<DomainLike> {

    private static final long serialVersionUID = 1034280913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDomainLike domainLike = new QDomainLike("domainLike");

    public final QDomain domain;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QDomainLike(String variable) {
        this(DomainLike.class, forVariable(variable), INITS);
    }

    public QDomainLike(Path<? extends DomainLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDomainLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDomainLike(PathMetadata metadata, PathInits inits) {
        this(DomainLike.class, metadata, inits);
    }

    public QDomainLike(Class<? extends DomainLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.domain = inits.isInitialized("domain") ? new QDomain(forProperty("domain"), inits.get("domain")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

