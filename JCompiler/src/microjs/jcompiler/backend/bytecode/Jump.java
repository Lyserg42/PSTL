package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Jump extends BCInstr {
	private String ref;
	
	public Jump(String ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "JUMP";
	}
	
	@Override
	public int getOpcode() {
		return 4;
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		gen.encode(gen.fetchLabel(ref));
	}
	
	@Override
	public int getSize() {
		return 2;
	}

	
	@Override
	public String toString() {
		return "  JUMP " + ref;
	}
}
