package com.yja.myapp.post;

import com.yja.myapp.contact.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    Optional<Post> findByPostNumber(String no);

//    Page<Post> findByTitle(String title, Pageable page);

    Page<Post> findByTitleContaining(String title, Pageable page);

    Page<Post> findByTitleContainsOrContentContains(String title, String content, Pageable pageable);
}
