package com.example.progettosis.Controllers;

import com.example.progettosis.Entities.Storico;
import com.example.progettosis.Repositories.Rep;
import com.example.progettosis.Services.Servizio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/controller")
public class CronologiaController {

    @Autowired
    private Rep repo;

    @Autowired
    private Servizio serv;


    @GetMapping("/tutto")
    public List<Storico> tutto() throws IOException {
        return repo.findAll() ;
    }

    @GetMapping("/mail")
    public List<Storico> maisearch(@RequestParam("email")String mail) throws IOException {
        return repo.findByEmail(mail) ;
    }

    @GetMapping("/aggiungi")
    public ResponseEntity agg(@RequestParam("email")String mail,@RequestParam("tipo") String tipo) throws  IOException{
        try{
            return new ResponseEntity(serv.salva(mail, tipo), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Errore");
        }
    }
}


