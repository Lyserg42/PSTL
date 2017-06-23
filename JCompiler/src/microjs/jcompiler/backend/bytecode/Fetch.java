package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Fetch extends BCInstr {
	private int ref;
	
	public Fetch(int ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "FETCH";
	}
	
	@Override
	public int getOpcode() {
		return 8;
	}
	
	@Override
	public int getSize() {
		return 2;
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		gen.encode(ref);
	}
	
	@Override
	public String toString() {
		return "  FETCH " + ref;
	}
}
