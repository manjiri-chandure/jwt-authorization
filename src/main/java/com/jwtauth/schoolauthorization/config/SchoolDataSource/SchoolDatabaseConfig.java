package com.jwtauth.schoolauthorization.config.SchoolDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(
        value = "com.jwtauth.schoolauthorization",
        annotationClass = SchoolDatabaseConnMapper.class,
        sqlSessionFactoryRef = "SchoolDatabaseSessionFactory")
@Configuration
public class SchoolDatabaseConfig {
    @Bean(name = "SchoolDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.school.datasource")
    public DataSource schoolDatabaseDataSource(){
     return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/school")
                .username("postgres")
                .password("root")
                .build();
    }

    @Bean(name = "SchoolDatabaseSessionFactory")
    public SqlSessionFactory schoolDatabaseSessionFactory(
            @Qualifier("SchoolDataSource") DataSource schoolDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(schoolDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("mapper/school/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jwtauth.schoolauthorization.entity");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SchoolDatabaseSessionTemplate")
    public SqlSessionTemplate schoolDatabaseSessionTemplate(
            @Qualifier("SchoolDatabaseSessionFactory")
            SqlSessionFactory schoolSessionTemplate){
        return new SqlSessionTemplate(schoolSessionTemplate);
    }

    @Bean(name = "SchoolDatabaseTransactionManager")
    public DataSourceTransactionManager schoolDatabaseTransactionManager(
            @Qualifier("SchoolDataSource")
            DataSource schoolDataSource) {

        return new DataSourceTransactionManager(schoolDataSource);
    }
}
