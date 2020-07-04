package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
//	@Autowired
	private MemberDao memDao;
//	@Autowired
	private MemberPrinter printer;

	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("�뜲�씠�꽣 �뾾�쓬\n");
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
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
