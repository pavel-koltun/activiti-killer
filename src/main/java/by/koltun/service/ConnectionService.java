package by.koltun.service;

import by.koltun.domain.Connection;
import by.koltun.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConnectionService {

    private ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(final ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public List<Connection> getAll() {
        return connectionRepository.findAll();
    }

    public Optional<Connection> getById(final Long id) {
        return connectionRepository.findById(id);
    }

    public Connection save(final Connection connection) {
        return connectionRepository.save(connection);
    }

    public Optional<Connection> update(final Long id, final Connection connection) {
        final Optional<Connection> object = connectionRepository
                .findById(Objects.requireNonNull(id, "Connection id is null"));

        object.ifPresent(connectionRepository::save);

        return object;
    }

    public Optional<Connection> delete(final Long id) {
        return connectionRepository.findById(id)
                .map(connection -> {
                    connectionRepository.delete(connection);
                    return connection;
                });
    }
}
