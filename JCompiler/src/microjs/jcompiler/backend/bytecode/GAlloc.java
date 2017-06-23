package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class GAlloc extends BCInstr {
	public GAlloc() {
		
	}
	
	@Override
	public String getOpcodeName() {
		return "GALLOC";
	}
	
	@Override
	public int getOpcode() {
		return 0;
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
		return "  GALLOC";
	}
}
