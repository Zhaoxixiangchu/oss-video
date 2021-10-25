package top.teambition.service_upload.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.teambition.service_upload.service.UploadService;
import top.teambition.service_upload.utils.MinioConstant;
import top.teambition.service_upload.utils.OssConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author liupengyu
 * @version 2021年07月05日 16:47
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConstant minioConstant;

    @Override
    public String uploadFile(MultipartFile file) {
        String cosRegion = OssConstant.COS_REGION;
        String bucketName = OssConstant.BUCKET_NAME;
        String secretId = OssConstant.SECRET_ID;
        String secretKey = OssConstant.SECRET_KEY;

        COSCredentials cosCredentials = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(cosRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);

        COSClient cosClient = null;

        try {
            cosClient = new COSClient(cosCredentials, clientConfig);
            InputStream inputStream = file.getInputStream();
            //处理文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + "-" + file.getOriginalFilename();

            /*把同一天上传的文件 放到同一个文件夹当中  2020/10/1/fileName*/
            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date+"/"+fileName;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            cosClient.putObject(bucketName, fileName, inputStream, objectMetadata);

            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethodName.GET);
//            Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
            Date expirationDate = new Date(3000, Calendar.DECEMBER, 31);
            req.setExpiration(expirationDate);
            URL url = cosClient.generatePresignedUrl(req);
            return url.toString();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cosClient != null){
                cosClient.shutdown();
            }
        }
        return null;
    }

    @Override
    public String uploadFileByMinio(MultipartFile file) {
        String bucketName = minioConstant.getBucketName();
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                minioClient.makeBucket(bucketName);
            }

            InputStream inputStream = file.getInputStream();
            //处理文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + "-" + file.getOriginalFilename();

            /*把同一天上传的文件 放到同一个文件夹当中  2020/10/1/fileName*/
            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date+"/"+fileName;

            PutObjectOptions options = new PutObjectOptions(file.getSize(), -1);
            options.setContentType(file.getContentType());
            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(bucketName, fileName, inputStream,options);
            return minioClient.getPresignedObjectUrl(Method.GET, bucketName, fileName, 3600, null);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String getFileUrlByMinio(String bucketName, String fileName) {
        if (!StringUtils.isNotBlank(bucketName)){
            bucketName = minioConstant.getBucketName();
        }

        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist) {
                return "bucket不存在";
            }
            return minioClient.presignedGetObject(bucketName, fileName, 3600);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
