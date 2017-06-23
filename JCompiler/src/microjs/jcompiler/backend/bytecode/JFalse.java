package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class JFalse extends BCInstr {
	private String ref;
	
	public JFalse(String ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "JFALSE";
	}
	
	@Override
	public int getOpcode() {
		return 9;
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
		return "  JFALSE " + ref;
	}
}
