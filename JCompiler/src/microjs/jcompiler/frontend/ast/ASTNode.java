package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KASTNode;
import microjs.jcompiler.utils.DotGraph;

public abstract class ASTNode {
	Location startPos;  // objects with getLine and getColumn
	Location endPos;
	
	
	/* package */ ASTNode(Location startPos, Location endPos) {
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public Location getStartPos() {
		return startPos;
	}
	
	public Location getEndPos() {
		return endPos;
	}
	
	public abstract KASTNode expand();

	protected abstract void prettyPrint(StringBuilder buf);
	
	@Override
	public final String toString() {
		StringBuilder buf = new StringBuilder();
		prettyPrint(buf);
		return buf.toString();
	}
	
	public DotGraph genDotGraph() {
		DotGraph graph = new DotGraph();
		buildDotGraph(graph);
		return graph;
	}
	
	protected abstract String buildDotGraph(DotGraph graph);
	
	
}

