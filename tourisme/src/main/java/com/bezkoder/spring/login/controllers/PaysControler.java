package com.bezkoder.spring.login.controllers;


import com.bezkoder.spring.login.img.ConfigImage;
import com.bezkoder.spring.login.models.Pays;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.PaysRepository;
import com.bezkoder.spring.login.security.services.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

@RestController
@RequestMapping(path = "/api/pays",name ="Pays")

public class PaysControler {
    @Autowired
    private PaysService paysService;
    @Autowired
    private PaysRepository paysRepository;

    // ----------------------------Controller-----------------------------------
    @Autowired
    public  PaysControler(PaysService paysService) {
        this.paysService = paysService;
    }
    // ----------------------------Request Post-----------------------------------

    @PostMapping(path ="/creer", name = "create")
    @PreAuthorize("hasRole('ADMIN') ")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add (@RequestBody Pays pays)
    {
        return paysService.ajouterPays(pays);

    }

    @PostMapping("/ajouterpays")
    @PreAuthorize("hasRole('ADMIN') ")
    public MessageResponse ajouterpays(@Param("nompays") String nompays, @Param("description") String description, @Param("images") String images, @Param("file") MultipartFile file) throws IOException {
        Pays pays = new Pays();
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());

        System.out.println(nompays);
        pays.setNompays(nompays);



        System.out.println(description);
        pays.setDescription(description);


        System.out.println(nomfile);
        pays.setImages(nomfile);






        if(paysRepository.findByNompays(nompays) == null){

            //  String u = "C:/Users/adcoulibaly/Desktop/ERP/ApplicationERPInterface/src/assets/images";
            String uploaDir = "C:\\Users\\adkonte\\Desktop\\InterfaceMaliTourist\\Front-APIREGION\\src\\assets\\images";
            //String uploaDir = new ClassPathResource("files/").getFile().getAbsolutePath();
            ConfigImage.saveimg(uploaDir, nomfile, file);
            return paysService.ajouterPays(pays);
        }else {
            MessageResponse message = new MessageResponse("Pays existe d??ja");
            return message;
        }

    }



    // ----------------------------Request Get-----------------------------------

    @GetMapping(path ="/liste", name = "list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")

    @ResponseStatus(HttpStatus.OK)
    public List<Pays> list()
    {
        return this.paysService.afficheTout();
    }


    // ----------------------------Request lIRE PAR PAYS-----------------------------------

    @GetMapping(path ="/unPays/{id_pays}", name = "lire")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse lire(@PathVariable Long id_pays)
    {
        return  this.paysService.afficherUn(id_pays);
    }

    // ----------------------------Request modifier-----------------------------------

    @PutMapping(path ="/modifier/{id_pays}", name = "modifier")
    @PreAuthorize("hasRole('ADMIN') ")

    @ResponseStatus(HttpStatus.OK)
    public MessageResponse modifier(@RequestBody Pays pays, @PathVariable Long id_pays)
    {

        return  this.paysService.modifierPays(pays, id_pays);
    }

    // ----------------------------Request supprimer-----------------------------------

    @DeleteMapping(path ="/supprimer/{id_pays}", name = "supprimer")
    @PreAuthorize("hasRole('ADMIN') ")

    //  @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id_pays)
    {
         this.paysService.supprimerPays(id_pays);
    }

}
