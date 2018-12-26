package com.magicalcoder.youyaboot.admin.api.demo;

import com.magicalcoder.youyaboot.admin.adminservice.demo.service.AdminServiceDemoService;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/admin/demo_rest/")
@RestController
public class DemoRestController  {
    @Resource
    private AdminServiceDemoService adminServiceDemoService;

    @RequestMapping(value = "/demo_goods/{id}")
    public ResponseMsg demoGoods(@PathVariable Long id){
        return new ResponseMsg(adminServiceDemoService.demoGoods(id));
    }

}
