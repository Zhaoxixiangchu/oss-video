package top.teambition.service_upload.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liupengyu
 * @version 2021年07月05日 16:39
 */
@Component
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConstant{

    private String endPoint;

    private String bucketName;

    private String secretId;

    private String secretKey;

    private String cosRegion;

    private Integer port;


}
