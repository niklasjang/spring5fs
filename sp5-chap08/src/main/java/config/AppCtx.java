package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class AppCtx {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false");
		ds.setUsername("spring5");
		ds.setPassword("tjrdl1226");
		//커넥션 풀을 초기화할 때 생성할 초기 커넥션 개수를 지정한다. 기본값은 10이다.
		ds.setInitialSize(2);
		//커넥션애서 가져올 수 있는 최대 커넥션의 갯수를 지정한다. 기본값은 100이다.
		ds.setMaxActive(10);
		//커넥션이 풀이 유휴 상태로 있는 동안에 검사할지 여부를 지정한다. 기본값은 false이다.
		ds.setMaxIdle(10);
		ds.setTestWhileIdle(true);
		//커넥션 풀에 유휴 상태로 유지할 최소 시간을 밀리초 단위로 지정한다.
		//testSHilwIdle이 true이면 이 값을 초과한 커넥션을 풀에서 제거한다. 기본값은 1분이다.
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		//커넥션 풀의 유휴 커넥션을 검사할 주기를 밀리초 단위로 지정한다. 기본값은 5000밀리초(5초)이다.
		//이 값을 1초 이하로 설정하면 안된다. 
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
