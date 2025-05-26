package org.lwx.aiagent.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Data
@Schema(name = "GitLab项目信息")
public class GitlabProjectVO {

    private Long id;
    private String name;
    private Date createdAt;
    private Date lastActivityAt;
    private String description;
    private String httpUrlToRepo;
    private GitlabNamespaceVO namespace;
    private String nameWithNamespace;
    private String pathWithNamespace;
    private String webUrl;
    @JsonProperty("_links")
    private Map<String, String> links;


}
