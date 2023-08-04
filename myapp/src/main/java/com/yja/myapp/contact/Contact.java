package com.yja.myapp.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


// @Entity는 기본적으로 클래스명(파스칼케이스) -> 테이블명(스네이크케이스) 맵핑
// class: ContacatActivuty -> table :contact_octivity

// ORM(Object Relaship Mapping)
// 소프트웨어의 객체를 데이터베이스 테이블로 맵핑하는 방법
// 데이터베이스의 의존성을 낮추기 위한 방법
@Entity
public class Contact {

    @Id
    private String email; // 계정 id (불변)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(length = 1024 * 1024 * 20)
    // 파일을 base64 data-url 문자열로 저장
    private String image;

}
