package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Prim extends BCValue{
	private int ref;
	
	public Prim(int ref) {
		this.ref = ref;
	}
	
	@Override
	public int getOpcode() {
		return 2;
	}
	
	@Override
	public String getOpcodeName() {
		return "PRIM";
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		gen.encode(ref);
	}
	
	@Override
	public int getSize() {
		return 2;
	}

	
	public String toString() {
		return "PRIM " + ref;
	};
}
