package top.teambition.service_video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liupengyu
 * @version 2021年07月03日 15:06
 */
@SpringBootApplication
@MapperScan("top.teambition.service_video.mapper")
//组件扫描
@ComponentScan(basePackages = {"top.teambition"})
public class VideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }
}
