package com.yja.myapp.post;

import com.yja.myapp.contact.Contact;
import com.yja.myapp.contact.ContactModifyRequest;
import com.yja.myapp.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    PostRepository repo;

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
        List<Post> postList = repo.findAll(Sort.by(Sort.Direction.DESC, "no"));

        return postList;

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



//        var list = new ArrayList<>(map.values());
//        list.sort(Comparator.comparingLong(Post::getNo));



    }



    @GetMapping(value = "/paging")
    public Page<Post> getContactPage(@RequestParam int page, @RequestParam int size){
        // 정렬 설정을 하지 않고 시작
        System.out.println(page);
        System.out.println(size);

        // 정렬객체                               역정렬
        Sort sort = Sort.by("no").descending();

        // 페이지 매개변수 객체
        // sql : OFFSET page * size LIMIT size
        // offset 10 : 10번째 이후
        // LIMIT 10 : 10건의 레코드
        // OFFSET 10 LIMIT 10
        PageRequest pageRequest = PageRequest.of(page,size,sort);
        return repo.findAll(pageRequest);

    }

    // GET/  contacts/paging?page=0&size=10&name=hong
    @GetMapping(value = "/paging/searchByTitle")
    public Page<Post> getContactPageSearchName(@RequestParam int page, @RequestParam int size, @RequestParam String title){
        System.out.println(page);
        System.out.println(size);

        Sort sort = Sort.by("email").descending();

        PageRequest pageRequest = PageRequest.of(page,size,sort);

        return repo.findByTitleContaining(title, pageRequest);
    }

    @GetMapping(value = "/paging/search")
    public Page<Post> getContactPageSearch(@RequestParam int page, @RequestParam int size, @RequestParam String query){
        // 정렬 설정을 하지 않고 시작
        System.out.println(page);
        System.out.println(size);
        System.out.println(query);

        Sort sort = Sort.by("no").descending();

        PageRequest pageRequest = PageRequest.of(page,size,sort);

        return repo.findByTitleContainsOrContentContains(query,query,pageRequest);
    }




    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {
        if (post.getCreatorName() == null || post.getTitle() == null || post.getContent() == null || post.getTitle().isEmpty()) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", null);
            res.put("message", "[title], [content] is field required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }


        // 데이터베이스의 auto-increment를 사용할 것이므로 필요없음
//        long no = num.incrementAndGet();
//        post.setNo(no);

//        map.put(no, post);
//        System.out.println(post);

        post.setCreatorTime(new Date().getTime());

        Post savePost = repo.save(post);

//        Optional<Contact>saveContact =  repo.findById(post.getNo());


        Map<String, Object> res = new HashMap<>();
        res.put("data", savePost);
        res.put("message", "create");


        return ResponseEntity.ok().body(res);
//        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @DeleteMapping(value = "/{no}")
    public ResponseEntity removePost(@PathVariable long no) {
        System.out.println(no);

        if(!repo.findById(no).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        if(map.get(email) == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

        // 객체 리소스 삭제
//        map.remove(email);
        repo.deleteById(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    // put 전체수정, path 일부수정
    @PutMapping(value = "/{no}")
    public ResponseEntity modifyPost(@PathVariable Long no, @RequestBody PostModifyRequest post){

        // 1. 키값으로 조회
        Optional<Post> findPost = repo.findById(no);
        System.out.println(post.getTitle());
        System.out.println(post.getContent());

        // 2. 해당 레코드가 있는지 확인
        if(!findPost.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }



        // 여기는 레코드가 존재하는 경우일때(키값 @id값이 존재)
        // 3. 조회한 레코드에 필드값을 수정
        Post toModifyPost = findPost.get();
        // 매개변수에 name값이 있으면 수정
        if(post.getTitle() != null && !post.getTitle().isEmpty()){
            toModifyPost.setTitle(post.getTitle());
        }
        if(post.getContent() != null && !post.getContent().isEmpty()){
            toModifyPost.setContent(post.getContent());
        }

        // (@ID값이 존재하므로 save시점에 update를 시도)
        repo.save(toModifyPost);

        //200 ok 처리
        return ResponseEntity.ok().build();
    }


}


