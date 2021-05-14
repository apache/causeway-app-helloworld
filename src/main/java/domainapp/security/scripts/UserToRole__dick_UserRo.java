package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.dom.AccountType;
import org.apache.isis.extensions.secman.api.user.fixtures.AbstractUserAndRolesFixtureScript;

import domainapp.security.isisroles.SecmanRoleNames;

public class UserToRole__dick_UserRo extends AbstractUserAndRolesFixtureScript {

    public UserToRole__dick_UserRo() {
        super("dick", "pass", "dick@france.com",
                "/FRA",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRo.ROLE_NAME
                        , SecmanRoleNames.USER
                ));
    }

}
