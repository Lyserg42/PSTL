package microjs.jcompiler.frontend.ast;

import java.util.List;
import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KCall;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KEVar;
import microjs.jcompiler.utils.DotGraph;

public class Tcreate extends Expr {
    private Expr function;
    private Expr argument;
    
    public Tcreate(Expr function, Expr argument, Location startPos, Location endPos) {
    	super(startPos, endPos);		
		this.function = function;
		this.argument = argument;
    }
    
    @Override
    public KCall expand(){
	    List<KExpr> kargs = new ArrayList<KExpr>();
	    kargs.add(argument.expand());
	    return new KCall(function.expand(), kargs, getStartPos(), getEndPos());
    }

    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Tcreate[" + function.toString() + "," + argument.toString() + "]");
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Tcreate(");    	
	function.prettyPrint(buf);
	buf.append(", ");    	
    	String sep = "";
	argument.prettyPrint(buf);
    	buf.append(")");
    }
}
