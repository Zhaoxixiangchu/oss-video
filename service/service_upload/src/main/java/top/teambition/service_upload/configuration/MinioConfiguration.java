package top.teambition.service_upload.configuration;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.teambition.service_upload.utils.MinioConstant;

/**
 * @author liupengyu
 * @version 2021年10月16日 14:27
 */
@Configuration
public class MinioConfiguration {

    @Autowired
    private MinioConstant minioConstant;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient client = new MinioClient(minioConstant.getEndPoint(), minioConstant.getPort(), minioConstant.getSecretId(), minioConstant.getSecretKey());
        return client;
    }
}
