package org.lwx.aiagent.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.lwx.aiagent.service.GitlabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GitlabServiceImpl implements GitlabService {

    @Autowired
    private GitLabApi gitLabApi;

    @Override
    public List<Project> getProjects() throws GitLabApiException {
        log.info("getProjects");
        List<Project> projects = gitLabApi.getProjectApi().getProjects();
        return projects;
    }
}
