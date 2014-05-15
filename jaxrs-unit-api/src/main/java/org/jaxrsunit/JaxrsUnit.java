/*
 * Copyright 2014 Benoît Prioux
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jaxrsunit;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JaxrsUnit {

    public static JaxrsServer newServer(Class<?>... resources) {
        ServiceLoader<JaxrsServer> serviceLoader = ServiceLoader.load(JaxrsServer.class);
        Iterator<JaxrsServer> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            JaxrsServer jaxrsServer = iterator.next();
            JaxrsServerConfig config = new JaxrsServerConfig();
            config.addResources(resources);
            jaxrsServer.configure(config);
            return jaxrsServer;
        } else {
            throw new RuntimeException("No implemention found for JaxrsServer");
        }
    }
}
