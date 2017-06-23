package microjs.jcompiler.backend.bytecode;

import microjs.jcompiler.backend.Serializer;

public abstract class BCValue {
	public abstract int getOpcode();
	public abstract String getOpcodeName();

	public abstract void genBytecode(Serializer gen);
	
	public abstract int getSize();
	
	public String genCDeclaration() {
		StringBuilder buf = new StringBuilder();
		buf.append("/** type "); buf.append(getOpcodeName()); buf.append(" */\n");
		buf.append("#define T_"); buf.append(getOpcodeName()); buf.append(" "); buf.append(getOpcode());
		buf.append("\n");
		return buf.toString();
	}

}
