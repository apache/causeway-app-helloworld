package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jpa.seed.scripts.AbstractUserAndRolesFixtureScript;

public class UserToRole__bob_UserRw extends AbstractUserAndRolesFixtureScript {

    public UserToRole__bob_UserRw() {
        super("bob", "pass", AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , SecmanConstants.USER_ROLE_NAME
                ));
    }

}
