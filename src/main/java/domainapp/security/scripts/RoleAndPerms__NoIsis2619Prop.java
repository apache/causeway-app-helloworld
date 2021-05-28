package domainapp.security.scripts;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.applib.role.fixtures.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__NoIsis2619Prop extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "no-isis2619prop";

    public RoleAndPerms__NoIsis2619Prop() {
        super(ROLE_NAME, "Veto access to HelloWorld#isis2619Prop");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.VETO,
                ApplicationPermissionMode.VIEWING,
                Can.of(ApplicationFeatureId.newMember("hello.HelloWorldObject", "isis2619Prop"))
        );
    }
}
