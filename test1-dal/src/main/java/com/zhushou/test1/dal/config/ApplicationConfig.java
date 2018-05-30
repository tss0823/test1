package com.zhushou.test1.dal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

/**
 * 等价于spring-config.xml
 */
@Configuration
public class ApplicationConfig {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     * 线上环境的profile名称
     */
    public static final String PROFILE_NAME_PROD = "prod";

    @Autowired
    Environment env;


    @Value("${profiles.active}")
    private String active;

    public boolean isProd() {
        return PROFILE_NAME_PROD.equals(active);
    }

    public boolean isTest() {
        return "test".equals(active);
    }

    /**
     * dev profile
     */
    @Profile("dev")
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurerDev() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ClassPathResource classPathResource[] = {new ClassPathResource("env/config-dev.properties"), new ClassPathResource("config-common.properties")};
        ppc.setLocations(classPathResource);
        LOG.info("env/config-dev.properties loaded");
        return ppc;
    }

    @Profile("test")
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurerTest() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ClassPathResource classPathResource[] = {new ClassPathResource("env/config-test.properties"), new ClassPathResource("config-common.properties")};
        ppc.setLocations(classPathResource);
        LOG.info("env/config-test.properties loaded");
        return ppc;
    }

    @Profile(PROFILE_NAME_PROD)
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurerProd() {
        PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        ClassPathResource classPathResource[] = {new ClassPathResource("env/config-prod.properties"), new ClassPathResource("config-common.properties")};
        LOG.info("env/config-prod.properties loaded");
        return ppc;
    }

}