package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.applib.SecmanConfiguration;
import org.apache.isis.extensions.secman.applib.user.dom.AccountType;
import org.apache.isis.extensions.secman.applib.user.fixtures.AbstractUserAndRolesFixtureScript;
import org.apache.isis.extensions.secman.applib.role.seed.IsisConfigurationRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtH2ConsoleRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisPersistenceJdoMetaModelRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisSudoImpersonateRoleAndPermissions;

public class UserToRole__bob_UserRw extends AbstractUserAndRolesFixtureScript {

    public UserToRole__bob_UserRw() {
        super("bob", "pass", "bob@world.com",
                "/",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , SecmanConfiguration.REGULAR_USER_ROLE_DEFAULT_NAME
                        , IsisConfigurationRoleAndPermissions.ROLE_NAME
                        , IsisSudoImpersonateRoleAndPermissions.ROLE_NAME
                        , IsisExtH2ConsoleRoleAndPermissions.ROLE_NAME
                        , IsisPersistenceJdoMetaModelRoleAndPermissions.ROLE_NAME
                ));
    }

}
