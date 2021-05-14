package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.dom.AccountType;
import org.apache.isis.extensions.secman.api.user.fixtures.AbstractUserAndRolesFixtureScript;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisExtH2ConsoleRoleAndPermissions;
import org.apache.isis.extensions.secman.model.seed.scripts.other.IsisPersistenceJdoMetaModelRoleAndPermissions;

import domainapp.security.isisroles.SecmanRoleNames;

public class UserToRole__joe_UserRw_but_NoDelete_or_isis2619Prop extends AbstractUserAndRolesFixtureScript {

    public UserToRole__joe_UserRw_but_NoDelete_or_isis2619Prop() {
        super("joe", "pass", "joe@italy.com",
                "/ITA",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , RoleAndPerms__NoDelete.ROLE_NAME  // <<< veto application behaviour
                        , RoleAndPerms__NoIsis2619Prop.ROLE_NAME  // <<< veto application behaviour
                        , IsisExtH2ConsoleRoleAndPermissions.ROLE_NAME
                        , IsisPersistenceJdoMetaModelRoleAndPermissions.ROLE_NAME
                        , SecmanRoleNames.USER
                ));
    }

}
