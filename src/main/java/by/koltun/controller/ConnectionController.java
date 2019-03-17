package by.koltun.controller;

import by.koltun.domain.Connection;
import by.koltun.exception.DataSourceNotFoundException;
import by.koltun.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/connection")
public class ConnectionController {

    private ConnectionService connectionService;

    @Autowired
    public void setDataSourceManager(final ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Connection> all() {
        return connectionService.getAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Connection> one(@PathVariable final Long id) {
        return ResponseEntity.ok(connectionService.getById(id).orElseThrow(DataSourceNotFoundException::new));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Connection create(@RequestBody final Connection payload) {
        return connectionService.save(payload);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Connection> update(@PathVariable final Long id, @RequestBody final Connection payload) {
        return connectionService.update(id, payload).map(ResponseEntity::ok)
                .orElseThrow(DataSourceNotFoundException::new);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        return connectionService.delete(id)
                .map((connection) -> ResponseEntity.status(HttpStatus.NO_CONTENT).build())
                .orElseThrow(DataSourceNotFoundException::new);
    }
}
