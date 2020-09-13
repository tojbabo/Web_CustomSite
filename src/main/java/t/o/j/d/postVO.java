package t.o.j.d;
public class postVO {
	private int num;
	private String id;
	private String name;
	private String head;
	private String body;
	public postVO() {
		num = -1;
		id ="";
		name="";
		head="";
		body="";
	}
	public postVO(int a,String b, String c, String d, String e) {
		num = a;
		id =b;
		name=c;
		head=d;
		body=e;
	
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
