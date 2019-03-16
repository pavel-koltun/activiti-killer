package by.koltun;

import by.koltun.exception.DataSourceInstantiationException;
import by.koltun.model.DataSourceTO;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DataSourceManager implements DataSource {

    private DataSource dataSource;

    public DataSourceManager(final DataSourceTO defaultDataSource) throws DataSourceInstantiationException {
        this.dataSource = createDataSource(defaultDataSource);
    }

    private DataSource createDataSource(final DataSourceTO dataSource) throws DataSourceInstantiationException {
        try {
            return DataSourceBuilder.create()
                    .driverClassName(dataSource.getDriverClassName())
                    .url(dataSource.getUrl())
                    .username(dataSource.getUsername())
                    .password(dataSource.getPassword())
                    .build();
        } catch (Exception e) {
            throw new DataSourceInstantiationException();
        }
    }

    public void setDataSource(final DataSourceTO dataSource) throws DataSourceInstantiationException {
        this.dataSource = createDataSource(dataSource);
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
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }
}
