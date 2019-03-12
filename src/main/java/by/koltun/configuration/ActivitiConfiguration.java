package by.koltun.configuration;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            final DataSource dataSource,
            final PlatformTransactionManager platformTransactionManager,
            final SpringAsyncExecutor springAsyncExecutor) throws IOException {
        return baseSpringProcessEngineConfiguration(dataSource, platformTransactionManager, springAsyncExecutor);
    }
}
