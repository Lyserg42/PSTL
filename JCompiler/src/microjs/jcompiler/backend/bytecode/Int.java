package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Int extends BCValue{
	private int value;
	
	public Int(int value) {
		this.value = value;
	}
	
	@Override
	public int getOpcode() {
		return 1;
	}
	
	@Override
	public String getOpcodeName() {
		return "INT";
	}

	@Override
	public void genBytecode(Serializer gen) {
		gen.encode(getOpcode());
		gen.encode(value);
	}

	@Override
	public int getSize() {
		return 2;
	}

	public String toString() {
		return "INT " + value;
	};
}
