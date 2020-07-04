package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		//AppContext에서 정의한 @Bean 설정 정보를 읽어서 ctx 객체를 생성한다.
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		/*
		 * ctx.getBean(param1, param2);
		 * param1 : @Bean annotation의 메서드 이름(빈 객체의 이름)
		 * param2 : 빈 객체의 타입
		 * */
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
		
	}

}
