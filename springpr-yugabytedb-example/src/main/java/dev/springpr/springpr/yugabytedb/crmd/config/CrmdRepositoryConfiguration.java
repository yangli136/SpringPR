/* (C)2025 */
package dev.springpr.springpr.yugabytedb.crmd.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan({"dev.springpr.springpr.yugabytedb.crmd.model"})
@EnableJpaRepositories({"dev.springpr.springpr.yugabytedb.crmd.repository"})
public class CrmdRepositoryConfiguration {}
