/* (C)2025 */
package org.springpr.springpr.yugabytedb.config;

import com.zaxxer.hikari.HikariConfig;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class YBClusterAwareDataSourceProperties {
    private String user = "Yugabyte";
    private String password = "";
    private String additionalEndpoints;
    private String topologyKeys;
    private String dataSourceClassName = "com.yugabyte.ysql.YBClusterAwareDataSource";
    private String databaseName;
    private String serverName = "localhost";
    private String portNumber = "5433";
    private String loadBalance = "false";
    private String poolName = "hikariPool";

    private HikariConfig hikari;
}
