package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Habitants;
import com.bezkoder.spring.login.payload.response.PaysNotFoundException;
import com.bezkoder.spring.login.repository.CRUD_Habitant;
import com.bezkoder.spring.login.repository.HabitantsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitantsService implements CRUD_Habitant {

    private HabitantsRepository habitantsRepository;

    public HabitantsService(HabitantsRepository HabitantsRepository) {
        this.habitantsRepository=HabitantsRepository;
            }

    @Override
    public Habitants ajouterHabitant(Habitants habitants) {
        return this.habitantsRepository.save(habitants);
    }

    @Override
    public List<Habitants> maListe() {
        return habitantsRepository.findAll();
    }

    @Override
    public Habitants modifierHabitant(Habitants habitants, Long id_habitants) {

        Optional<Habitants> HabitantExistePays = this.habitantsRepository.findById(id_habitants);
        if (!HabitantExistePays.isPresent())
        {
            throw new PaysNotFoundException(String.format("Le pays avec id %s n'est pas trouvé"+id_habitants));
        }
        return habitantsRepository.save(habitants);
    }

    @Override
    public void supprimerHabitant(Long id_habitants) {
        Optional<Habitants> habitants = this.habitantsRepository.findById(id_habitants);
        if (!habitants.isPresent())
        {
            throw new PaysNotFoundException(String.format("Le pays avec id %s n'est pas trouvé"+id_habitants));
        }
        this.habitantsRepository.delete(habitants.get());
    }


}
