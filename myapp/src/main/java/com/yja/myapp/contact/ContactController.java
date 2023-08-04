package com.yja.myapp.contact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {
    Map<String, Contact> map = new ConcurrentHashMap<>();


    // 스프링 프레임워크 data jpa 라이브러리
    // @Repository 인터페이스에 맞는 동적 클래스를 실행 시점에 생성해
    // @Autowired 키워드가 있는 선언 변수에 주입한다
    @Autowired
    ContactRepository repo;
//    public ContactController(ContactRepository repo){
//        this.repo = repo;
//    }


    // 싱글턴 : 첫 실행시점에 객체가 1번 실행됨, 이후 부터는 생성된 객체를 재사용
    // @Configuration 클래스에 bean 객체를 선언


    // 객체를 사용하는 곳 외부에서 객체를 생성한 후에
    // 객체를 사용하는 곳에 필드, 메서드 매개변수로 넣어줌

    // 스프링 프레임 워크에서는
    // @Configuration 클래스의 @Bean 클래스를 생성
    //    -> @Bean 클래스: 싱글턴 객체를 생성하는 메서드의 반환 클래스
    // @Autowired 어노테이션 변수에 객체를 의존성 주입


//-- 객체 생성을 정의하는 곳
//    @Configuration
//    public class ResourceConfig {
//        @Bean
//        public MangKyuResource mangKyuResource() {
//            return new MangKyuResource();
//        }
//    }
//
//-- 객체 사용하는 곳(생성 구문을 사용할 필요가 없음)
//    public class A {
//        @Autowired
//        Repository repo;
//    }
//
//    public class B {
//        @Autowired
//        Repository repo;
//    }
    @GetMapping
    public List<Contact> getContactList() {
        // repo.findAll() 전체 테이블 목록 조회
        // SELECT * from 테이블
        // repo.findAll(Sort.by()): 정렬하여 전체 테이블 목록 조회

        List<Contact> list = repo.findAll(Sort.by("name").ascending());
        return list;
    }

    @GetMapping(value = "/paging")
    public Page<Contact> getContactPage(@RequestParam int page, @RequestParam int size){
        // 정렬 설정을 하지 않고 시작
        System.out.println(page);
        System.out.println(size);

        // 정렬객체                               역정렬
        Sort sort = Sort.by("email").descending();

        // 페이지 매개변수 객체
        // sql : OFFSET page * size LIMIT size
        // offset 10 : 10번째 이후
        // LIMIT 10 : 10건의 레코드
        // OFFSET 10 LIMIT 10
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        return repo.findAll(pageRequest);

    }


    // GET/  contacts/paging?page=0&size=10&name=hong
    @GetMapping(value = "/paging/searchByName")
    public Page<Contact> getContactPageSearchName(@RequestParam int page, @RequestParam int size, @RequestParam String name){
        System.out.println(page);
        System.out.println(size);
        System.out.println(name);

        Sort sort = Sort.by("email").descending();

        PageRequest pageRequest = PageRequest.of(page,size,sort);

        return repo.findByNameContaining(name, pageRequest);
    }


    @GetMapping(value = "/paging/search")
    public Page<Contact> getContactPageSearch(@RequestParam int page, @RequestParam int size, @RequestParam String query){
        // 정렬 설정을 하지 않고 시작
        System.out.println(page);
        System.out.println(size);
        System.out.println(query);

        Sort sort = Sort.by("email").descending();

        PageRequest pageRequest = PageRequest.of(page,size,sort);

        return repo.findByNameContainsOrPhoneContains(query,query,pageRequest);
    }

    // HTTP 1.1 POST /contacts
    // HTTP버전 메서드 리소스URL
    // : Request Line

    // Content-Type: application/json - 요청보낼 데이터 형식
    // Accept: */* - 서버로 부터 어떤 형식의 데이터를 받을지
    //         ex) image/jpeg, application/json, text/html, plain/text
    // : Request Header
    // : 서버에 부가적인 요청정보

    // {"name":"홍길동", "phone":"010-1234-5678", "email":"hong@gmail.com"}
    // :Request Body(요청 본문)

    // JSON: 문자열, 자바스크립트 객체 표기법
    // Client(브라우저) Request Header에 Content-Type: application/json
    // Request Body가 JSON 문자열이면

    // Server(스프링) @RequestBody를 메서드 매개변수에 넣어주면
    // JSON(문자열) -> Java객체로 변환


    @PostMapping
    public ResponseEntity<Map<String, Object>> addContact(@RequestBody Contact contact){
//        new Contact("","","");
        // 클라이언트에서 넘어온 json이 객체로 잘 변환됐는지 확인
        System.out.println(contact.getName());
        System.out.println(contact.getPhone());
        System.out.println(contact.getEmail());



        // 이메일 필수값 검증
        // 400 :BAD_REQUEST
        if(contact.getEmail() == null || contact.getEmail().isEmpty()) {
            Map<String, Object> res = new HashMap<>();
//            res.put("data", null);
//            res.put("message", "[email] field is required");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        if(contact.getEmail() != null && map.get(contact.getEmail()) != null) {
            // 맵에 해당 이메일이 있음
            // 이미 있는 데이터를 클라이언트(브라우저) 보냈거나
            // 클라이언트에서 중복 데이터를 보냈거나..
            Map<String, Object> res = new HashMap<>();
            res.put("data", map.get(contact.getEmail()));
            res.put("message", "동일한 정보가 이미 존재합니다.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }


        // 이메일 형식 검증
        // 400: bad request

        // 이메일 중복 검증
        // 409: conflict




        // 앱에 객체 추가
        map.put(contact.getEmail(), contact);



        // 테이블에 레코드 추가
        // key값이 테이블에 이미 있으면 update
        // 없으면 insert구문 실행
        repo.save(contact);


        // 응답 객체 생성

        // optional은 null값이 없음
        Optional<Contact>saveContact =  repo.findById(contact.getEmail());

        // 레코드가 존재하는지 여부
        if(saveContact.isPresent()){
            Map<String, Object> res = new HashMap<>();
            res.put("data", saveContact);
            res.put("message", "created");

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }



        // HTTP Status Code: 201 Created
        // 리소스가 정상적으로 생성되었음.

        return ResponseEntity.ok().build();

    }
    @DeleteMapping(value = "/{email}")
    public ResponseEntity removeContact(@PathVariable String email) {
        System.out.println(email);

        if(repo.findById(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        if(map.get(email) == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        // 객체 리소스 삭제
//        map.remove(email);
        repo.deleteById(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // put 전체수정, path 일부수정
    @PutMapping(value = "/{email}")
    public ResponseEntity modifyContact(@PathVariable String email, @RequestBody ContactModifyRequest contact){

        // 1. 키값으로 조회
        Optional<Contact> findContact = repo.findById(email);

        // 2. 해당 레코드가 있는지 확인
        if(!findContact.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }



        // 여기는 레코드가 존재하는 경우일때(키값 @id값이 존재)
        // 3. 조회한 레코드에 필드값을 수정
        Contact toModifyContact = findContact.get();
        // 매개변수에 name값이 있으면 수정
        if(contact.getName() != null && !contact.getName().isEmpty()){
                toModifyContact.setName(contact.getName());
        }
        if(contact.getPhone() != null && !contact.getPhone().isEmpty()){
            toModifyContact.setPhone(contact.getPhone());
        }

        // (@ID값이 존재하므로 save시점에 update를 시도)
        repo.save(toModifyContact);

        //200 ok 처리
        return ResponseEntity.ok().build();
    }


}
