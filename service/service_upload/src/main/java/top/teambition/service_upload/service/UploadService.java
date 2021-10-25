package top.teambition.service_upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author LPY
 */
public interface UploadService {

    /**
     * 上传文件到COS 且返回url
     * @param file  流
     * @return url
     */
    String uploadFile(MultipartFile file);

    /**
     * 上传文件到COS 且返回url
     * @param file  流
     * @return url
     */
    String uploadFileByMinio(MultipartFile file);

    String getFileUrlByMinio(String bucketName, String fileName);
}
