package com.jjm.triphelper.init;

import com.fasterxml.classmate.TypeResolver;
import com.jjm.chameleon.annotation.EnableChameleonRepository;
import com.jjm.chameleon.init.AbstractChameleonInitializer;
import com.jjm.foursquare.init.FoursquareApplication;
import com.jjm.chameleon.annotation.ChameleonScan;
import com.jjm.chameleon.context.ChameleonApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import java.time.LocalDate;
import static springfox.documentation.schema.AlternateTypeRules.newRule;
import static com.google.common.collect.Lists.newArrayList;
import java.util.List;

@Configurable
@EnableSwagger2
@SpringBootApplication

@ComponentScan(basePackages = {"com.jjm.triphelper", "com.jjm.foursquare", "com.jjm.chameleon"})
@EntityScan(basePackages = "com.jjm.triphelper.entity.jpa")
@EnableJpaRepositories(basePackages = {"com.jjm.triphelper.repository.impl", "com.jjm.triphelper.repository"})

@ChameleonScan(basePackages = "com.jjm.triphelper.entity.jpa")
@EnableChameleonRepository(basePackages = "com.jjm.triphelper.repository.dto")

@EnableAutoConfiguration
@EnableTransactionManagement
public class Application extends AbstractChameleonInitializer {

    @Autowired private TypeResolver typeResolver;

    public static void main(String[] args) {
        FoursquareApplication.getInstance().init("HWBMSYQA51QV0NH2I3F4GZRUWRBZAUGGFOM5PK1OXG2H1TB4", "4BAHTIQM4L22O5KOV3QN35XQ14PMF4PLRTRJXUBNN0SHFRR4");
        ChameleonApplication.run(Application.class);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("production")
    public CacheManager getEhCacheManager(){
        return new EhCacheCacheManager(getEhCacheFactory().getObject());
    }

    @Bean
    @Profile("production")
    public EhCacheManagerFactoryBean getEhCacheFactory(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }

    @Bean
    @Profile("production")
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(
                        typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
                                .responseModel(new ModelRef("Error")).build()))
                .securitySchemes(newArrayList(apiKey())).securityContexts(newArrayList(securityContext()));
    }

    @Profile("production")
    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    @Profile("production")
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    @Profile("production")
    public List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("mykey", authorizationScopes));
    }

    @Bean
    @Profile("production")
    public SecurityConfiguration security() {
        return new SecurityConfiguration("test-app-client-id", "test-app-realm", "test-app", "apiKey");
    }

    @Bean
    @Profile("production")
    public UiConfiguration uiConfig() {
        return new UiConfiguration("validatorUrl");
    }




}