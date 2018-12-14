package vo;

public class User {
	private int num;
	private String r_user; // 수령인 이름
	private String o_user; // 주문자 이름
	private String r_address;
	private String r_sub_add;
	private String o_phone;
	private String r_phone;
	private String s_user; // 판매자 이름
	private String s_phone;
	private String s_address;
	private String passwd;
	private String product;
	
	public User() {
		super();

	}

	
	public String getR_sub_add() {
		return r_sub_add;
	}


	public void setR_sub_add(String r_sub_add) {
		this.r_sub_add = r_sub_add;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getR_user() {
		return r_user;
	}

	public void setR_user(String r_user) {
		this.r_user = r_user;
	}

	public String getO_user() {
		return o_user;
	}

	public void setO_user(String o_user) {
		this.o_user = o_user;
	}

	public String getR_address() {
		return r_address;
	}

	public void setR_address(String r_address) {
		this.r_address = r_address;
	}

	public String getO_phone() {
		return o_phone;
	}

	public void setO_phone(String o_phone) {
		this.o_phone = o_phone;
	}

	public String getR_phone() {
		return r_phone;
	}

	public void setR_phone(String r_phone) {
		this.r_phone = r_phone;
	}

	public String getS_user() {
		return s_user;
	}

	public void setS_user(String s_user) {
		this.s_user = s_user;
	}

	public String getS_phone() {
		return s_phone;
	}

	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
}
