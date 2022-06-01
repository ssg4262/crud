package com.example.crud.apiController;

import com.example.crud.LanguageMapper;
import com.example.crud.dto.LanguageForm;
import com.example.crud.entity.Language;
import com.example.crud.repository.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LanguageController {


    @Autowired
    private LanguageRepository languageRepository;

    //전체 목록
    @GetMapping("/api/languages")
    public List<Language> showLanguage(){

        return languageRepository.findAll();
    }

    //아이디로 매게값 으로 찾은 목록
    @GetMapping("/api/languages/{language_id}")
    public  Language index(@PathVariable Long language_id){
        return languageRepository.findById(language_id).orElse(null);


    }
    //post 만들기
    @PostMapping("/api/languages")
    public Language create(@RequestBody LanguageForm dto){ // 요청을 dto로받기
        Language language = dto.toEntity();
        return languageRepository.save(language);
    }


    //PATCH 수정
    @PatchMapping("/api/languages/{language_id}")
    public ResponseEntity<Language> update(@PathVariable Long language_id,
                                           @RequestBody LanguageForm dto){

        //1: 수정용 엔티티 생성
        Language language =  dto.toEntity();

        //2: 대상 엔티티 조회

        Language target = languageRepository.findById(language_id).orElse(null);



        //3:잘못된 요청 처리
        if(target ==null || language_id != language.getLanguage_id()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4: 업데이트 및 정상 응답
        target.patch(language);
        Language updated = languageRepository.save(language);
        return ResponseEntity.status(HttpStatus.OK).body(updated);






    }




    //Delete
    @DeleteMapping("/api/languages/{language_id}")
    public ResponseEntity<Language> delete(@PathVariable Long language_id){
        //대상 찾기
        Language target = languageRepository.findById(language_id).orElse(null);

        //잘못된예외 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }


        //대상 찾았으니 삭제

        languageRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    //mybatis 적용
    @Autowired
    private LanguageMapper languageMapper;


    //get
    @GetMapping("api/get/languages")
 public List<LanguageForm> getAll(){
        return languageMapper.getAll();
    }

    @GetMapping("api/get/languages/{id}")
    public LanguageForm getById(@PathVariable("id") int id){
        return languageMapper.getById(id);
    }






    // insert
    @PostMapping("api/insert/languages")
    public int post(@RequestBody LanguageForm languageForm){
       return languageMapper.insert(languageForm);
    }

    //patch

    @PutMapping ("api/patch/languages/{id}")
    public void patchLanguage(@PathVariable("id") int id,@RequestBody  LanguageForm languageForm){
                 String name  = languageForm.getName();
          languageMapper.updateLanguage(id,name);
    }

    @DeleteMapping("api/delete/languages/{id}")
    public void delete(@PathVariable("id") int id ){
         languageMapper.deleteLanguage(id);
    }

}
