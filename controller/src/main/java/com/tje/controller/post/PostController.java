package com.tje.controller.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping(value = "/posts")
public class PostController {
    Map<Long, Post> map = new ConcurrentHashMap<>();
    AtomicLong num = new AtomicLong(0);

    //--------------
    //    게시자
    //--------------
    //    제목<h3>
    //    본문<p>

    //--------------
    //    생성시간
    //--------------

    @GetMapping
    public List<Post> getPostList() {
        // HashMap -> ConcurrentHashMap
        // Integer -> incrementAndGet

        // 증가시키고 값 가져오기
//        long no = num.incrementAndGet();
        // 수정 후 코드:

//        map.put(no, Post.builder().no(1).creatorName("유진아")
//                .title("게시글 1").content("게시글 1의 내용입니다!")
//                .creatorTime(new Date().getTime()).build());
//
//        no = num.incrementAndGet();
//        map.put(no, Post.builder().no(2).creatorName("홍길동")
//                .title("게시글 2").content("게시글 2의 내용입니다!")
//                .creatorTime(new Date().getTime()).build());



        var list = new ArrayList<>(map.values());
        list.sort(Comparator.comparingLong(Post::getNo));

        return list;

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {
        if (post.getCreatorName() == null || post.getTitle() == null || post.getContent() == null || post.getTitle().isEmpty()) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "[title], [content] is field required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        long no = num.incrementAndGet();
        post.setNo(no);

        map.put(no, post);
        System.out.println(post);

        Map<String, Object> res = new HashMap<>();
        res.put("data", map.get(no));
        res.put("message", "create");

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }



}


