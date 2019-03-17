package by.koltun.controller;

import by.koltun.configuration.ActivitiDataSourceManager;
import by.koltun.domain.Connection;
import by.koltun.exception.DataSourceInstantiationException;
import by.koltun.exception.DataSourceNotFoundException;
import by.koltun.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/switch")
public class SwitchController {

    private ConnectionService connectionService;
    private ActivitiDataSourceManager activitiDataSourceManager;

    @Autowired
    public void setDataSourceManager(final ConnectionService connectionService,
                                     final ActivitiDataSourceManager activitiDataSourceManager) {
        this.connectionService = connectionService;
        this.activitiDataSourceManager = activitiDataSourceManager;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Connection> switchDataSource(@PathVariable(value = "id") final Long id)
            throws DataSourceNotFoundException, DataSourceInstantiationException {
        return connectionService.getById(id)
                .map(connection -> {
                    activitiDataSourceManager.setConnection(connection);
                    return ResponseEntity.ok(connection);
                }).orElseThrow(DataSourceNotFoundException::new);
    }
}
