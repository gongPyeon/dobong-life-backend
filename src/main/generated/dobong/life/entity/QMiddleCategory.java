package dobong.life.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import dobong.life.domain.store.MiddleCategory;


/**
 * QMiddleCategory is a Querydsl query type for MiddleCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMiddleCategory extends EntityPathBase<MiddleCategory> {

    private static final long serialVersionUID = 1881990153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMiddleCategory middleCategory = new QMiddleCategory("middleCategory");

    public final QDomain domain;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSubCategory subCategory;

    public final QSubTag subTag;

    public QMiddleCategory(String variable) {
        this(MiddleCategory.class, forVariable(variable), INITS);
    }

    public QMiddleCategory(Path<? extends MiddleCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMiddleCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMiddleCategory(PathMetadata metadata, PathInits inits) {
        this(MiddleCategory.class, metadata, inits);
    }

    public QMiddleCategory(Class<? extends MiddleCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.domain = inits.isInitialized("domain") ? new QDomain(forProperty("domain"), inits.get("domain")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
        this.subTag = inits.isInitialized("subTag") ? new QSubTag(forProperty("subTag"), inits.get("subTag")) : null;
    }

}

