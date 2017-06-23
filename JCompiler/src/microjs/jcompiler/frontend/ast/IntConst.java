package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KInt;
import microjs.jcompiler.utils.DotGraph;

public class IntConst extends Expr {
	private int value;
	
	public IntConst(int value, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.value = value;
	}
	
	@Override
	public KInt expand() {
		return new KInt(value, getStartPos(), getEndPos());
	}

	@Override
	protected String buildDotGraph(DotGraph graph) {
		String intNode = graph.addNode("Int[" + value +"]");
		return intNode;
	}

	@Override
	protected void prettyPrint(StringBuilder buf) {
		buf.append(value);
	}
}
