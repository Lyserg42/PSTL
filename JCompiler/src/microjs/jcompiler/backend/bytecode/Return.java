package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Return extends BCInstr {
	public Return() {
		
	}
	
	@Override
	public String getOpcodeName() {
		return "RETURN";
	}
	
	@Override
	public int getOpcode() {
		return 7;
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
	}	
	
	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String toString() {
		return "  RETURN";
	}
}
