package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class GFetch extends BCInstr {
	private int ref;
	
	public GFetch(int ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "GFETCH";
	}
	
	@Override
	public int getOpcode() {
		return 5;
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
		return "  GFETCH " + ref;
	}
}
