package org.lwx.aiagent.service;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Project;

import java.util.List;

public interface GitlabService {
    List<Project> getProjects() throws GitLabApiException;

    List<Commit> getProjectCommits(Long projectId) throws GitLabApiException;


    
}
