/**
 * 
 */
package abraham.core.test;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import abraham.core.settings.JPASettings4Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import pan.utils.annotation.env.UnitTestEnv;
import pan.utils.security.shiro.CredentialsService;
import pan.utils.security.shiro.HashedCredentialsService;

/**
 * @author panqingrong
 *
 */
@Configuration
public class TestingConfig {

	@Bean
	@UnitTestEnv
	public DataSource devDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	//You can change the credentials-logic here.
	public CredentialsService credentialsService() {
		return new HashedCredentialsService();
	}

	@Bean
	@UnitTestEnv
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
		hibernateAdapter.setGenerateDdl(true);
		hibernateAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(hibernateAdapter);
		factoryBean.setPackagesToScan("abraham.core.ca.domain",
				"abraham.core.web.security.domain");
		factoryBean.setDataSource(dataSource);
		factoryBean.afterPropertiesSet();

		return factoryBean.getObject();
	}

}
