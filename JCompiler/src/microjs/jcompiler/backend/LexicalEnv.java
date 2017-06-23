package microjs.jcompiler.backend;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LexicalEnv {
	private Deque<String> lex;
	
	public LexicalEnv() {
		lex = new ArrayDeque<String>();
	}
	
	public int fetch(String var) throws VarNotFound {
		int i = 0;
		for(String v : lex) {
			if(var.equals(v)) {
				return i;
			}
			i++;
		}
		throw new VarNotFound(var);
	}
	
	public void extend(List<String> vars) {
		for(int i=vars.size()-1;i>=0;i--) {
			lex.addFirst(vars.get(i));
		}
	}
	
	public void drop(int howMany) {
		for(int i=0;i<howMany;i++) {
			lex.removeFirst();
		}
	}
	
	@Override
	public String toString() {
		return lex.toString();
	}
	
	public class VarNotFound extends Exception {
		private static final long serialVersionUID = -428657346019066106L;

		public VarNotFound(String var) {
			super("Local variable '" + var + "' not found.");
		}
	}

	
}
