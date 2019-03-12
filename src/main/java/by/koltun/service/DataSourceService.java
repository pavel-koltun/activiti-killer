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

    private static final Map<String, DataSourceTO> dataSources = new HashMap<>();

    static {
        dataSources.put("default", new DataSourceTO("oracle.jdbc.driver.OracleDriver",
                                                    "jdbc:oracle:thin:@localhost:FOS11G",
                                                    "username",
                                                    "password"));
    }

    public List<DataSourceTO> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(dataSources.values()));
    }

    public Optional<DataSourceTO> getById(final String id) {
        return Optional.ofNullable(dataSources.get(id));
    }

    public void save(final String id, final DataSourceTO dataSource) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dataSource);

        dataSources.put(id, dataSource);
    }
}
