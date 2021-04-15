package domainapp.security.isisroles;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__ExtFixtures__Allow extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "isis-ext-fixtures--allow";

    public RoleAndPerms__ExtFixtures__Allow() {
        super(ROLE_NAME, "Execute fixture scripts");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.CHANGING,
                Can.of(
                        ApplicationFeatureId.newFeature(ApplicationFeatureSort.NAMESPACE, "isis.ext.fixtures")
                )
        );
    }
}
