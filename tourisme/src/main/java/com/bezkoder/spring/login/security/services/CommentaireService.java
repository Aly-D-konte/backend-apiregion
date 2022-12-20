package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Commentaire;

import java.util.List;

public interface CommentaireService {

    String addNewCommentaire(Commentaire commentaire);
    String SupprimerCommentaire(Long idCommentaire);
    String ModifierCommentaire(String username,Commentaire commentaire);
    List<Commentaire> toutLesCommentaires();
}
