package io.kid19999.backstage.controller;

import io.kid19999.backstage.model.College;
import io.kid19999.backstage.repository.collegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @desc:  学院信息
 * @auther: kid1999
 * @date: 2019/12/2 17:32
 **/
@RestController
public class CollegeController {

    @Autowired
    private collegeRepository collegeRepository;

    @GetMapping("/colleges")
    List<College> colleges(){
        return collegeRepository.findAll();
    }
}
