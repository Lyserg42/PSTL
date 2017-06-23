package microjs.jcompiler.backend.bytecode;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Bytecode {
	private List<BCInstr> code;
	
	public Bytecode() {
		code = new ArrayList<BCInstr>();
	}
	
	public List<BCInstr> getCode() {
		return code;
	}
	
	public Bytecode galloc() {
		code.add(new GAlloc());
		return this;
	}

	public Bytecode gfetch(int ref) {
		code.add(new GFetch(ref));
		return this;
	}

	public Bytecode gstore(int ref) {
		code.add(new GStore(ref));
		return this;
	}

	public Bytecode fetch(int ref) {
		code.add(new Fetch(ref));
		return this;
	}

	public Bytecode store(int ref) {
		code.add(new Store(ref));
		return this;
	}
	
	public Bytecode push(BCValue value) {
		code.add(new Push(value));
		return this;
	}
	
	public Bytecode pop() {
		code.add(new Pop());
		return this;
	}
	
	public Bytecode call(int ref) {
		code.add(new Call(ref));
		return this;
	}

	public Bytecode bcReturn() {
		code.add(new Return());
		return this;
	}

	public Bytecode label(String lbl) {
		code.add(new Label(lbl));
		return this;
	}
	
	public Bytecode jump(String lbl) {
		code.add(new Jump(lbl));
		return this;
	}

	public Bytecode jfalse(String lbl) {
		code.add(new JFalse(lbl));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for(BCInstr instr : code) {
			buf.append(instr.toString());
			buf.append("\n");
		}
		return buf.toString();
	}
	
	private static SortedMap<Integer, BCInstr> instructionSet = null;
	
	public static SortedMap<Integer, BCInstr> getInstructionSet() {
		if(instructionSet == null) {
			BCInstr instr = null;
			instructionSet = new TreeMap<Integer, BCInstr>();
			instr = new Label("lbl");
			instructionSet.put(instr.getOpcode(), instr);
			instr = new GAlloc();
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Push(new Unit());
			instructionSet.put(instr.getOpcode(), instr);
			instr = new GStore(-1);
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Pop();
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Jump("lbl");
			instructionSet.put(instr.getOpcode(), instr);
			instr = new GFetch(-1);
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Call(-1);
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Return();
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Fetch(-1);
			instructionSet.put(instr.getOpcode(), instr);
			instr = new JFalse("lbl");
			instructionSet.put(instr.getOpcode(), instr);
			instr = new Store(-1);
			instructionSet.put(instr.getOpcode(), instr);
		}
		return instructionSet;
	}
	
	private static SortedMap<Integer, BCValue> valueSet = null;
	
	public static SortedMap<Integer, BCValue> getValueSet() {
		if(valueSet == null) {
			BCValue val = null;
			valueSet = new TreeMap<Integer, BCValue>();
			val = new Unit();
			valueSet.put(val.getOpcode(), val);
			val = new Int(42);
			valueSet.put(val.getOpcode(), val);
			val = new Prim(42);
			valueSet.put(val.getOpcode(), val);
			val = new Fun("lbl");
			valueSet.put(val.getOpcode(), val);
			val = new Bool(false);
			valueSet.put(val.getOpcode(), val);
		}
		return valueSet;
	}
	
	public static String genCDeclarations() {
		StringBuilder buf = new StringBuilder();
		buf.append("/* Constantes pour les opcodes */\n");
		for(Integer id : getInstructionSet().keySet()) {
			BCInstr instr = getInstructionSet().get(id);
			buf.append("\n");
			buf.append(instr.genCDeclaration());
		}
		buf.append("\n");
		buf.append("/** Noms des opcodes */\n");
		buf.append("extern const char *opcode_names[];\n\n");
		
		buf.append("/* Constantes pour les types */\n");
		
		for(Integer id : getValueSet().keySet()) {
			BCValue val = getValueSet().get(id);
			buf.append("\n");
			buf.append(val.genCDeclaration());
		}
		buf.append("\n/* type T_PAIR (réservé) */\n");
		buf.append("#define T_PAIR "); buf.append(getValueSet().size()); buf.append("\n");
		buf.append("\n");
		buf.append("/** Noms des types */\n");
		buf.append("extern const char *type_names[];\n\n");
		
		return buf.toString();
	}
	
	public static String genCDefinitions() {
		StringBuilder buf = new StringBuilder();

		buf.append("/** Noms des opcodes */\n");
		buf.append("const char *opcode_names[] = {\n");
		for(Integer id : getInstructionSet().keySet()) {
			BCInstr instr = getInstructionSet().get(id);
			buf.append("  \""); buf.append(instr.getOpcodeName()); buf.append("\",\n");
		}
		buf.append("  \"<unknown>\"\n");
		buf.append("};\n\n");
		
		buf.append("/** Noms des types */\n");
		buf.append("const char *type_names[] = {\n");
		for(Integer id : getValueSet().keySet()) {
			BCValue val = getValueSet().get(id);
			buf.append("  \""); buf.append(val.getOpcodeName()); buf.append("\",\n");
		}
		buf.append("  \"pair\",\n");
		buf.append("  \"<unknown>\"\n");
		buf.append("};\n\n");

		return buf.toString();
	}
	
	
}
