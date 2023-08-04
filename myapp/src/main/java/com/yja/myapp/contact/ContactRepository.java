package com.yja.myapp.contact;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// JpaRepository<Contact, String>
// <엔티티클래스. 엔티티의 키타입>
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query(value = "select *" +
            "from contact" +
            "order by name asc", nativeQuery = true)

    Optional<Contact> findByContactByEmail(String email);


//    List<Contact> findContactFirstName();
//
//    Optional<Contact> findByEmail(String email);


    Page<Contact> findByName(String name, Pageable page);

    Page<Contact> findByNameContaining(String name, Pageable page);

    Page<Contact> findByNameContainsOrPhoneContains(String name, String phone, Pageable pageable);

}
