package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Store extends BCInstr {
	private int ref;
	
	public Store(int ref) {
		this.ref = ref;
	}

	@Override
	public String getOpcodeName() {
		return "STORE";
	}
	
	@Override
	public int getOpcode() {
		return 10;
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

	
	@Override
	public String toString() {
		return "  STORE " + ref;
	}
}
