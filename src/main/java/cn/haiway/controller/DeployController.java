package cn.haiway.controller;

import cn.haiway.process.DeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haiway  on 2019/5/30
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    @Autowired
    DeployService deployService;

    @RequestMapping("")
    public void deploy(){
        System.out.println("deploy");
        deployService.deploy();
    }
}
