package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.SecmanConfiguration;
import org.apache.isis.extensions.secman.api.user.dom.AccountType;
import org.apache.isis.extensions.secman.api.user.fixtures.AbstractUserAndRolesFixtureScript;

public class UserToRole__dick_UserRo extends AbstractUserAndRolesFixtureScript {

    public UserToRole__dick_UserRo() {
        super("dick", "pass", "dick@france.com",
                "/FRA",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRo.ROLE_NAME
                        , SecmanConfiguration.DEFAULT_REGULAR_USER_ROLE_NAME
                ));
    }

}
