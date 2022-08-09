package com.example.progettosis.Controllers;



import com.example.progettosis.Entities.Allegato;
import com.example.progettosis.Services.AllegatoService;
import com.example.progettosis.Services.AllegatoServiceInt;
import com.example.progettosis.Services.EmailService;
import com.example.progettosis.configurations.ResponseData;
import com.nimbusds.jose.util.IOUtils;
import org.apache.tomcat.jni.Proc;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class AllegatoController {

    private AllegatoServiceInt allegatoServiceInt;
    private EmailService es;

    public AllegatoController(AllegatoServiceInt allegatoServiceInt, EmailService es){
        this.allegatoServiceInt=allegatoServiceInt;
        this.es = es;
    }

    @PostMapping("/upload")
    public ResponseData uploadFIle(@RequestParam("file")MultipartFile alleg, @RequestParam("mail")String mail) throws Exception {
        Allegato allegato = null;
        //kmeans("C:/Users/Giuseppe/Downloads/"+alleg.getOriginalFilename(),5,1000,mail);
        //es.sendMailAllegato("C:\\Users\\Giuseppe\\Documents\\ProgettoSis\\"+allegato.getFilename());
        String downloadURL = "";
        //MultipartFile file = convert(mail);
        //allegato = allegatoServiceInt.saveAllegato(file,mail);
        //MultipartFile file = convert(alleg.getOriginalFilename(), mail);
        allegato = allegatoServiceInt.saveAllegato(alleg,mail);  //salvo l'allegato ricevuto
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(allegato.getId()).toUriString();
        browse(downloadURL); //scarico il file inviato e sul quale si vuole fare analisi
        kmeans("C:/Users/Giuseppe/Downloads/"+alleg.getOriginalFilename(),5,1000,mail,alleg.getOriginalFilename()); //richiamo il metodo python
        String filename="";
        if (allegato.getFilename().indexOf(".") > 0) {  //qua elimino l'estensione dell'allegtao
            filename = allegato.getFilename().substring(0, allegato.getFilename().lastIndexOf("."));
        } else {
            filename= allegato.getFilename();
        }
        es.sendMailAllegato(mail+filename+".png",mail);
        File file1 = new File(mail+filename+".png");
        file1.delete();
        File file2 = new File(allegato.getFilename());
        file2.delete();
        //browse(downloadURL);
        return new ResponseData(allegato.getFilename(),downloadURL,alleg.getContentType(),alleg.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Allegato allegato = null;
        allegato = allegatoServiceInt.getAllegato(fileId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(allegato.getFiletype())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+allegato.getFilename()+"\"").body(new ByteArrayResource(allegato.getData()));
    }


    public static void browse(String url) { //scarica il file dal link
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {

                desktop.browse(new URI(url));

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static  void kmeans(String path, int ncluster, int iterations,String mail, String nome){ //richiama lo script python
        try {
            String filename="";
            if (nome.indexOf(".") > 0) {
                filename = nome.substring(0, nome.lastIndexOf("."));
            } else {
                filename= nome;
            }
            ProcessBuilder pb = new ProcessBuilder("python","ok.py",path,"4",mail,filename);
            Process gg = pb.start();

            //Process p = Runtime.getRuntime().exec("python  C:\\Users\\Giuseppe\\Desktop\\ok.py");
            gg.waitFor();
            gg.destroy();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    public static MultipartFile convert(String filename, String mail) throws IOException {
        File file = new File("C:\\Users\\Giuseppe\\Documents\\ProgettoSis\\"+filename);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "image/png", input);
        return multipartFile;
    }

     */

}
