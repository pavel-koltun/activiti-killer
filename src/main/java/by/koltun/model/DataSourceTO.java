package by.koltun.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class DataSourceTO implements Serializable {

    private static final long serialVersionUID = 7658902544317678211L;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id = UUID.randomUUID().toString();

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public DataSourceTO() {}

    public DataSourceTO(final String driverClassName, final String url,
                        final String username, final String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(final String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
