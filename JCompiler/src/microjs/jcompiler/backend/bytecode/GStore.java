package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class GStore extends BCInstr {
	private int ref;
	
	public GStore(int ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "GSTORE";
	}
	
	@Override
	public int getOpcode() {
		return 2;
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
		return "  GSTORE " + ref;
	}
}
