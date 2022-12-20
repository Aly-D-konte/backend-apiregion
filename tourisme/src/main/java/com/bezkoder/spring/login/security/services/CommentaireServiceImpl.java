package com.bezkoder.spring.login.security.services;


import com.bezkoder.spring.login.models.Commentaire;
import com.bezkoder.spring.login.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    CommentaireRepository commentaireRepository;

    public CommentaireServiceImpl(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }


    @Override
    public String addNewCommentaire(Commentaire commentaire) {

        System.out.println("test **************************************** "+commentaire);

            commentaireRepository.save(commentaire);
            return "Commentaires ajouté avec succes";

    }

    @Override
    public String SupprimerCommentaire(Long idCommentaire) {
        return null;
    }

    @Override
    public String ModifierCommentaire(String username, Commentaire commentaire) {
        return null;
    }

    @Override
    public List<Commentaire> toutLesCommentaires() {
        return commentaireRepository.findAll();
    }
}
