package top.teambition.service_upload.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liupengyu
 * @version 2021年07月05日 16:39
 */
@Component
public class OssConstant implements InitializingBean {

    @Value("${oss.endpoint}")
    private String endPoint;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.accessKeyId}")
    private String secretId;

    @Value("${oss.accessKeySecret}")
    private String secretKey;

    @Value("${oss.cosRegion}")
    private String cosRegion;

    public static String END_POINT;

    public static String BUCKET_NAME;

    public static String SECRET_ID;

    public static String SECRET_KEY;

    public static String COS_REGION;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        BUCKET_NAME = bucketName;
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        COS_REGION = cosRegion;
    }
}
