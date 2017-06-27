package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.utils.DotGraph;

public class Print extends Expr {
    private Expr toprint;
    
    public Print(Expr toprint, Location startPos, Location endPos) {
    	super(startPos, endPos);		
	this.toprint = toprint;
    }
    
    @Override
    public KCall expand() {
	List<KExpr> args = new ArrayList<KExpr>();
	args.add(toprint.expand());
    	return new KCall(new KEVar("print", getStartPos(), getEndPos()), args, getStartPos(), getEndPos());
    }
    
    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Print[" + toprint.toString() + "]");
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Print(");    	
	buf.append(toprint);
    	buf.append(")");
    }
}
