package com.example.progettosis.Services;


import com.example.progettosis.Entities.Storico;
import com.example.progettosis.Repositories.Rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class Servizio {
    @Autowired
    private Rep repository;

    @Transactional(readOnly = true)
    public List<Storico> getCronoTot() throws Exception {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Storico> getCrono(String mail) throws Exception {
        return repository.findByEmail(mail);
    }

    @Transactional(readOnly = false)
    public Storico salva(String mail, String tipo) throws  Exception{
        Storico s = new Storico(mail,tipo);
        repository.save(s);
        return s;
    }

}
