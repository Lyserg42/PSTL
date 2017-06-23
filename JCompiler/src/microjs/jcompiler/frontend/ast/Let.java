package microjs.jcompiler.frontend.ast;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KClosure;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.middleend.kast.KVoidExpr;
import microjs.jcompiler.utils.DotGraph;

public class Let extends Statement {
    private String name;
    private Expr expr;
    private List<Statement> body;
    
    public Let(String name, Expr expr, List<Statement> body, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name = name;
		this.expr = expr;
    	this.body = body;
    }
    
    @Override
    public KVoidExpr expand() {
    	List<String> params = new ArrayList<String>();
    	params.add(name);
    	
    	List<KStatement> kstmts = Statement.expandStatements(body);
    	KStatement kbody = KSeq.buildKSeq(kstmts, getStartPos(), getEndPos());
    	
    	KClosure fun = new KClosure(params, kbody, getStartPos(), getEndPos());
    	
    	List<KExpr> kargs = new ArrayList<>();
    	kargs.add(expr.expand());
    	
    	KCall call = new KCall(fun, kargs, startPos, endPos);
    	
    	return new KVoidExpr(call, startPos, endPos);
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String letNode = graph.addNode("Let[" + name + "]");
		String exprNode = expr.buildDotGraph(graph);
		graph.addEdge(letNode, exprNode, "expr");
		for(int i=0; i<body.size(); i++) {
			Statement st = body.get(i);
			String stRoot = st.buildDotGraph(graph);
			graph.addEdge(letNode, stRoot, "body[" + i + "]");
		}
		return letNode;
	}

    
    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append("let ");
    	buf.append(name);
    	buf.append(" = ");
    	expr.prettyPrint(buf);
    	buf.append(";\n");
    	Statement.prettyPrintStatements(buf, body, indent_level);
    }
}
