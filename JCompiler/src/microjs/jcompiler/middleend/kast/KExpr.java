package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class KExpr extends KASTNode {

	/* package */ KExpr(Location startPos, Location endPos) {
		super(startPos, endPos);
	}
	

}
