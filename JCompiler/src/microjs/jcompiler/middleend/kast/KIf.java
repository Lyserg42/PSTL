package microjs.jcompiler.middleend.kast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KIf extends KStatement {
    private KExpr cond;
    private KStatement kthen;
    private KStatement kelse;
    
    public KIf(KExpr cond, KStatement kthen, KStatement kelse, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.cond = cond;
    	this.kthen = kthen;
    	this.kelse = kelse;
    }
    
    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

	public KExpr getCond() {
		return cond;
	}
	
	public KStatement getThen() {
		return kthen;
	}
	
	public KStatement getElse() {
		return kelse;
	}
}
