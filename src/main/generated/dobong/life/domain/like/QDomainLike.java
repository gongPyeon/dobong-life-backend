package dobong.life.domain.like;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDomainLike is a Querydsl query type for DomainLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDomainLike extends EntityPathBase<DomainLike> {

    private static final long serialVersionUID = -980401987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDomainLike domainLike = new QDomainLike("domainLike");

    public final dobong.life.domain.store.QDomain domain;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dobong.life.domain.user.QUser user;

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
        this.domain = inits.isInitialized("domain") ? new dobong.life.domain.store.QDomain(forProperty("domain"), inits.get("domain")) : null;
        this.user = inits.isInitialized("user") ? new dobong.life.domain.user.QUser(forProperty("user")) : null;
    }

}

