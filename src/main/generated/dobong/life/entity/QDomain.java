package dobong.life.entity;

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

    private static final long serialVersionUID = 1973934362L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDomain domain = new QDomain("domain");

    public final StringPath addressDetail = createString("addressDetail");

    public final StringPath addressDong = createString("addressDong");

    public final QCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath itemName = createString("itemName");

    public final StringPath mapUrl = createString("mapUrl");

    public final StringPath name = createString("name");

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
    }

}

