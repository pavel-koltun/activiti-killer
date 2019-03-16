package by.koltun.controller;

import by.koltun.DataSourceManager;
import by.koltun.exception.DataSourceInstantiationException;
import by.koltun.exception.DataSourceNotFoundException;
import by.koltun.model.DataSourceTO;
import by.koltun.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<DataSourceTO> all() {
        return dataSourceService.getAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataSourceTO> one(@PathVariable final String id) {
        return ResponseEntity.ok(dataSourceService.getById(id).orElseThrow(DataSourceNotFoundException::new));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public DataSourceTO create(@RequestBody final DataSourceTO dataSource) {
        return dataSourceService.save(dataSource);
    }

    @GetMapping(value = "/switch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataSourceTO> switchDataSource(@PathVariable(value = "id") final String id)
            throws DataSourceNotFoundException, DataSourceInstantiationException{
        final DataSourceTO dataSource = dataSourceService.getById(id).orElseThrow(DataSourceNotFoundException::new);
        dataSourceManager.setDataSource(dataSource);
        return ResponseEntity.ok(dataSource);
    }
}
