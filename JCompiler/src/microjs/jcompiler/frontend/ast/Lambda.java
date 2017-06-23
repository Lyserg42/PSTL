package microjs.jcompiler.frontend.ast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KClosure;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.utils.DotGraph;

public class Lambda extends Expr {
    private List<String> params;
    private List<Statement> body;
    
    public Lambda(List<String> params, List<Statement> body, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.params = params;
    	this.body = body;
    }
    
    @Override
    public KClosure expand() {
    	List<KStatement> kstmts = Statement.expandStatements(body);
    	Location startBody = getStartPos(); // XXX: good approx for empty body ?
    	Location endBody = getEndPos();
    	KStatement kbody = KSeq.buildKSeq(kstmts, startBody, endBody);
    	return new KClosure(params, kbody, getStartPos(), getEndPos());
    }
   
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String lamNode = graph.addNode("Lambda(" + params + ")");
		for(int i=0; i<body.size(); i++) {
			Statement st = body.get(i);
			String stRoot = st.buildDotGraph(graph);
			graph.addEdge(lamNode, stRoot, "body[" + i + "]");
		}
		return lamNode;
	}

    
    @Override
    protected void prettyPrint(StringBuilder buf) {
    	buf.append("lambda");
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
    	Statement.prettyPrintStatements(buf, body, 2);
    	buf.append("}");
    }
}
