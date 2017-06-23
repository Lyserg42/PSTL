package microjs.jcompiler.frontend.ast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KClosure;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.middleend.kast.KVar;
import microjs.jcompiler.utils.DotGraph;

public class Function extends Statement {
    private String name;
    private List<String> params;
    private List<Statement> body;
    
    public Function(String name, List<String> params, List<Statement> body, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.name = name;
    	this.params = params;
    	this.body = body;
    }
    
    @Override
    public KVar expand() {
    	List<KStatement> kstmts = Statement.expandStatements(body);
    	Location startBody = getStartPos(); // XXX: good approx for empty body ?
    	Location endBody = getEndPos();
    	KStatement kbody = KSeq.buildKSeq(kstmts, startBody, endBody);
    	return new KVar(name, 
    			new KClosure(params, kbody, getStartPos(), getEndPos()),
    			getStartPos(), getEndPos());
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String funNode = graph.addNode("Fun[" + name + "](" + params + ")");
		for(int i=0; i<body.size(); i++) {
			Statement st = body.get(i);
			String stRoot = st.buildDotGraph(graph);
			graph.addEdge(funNode, stRoot, "body[" + i + "]");
		}
		return funNode;
	}
    
    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append("function ");
    	buf.append(name);
    	buf.append("(");
    	String sep = "";
    	for(String param : params) {
    		buf.append(sep);
    		buf.append(param);
    		if(sep.equals("")) {
    			sep = ", ";
    		}
    	}
    	buf.append(") {\n");
    	Statement.prettyPrintStatements(buf, body, indent_level + 1);
    	indent(buf, indent_level);
    	buf.append("}");
    }
}
