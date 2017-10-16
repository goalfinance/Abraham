/**
 *
 */
package abraham.core;

import abraham.core.settings.JPASettings4Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import pan.utils.annotation.env.Development;
import pan.utils.annotation.env.Production;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author panqingrong
 */
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class ApplicationConfig {
    @Autowired
    private JPASettings4Hibernate jpaSettings4Hibernate;

    @Bean(name="abraham.datasource")
    @Production
    @Development
    @ConfigurationProperties(prefix = "abraham.datasource")
    public DataSource productionDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Production
    @Development
    public EntityManagerFactory entityManagerFactory(@Qualifier("abraham.datasource") DataSource dataSource) {
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        hibernateAdapter.setGenerateDdl(jpaSettings4Hibernate.getGenerateDdl());
        hibernateAdapter.setShowSql(jpaSettings4Hibernate.getShowSql());
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(hibernateAdapter);
        factoryBean.setPackagesToScan(jpaSettings4Hibernate.getPackagesToScan()
                .toArray(new String[this.jpaSettings4Hibernate.getPackagesToScan().size()]));
        factoryBean.setDataSource(dataSource);
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", this.jpaSettings4Hibernate.getDialect());
        factoryBean.setJpaProperties(p);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    public MethodValidationPostProcessor MethodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
