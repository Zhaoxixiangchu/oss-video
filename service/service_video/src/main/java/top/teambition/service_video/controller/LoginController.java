package top.teambition.service_video.controller;

import org.springframework.web.bind.annotation.*;
import top.teambition.utils.ResponseResult;

/**
 * @author liupengyu
 * @version 2021年07月15日 16:31
 */
@RestController
@RequestMapping("/user")
//处理跨域问题
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public ResponseResult login(){
        return ResponseResult.ok().data("token", "admin-token");
    }

    @GetMapping("/info")
    public ResponseResult info(){
        return ResponseResult.ok().data("roles", "['admin']-token").data("introduction", "I am a super administrator")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .data("name", "It's Me");
    }
}
