package by.koltun;

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

    public DataSourceManager() {}

    public DataSourceManager(final DataSourceTO defaultDataSourceTO) {
        this.dataSource = createDataSource(defaultDataSourceTO);
    }

    private DataSource createDataSource(final DataSourceTO dataSourceTO) {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceTO.getDriverClassName())
                .url(dataSourceTO.getUrl())
                .username(dataSourceTO.getUsername())
                .password(dataSourceTO.getPassword())
                .build();
    }

    public void switchDataSource(final DataSourceTO dataSourceTO) {
        this.dataSource = createDataSource(dataSourceTO);
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
