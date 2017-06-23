package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Push extends BCInstr {
	private BCValue value;
	
	public Push(BCValue value) {
		this.value = value;
	}
	
	@Override
	public String getOpcodeName() {
		return "PUSH";
	}
	
	@Override
	public int getOpcode() {
		return 1;
	}
	
	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		value.genBytecode(gen);
	}
	
	@Override
	public int getSize() {
		return value.getSize() + 1;
	}


	
	@Override
	public String toString() {
		return "  PUSH " + value.toString();
	}
}
