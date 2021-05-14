package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.dom.AccountType;
import org.apache.isis.extensions.secman.api.user.fixtures.AbstractUserAndRolesFixtureScript;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisConfigurationRoleAndPermissions;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisExtH2ConsoleRoleAndPermissions;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisPersistenceJdoMetaModelRoleAndPermissions;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisSudoImpersonateRoleAndPermissions;

import domainapp.security.isisroles.SecmanRoleNames;

public class UserToRole__bob_UserRw extends AbstractUserAndRolesFixtureScript {

    public UserToRole__bob_UserRw() {
        super("bob", "pass", "bob@world.com",
                "/",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , SecmanRoleNames.USER
                        , IsisConfigurationRoleAndPermissions.ROLE_NAME
                        , IsisSudoImpersonateRoleAndPermissions.ROLE_NAME
                        , IsisExtH2ConsoleRoleAndPermissions.ROLE_NAME
                        , IsisPersistenceJdoMetaModelRoleAndPermissions.ROLE_NAME
                ));
    }

}
