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
    private List<Expr> arguments;
    
    public Th_create(String fun, List<Expr> arguments, Location startPos, Location endPos) {
    	super(startPos, endPos);		
		this.fun = fun;
		this.arguments = arguments;
    }
    
    @Override
    public KCall expand(){
	    List<KExpr> kargs = new ArrayList<KExpr>();
	    kargs.add(new KEVar(fun, getStartPos(), getEndPos()));
	    kargs.addAll(Expr.expandExprs(arguments));
	    return new KCall(new KEVar("th_create", getStartPos(), getEndPos()), kargs, getStartPos(), getEndPos());
    }

    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Th_create[" + fun.toString() + "]");
	for(int i=0; i<arguments.size(); i++) {
		Expr arg = arguments.get(i);
		String argRoot = arg.buildDotGraph(graph);
		graph.addEdge(callNode, argRoot, "arg[" + i + "]");
	}
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Th_create(");    	
	buf.append(fun);
	buf.append(", ");    	
    	String sep = "";
    	for(Expr arg : arguments) {
    		buf.append(sep);
    		arg.prettyPrint(buf);
    		if(sep.equals("")) {
    			sep = ", ";
    		}
    	}
    	buf.append(")");
    }
}
