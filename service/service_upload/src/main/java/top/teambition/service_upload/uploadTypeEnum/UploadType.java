package top.teambition.service_upload.uploadTypeEnum;

public enum UploadType {
    //COS
    COS(1, "COS"),
    //MINIO
    MINIO(2, "MINIO");

    UploadType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private final Integer id;
    private final String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
