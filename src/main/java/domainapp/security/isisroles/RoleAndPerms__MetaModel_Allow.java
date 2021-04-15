package domainapp.security.isisroles;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.jpa.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__MetaModel_Allow extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "isis-metamodel--allow";

    public RoleAndPerms__MetaModel_Allow() {
        super(ROLE_NAME, "Access objects defined in isis' metamodel.  Note that this includes access to configuration");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.CHANGING,
                Can.of(
                        ApplicationFeatureId.newFeature(ApplicationFeatureSort.NAMESPACE, "isis.applib")
                )
        );
    }
}
