package domainapp.security.isisroles;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractUserAndRolesFixtureScript;

public class SecmanRoleNames {
    private SecmanRoleNames(){}
    public static final String ADMIN = "isis-ext-secman-admin";
    public static final String USER = "isis-ext-secman-user";
}
