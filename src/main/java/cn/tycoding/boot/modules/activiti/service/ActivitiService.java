package cn.tycoding.boot.modules.activiti.service;

import org.activiti.engine.repository.Deployment;

import java.util.ArrayList;

public interface ActivitiService {

    public Deployment DeploymentProcess(ArrayList<String> classPath, String name);

    void StartProcess();

    void GetTask();
}
