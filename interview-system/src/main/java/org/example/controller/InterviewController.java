package org.example.controller;

import org.example.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/interview")
public class InterviewController {
    @PostMapping("/enterroom")
    public Result<String> enterRoomController(@RequestBody  String roomNumber){
        return new Result<>(roomNumber);
    }

    @PostMapping("/createroom")
    public Result<String> createRoomController(@RequestBody  String roomNumber){
        Random random = new Random();
        Integer number  = (Integer) random.nextInt(100, 9999999);
        String roomnumber = number.toString();
        return new Result<>(roomnumber);
    }
}
