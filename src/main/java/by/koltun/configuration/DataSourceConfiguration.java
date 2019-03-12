package by.koltun.configuration;

import by.koltun.DataSourceManager;
import by.koltun.service.DataSourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    private final DataSourceService dataSourceService;

    public DataSourceConfiguration(final DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @Bean
    public DataSourceManager dataSource() {
        return new DataSourceManager(dataSourceService.getById("default").orElse(null));
    }
}
