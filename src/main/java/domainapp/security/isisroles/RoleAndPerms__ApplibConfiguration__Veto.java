package domainapp.security.isisroles;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractRoleAndPermissionsFixtureScript;

public class RoleAndPerms__ApplibConfiguration__Veto extends AbstractRoleAndPermissionsFixtureScript {

    public static final String ROLE_NAME = "isis-applib-configuration--veto";

    public RoleAndPerms__ApplibConfiguration__Veto() {
        super(ROLE_NAME, "Veto access to configuration menu");
    }

    @Override
    protected void execute(ExecutionContext ec) {
        newPermissions(
                ApplicationPermissionRule.VETO,
                ApplicationPermissionMode.VIEWING,
                Can.of(
                        ApplicationFeatureId.newFeature(ApplicationFeatureSort.MEMBER, "isis.applib.ConfigurationMenu#configuration"),
                        ApplicationFeatureId.newFeature(ApplicationFeatureSort.TYPE, "isis.applib.ConfigurationProperty"),
                        ApplicationFeatureId.newFeature(ApplicationFeatureSort.TYPE, "isis.applib.ConfigurationViewModel")
                )
        );
    }
}
