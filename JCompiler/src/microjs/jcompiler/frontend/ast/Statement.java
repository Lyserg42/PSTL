package microjs.jcompiler.frontend.ast;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KStatement;

public abstract class Statement extends ASTNode {
	public static final int INDENT_FACTOR = 2;

	/* package */ Statement(Location startPos, Location endPos) {
		super(startPos, endPos);
	}
	
	@Override
	public abstract KStatement expand();
	
	
	protected static List<KStatement> expandStatements(List<Statement> stmts) {
    	List<KStatement> kstmts = new ArrayList<KStatement>();
    	for(Statement stmt : stmts) {
    		kstmts.add(stmt.expand());
    	}
    	return kstmts;
	}
	
	protected void indent(StringBuilder buf, int level) {
		for(int i=0; i< level * INDENT_FACTOR ; i++) {
			buf.append(' ');
		}
	}

	protected abstract void prettyPrint(StringBuilder buf, int indent_level);
		
	@Override
	protected void prettyPrint(StringBuilder buf) {
		prettyPrint(buf, 0);
	}
	
	protected static void prettyPrintStatements(StringBuilder buf, List<Statement> stmts, int indent_level) {
		for(Statement stmt : stmts) {
			stmt.prettyPrint(buf, indent_level);
			buf.append(";\n");
		}	
	}

}
