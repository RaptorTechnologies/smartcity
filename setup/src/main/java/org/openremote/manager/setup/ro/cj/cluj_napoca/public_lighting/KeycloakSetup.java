/*
 * Copyright 2021, OpenRemote Inc.
 *
 * See the CONTRIBUTORS.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.openremote.manager.setup.ro.cj.cluj_napoca.public_lighting;

import org.openremote.manager.setup.AbstractKeycloakSetup;
import org.openremote.model.Container;
import org.openremote.model.security.ClientRole;
import org.openremote.model.security.Realm;
import org.openremote.model.util.TextUtil;

import static org.openremote.container.util.MapAccess.getString;

public class KeycloakSetup extends AbstractKeycloakSetup {

    public static final String DEMO_USER_PASSWORD = "CUSTOM_USER_PASSWORD";
    public static final String DEMO_USER_PASSWORD_DEFAULT = "demo";
    protected final String demoUserPassword;

    public KeycloakSetup(Container container, boolean isProduction) {
        super(container);

        demoUserPassword = getString(container.getConfig(), DEMO_USER_PASSWORD, DEMO_USER_PASSWORD_DEFAULT);

        if (isProduction && TextUtil.isNullOrEmpty(demoUserPassword)) {
            throw new IllegalStateException("Custom user password must be supplied in production");
        }
    }

    @Override
    public void onStart() throws Exception {
        // Create custom realm
        Realm customRealm = createRealm("public_lighting", "Public Lighting", true);

        // Create user(s) for custom realm
        createUser("public_lighting", "demo", demoUserPassword, "demo", "demo", null, true, new ClientRole[] {
                ClientRole.READ,
                ClientRole.WRITE,
        });
    }
}
