package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Call extends BCInstr {
	private int ref;
	
	public Call(int ref) {
		this.ref = ref;
	}
	
	@Override
	public String getOpcodeName() {
		return "CALL";
	}
	
	@Override
	public int getOpcode() {
		return 6;
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
		return "  CALL " + ref;
	}
}
