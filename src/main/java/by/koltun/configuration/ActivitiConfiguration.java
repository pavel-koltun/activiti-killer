package by.koltun.configuration;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;

@Configuration
public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            @Qualifier("activitiDataSource") final DataSource dataSource,
            final PlatformTransactionManager platformTransactionManager,
            final SpringAsyncExecutor springAsyncExecutor) throws IOException {
        activitiProperties.setCheckProcessDefinitions(false);

        final SpringProcessEngineConfiguration configuration =
                baseSpringProcessEngineConfiguration(dataSource, platformTransactionManager, springAsyncExecutor);

        configuration.setDatabaseSchemaUpdate(databaseSchemaUpdate(dataSource))
                .setDbHistoryUsed(false)
                .setDbIdentityUsed(false)
                .setCreateDiagramOnDeploy(false);

        return configuration;
    }

    private String databaseSchemaUpdate(final DataSource dataSource) {
        try (final Connection connection = dataSource.getConnection()) {
            if (connection.getMetaData().getDatabaseProductName().startsWith("H2")) {
                return ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE;
            }
        } catch (Exception ignored) {}

        return ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE;
    }
}
