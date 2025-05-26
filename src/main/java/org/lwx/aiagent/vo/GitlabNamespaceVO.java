package org.lwx.aiagent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "Gitlab 命名空间")
public class GitlabNamespaceVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String path;
    private String kind;
    private String fullPath;
    private Long parentId;
    private String avatarUrl;
    private String webUrl;
}
