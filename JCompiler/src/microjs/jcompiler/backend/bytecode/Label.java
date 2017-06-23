package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public class Label extends BCInstr {
	private String ref;
	
	public Label(String ref) {
		this.ref = ref;
	}
	
	public String getRef() {
		return ref;
	}
	
	public boolean isLabel() {
		return true;
	}
	
	@Override
	public String getOpcodeName() {
		return "LABEL";
	}
	
	@Override
	public int getOpcode() {
		return -1;
	}
	
	@Override
	public void genBytecode(Serializer gen) {
		// nothing to do (no generation of labels)
	}
	
	@Override
	public int getSize() {
		return 0;
	}

		
	@Override
	public String toString() {
		return ref + ":";
	}
}
