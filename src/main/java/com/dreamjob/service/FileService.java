package com.dreamjob.service;

import com.dreamjob.model.File;
import com.dreamjob.model.FileDto;

import java.util.Optional;

public interface FileService {

    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);

}
