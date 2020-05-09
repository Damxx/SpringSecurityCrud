package springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = "springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer {

    // set up variable to hold the properties
    @Autowired
    private Environment env;

    // set up a logger for diagnostics
    private Logger logger = Logger.getLogger(getClass().getName());


    // define a bean for ViewResolver
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");


        return viewResolver;
    }

    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/", "D:/statics/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        // Register resource handler for images
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

        //register resources for JavaScript
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
    // define a bean for our security datasource

    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            securityDataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }


        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));


        // set connection pool props
        securityDataSource.setInitialPoolSize(
                getIntProperty("connection.pool.initialPoolSize"));

        securityDataSource.setMinPoolSize(
                getIntProperty("connection.pool.minPoolSize"));

        securityDataSource.setMaxPoolSize(
                getIntProperty("connection.pool.maxPoolSize"));

        securityDataSource.setMaxIdleTime(
                getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // need a helper method
    // read environment property and convert to int

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.search.default.directory_provide", "filesystem");
        props.setProperty("hibernate.search.default.indexBase", "C:/hibernate/lucence/indexes");

        return props;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        // create session factorys
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());


        return sessionFactory;
    }


    @Bean
    public ThemeSource themeSource() {
        ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
        themeSource.setBasenamePrefix("theme/");
        return themeSource;
    }

    @Bean
    public ThemeResolver themeResolver() {
        CookieThemeResolver resolver = new CookieThemeResolver();
        resolver.setDefaultThemeName("pulse");
        return resolver;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {


        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);


        return txManager;
    }

    @Bean("messageSource")
    public MessageSource messageSource() {


        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }


    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        return localeResolver;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
        themeChangeInterceptor.setParamName("theme");
        registry.addInterceptor(themeChangeInterceptor);

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }


}
















