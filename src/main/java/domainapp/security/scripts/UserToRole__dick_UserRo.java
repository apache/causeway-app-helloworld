package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.core.config.IsisConfiguration;
import org.apache.isis.extensions.secman.applib.SecmanConfiguration;
import org.apache.isis.extensions.secman.applib.user.dom.AccountType;
import org.apache.isis.extensions.secman.applib.user.fixtures.AbstractUserAndRolesFixtureScript;

public class UserToRole__dick_UserRo extends AbstractUserAndRolesFixtureScript {

    public UserToRole__dick_UserRo() {
        super("dick", "pass", "dick@france.com",
                "/FRA",
                AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRo.ROLE_NAME
                        , IsisConfiguration.Extensions.Secman.Seed.REGULAR_USER_ROLE_NAME_DEFAULT
                ));
    }

}
