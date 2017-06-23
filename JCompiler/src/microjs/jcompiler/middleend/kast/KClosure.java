package microjs.jcompiler.middleend.kast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KClosure extends KExpr {
    private List<String> params;
    private KStatement body;
    
    public KClosure(List<String> params, KStatement body, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.params = params;
    	this.body = body;
    }
    
    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }
    
    public List<String> getParams() {
    	return params;
    }
    
    public KStatement getBody() {
    	return body;
    }
}
