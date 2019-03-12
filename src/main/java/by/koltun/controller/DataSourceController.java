package by.koltun.controller;

import by.koltun.DataSourceManager;
import by.koltun.model.DataSourceTO;
import by.koltun.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/datasources")
public class DataSourceController {

    private DataSourceService dataSourceService;
    private DataSourceManager dataSourceManager;

    @Autowired
    public void setDataSourceManager(final DataSourceService datasourceService,
                                     final DataSourceManager dataSourceManager) {
        this.dataSourceService = datasourceService;
        this.dataSourceManager = dataSourceManager;
    }

    @GetMapping
    public List<DataSourceTO> index() {
        return dataSourceService.getAll();
    }

    @GetMapping(value = "/switch/{id}")
    public ResponseEntity<String> datasource(@PathVariable(value = "id") final String id) {
        final Optional<DataSourceTO> dataSource = dataSourceService.getById(id);
        if (!dataSource.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        dataSourceManager.switchDataSource(dataSource.get());

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void create(@RequestBody final DataSourceTO dataSource) {
        dataSourceService.save(UUID.randomUUID().toString(), dataSource);
    }
}
