package com.pswiderski.kotlin.web

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.RedirectView
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("My API Title")
                .description("Awesome description")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(Contact("", "", "contact@contact.com.uy"))
                .build()
    }


    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
//            all except basic error and index handler
            .apis(Predicates.and(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")),
                    Predicates.not(RequestHandlerSelectors.withClassAnnotation(org.springframework.stereotype.Controller::class.java))))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
//            all except spring web classes
            .ignoredParameterTypes(ModelAndView::class.java, View::class.java, RedirectView::class.java)
}