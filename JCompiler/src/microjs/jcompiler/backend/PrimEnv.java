package microjs.jcompiler.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PrimEnv {
	private Map<String, Primitive> prims;
	private List<String> primIds;
	
	public PrimEnv() {
		prims = new TreeMap<String, Primitive>();
		primIds = new ArrayList<String>();
	}
	
	public void register(Primitive prim) {
		if(prims.containsKey(prim.getName())) {
			throw new PrimAlreadyDefined(prim.getName());
		}
		prims.put(prim.getName(), prim);
		prim.setId(primIds.size());
		primIds.add(prim.getName());
	}
	
	public Primitive fetch(String name) throws PrimNotFound {
		Primitive prim = prims.get(name);
		if(prim==null) {
			throw new PrimNotFound(name);
		}
		return prim;
	}
	
	public class PrimAlreadyDefined extends Error {
		private static final long serialVersionUID = -129581314413064877L;

		public PrimAlreadyDefined(String prim) {
			super("Primitive '" + prim + "' already defined.");
		}
	}
	
	public class PrimNotFound extends Exception {
		private static final long serialVersionUID = 132383297676147375L;

		public PrimNotFound(String prim) {
			super("Primitive '" + prim + "' not found.");
		}
	}
	
	public static PrimEnv defaultPrimEnv() {
		PrimEnv primEnv = new PrimEnv();
		
		primEnv.register(new Primitive("+", "Addition", "P_ADD"));
		primEnv.register(new Primitive("-", "Subtraction", "P_SUB"));
		primEnv.register(new Primitive("*", "Multiplication", "P_MUL"));
		primEnv.register(new Primitive("/", "Division", "P_DIV"));
		primEnv.register(new Primitive("==", "Equality", "P_EQ"));
		primEnv.register(new Primitive("<", "Lower", "P_INF"));
		primEnv.register(new Primitive(">", "Greater", "P_SUP"));
		primEnv.register(new Primitive("<=", "Lower or equal", "P_INFEQ"));
		primEnv.register(new Primitive(">=", "Greater or equal", "P_SUPEQ"));
		primEnv.register(new Primitive("cons", "Cons", "P_CONS"));
		primEnv.register(new Primitive("car", "Car", "P_CAR"));
		primEnv.register(new Primitive("cdr", "Cdr", "P_CDR"));
		primEnv.register(new Primitive("nil", "Nil", "P_NIL"));
		
		return primEnv;
	}
	
	public String genCDeclarations() {
		StringBuilder buf = new StringBuilder();
		buf.append("/* Constantes pour les primitives */\n");
		for (String primName : primIds) {
			Primitive prim = prims.get(primName);
			buf.append("\n");
			buf.append(prim.getCDeclaration());
			buf.append("\n");
		}
		buf.append("\n/** Noms des primitives */\n");
		buf.append("extern const char *primitive_names[];\n");
		return buf.toString();
	}
		
	public String genCDefinitions() {
		StringBuilder buf = new StringBuilder();
		buf.append("/** Noms des primitives */\n");
		buf.append("const char *primitive_names[] = {\n");
		for (String primName : primIds) {
			buf.append("  \""); buf.append(primName); buf.append("\",\n");
		}
		buf.append("  \"<unknown>\"\n");
		buf.append("};\n");
		return buf.toString();
	}


}
