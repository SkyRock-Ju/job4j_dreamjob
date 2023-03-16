package com.dreamjob.repository;

import com.dreamjob.model.File;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FileRepository {

    File save(File file);

    Optional<File> findById(int id);

    boolean deleteById(int id);
}
