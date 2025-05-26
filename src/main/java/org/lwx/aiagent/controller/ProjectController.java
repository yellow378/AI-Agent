package org.lwx.aiagent.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.lwx.aiagent.dto.Response;
import org.lwx.aiagent.service.GitlabService;
import org.lwx.aiagent.utils.bean.BeanCopyCallBack;
import org.lwx.aiagent.utils.bean.BeanHelper;
import org.lwx.aiagent.vo.GitlabNamespaceVO;
import org.lwx.aiagent.vo.GitlabProjectVO;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@Slf4j
public class ProjectController extends BaseController {

    @Autowired
    private GitlabService gitlabService;

    @GetMapping("/gitlab/projects")
    @Operation(summary = "查询gitlab所有项目")
    public Response<List<GitlabProjectVO>> getProjects() throws GitLabApiException {
        List<GitlabProjectVO> voProjects = null;
        try{
            List<Project> projects = gitlabService.getProjects();
            voProjects = BeanHelper.copyList(projects, GitlabProjectVO::new, new BeanCopyCallBack() {
                @Override
                public void callBack(Object o, Object o2) {
                    ((GitlabProjectVO) o2).setNamespace(BeanHelper.copyAndReturn(((Project) o).getNamespace(), GitlabNamespaceVO::new));
                }
            });
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return successWithData(voProjects);
    }

    @GetMapping("/gitlab/{projectId}/commits")
    public Response<List<Commit>> getCommits(@PathVariable Long projectId) throws GitLabApiException {
        log.info("getCommits, projectId: {}", projectId);
        List<Commit> commits = gitlabService.getProjectCommits(projectId);
        return successWithData(commits);
    }
}
