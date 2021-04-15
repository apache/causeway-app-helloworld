package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jpa.seed.scripts.AbstractUserAndRolesFixtureScript;

public class SecmanConstants {
    private SecmanConstants(){}
    public static final String ADMIN_ROLE_NAME = "secman-admin-role";
    public static final String USER_ROLE_NAME = "secman-user-role";
}
