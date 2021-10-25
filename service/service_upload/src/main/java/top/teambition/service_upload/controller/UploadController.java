package top.teambition.service_upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.teambition.service_upload.service.UploadService;
import top.teambition.service_upload.uploadTypeEnum.UploadType;
import top.teambition.utils.ResponseResult;

/**
 * @author liupengyu
 * @version 2021年07月05日 16:45
 */
@RestController
@RequestMapping("/service_upload/file")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("/ossUploadFile")
    public ResponseResult ossUploadFile(MultipartFile file, Integer type){
        String url = "";
        if (UploadType.COS.getId().equals(type)){
            url = uploadService.uploadFile(file);
        } else if (UploadType.MINIO.getId().equals(type)){
            url = uploadService.uploadFileByMinio(file);
        }
        return ResponseResult.ok().data("url", url);
    }

    @GetMapping("/ossGetFileUrl")
    public ResponseResult ossGetFileUrl(String bucketName, String fileName, Integer type){
        String url = "";
        if (UploadType.COS.getId().equals(type)){

        } else if (UploadType.MINIO.getId().equals(type)){
            url = uploadService.getFileUrlByMinio(bucketName, fileName);
        }
        return ResponseResult.ok().data("url", url);
    }
}
