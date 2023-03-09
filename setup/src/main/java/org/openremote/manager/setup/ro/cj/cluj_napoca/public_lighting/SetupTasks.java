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

import org.openremote.model.Container;
import org.openremote.model.setup.Setup;

import java.util.Arrays;
import java.util.List;

public class SetupTasks implements org.openremote.model.setup.SetupTasks {

    public static final String PRODUCTION = "production";

    @Override
    public List<Setup> createTasks(Container container, String setupType, boolean keycloakEnabled) {

        boolean isProduction = PRODUCTION.equalsIgnoreCase(setupType);

        // Add custom Setup task implementations here with tasks optionally dependent on setupType
        return Arrays.asList(
            new KeycloakSetup(container, isProduction),
            new ManagerSetup(container)
        );
    }
}
