package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {

	private MemberDao memDao;
	private MemberPrinter printer;

	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}

	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
//	@Autowired될 의존 객체가 null일 수 있는 경우 대응 방법 3가지
//	1.
//	@Autowired(required=false)
//	@Qualifier("printer")
//	public void setPrinter(MemberSummaryPrinter printer) {
//		this.printer = printer;
//	}
//	2.
//	@Autowired
//	public void setDateFormatter(Optional<DataTimeFormatter> formatterOpt){
//	    if(forammterOpt.isPresent()){
//	      this.dateTimeFormatter = formatterOpt.get();
//	    }else{
//	      this.dateTimeFormatter = null;
//	      //물론 null에 대한 처리가 추가되어야 한다.
//	    }
//	}
//	3. 
//	@Autowired
//	public void setDateFormatter(@Nullable DataTimeFormatter dateTimeFormatter){
//	      this.dateTimeFormatter = null;
//	      //물론 null에 대한 처리가 추가되어야 한다.
//	    }
//	}
}
