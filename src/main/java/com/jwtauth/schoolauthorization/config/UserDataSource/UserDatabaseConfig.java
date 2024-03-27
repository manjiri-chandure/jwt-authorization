package com.jwtauth.schoolauthorization.config.UserDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(
        value = "com.jwtauth.schoolauthorization",
        annotationClass = UserDatabaseConnMapper.class,
        sqlSessionFactoryRef = "UserDatabaseSessionFactory")
@Configuration
public class UserDatabaseConfig {
    @Bean(name = "UserDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.user.datasource")
    public DataSource userDatabaseDataSource(){
     return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/jwt")
                .username("postgres")
                .password("root")
                .build();
    }

    @Bean(name = "UserDatabaseSessionFactory")
    public SqlSessionFactory userDatabaseSessionFactory(
            @Qualifier("UserDataSource") DataSource userDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(userDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("mapper/user/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jwtauth.schoolauthorization.Entity");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "UserDatabaseSessionTemplate")
    public SqlSessionTemplate userDatabaseSessionTemplate(
            @Qualifier("UserDatabaseSessionFactory")
            SqlSessionFactory userSessionTemplate){
        return new SqlSessionTemplate(userSessionTemplate);
    }

    @Bean(name = "UserDatabaseTransactionManager")
    public DataSourceTransactionManager userDatabaseTransactionManager(
            @Qualifier("UserDataSource")
            DataSource userDataSource) {

        return new DataSourceTransactionManager(userDataSource);
    }
}
