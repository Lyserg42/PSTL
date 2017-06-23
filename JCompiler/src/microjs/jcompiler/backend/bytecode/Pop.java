package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Pop extends BCInstr {
	public Pop() {
		
	}

	@Override
	public String getOpcodeName() {
		return "POP";
	}
	
	@Override
	public int getOpcode() {
		return 3;
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
		return "  POP";
	}
}
