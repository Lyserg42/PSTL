package microjs.jcompiler.middleend.kast;

public interface KASTVisitor {
	/* program visitor */
	public void visit(KProg prog);
	/* statement visitors */
	public void visit(KVoidExpr stmt);
	public void visit(KVar stmt);
	public void visit(KIf stmt);
	public void visit(KSeq stmt);
	public void visit(KAssign stmt);
	public void visit(KReturn stmt);
	/* expression visitors */
	public void visit(KInt expr);
	public void visit(KTrue expr);
	public void visit(KFalse expr);
	public void visit(KEVar expr);
	public void visit(KCall expr);
	public void visit(KClosure expr);
	public void visit(KNil expr);
}
