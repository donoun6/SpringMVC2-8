package hello.upload.file;

import hello.upload.domain.UploadFIle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<UploadFIle> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFIle> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                if (!multipartFile.isEmpty()) {
                    extracted(storeFileResult, multipartFile);
                }
            }
        }
        return storeFileResult;
    }

    private void extracted(List<UploadFIle> storeFileResult, MultipartFile multipartFile) throws IOException {
        UploadFIle uploadFIle = storeFile(multipartFile);
        storeFileResult.add(uploadFIle);
    }

    //스프링이 제공하는 멀티파트 파일을 가지고 나에게 파일을 저장하고 업로드 파일로 바꿔서 반환
    public UploadFIle storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFimeName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFIle(originalFilename, storeFileName);
    }

    //서버에 저장하는 파일명
    private String createStoreFimeName(String originalFilename) {
        String ext = extracted(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    //확장자 가져오기
    private String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
