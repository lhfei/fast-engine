/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.engine.config;

import java.beans.PropertyVetoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 13, 2018
 */
@Configuration
public class DatasourceConfig {

private Logger LOG = LoggerFactory.getLogger(DatasourceConfig.class);
	
	@Bean	
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("ck-111");

        try {
            dataSource.setDriverClass(driverClassName);
        } catch (PropertyVetoException pve){
			LOG.debug("Cannot load datasource driver ({)} : {}", dbUrl, pve.getMessage());
            return null;
        }
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMinPoolSize(c3p0MinSize);
        dataSource.setMaxPoolSize(c3p0MaxSize);
        dataSource.setMaxIdleTime(poolIdlePeriod);

        return dataSource;
    }
	
	@Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    
    @Value("${c3p0.max_size}")
    private int c3p0MaxSize;
    
    @Value("${c3p0.min_size}")
    private int c3p0MinSize;
    
    @Value("${c3p0.idle_test_period}")
    private int poolIdlePeriod;
}
