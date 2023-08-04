package com.yja.myapp.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    // database의 auto-increment를 사용함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no; // 번호


    @Column(nullable = false)
    private String title;  // 타이틀


    @Column(nullable = false)
    private String content; // 내용


    private String creatorName;  // 게시자

    // 데이터베이스에 null을 넣고 싶으면 class 타입을 써야함
    private long creatorTime;  // 생성시간





}
