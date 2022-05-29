package com.example.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long language_id;

    @Column
    private String name;

    @Column
    private Timestamp last_Update;


    public void patch(Language language) {
        if(language.name!=null)
            this.name = language.name; // 수정할때 비어있는항목 기존정보로 해당항목을 넣기

    }
}
