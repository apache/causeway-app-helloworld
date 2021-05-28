package domainapp.security.scripts;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.applib.role.fixtures.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__NoDelete extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "no-delete";

    public RoleAndPerms__NoDelete() {
        super(ROLE_NAME, "Veto access to deleting HelloWorld objects");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.VETO,
                ApplicationPermissionMode.VIEWING,
                Can.of(ApplicationFeatureId.newFeature(ApplicationFeatureSort.MEMBER, "hello.HelloWorldObject#delete"))
        );
    }
}
