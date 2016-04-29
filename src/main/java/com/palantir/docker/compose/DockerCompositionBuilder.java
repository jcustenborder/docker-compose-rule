/*
 * Copyright 2016 Palantir Technologies, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.palantir.docker.compose;

import com.google.common.collect.ImmutableList;
import com.palantir.docker.compose.ImmutableDockerComposeRule.Builder;
import com.palantir.docker.compose.configuration.DockerComposeFiles;
import com.palantir.docker.compose.configuration.ProjectName;
import com.palantir.docker.compose.connection.DockerMachine;
import com.palantir.docker.compose.connection.waiting.MultiServiceHealthCheck;
import com.palantir.docker.compose.connection.waiting.SingleServiceHealthCheck;
import com.palantir.docker.compose.execution.DockerCompose;
import org.joda.time.Duration;

public class DockerCompositionBuilder {
    private final Builder builder;

    public static final int DEFAULT_RETRY_ATTEMPTS = 2;

    public DockerCompositionBuilder() {
        this.builder = DockerComposeRule.builder();
    }

    public DockerCompositionBuilder waitingForService(String serviceName, SingleServiceHealthCheck healthCheck) {
        builder.waitingForService(serviceName, healthCheck);
        return this;
    }

    public DockerCompositionBuilder waitingForServices(ImmutableList<String> services, MultiServiceHealthCheck healthCheck) {
        builder.waitingForServices(services, healthCheck);
        return this;
    }

    public DockerComposition build() {
        DockerComposeRule rule = builder.build();
        return new DockerComposition(rule);
    }

    public DockerCompositionBuilder dockerCompose(DockerCompose compose) {
        builder.dockerCompose(compose);
        return this;
    }

    public DockerCompositionBuilder files(DockerComposeFiles files) {
        builder.files(files);
        return this;
    }

    public DockerCompositionBuilder machine(DockerMachine machine) {
        builder.machine(machine);
        return this;
    }

    public DockerCompositionBuilder projectName(ProjectName name) {
        builder.projectName(name);
        return this;
    }

    public DockerCompositionBuilder saveLogsTo(String absolutePath) {
        builder.saveLogsTo(absolutePath);
        return this;
    }

    public DockerCompositionBuilder timeout(Duration timeout) {
        builder.timeout(timeout);
        return this;
    }

}
