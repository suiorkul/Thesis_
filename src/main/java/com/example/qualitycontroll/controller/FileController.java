package com.example.qualitycontroll.controller;

import com.example.qualitycontroll.config.MediaTypeUtils;
import com.example.qualitycontroll.dal.entity.Analysis;
import com.example.qualitycontroll.dal.repository.AnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final AnalysisRepository analysisRepository;
    private final ServletContext servletContext;

    @GetMapping("/download/{id}/{name}")
    public ResponseEntity<byte[]> download(@PathVariable Long id, @PathVariable String name, HttpServletResponse response) throws IOException {
        Analysis analysis = analysisRepository.findById(id).orElseThrow();
        File file = Objects.equals(name, "turkey") ? analysis.getDocumentFromTurkey()
                : analysis.getDepartmentDocument();
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, file.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(new byte[(int) file.length()]);
    }

}
