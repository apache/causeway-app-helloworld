package domainapp.security.scripts;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.applib.role.fixtures.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__UserRo extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "user-ro";

    public RoleAndPerms__UserRo() {
        super(ROLE_NAME, "Read-only access to entire application");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.VIEWING,
                Can.of(ApplicationFeatureId.newNamespace("hello")));
        newPermissions(
                ApplicationPermissionRule.ALLOW,
                ApplicationPermissionMode.CHANGING,
                Can.of(
                    ApplicationFeatureId.newFeature(ApplicationFeatureSort.MEMBER, "hello.HelloWorldObjects#findByName"),
                    ApplicationFeatureId.newFeature(ApplicationFeatureSort.MEMBER, "hello.HelloWorldObjects#listAll")
                ));
    }
}
