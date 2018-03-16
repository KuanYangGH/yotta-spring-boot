package com.xjtu.domain.controller;

import com.xjtu.common.domain.Result;
import com.xjtu.common.domain.ResultEnum;
import com.xjtu.domain.service.DomainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *api:处理domain课程数据
 *@author:yangkuan
 *@date:2018/03/08 14:35
 */
@RestController
@RequestMapping(value = "/domain")
public class DomainController {

    @Autowired
    private DomainService domainService;


    @GetMapping("/getDomainsBySubject")
    @ApiOperation(value = "获得学科和课程信息，不包含主题信息", notes = "获得学科和课程信息，不包含主题信息")
    public ResponseEntity getDomainsBySubject(){
        Result result = domainService.findDomainsBySubject();
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getDomains")
    @ApiOperation(value = "获得所有课程信息", notes = "获得所有课程信息")
    public ResponseEntity getDomains(){
        Result result = domainService.findDomains();
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 读取domain，得到所有领域名和各领域下主题、分面、碎片、关系的数量
     *  未写
     */

    /**
     * 插入一门课程
     * insertDomain
     */
    @GetMapping("/insertDomain")
    @ApiOperation(value = "插入一门课程", notes = "插入一门课程")
    public ResponseEntity insertDomain(@RequestParam(name = "domainName") String domainName){
        Result result = domainService.insertDomainByName(domainName);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/countDomains")
    @ApiOperation(value = "统计课程数量", notes = "统计课程数量")
    public ResponseEntity countDomains(){
        Result result = domainService.countDomains();
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/updateDomainByDomainName")
    @ApiOperation(value = "更新课程名", notes = "更新课程名")
    public ResponseEntity updateDomainByDomainName(@RequestParam(name = "oldDomainName") String oldDomainName
            , @RequestParam(name = "newDomainName") String newDomainName){
        Result result = domainService.updateDomainByDomainName(oldDomainName, newDomainName);
        if (!result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    /**
     * 按课程转换RDF数据
     * 未写
     */




}
