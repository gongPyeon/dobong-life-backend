package dobong.life.base.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSetting is a Querydsl query type for Setting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSetting extends EntityPathBase<Setting> {

    private static final long serialVersionUID = 2144220501L;

    public static final QSetting setting = new QSetting("setting");

    public final StringPath businessEmail = createString("businessEmail");

    public final StringPath contactUs = createString("contactUs");

    public final StringPath developEmail = createString("developEmail");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openSourceLicenses = createString("openSourceLicenses");

    public final StringPath privacyPolicy = createString("privacyPolicy");

    public final StringPath termsOfService = createString("termsOfService");

    public QSetting(String variable) {
        super(Setting.class, forVariable(variable));
    }

    public QSetting(Path<? extends Setting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSetting(PathMetadata metadata) {
        super(Setting.class, metadata);
    }

}

