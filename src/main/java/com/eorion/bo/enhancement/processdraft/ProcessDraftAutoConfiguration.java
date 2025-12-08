package com.eorion.bo.enhancement.processdraft;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
public class ProcessDraftAutoConfiguration {
    @Bean
    public GroupedOpenApi processDraftGroup() {
        return GroupedOpenApi.builder()
                .group("process-draft")
                .packagesToScan("com.eorion.bo.enhancement.processdraft.adapter.inbound")
                .pathsToMatch("/enhancement/draft/**")
                .build();
    }
}
