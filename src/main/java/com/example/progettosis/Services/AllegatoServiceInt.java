package com.example.progettosis.Services;

import com.example.progettosis.Entities.Allegato;
import org.springframework.web.multipart.MultipartFile;

public interface AllegatoServiceInt {
    Allegato saveAllegato(MultipartFile file,String mail) throws Exception;

    Allegato getAllegato(String fileId) throws Exception;
}
