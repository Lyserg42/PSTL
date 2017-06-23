package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KEVar extends KExpr {
	private String name;
	
	public KEVar(String name, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.name = name;
	}
	
    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }
    
    public String getName() {
    	return name;
    }
    		
}
