package com.jwtauth.schoolauthorization.config.management.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(
  value = "com.jwtauth.schoolauthorization",
  annotationClass = ManagementDatabaseConnMapper.class,
  sqlSessionFactoryRef = "ManagementDatabaseSessionFactory")
@Configuration
public class ManagementDatabaseConfig {
  @Bean(name = "ManagementDataSource")
  @ConfigurationProperties(prefix = "spring.management.datasource")
  public DataSource managementDatabaseDataSource() {

    return DataSourceBuilder.create()
      .driverClassName("org.postgresql.Driver")
      .url("jdbc:postgresql://localhost:5432/management")
      .username("postgres")
      .password("root")
      .build();
  }

  @Bean(name = "ManagementDatabaseSessionFactory")
  public SqlSessionFactory managementDatabaseSessionFactory(
    @Qualifier("ManagementDataSource") DataSource managementDataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(managementDataSource);
    sqlSessionFactoryBean.setMapperLocations(
      new PathMatchingResourcePatternResolver().getResources("mapper/management/*.xml"));
    sqlSessionFactoryBean.setTypeAliasesPackage("mybatis.com.mybatis.ManagementEntity");
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "ManagementDatabaseSessionTemplate")
  public SqlSessionTemplate managementDatabaseSessionTemplate(
    @Qualifier("ManagementDatabaseSessionFactory")
    SqlSessionFactory managementSessionTemplate) {
    return new SqlSessionTemplate(managementSessionTemplate);
  }

  @Bean(name = "ManagementDatabaseTransactionManager")
  public DataSourceTransactionManager managementDatabaseTransactionManager(
    @Qualifier("ManagementDataSource")
    DataSource managementDataSource) {

    return new DataSourceTransactionManager(managementDataSource);
  }
}
