package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractUserAndRolesFixtureScript;

public class UserToRole__dick_UserRo extends AbstractUserAndRolesFixtureScript {

    public UserToRole__dick_UserRo() {
        super("dick", "pass", AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRo.ROLE_NAME
                        , SecmanConstants.USER_ROLE_NAME
                ));
    }

}
