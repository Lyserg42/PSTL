package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.utils.DotGraph;

public class Th_join extends Expr {

    private Expr thread;
    
    public Th_join(Expr thread, Location startPos, Location endPos) {
    	super(startPos, endPos);		
	this.thread = thread;
    }

    @Override
    public KCall expand() {
	List<KExpr> args = new ArrayList<KExpr>();
	args.add(thread.expand());
    	return new KCall(new KEVar("th_join", getStartPos(), getEndPos()), args, getStartPos(), getEndPos());
    }
    
    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Th_join[" + thread.toString() + "]");
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Th_join(");    	
	buf.append(thread);
    	buf.append(")");
    }
}
