package com.zhushou.test1.dal.config;

import com.zhushou.test1.dal.mapper.*;
import com.zhushou.test1.dal.mybatis.IdTypeHandler;
import com.yuntao.platform.common.interceptor.MyBatisInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;
import javax.sql.DataSource;

/**
 * 等价于spring-dao.xml
 */
@Configuration
@ImportResource("classpath:applicationContext-dal.xml")
public class DalConfig implements ApplicationContextAware {

    @Value("classpath:mybatis-config.xml")
    Resource mybatisMapperConfig;

    @Autowired
    DataSource dataSource;

    ApplicationContext applicationContext;

        @Bean
    public DemoMapper demoMapper() throws Exception {
        return newMapperFactoryBean(DemoMapper.class).getObject();
    }
    
	@Bean
    public UserMapper userMapper() throws Exception {
        return newMapperFactoryBean(UserMapper.class).getObject();
    }

	<T> MapperFactoryBean<T> newMapperFactoryBean(Class<T> clazz)
            throws Exception {
        MapperFactoryBean<T> b = new MapperFactoryBean<T>();
        b.setMapperInterface(clazz);
        b.setSqlSessionFactory(sqlSessionFactory());
        return b;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setConfigLocation(mybatisMapperConfig);
        fb.setDataSource(dataSource);
        fb.setTypeAliases(new Class<?>[]{IdTypeHandler.class});
        fb.setPlugins(new Interceptor[] { new MyBatisInterceptor(this.applicationContext) });
        return fb.getObject();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}