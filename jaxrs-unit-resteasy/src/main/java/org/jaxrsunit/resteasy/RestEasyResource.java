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
package org.jaxrsunit.resteasy;

import org.jaxrsunit.JaxrsResource;
import org.jaxrsunit.JaxrsResponse;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;

import java.net.URISyntaxException;

public class RestEasyResource implements JaxrsResource {

    private final Dispatcher dispatcher;
    private final String uri;

    RestEasyResource(Dispatcher dispatcher, String uri) {
       this.dispatcher = dispatcher;
       this.uri = uri;
    }

    @Override
    public JaxrsResponse get() {
        try {
            MockHttpRequest request = MockHttpRequest.get(uri);
            MockHttpResponse response = new MockHttpResponse();
            dispatcher.invoke(request, response);
            return new RestEasyResponse(response);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}