package com.example.progettosis.Controllers;


import com.example.progettosis.Entities.Storico;
import com.example.progettosis.Repositories.Rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import supporto.src.DBScan;
import supporto.src.MainCset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prova")
public class CheckController {

    @Autowired
    private Rep repo;

    @GetMapping("/u")
    public ResponseEntity verifica() throws IOException {
        MainCset.run();
        return new ResponseEntity("Operazione effettuata", HttpStatus.OK);
    }

    @GetMapping("/v")
    public ResponseEntity verifica1(@RequestParam("path") String path) throws IOException {
        DBScan.run(path);
        return new ResponseEntity("Operazione effettuata", HttpStatus.OK);
    }

    @GetMapping("/tutto")
    public List<Storico> tutto() throws IOException {
        return repo.findAll() ;
    }
}
