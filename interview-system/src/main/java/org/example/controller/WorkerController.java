package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.domain.Worker;
import org.example.service.WorkerService;
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
@RequestMapping("/worker")
public class WorkerController {
    private static final String WORKER_SESSION_NAME = "WorkerInfo";

    @Resource
    private WorkerService workerService;

    @PostMapping("/login")
    public Result<Worker> loginController(@RequestBody Worker worker, HttpServletRequest request){
        Result<Worker> result;
        result = workerService.loginService(worker);

        if (result.getCode().equals("0")){
            request.getSession().setAttribute(WORKER_SESSION_NAME, result.getData());
        }
        return result;
    }

    @PostMapping("/register")
    public Result<Worker> registerController(@RequestBody Worker newWorker){
        newWorker.setAddress("null");
        newWorker.setImage("null");
        newWorker.setName(newWorker.getWorkerID());
        Result<Worker> result;
        if (newWorker.getWorkerID().isEmpty() || newWorker.getPassword().isEmpty()){
            result = new Result<>();
            result.setMsg("用户名或密码为空！");
            return result;
        }

        result = workerService.registerService(newWorker);

        return result;
    }

    @PostMapping("/query/workerID")
    public Result<Worker> searchByIDController(@RequestParam String workerID){
        Worker worker = workerService.findByWorkerIDService(workerID);
        if (worker == null){
            return Result.success(worker, "Not found!");
        }
        return Result.success(worker);
    }

    @PostMapping("/query/name")
    public Result<List<Worker>> searchByNameController(@RequestParam String name){
        List<Worker> workers = workerService.findByNameService(name);
        if (workers.isEmpty()){
            return Result.success(workers, "Not found!");
        }
        return Result.success(workers);
    }

    @PostMapping("/showAll")
    public Result<List<Worker>> modifyController(){
        List<Worker> workers = workerService.findALlService();
        if (workers.isEmpty()){
            return Result.success(workers, "Not found!");
        }
        return Result.success(workers);
    }

    @PostMapping("/update")
    public Result<Worker> updateWorkerController(@RequestBody Worker newWorker){
        Worker worker = workerService.updateWorkerInfoService(newWorker);
        if (worker == null){
            return Result.error("500", "Not existed");
        } else {
            return Result.success(worker, worker.getName());
        }
    }

    @PostMapping("/delete")
    public Result<Worker> deleteWorkerController(@RequestBody Worker worker){
        Worker oldWorker = workerService.deleteWorkerService(worker);
        if (oldWorker == null){
            return Result.error("500", "Not existed");
        } else {
            return Result.success(oldWorker, oldWorker.getName());
        }
    }

    @PostMapping("/uploadImage")
    public Result<String> uploadImage(@RequestPart(value = "image") MultipartFile file, HttpSession session) throws IOException {
        String newFileName = FileName.getNewFileName(file.getOriginalFilename());
        Worker worker = (Worker) session.getAttribute(WORKER_SESSION_NAME);
        if (!FileUtils.saveImage(file, newFileName)){
            return Result.error("500", "save image err.");
        }
        else {
            worker.setImage(newFileName);
            workerService.updateWorkerInfoService(worker);
            return Result.success(newFileName, "ok");
        }
    }

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/getImage/{image}")
    public ResponseEntity getImage(@PathVariable String image) {
        String path = "file:" + FileUtils.IMAGE_LOCATION + image;
        System.out.println(path);
        return ResponseEntity.ok(resourceLoader.getResource(path));
    }


    @PostMapping("logout")
    public Result<Void> logout(HttpServletRequest request) {
        request.getSession().setAttribute(WORKER_SESSION_NAME, null);
        return Result.success();
    }
}
