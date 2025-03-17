package dobong.life.base.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -676877594L;

    public static final QUser user = new QUser("user");

    public final BooleanPath business = createBoolean("business");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath providerId = createString("providerId");

    public final EnumPath<dobong.life.global.auth.enums.SocialType> providerType = createEnum("providerType", dobong.life.global.auth.enums.SocialType.class);

    public final EnumPath<dobong.life.global.auth.enums.Role> role = createEnum("role", dobong.life.global.auth.enums.Role.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

