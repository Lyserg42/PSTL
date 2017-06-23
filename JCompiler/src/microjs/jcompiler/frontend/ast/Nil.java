package microjs.jcompiler.frontend.ast;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KExpr;
import microjs.jcompiler.middleend.kast.KNil;
import microjs.jcompiler.utils.DotGraph;

public class Nil extends Expr{

	public Nil(Location startPos, Location endPos){
		super(startPos, endPos);
	}
	
	@Override
	public KExpr expand(){
		return new KNil(startPos, endPos);	
	}
	
	@Override
	protected String buildDotGraph(DotGraph graph){
		String nilNode = graph.addNode("Nil");
		return nilNode;
	}

	@Override
	protected void prettyPrint(StringBuilder buf){
		buf.append("Nil");
	}
}
