package microjs.jcompiler.frontend.ast;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.utils.DotGraph;

public class Access extends Expr {

	private String name;
	private Expr expr;
	
	public Access(String name, Expr expr, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.name = name;
		this.expr = expr;
	}
	
	@Override
	public KCall expand() {
	
		List<KExpr> args = new ArrayList<KExpr>();
		args.add(expr.expand());
	
		return new KCall(new KEVar(name, getStartPos(), getEndPos()), args, getStartPos(), getEndPos());
	}

	@Override
	protected String buildDotGraph(DotGraph graph) {
		String accessNode = graph.addNode("Access[" + name + "]");

		String exprNode = expr.buildDotGraph(graph);
		graph.addEdge(accessNode, exprNode, "expr");
	
		return accessNode;
	}

	@Override
	protected void prettyPrint(StringBuilder buf) {
		buf.append(name);
		buf.append("(");
		expr.prettyPrint(buf);
		buf.append(")");
	}
}
