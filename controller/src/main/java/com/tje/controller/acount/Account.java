package com.tje.controller.acount;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter 컴파일 시점에 자동생성
@Builder
@AllArgsConstructor
public class Account {
    private  String ano;
    private  String ownerName;
    private  long balance;
    private  long createTime;
}
