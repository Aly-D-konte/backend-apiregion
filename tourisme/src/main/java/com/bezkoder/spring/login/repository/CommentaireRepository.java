package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    Commentaire findByObjet(Commentaire objets);
}
