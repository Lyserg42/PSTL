package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KTrue extends KExpr {
	
	public KTrue(Location startPos, Location endPos) {
		super(startPos, endPos);
	}

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }

}
