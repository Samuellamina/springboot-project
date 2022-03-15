package io.sam.project.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.sam.project")
@EnableJpaRepositories("io.sam.project")
@EnableTransactionManagement
public class DomainConfig {
}
