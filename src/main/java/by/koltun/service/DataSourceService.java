package by.koltun.service;

import by.koltun.model.DataSourceTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class DataSourceService {

    private static final String DEFAULT_DS_DRIVER = "org.h2.Driver";
    private static final String DEFAULT_DS_URL = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
    private static final String DEFAULT_DS_PASSWORD = "sa";
    private static final String DEFAULT_DS_USERNAME = "sa";

    private static final DataSourceTO defaultDataSource;
    private static final Map<String, DataSourceTO> dataSources = new HashMap<>();

    static {
        defaultDataSource = new DataSourceTO(
                DEFAULT_DS_DRIVER, DEFAULT_DS_URL, DEFAULT_DS_PASSWORD, DEFAULT_DS_USERNAME);
    }

    public DataSourceTO getDefaultDataSource() {
        return defaultDataSource;
    }

    public List<DataSourceTO> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(dataSources.values()));
    }

    public Optional<DataSourceTO> getById(final String id) {
        return Optional.ofNullable(dataSources.get(id));
    }

    public DataSourceTO save(final DataSourceTO dataSource) {
        dataSources.put(Objects.requireNonNull(dataSource.getId(), "Data source id is null"),
                Objects.requireNonNull(dataSource, "Data source is null."));
        return dataSource;
    }
}
