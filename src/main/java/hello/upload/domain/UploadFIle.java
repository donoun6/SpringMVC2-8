package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFIle {

    //같은 이름의 파일을 등록하면 덮어씌우기 때문에 UUID처럼 겹치지 않게 저장하기 위해 따로 만든다.
    private String uploadFileName;
    private String storeFileName;

    public UploadFIle(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
