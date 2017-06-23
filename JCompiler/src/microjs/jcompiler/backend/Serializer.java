package microjs.jcompiler.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import microjs.jcompiler.backend.bytecode.BCInstr;
import microjs.jcompiler.backend.bytecode.Bytecode;

public class Serializer {
	private Map<String, Integer> labels;
	private int bcSize;
	private Bytecode bc;
	private StringBuilder buf;
	
	public Serializer(Bytecode bc) {
		labels = null;
		bcSize = -1;
		this.bc = bc;
		buf = null;
	}
	
	public void encode(int val) {
		buf.append(" ");
		buf.append(val);
	}
	
	public int fetchLabel(String lbl) {
		return labels.get(lbl);
	}
	
	private void computeJumps() {
		List<BCInstr> code = bc.getCode();
		labels = new HashMap<String, Integer>();
		
		int pc = 0;
		for(int i=0; i < code.size(); i++) {
			BCInstr instr = code.get(i);
			if(instr.isLabel()) {
				labels.put(instr.asLabel().getRef(), pc);
			} else {
				pc += instr.getSize();
			}
		}
		
		bcSize = pc;
	}
	
	public String serialize() {
		computeJumps();
		
		buf = new StringBuilder();
		
		// preamble
		buf.append("424242");
		encode(bcSize);
		
		for(BCInstr instr : bc.getCode()) {
			if(instr.isLabel()) {
				// rien à faire
			} else {
				instr.genBytecode(this);
			}
		}
		
		buf.append(" ");  // need at least one space at the end
		
		return buf.toString();
		
	}
	
	public void serializeToFile(String filename) throws IOException {
		FileWriter writer = new FileWriter(filename);
		String bcStr = serialize();
		writer.write(bcStr);
		writer.close();
	}
}
