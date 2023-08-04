package com.yja.myapp.acount;


// 서버란 ?
// = 컴퓨터
// 컴퓨터란 ?
// = 서비스를 제공해주는 컴퓨터
// 서비스는 요청에 대한 응답을 제공해주는 것

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 서버 프로그램(백엔드)
// 요청에 대한 응답을 제공해주는 프로그램

// JSON : 자바스크립트 객체 표기법(문자열)
// 클라이언트 <-> 서버간의 통신에
// 데이터를 주고받을 때 사용하는 데이터 형식

// Controller
// 요청에 대한 응답을 처리하는 메서드를 정의
// 데이터, 문자열, 숫자, 웹페이지, 파일


// @ResponseBody
// 응답형식이 객체면 JSON 형식으로 변환해서 반환함

// @RestController
// 요청에 대한 응답을 처리하는 메서드를 정의하는데
// 응답형식이 주로 데이터(JSON)

@RestController
// 컨트롤러에 RequestMapping을 사용하여
// value 경로의 요청을 컨트롤러 내부의 메서드로 실행
// GET ../accounts
@RequestMapping(value = "/accounts")

public class AccountController {
//    public  void func(){
//        Account acc = new Account("0101", "길동", 1000, new Date().getTime() );
//
//        // 톰북 라이브러리를 사용중이면 쓸 수 있게 해주는 것
//        acc.getAno();
//        acc.getAno("002");
//
//
//    }
    List<Account> list = new ArrayList<>();
    @GetMapping
    public List<Account> getAccountList() {
        list.clear();
        // 전체 필드 매개변수 생성자로 생성
        // 매개변수 순서가 필드 순서와 같음
        list.add(new Account("0101", "길동", 1000, new Date().getTime()));

        list.add(
                Account.builder().ownerName("김철수")
                .ano("0101").balance(1000)
                .createTime(new Date().getTime())
                .build());

        return list;
    }
}
