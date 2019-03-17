package by.koltun.configuration;

import by.koltun.domain.Connection;
import by.koltun.exception.DataSourceInstantiationException;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class ActivitiDataSourceManager implements DataSource {

    private DataSource dataSource;

    ActivitiDataSourceManager(final String driverClassName,
                              final String url,
                              final String username,
                              final String password) throws DataSourceInstantiationException {
        this.dataSource = createDataSource(
                driverClassName,
                url,
                username,
                password
        );
    }

    private DataSource createDataSource(final Connection connection)
            throws DataSourceInstantiationException {
        return createDataSource(
                connection.getDriverClassName(),
                connection.getUrl(),
                connection.getUsername(),
                connection.getPassword()
        );
    }

    private DataSource createDataSource(final String driverClassName,
                                        final String url,
                                        final String username,
                                        final String password) throws DataSourceInstantiationException {
        try {
            return DataSourceBuilder.create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .username(username)
                    .password(password)
                    .build();
        } catch (Exception e) {
            throw new DataSourceInstantiationException();
        }
    }

    public void setConnection(final Connection connection) throws DataSourceInstantiationException {
        this.dataSource = createDataSource(connection);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSource.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSource.isWrapperFor(iface);
    }

    @Override
    public java.sql.Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public java.sql.Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }
}
