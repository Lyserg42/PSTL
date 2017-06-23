package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class KASTNode {
	Location startPos;  // objects with getLine and getColumn
	Location endPos;
	
	
	/* package */ KASTNode(Location startPos, Location endPos) {
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public Location getStartPos() {
		return startPos;
	}
	
	public Location getEndPos() {
		return endPos;
	}
	
	public int getEndColumn() {
		return endPos.getColumn();
	}
	
	public abstract void accept(KASTVisitor visitor);

}
