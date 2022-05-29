package com.example.crud.repository;


import com.example.crud.entity.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LanguageRepository extends CrudRepository<Language,Long> {
    @Override
    ArrayList<Language> findAll();
}
