/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.spring.uaa.accesstokenadministration;

import lombok.ToString;
import org.cloudfoundry.spring.util.AbstractSpringOperations;
import org.cloudfoundry.uaa.accesstokenadministration.AccessTokenAdministration;
import org.cloudfoundry.uaa.accesstokenadministration.GetTokenKeyRequest;
import org.cloudfoundry.uaa.accesstokenadministration.GetTokenKeyResponse;
import org.springframework.web.client.RestOperations;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.net.URI;

@ToString(callSuper = true)
public final class SpringAccessTokenAdministration extends AbstractSpringOperations implements AccessTokenAdministration {

    /**
     * Creates an instance
     *
     * @param restOperations the {@link RestOperations} to use to communicate with the server
     * @param root           the root URI of the server.  Typically something like {@code https://uaa.run.pivotal.io}.
     * @param schedulerGroup The group to use when making requests
     */
    public SpringAccessTokenAdministration(RestOperations restOperations, URI root, Scheduler schedulerGroup) {
        super(restOperations, root, schedulerGroup);
    }

    @Override
    public Mono<GetTokenKeyResponse> getTokenKey(GetTokenKeyRequest request) {
        return get(request, GetTokenKeyResponse.class, builder -> builder.pathSegment("token_key"));
    }

}
