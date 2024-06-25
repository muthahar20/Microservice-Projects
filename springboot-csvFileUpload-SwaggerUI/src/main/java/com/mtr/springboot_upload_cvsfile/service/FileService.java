package com.mtr.springboot_upload_cvsfile.service;

import com.mtr.springboot_upload_cvsfile.model.UserFile;
import com.mtr.springboot_upload_cvsfile.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    public ResponseEntity<String> uploadFile(MultipartFile file) {
        log.info("FileService::uploadFile. Attempting to save file in DB :");

        long start =    System.currentTimeMillis();
        try {
            List<UserFile> uploadFile = new ArrayList<>();
            getCSVRecordsFormatFile(file).forEach(record -> {
               log.info("Row : {} {} {} ",
                record.get(0),record.get(1),record.get(2));
                uploadFile.add(UserFile.builder()
                        .fileId(record.get(0))
                        .name(record.get(1))
                        .color(record.get(2)).build());
            });
            fileRepository.saveAll(uploadFile);
            log.info("End FileService:: uploadFileFromCSV. Successfully saved row in DB in {} ms",
                    System.currentTimeMillis()-start);

        }catch (Exception e){
            return handleException(e, "uploadFileFormatCSV");
        }


        return ResponseEntity.ok().body("Success");
    }

    private ResponseEntity<String> handleException(Exception e, String method) {
        log.error("File Service:: handleException Received {} from method", e, method);
        if(e instanceof IOException){
            return ResponseEntity.internalServerError().body("IOException occurred: "+e.getMessage());
        }else {
            return ResponseEntity.internalServerError().body("Exception Occurred"+e.getMessage());
        }
    }

    private List<CSVRecord> getCSVRecordsFormatFile(MultipartFile file) throws IOException {
        log.info("FileService::getCSVRecordsFormatFile");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        return csvParser.getRecords();
    }
}
