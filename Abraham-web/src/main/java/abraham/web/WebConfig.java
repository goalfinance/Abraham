/**
 *
 */
package abraham.web;

import abraham.core.web.security.service.WebSecurityService;
import abraham.web.shiro.ApplicationRealm;
import abraham.web.shiro.DefaultRolePermissionResolver;
import abraham.web.sitemesh.DefaultSiteMeshFilter;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pan.utils.security.shiro.CredentialsService;
import pan.utils.security.shiro.HashedCredentialsService;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * @author panqingrong
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired(required = false)
    private WebSecurityService webSecurityService;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/metronic/**").addResourceLocations("/presence/metronic/v4.7/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/presence/metronic/v4.7/");
        registry.addResourceHandler("../assets/**").addResourceLocations("/presence/metronic/v4.7/");
    }

    // private class WebCustomizer implements EmbeddedServletContainerCustomizer
    // {
    //
    // @Override
    // public void customize(ConfigurableEmbeddedServletContainer container) {
    // container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
    // "/WEB-INF/jsp/common/notfound.jsp"));
    // container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
    // "/WEB-INF/jsp/common/notfound.jsp"));
    // container.addErrorPages(new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE,
    // "/WEB-INF/common/error.jsp"));
    // }
    //
    // }

    // @Bean
    // public EmbeddedServletContainerCustomizer containerCustomized() {
    // return new WebCustomizer();
    // }
    @Bean
    public ApplicationRealm applicationRealm(CredentialsService credentialsService) {
        ApplicationRealm realm = new ApplicationRealm(webSecurityService, credentialsService);
        RolePermissionResolver rolePermissionResolver = new DefaultRolePermissionResolver(webSecurityService);
        realm.setRolePermissionResolver(rolePermissionResolver);
        return realm;
    }

    @Bean
    //You can change the credentials-logic here.
    public CredentialsService credentialsService() {
        return new HashedCredentialsService();
    }

    @Bean
    public Filter sitemeshFilter() {
        return new DefaultSiteMeshFilter();
    }

    @Bean
    public FilterRegistrationBean shiroFilterRegistration4devoops(ApplicationRealm applicationRealm) {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(applicationRealm);
        securityManager.setCacheManager(cacheManager);

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/common/show_login");
        shiroFilterFactoryBean.setSuccessUrl("/common/show_dashboard");

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/common/show_login");
        shiroFilterFactoryBean.getFilters().put("logout", logoutFilter);

        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/static/metronic/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/common/**", "authc");
        filterChainDefinitionMap.put("/data/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        try {
            Filter shiroFilter = (Filter) shiroFilterFactoryBean.getObject();

            FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(shiroFilter);
            List<String> urlPatterns = new ArrayList<String>();
            urlPatterns.add("/*");
            urlPatterns.add("/restapis/*");
            filterRegistrationBean.setUrlPatterns(urlPatterns);
            //For using Servlet 3.0 async
            filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
            return filterRegistrationBean;
        } catch (Exception e) {
            throw new BeanInstantiationException(FilterRegistrationBean.class,
                    "A error in terms of instantiating bean occured!, errmsg:[" + e.getMessage() + "]");
        }
    }

}
