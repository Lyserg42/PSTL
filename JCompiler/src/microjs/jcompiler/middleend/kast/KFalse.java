package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KFalse extends KExpr {
	
	public KFalse(Location startPos, Location endPos) {
		super(startPos, endPos);
	}

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

}
