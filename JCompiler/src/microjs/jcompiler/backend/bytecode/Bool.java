package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Bool extends BCValue{
	private boolean value;
	
	public Bool(boolean value) {
		this.value = value;
	}
	
	@Override
	public int getOpcode() {
		return 4;
	}
	
	@Override
	public String getOpcodeName() {
		return "BOOL";
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		if(value == true) {
			gen.encode(1);
		} else {
			gen.encode(0);
		}
	}
	
	@Override
	public int getSize() {
		return 2;
	}
	
	public String toString() {
		if(value) {
			return "BOOL TRUE";
		} else {
			return "BOOL FALSE";
		}
	};
}
