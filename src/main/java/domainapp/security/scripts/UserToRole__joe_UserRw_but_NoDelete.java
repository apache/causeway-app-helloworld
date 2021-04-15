package domainapp.security.scripts;

import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.user.AccountType;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractUserAndRolesFixtureScript;

import domainapp.security.isisroles.RoleAndPerms__ApplibConfiguration__Veto;
import domainapp.security.isisroles.RoleAndPerms__Applib__Allow;
import domainapp.security.isisroles.RoleAndPerms__ExtFixtures__Allow;
import domainapp.security.isisroles.RoleAndPerms__ExtH2Console__Allow;
import domainapp.security.isisroles.RoleAndPerms__MetaModel_Allow;
import domainapp.security.isisroles.RoleAndPerms__PersistenceJdo_Allow;
import domainapp.security.isisroles.SecmanRoleNames;

public class UserToRole__joe_UserRw_but_NoDelete extends AbstractUserAndRolesFixtureScript {

    public UserToRole__joe_UserRw_but_NoDelete() {
        super("joe", "pass", AccountType.LOCAL,
                Can.of(
                        RoleAndPerms__UserRw.ROLE_NAME
                        , RoleAndPerms__NoDelete.ROLE_NAME  // <<< veto application behaviour
                        , RoleAndPerms__Applib__Allow.ROLE_NAME
                        , RoleAndPerms__ApplibConfiguration__Veto.ROLE_NAME // <<< veto framework behaviour
                        , RoleAndPerms__ExtFixtures__Allow.ROLE_NAME
                        , RoleAndPerms__ExtH2Console__Allow.ROLE_NAME
                        , RoleAndPerms__MetaModel_Allow.ROLE_NAME
                        , RoleAndPerms__PersistenceJdo_Allow.ROLE_NAME
                        , SecmanRoleNames.USER
                ));
    }

}
