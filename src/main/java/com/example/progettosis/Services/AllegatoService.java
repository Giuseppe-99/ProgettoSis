package com.example.progettosis.Services;

import com.example.progettosis.Entities.Allegato;
import com.example.progettosis.Repositories.AllegatoRepository;
import org.keycloak.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AllegatoService implements AllegatoServiceInt{

    private AllegatoRepository allegatoRepository;

    public AllegatoService(AllegatoRepository allegatoRepository) {
        this.allegatoRepository = allegatoRepository;
    }

    @Override
    public Allegato saveAllegato(MultipartFile file,String mail) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid path sequence "+fileName);

            }
            Allegato allegato = new Allegato(fileName,file.getContentType(),mail, file.getBytes());
            return allegatoRepository.save(allegato);
        }catch (Exception e) {
            throw new Exception("impossibile salvare il file");
        }
    }

    @Override
    public Allegato getAllegato(String fileId) throws Exception {
        return allegatoRepository.findById(fileId).orElseThrow(() -> new Exception("File not found "+fileId));
    }
}
