package microjs.jcompiler.middleend.kast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class KInt extends KExpr {
	private int value;
	
	public KInt(int value, Location startPos, Location endPos) {
		super(startPos, endPos);
		this.value = value;
	}

    @Override
    public void accept(KASTVisitor visitor) {
    	visitor.visit(this);
    }
    
    public int getValue() {
    	return value;
    }

}
