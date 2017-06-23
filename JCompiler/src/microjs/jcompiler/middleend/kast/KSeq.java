package microjs.jcompiler.middleend.kast;

import java.util.List;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KSeq extends KStatement {
	private List<KStatement> stmts;
	
	public KSeq(List<KStatement> stmts, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.stmts = stmts;
	}
	
	public static KStatement buildKSeq(List<KStatement> stmts, Location defaultStartPos, Location defaultEndPos) {
		if(stmts.size() == 1) {
			// not a *real* sequence
			return stmts.get(0);
		}

		if(!stmts.isEmpty()) {
			defaultStartPos = stmts.get(0).getStartPos();
			defaultEndPos = stmts.get(stmts.size()-1).getEndPos();
		}
		return new KSeq(stmts, defaultStartPos, defaultEndPos);
	}
	
	@Override
	public void accept(KASTVisitor visitor) {
		visitor.visit(this);
	}

	public List<KStatement> getStatements() {
		return stmts;
	};

}
