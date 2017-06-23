package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Unit extends BCValue{
	public Unit() {
		
	}
	
	@Override
	public int getOpcode() {
		return 0;
	}
	
	@Override
	public String getOpcodeName() {
		return "UNIT";
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	public String toString() {
		return "UNIT";
	};
}
