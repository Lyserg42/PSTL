package microjs.jcompiler.backend;

public class Primitive {
	private String name;
	private int id;
	private String doc;
	private String cname;
	
	public Primitive(String name, String doc, String cname) {
		this.name = name;
		this.id = -1;
		this.doc = doc;
		this.cname = cname;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDoc() {
		return doc;
	}
	
	public String getCName() {
		return cname;
	}
	
	/* package */ void setId(int id) {
		this.id = id;
	}
	
	public String getCDeclaration() {
		StringBuilder buf = new StringBuilder();
		buf.append("/** primitive "); buf.append(getName()); buf.append(" */\n");
		buf.append("#define "); buf.append(getCName()); buf.append(" "); buf.append(getId()); 
		return buf.toString();
	}
	
	
}
