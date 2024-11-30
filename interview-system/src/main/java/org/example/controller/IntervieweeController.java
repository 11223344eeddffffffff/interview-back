package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.domain.Interviewee;
import org.example.service.IntervieweeService;
import org.example.utils.FileName;
import org.example.utils.FileUtils;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/interviewee")
public class IntervieweeController {
    private static final String WORKER_SESSION_NAME = "WorkerInfo";

    @Resource
    private IntervieweeService intervieweeService;

    @PostMapping("/login")
    public Result<Interviewee> loginController(@RequestBody Interviewee interviewee, HttpServletRequest request){
        Result<Interviewee> result;
        result = intervieweeService.loginService(interviewee);

        if (result.getCode().equals("0")){
            request.getSession().setAttribute(WORKER_SESSION_NAME, result.getData());
        }
        return result;
    }

    @PostMapping("/register")
    public Result<Interviewee> registerController(@RequestBody Interviewee newInterviewee){
        newInterviewee.setName(newInterviewee.getIntervieweeId());
        Result<Interviewee> result;
        if (newInterviewee.getIntervieweeId().isEmpty() || newInterviewee.getPassword().isEmpty()){
            result = new Result<>();
            result.setMsg("用户名或密码为空！");
            return result;
        }

        result = intervieweeService.registerService(newInterviewee);

        return result;
    }

    @PostMapping("/query/intervieweeId")
    public Result<Interviewee> searchByIDController(@RequestParam String workerID){
        Interviewee interviewee = intervieweeService.findByIntervieweeIdService(workerID);
        if (interviewee == null){
            return Result.success(interviewee, "Not found!");
        }
        return Result.success(interviewee);
    }

    @PostMapping("/query/name")
    public Result<List<Interviewee>> searchByNameController(@RequestParam String name){
        List<Interviewee> interviewees = intervieweeService.findByNameService(name);
        if (interviewees.isEmpty()){
            return Result.success(interviewees, "Not found!");
        }
        return Result.success(interviewees);
    }

    @GetMapping("/showAll")
    public Result<List<Interviewee>> modifyController(){
        List<Interviewee> interviewees = intervieweeService.findALlService();
        if (interviewees.isEmpty()){
            return Result.success(interviewees, "Not found!");
        }
        return Result.success(interviewees);
    }

    @PostMapping("/update")
    public Result<Interviewee> updateWorkerController(@RequestBody Interviewee newInterviewee){
        Interviewee interviewee = intervieweeService.updateIntervieweeInfoService(newInterviewee);
        if (interviewee == null){
            return Result.error("500", "Not existed");
        } else {
            return Result.success(interviewee, interviewee.getName());
        }
    }

    @PostMapping("/delete")
    public Result<Interviewee> deleteWorkerController(@RequestBody Interviewee interviewee){
        Interviewee oldInterviewee = intervieweeService.deleteIntervieweeService(interviewee);
        if (oldInterviewee == null){
            return Result.error("500", "Not existed");
        } else {
            return Result.success(oldInterviewee, oldInterviewee.getName());
        }
    }

    @PostMapping("/uploadImage")
    public Result<String> uploadImage(@RequestPart(value = "image") MultipartFile file, HttpSession session) throws IOException {
        String newFileName = FileName.getNewFileName(file.getOriginalFilename());
        Interviewee interviewee = (Interviewee) session.getAttribute(WORKER_SESSION_NAME);
        if (!FileUtils.saveImage(file, newFileName)){
            return Result.error("500", "save image err.");
        }
        else {
            intervieweeService.updateIntervieweeInfoService(interviewee);
            return Result.success(newFileName, "ok");
        }
    }

    @Autowired
    ResourceLoader resourceLoader;

//    @GetMapping("/getImage/{image}")
//    public ResponseEntity getImage(@PathVariable String image) {
//        String path = "file:" + FileUtils.IMAGE_LOCATION + image;
//        System.out.println(path);
//        return ResponseEntity.ok(resourceLoader.getResource(path));
//    }


    @PostMapping("logout")
    public Result<Void> logout(HttpServletRequest request) {
        request.getSession().setAttribute(WORKER_SESSION_NAME, null);
        return Result.success();
    }
}
