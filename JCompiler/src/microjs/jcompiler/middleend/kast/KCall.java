package microjs.jcompiler.middleend.kast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KCall extends KExpr {
    private KExpr fun;
    private List<KExpr> arguments;
    
    public KCall(KExpr fun, List<KExpr> arguments, Location startPos, Location endPos) {
    	super(startPos, endPos);		
		this.fun = fun;
		this.arguments = arguments;
    }

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }
    
    public KExpr getFun() {
    	return fun;
    }
    
    public List<KExpr> getArguments() {
    	return arguments;
    }

}
