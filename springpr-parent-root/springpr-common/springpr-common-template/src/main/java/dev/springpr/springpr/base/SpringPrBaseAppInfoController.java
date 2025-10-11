/* (C)2023 */
package dev.springpr.springpr.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/${app.id}")
public class SpringPrBaseAppInfoController {
    private final Environment env;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.app.artifactId}")
    private String artifactId;

    @Value("${git.commit.id.abbrev}")
    private String commitId;

    @Value("${git.branch}")
    private String branch;

    @Value("${git.build.time}")
    private String buildTime;

    @Value("${git.build.version}")
    private String buildVersion;

    @Value("${git.commit.time}")
    private String commitTime;

    @Value("${app.id}")
    private String appId;

    @Value("${management.endpoints.web.base-path}")
    private String managementBasePath;

    @Value("${app.hostname}")
    private String appHostname;

    @Value("${app.port}")
    private String appPort;

    @Value("${app.protocol}")
    private String appProtocol;

    @GetMapping(value = "welcome", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    @SuppressWarnings("squid:S1192")
    public String welcomeAsHTML() {
        return "<html>\n"
                + "<header><title>"
                + appName
                + " on "
                + appHostname
                + "</title></header>\n"
                + "<body>\n"
                + "<h1>"
                + appName
                + "</h1>\n"
                + "<p><b>ip:</b>"
                + appHostname
                + "</b></p>\n"
                + "<p><b>artifactId:</b>"
                + artifactId
                + "</p>\n"
                + "<p><b>build version:</b>"
                + buildVersion
                + "</p>\n"
                + "<p><b>branch:</b>"
                + branch
                + "</p>\n"
                + "<p><b>commit id:</b>"
                + commitId
                + "</p>\n"
                + "<p><b>commit time:</b>"
                + commitTime
                + "</p>\n"
                + "<br></br>"
                + "<p><a href=\"simpleHealthCheck\"><b>simple health check</b></a></p>\n"
                + "<p><a href=\""
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/health\"><b>health</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/info><b>info</b></a></p>\n"
                + "<br>"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/env><b>env</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/prometheus><b>metrics</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/beans><b>beans</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/conditions><b>conditions</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/configprops><b>Configured Properties</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/scheduledtasks><b>Scheduled Tasks</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/caches><b>caches</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/mappings><b>endponts</b></a></p>\n"
                + "<br>"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/openapi><b>API Docs</b></a></p>\n"
                + "<p><a href="
                + this.appProtocol
                + "://"
                + this.appHostname
                + ":"
                + this.appPort
                + this.managementBasePath
                + "/swagger-ui><b>Swagger UI</b></a></p>\n"
                + "</body>\n"
                + "</html>";
    }
}
