package com.tje.controller.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Contact{
    private String name;

    private String phone;

    private String email;

    private String image;

}
