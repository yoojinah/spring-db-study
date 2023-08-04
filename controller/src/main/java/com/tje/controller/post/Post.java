package com.tje.controller.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    private long no; // 번호
    private String title;  // 타이틀
    private String content; // 내용
    private String creatorName;  // 게시자
    private long creatorTime;  // 생성시간

//    public Map<Integer, String> validate() {
//
//        Map<Integer, String> res = new HashMap<>();
//        res.put("data", null);
//        res.put("message", "[title], [content] is field required");
//        return res;
//
//    }
//    @Override
//    public String toString(){
//        return null;
//    }
}
