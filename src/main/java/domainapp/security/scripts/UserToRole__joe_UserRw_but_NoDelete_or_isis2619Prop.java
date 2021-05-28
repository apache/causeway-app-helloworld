package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.applib.SecmanConfiguration;
import org.apache.isis.extensions.secman.applib.user.dom.AccountType;
import org.apache.isis.extensions.secman.applib.user.fixtures.AbstractUserAndRolesFixtureScript;
import org.apache.isis.extensions.secman.applib.role.seed.IsisExtH2ConsoleRoleAndPermissions;
import org.apache.isis.extensions.secman.applib.role.seed.IsisPersistenceJdoMetaModelRoleAndPermissions;

public class UserToRole__joe_UserRw_but_NoDelete_or_isis2619Prop extends AbstractUserAndRolesFixtureScript {

    public UserToRole__joe_UserRw_but_NoDelete_or_isis2619Prop() {
        super("joe", "pass", "joe@italy.com",
                "/ITA",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , RoleAndPerms__NoDelete.ROLE_NAME  // <<< veto application behaviour
                        , RoleAndPerms__NoIsis2619Prop.ROLE_NAME  // <<< veto application behaviour
                        , SecmanConfiguration.REGULAR_USER_ROLE_DEFAULT_NAME
                        , IsisExtH2ConsoleRoleAndPermissions.ROLE_NAME
                        , IsisPersistenceJdoMetaModelRoleAndPermissions.ROLE_NAME
                ));
    }
}
