package microjs.jcompiler.frontend.ast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KThread;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.utils.DotGraph;

public class MyThread extends Expr {
    private String fun;
    private List<Expr> arguments;
    
    public MyThread(String fun, List<Expr> arguments, Location startPos, Location endPos) {
    	super(startPos, endPos);		
		this.fun = fun;
		this.arguments = arguments;
    }
    
    @Override
    public KThread expand() {
    	List<KExpr> kargs = Expr.expandExprs(arguments);
    	return new KThread(fun, kargs, getStartPos(), getEndPos());
    }
    
    @Override
    protected String buildDotGraph(DotGraph graph) {
      	String callNode = graph.addNode("Thread[" + fun.toString() + "]");
	for(int i=0; i<arguments.size(); i++) {
		Expr arg = arguments.get(i);
		String argRoot = arg.buildDotGraph(graph);
		graph.addEdge(callNode, argRoot, "arg[" + i + "]");
	}
	return callNode;
    }

    @Override
    protected void prettyPrint(StringBuilder buf) {	
	buf.append("Thread(");    	
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
