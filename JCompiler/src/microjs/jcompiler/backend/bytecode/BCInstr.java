package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public abstract class BCInstr {
	public abstract int getOpcode();
	public abstract String getOpcodeName();
	
	public abstract void genBytecode(Serializer gen);
	
	public abstract int getSize();
	
	public boolean isLabel() {
		return false;
	}
	
	public Label asLabel() {
		return (Label) this;
	}
	
	public String genCDeclaration() {
		StringBuilder buf = new StringBuilder();
		buf.append("/** opcode "); buf.append(getOpcodeName()); buf.append(" */\n");
		buf.append("#define I_"); buf.append(getOpcodeName()); buf.append(" "); buf.append(getOpcode());
		buf.append("\n");
		return buf.toString();
	}
}
