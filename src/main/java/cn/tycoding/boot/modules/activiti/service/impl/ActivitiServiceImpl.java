package cn.tycoding.boot.modules.activiti.service.impl;

import cn.tycoding.boot.modules.activiti.service.ActivitiService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    ProcessEngine processEngine;

    @Override
    public Deployment DeploymentProcess(ArrayList<String> classPath, String name) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .addClasspathResource(classPath.get(0))
                .addClasspathResource(classPath.get(1))
                .deploy();
        return deploy;
    }

    @Override
    public void StartProcess() {

    }

    @Override
    public void GetTask() {

    }
}
