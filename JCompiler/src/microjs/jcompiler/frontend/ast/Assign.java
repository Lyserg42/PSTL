package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KAssign;
import microjs.jcompiler.utils.DotGraph;

public class Assign extends Statement {
    private String name;
    private Expr expr;
    
    public Assign(String name, Expr expr, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name = name;
		this.expr = expr;
    }
    
    @Override
    public KAssign expand() {
    	return new KAssign(name, expr.expand(), getStartPos(), getEndPos());
    }

    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append(name);
    	buf.append(" = ");
    	expr.prettyPrint(buf);
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String assignNode = graph.addNode("Assign[" + name + "]");
		String exprNode = expr.buildDotGraph(graph);
		graph.addEdge(assignNode, exprNode, "expr");
	
		return assignNode;
	}

    
    
}
