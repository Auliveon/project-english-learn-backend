package by.savitsky.englishlearn.configuration;

import by.savitsky.englishlearn.training.TrainingFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@ComponentScan(basePackages = { "by.savitsky" }, includeFilters = { @ComponentScan.Filter(Service.class), @ComponentScan.Filter(TrainingFactory.class)},
        excludeFilters = {@ComponentScan.Filter(Configuration.class)}, useDefaultFilters = false)
public class EnglishLearnTestConfiguration {

    private final String url;

    private final String login;

    private final String password;

    public EnglishLearnTestConfiguration(@Value("${datasource.url:jdbc:h2:mem:english_learn_test}") String url,
            @Value("${datasource.login:root}") String login,
            @Value("${datasource.password:root}") String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    @Value("${bpm.hibernate.dialect:org.hibernate.dialect.H2Dialect}")
    public String hibernateDialect;

    @Bean("testDatasource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        return dataSource;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            private static final long serialVersionUID = -8495353766778640025L;

            {
                setProperty("use_outer_join", "false");
                setProperty("hibernate.jdbc.use_streams_for_binary", "true");
                setProperty("hibernate.jdbc.batch_size", "20");
                setProperty("hibernate.max_fetch_depth", "2");
                setProperty("hibernate.dialect", hibernateDialect);
                // setProperty("hibernate.default_entity_mode", "dynamic-map");
                setProperty("hibernate.hbm2ddl.auto", "update");
                // setProperty("hibernate.enable_lazy_load_no_trans", "true");
                //setProperty("hibernate.show_sql", "true");
                //setProperty("hibernate.format_sql", "true");
                //setProperty("hibernate.use_sql_comments", "true");
                //setProperty("hibernate.generate_statistics", "true");
            }
        };
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(@Qualifier("testDatasource") DataSource mainDataSource) {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        sessionFactory.setPackagesToScan("by.savitsky");
        sessionFactory.setHibernateProperties(hibernateProperties());
        //sessionFactory.setMappingDirectoryLocations(new ClassPathResource("mappings"));
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
