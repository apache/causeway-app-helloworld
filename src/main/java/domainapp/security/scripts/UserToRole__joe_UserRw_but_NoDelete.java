package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractUserAndRolesFixtureScript;

public class UserToRole__joe_UserRw_but_NoDelete extends AbstractUserAndRolesFixtureScript {

    public UserToRole__joe_UserRw_but_NoDelete() {
        super("joe", "pass", AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , RoleAndPerms__NoDelete.ROLE_NAME
                        , SecmanConstants.USER_ROLE_NAME
                ));
    }

}
