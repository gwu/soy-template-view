soy-template-view
=================

This project contains an implementation of a Spring MVC View that
renders Google Closure Templates (soy templates).

## Usage

In your Spring Java configuration, you may configure a ViewResolver:

    // ...
    import com.garrettwu.spring.view.soy.DynamicSoyTemplateManager;
    import com.garrettwu.spring.view.soy.TemplateViewResolver;

    @Configuration
    @EnableWebMvc
    public class WebAppConfiguration {
      // ...

      @Bean
      public ViewResolver configureViewResolver() {
        TemplateViewResolver resolver = new TemplateViewResolver();
        resolver.setTemplateManager(
            new DynamicSoyTemplateManager("path/to/template/resources"));
        return resolver;
      }
    }

## Maven integration

To depend on this project using maven, you can add the following to
your dependencies section:

    <dependency>
      <groupId>com.garrettwu.spring</groupId>
      <artifactId>soy-template-view</artifactId>
      <version>0.0.1</version>
      <scope>compile</scope>
    </dependency>

And add the following repositories declaration to your pom.xml:

    <repositories>
      <repository>
        <id>gwu-maven-repo</id>
        <url>https://raw.github.com/gwu/maven-repo/master/repo</url>
      </repository>
    </repositories>
