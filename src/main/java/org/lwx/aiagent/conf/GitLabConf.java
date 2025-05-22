package org.lwx.aiagent.conf;

import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GitLabConf {

    @Value("${gitlab.url}")
    String gitlabUrl;

    @Value("${gitlab.accessToken}")
    String gitlabAccessToken;

    @Bean
    public GitLabApi gitlab() {
        // Create a GitLabApi instance to communicate with your GitLab server
        GitLabApi gitLabApi = new GitLabApi(gitlabUrl, gitlabAccessToken);
        gitLabApi.setIgnoreCertificateErrors(true);
        return gitLabApi;
    }

}
