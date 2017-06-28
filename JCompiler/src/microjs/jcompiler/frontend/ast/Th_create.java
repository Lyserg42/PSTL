package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.utils.DotGraph;

public class Th_create extends Expr {
    private String fun;
    private Expr argument;
    
    public Th_create(String fun, Expr argument, Location startPos, Location endPos) {
    	super(startPos, endPos);		
		this.fun = fun;
		this.argument = argument;
    }
    
    @Override
    public KCall expand(){
	    List<KExpr> kargs = new ArrayList<KExpr>();
	    kargs.add(new KEVar(fun, getStartPos(), getEndPos()));
	    kargs.add(argument.expand());
	    return new KCall(new KEVar("th_create", getStartPos(), getEndPos()), kargs, getStartPos(), getEndPos());
    }

    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Th_create[" + fun.toString() + "," + argument.toString() + "]");
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Th_create(");    	
	buf.append(fun);
	buf.append(", ");    	
    	String sep = "";
	argument.prettyPrint(buf);
    	buf.append(")");
    }
}
