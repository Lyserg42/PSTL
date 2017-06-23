package microjs.jcompiler.frontend.ast;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;
import microjs.jcompiler.middleend.kast.KExpr;

public abstract class Expr extends ASTNode {

	/* package */ Expr(Location startPos, Location endPos) {
		super(startPos, endPos);
	}
	
	@Override
	public abstract KExpr expand();
	
	protected static List<KExpr> expandExprs(List<Expr> exprs) {
		List<KExpr> kexprs = new ArrayList<KExpr>();
		for(Expr expr : exprs) {
			kexprs.add(expr.expand());
		}
		return kexprs;
	}
}
