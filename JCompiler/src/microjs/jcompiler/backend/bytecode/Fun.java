package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Fun extends BCValue{
	private String lbl;
	
	public Fun(String lbl) {
		this.lbl = lbl;
	}
	
	@Override
	public int getOpcode() {
		return 3;
	}
	
	@Override
	public String getOpcodeName() {
		return "FUN";
	}
	
	@Override
	public int getSize() {
		return 2;
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		gen.encode(gen.fetchLabel(lbl));
	}
	
	public String toString() {
		return "FUN " + lbl;
	};
}
