package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class KStatement extends KASTNode {

	/* package */ KStatement(Location startPos, Location endPos) {
		super(startPos, endPos);
	}
	
}
