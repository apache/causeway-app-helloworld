package domainapp.security.scripts;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.value.Password;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.jdo.dom.role.ApplicationRole;
import org.apache.isis.extensions.secman.jdo.dom.role.ApplicationRoleRepository;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractRoleAndPermissionsFixtureScript;
import org.apache.isis.extensions.secman.model.dom.user.ApplicationUserMenu;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;

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
