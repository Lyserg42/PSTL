package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KProg extends KASTNode {
	private String filename;
	private KStatement body;
	
	public KProg(String filename, KStatement body, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.filename = filename;
		this.body = body;
	}
	
	@Override
	public void accept(KASTVisitor visitor) {
		visitor.visit(this);
	}
	
	public KStatement getBody() {
		return body;
	}
	
	@Override
	public String toString() {
		KPrettyPrint pretty = new KPrettyPrint();
		accept(pretty);
		return pretty.toString();
	}

}
