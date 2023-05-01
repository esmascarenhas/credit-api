package com.bootcampTqiDio.creditapi.common.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
                .group("springcreditapplicationsystem-public")
                .pathsToMatch("/api/customers/**", "/api/credits/**")
                .displayName("Api Credit System")
                .build()
    }
}