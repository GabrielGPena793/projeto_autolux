package com.dh.PI.services;

import com.dh.PI.dto.CityDTO;
import com.dh.PI.exceptions.ResourceAlreadyExistsException;
import com.dh.PI.exceptions.ResourceNotFoundException;
import com.dh.PI.model.City;
import com.dh.PI.repositories.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public CityDTO create(CityDTO cityDTO){

        if (repository.existsByNameAndCountry(cityDTO.getName(), cityDTO.getCountry())){
            throw new ResourceAlreadyExistsException("This city already registered");
        }

        City city = new City();

        BeanUtils.copyProperties(cityDTO, city);

        return new CityDTO(repository.save(city));
    }

    public List<CityDTO> findAll(){
        return repository.findAll().stream().map(CityDTO::new).collect(Collectors.toList());
    }

    public City findByName(String name){

        City city = repository.findByName(name);

        if (city == null) {
            throw new ResourceNotFoundException("City " + name + " not registered");
        }

        return city;
    }

    public CityDTO update(CityDTO cityDTO) {

        City cityModel = findByName(cityDTO.getName());

        BeanUtils.copyProperties(cityDTO, cityModel);

        return new CityDTO(repository.saveAndFlush(cityModel));
    }
}
