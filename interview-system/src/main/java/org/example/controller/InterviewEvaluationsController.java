package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.domain.InterviewEvaluations;
import org.example.service.InterviewEvaluationsService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class InterviewEvaluationsController {
    @Autowired
    InterviewEvaluationsService interviewEvaluationsService;

    @PostMapping("/add")
    public Result<InterviewEvaluations> addInterviewEvaluations(@RequestBody InterviewEvaluations interviewEvaluations) {
        return interviewEvaluationsService.save(interviewEvaluations) ? new Result("添加成功") : new Result("添加失败");
    }

    @GetMapping("/select/{pageNo}/{intervieweeId}")
    public Result<IPage<InterviewEvaluations>> getAllInterviewEvaluations(@PathVariable int pageNo,@PathVariable String intervieweeId) {
        IPage<InterviewEvaluations> page = interviewEvaluationsService.getAllRecord(pageNo,10,intervieweeId);
        if(pageNo > page.getPages()){
            page = interviewEvaluationsService.getAllRecord((int)page.getPages(),10,intervieweeId);
        }
        return new Result<>(page);
    }

    @GetMapping("/search/{pageNo}/{interviewId}")
    public Result<IPage<InterviewEvaluations>> getById(@PathVariable int pageNo,@PathVariable String interviewId) {
        IPage<InterviewEvaluations> page = interviewEvaluationsService.getAllRecord(pageNo,10,interviewId);
        if(pageNo > page.getPages()){
            page = interviewEvaluationsService.getAllRecord((int)page.getPages(),10,interviewId);
        }
        return new Result<>(page);
    }
}
