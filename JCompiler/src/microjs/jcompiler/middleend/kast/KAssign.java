package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KAssign extends KStatement {
    private String name;
    private KExpr expr;
    
    public KAssign(String name, KExpr expr, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name = name;
		this.expr = expr;
    }

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

	public String getVarName() {
		return name;
	}
	
	public KExpr getExpr() {
		return expr;
	}

}
