package microjs.jcompiler.backend;

import java.util.HashMap;
import java.util.Map;

public class GlobalEnv {
	private Map<String, Integer> vars;
	
	public GlobalEnv() {
		vars = new HashMap<String, Integer>();
	}
	
	public int fetch(String var) throws VarNotFound {
		Integer ref = vars.get(var);
		if(ref==null) {
			throw new VarNotFound(var);
		}
		return ref;
	}
	
	public int extend(String var) throws VarAlreadyDefined {
		if(vars.containsKey(var)) {
			throw new VarAlreadyDefined(var);
		}
		vars.put(var, vars.size());
		return vars.size() - 1;
	}
	
	public class VarAlreadyDefined extends Exception {
		private static final long serialVersionUID = 5877188650153737752L;

		public VarAlreadyDefined(String var) {
			super("Global variable '" + var + "' already defined.");
		}
	}
	
	public class VarNotFound extends Exception {
		private static final long serialVersionUID = -7497344127580702658L;

		public VarNotFound(String var) {
			super("Global variable '" + var + "' not found.");
		}
	}


}
