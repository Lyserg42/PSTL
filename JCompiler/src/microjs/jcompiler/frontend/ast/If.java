package microjs.jcompiler.frontend.ast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KIf;
import microjs.jcompiler.middleend.kast.KSeq;
import microjs.jcompiler.middleend.kast.KStatement;
import microjs.jcompiler.utils.DotGraph;

public class If extends Statement {
    private Expr cond;
    private List<Statement> thens;
    private List<Statement> elses;
    
    public If(Expr cond, List<Statement> thens, List<Statement> elses, Location startPos, Location endPos) {
    	super(startPos, endPos);		
    	this.cond = cond;
    	this.thens = thens;
    	this.elses = elses;
    }
    
    @Override
    public KIf expand() {
    	// then part
    	Location thenStartPos = getStartPos(); // XXX: good approximation ?
    	Location thenEndPos = getStartPos();
    	List<KStatement> kthens = Statement.expandStatements(thens);
    	KStatement kthen = KSeq.buildKSeq(kthens, thenStartPos, thenEndPos);
    	
    	// else part
    	Location elseStartPos = thenEndPos; // XXX: good approximation ?
    	Location elseEndPos = thenEndPos;
    	List<KStatement> kelses = Statement.expandStatements(elses);
    	KStatement kelse = KSeq.buildKSeq(kelses, elseStartPos, elseEndPos);
    	return new KIf(cond.expand(), kthen, kelse, getStartPos(), getEndPos());
    }
    
	@Override
	protected String buildDotGraph(DotGraph graph) {
		String ifNode = graph.addNode("If");
		String condNode = cond.buildDotGraph(graph);
		graph.addEdge(ifNode, condNode, "cond");
		String thenNode = cond.buildDotGraph(graph);
		graph.addEdge(ifNode, thenNode, "then");
		String elseNode = cond.buildDotGraph(graph);
		graph.addEdge(ifNode, elseNode, "else");
	
		return ifNode;
	}

    
    @Override
    protected void prettyPrint(StringBuilder buf, int indent_level) {
    	indent(buf, indent_level);
    	buf.append("if (");
    	cond.prettyPrint(buf);
    	buf.append(") {\n");
    	Statement.prettyPrintStatements(buf, thens, indent_level + 1);
    	indent(buf, indent_level);
    	buf.append("} else {\n");
    	Statement.prettyPrintStatements(buf, elses, indent_level + 1);
    	indent(buf, indent_level);
    	buf.append("}");
    }
}
