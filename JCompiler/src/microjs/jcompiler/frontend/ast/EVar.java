package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.utils.DotGraph;

public class EVar extends Expr {
	private String name;
	
	public EVar(String name, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.name = name;
	}
	
	@Override
	public KEVar expand() {
		return new KEVar(name, getStartPos(), getEndPos());
	}

	@Override
	protected String buildDotGraph(DotGraph graph) {
		String varNode = graph.addNode("EVar[" + name +"]");
		return varNode;
	}

	@Override
	protected void prettyPrint(StringBuilder buf) {
		buf.append(name);
	}
		
}
