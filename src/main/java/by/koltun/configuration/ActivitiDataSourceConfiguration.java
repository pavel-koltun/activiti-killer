package by.koltun.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiDataSourceConfiguration {

    @Value("${activiti.datasource.driver-class-name}")
    private String activitiDataSourceDriverClassName;
    @Value("${activiti.datasource.url}")
    private String activitiDataSourceUrl;
    @Value("${activiti.datasource.username}")
    private String activitiDataSourceUsername;
    @Value("${activiti.datasource.password}")
    private String activitiDataSourcePassword;

    @Bean
    public ActivitiDataSourceManager activitiDataSource() {
        return new ActivitiDataSourceManager(
                activitiDataSourceDriverClassName,
                activitiDataSourceUrl,
                activitiDataSourceUsername,
                activitiDataSourcePassword
        );
    }
}
