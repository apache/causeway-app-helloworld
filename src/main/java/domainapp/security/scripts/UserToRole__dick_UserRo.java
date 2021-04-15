package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractUserAndRolesFixtureScript;

import domainapp.security.isisroles.RoleAndPerms__ApplibConfiguration__Veto;
import domainapp.security.isisroles.RoleAndPerms__Applib__Allow;
import domainapp.security.isisroles.RoleAndPerms__ExtFixtures__Allow;
import domainapp.security.isisroles.RoleAndPerms__ExtH2Console__Allow;
import domainapp.security.isisroles.RoleAndPerms__MetaModel_Allow;
import domainapp.security.isisroles.SecmanRoleNames;

public class UserToRole__dick_UserRo extends AbstractUserAndRolesFixtureScript {

    public UserToRole__dick_UserRo() {
        super("dick", "pass", AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRo.ROLE_NAME
                        , SecmanRoleNames.USER
                        , RoleAndPerms__Applib__Allow.ROLE_NAME
                        , RoleAndPerms__ApplibConfiguration__Veto.ROLE_NAME
                        , RoleAndPerms__MetaModel_Allow.ROLE_NAME
                ));
    }

}
