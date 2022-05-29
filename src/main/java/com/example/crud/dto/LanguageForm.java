package com.example.crud.dto;

import com.example.crud.entity.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
public class LanguageForm {
    private Long language_id;

    private String name;

    private Timestamp last_Update;


    public Long getId() {
        return language_id;
    }

    public void setId(Long language_id) {
        this.language_id = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        this.last_Update = last_Update;
    }



    public Language toEntity(){
        return  new Language(language_id,name,last_Update);
    }
}