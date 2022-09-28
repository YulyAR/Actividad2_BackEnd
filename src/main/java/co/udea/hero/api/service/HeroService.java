package co.udea.hero.api.service;

import co.udea.hero.api.exception.BusinessException;
import co.udea.hero.api.model.Hero;
import co.udea.hero.api.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroService {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);

    private HeroRepository heroRepository;


    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        if(!optionalHero.isPresent()){
            log.info("No se encuentra un heroe con ID:"+id);
            throw new BusinessException("exception.data_not_found.hero");
        }
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){
        //TODO mejorar el control de excepciones
        List<Hero> heroesList = heroRepository.findAll();
        return heroesList;
    }

}
