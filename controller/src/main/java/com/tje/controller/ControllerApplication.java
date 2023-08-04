package com.tje.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootConfiguration


// -----------------------------
// @EnableAutoConfiguration
// -- 자동화 설정을 쓰겠다.
// 의존성 라이브러리에 맞는 자동화된 기본설정을 허용
// spring-web , spring-test
// 웹서버를 띄워주고 테스트 코드를 실행할 수 있게 해주는 환경을 구성해 줌


// @ComponentSca
// = @Component 어노테이션이 있는 클래스들을 찾아서 객체를 생성하고
// 객체관리 컨테이너에 추가함 (Spring IOC 컨테이너)


// 스프링부트란 ?
// 자동화 된 설정과 의존성 주입을 이용한 개발방법을 채택
// 자동화된 설정 : 의존성 라이브러리, 어노테이션 프로세싱


@SpringBootApplication
public class ControllerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);

	}


}
