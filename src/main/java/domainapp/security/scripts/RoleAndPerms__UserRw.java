package domainapp.security.scripts;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.dom.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.api.role.fixtures.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__UserRw extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "user-rw";

    public RoleAndPerms__UserRw() {
        super(ROLE_NAME, "Read-write access to entire application");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.CHANGING,
                Can.of(ApplicationFeatureId.newNamespace("hello"))
        );
    }
}
