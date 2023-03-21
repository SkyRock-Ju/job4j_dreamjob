package com.dreamjob.service;

import com.dreamjob.model.File;
import com.dreamjob.model.FileDto;
import net.jcip.annotations.ThreadSafe;

import java.util.Optional;

@ThreadSafe
public interface FileService {

    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);

}
