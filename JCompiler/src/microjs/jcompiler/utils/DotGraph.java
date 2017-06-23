package microjs.jcompiler.utils;

import java.util.ArrayList;
import java.util.List;

public class DotGraph {
	private List<Node> nodes;
	private List<Edge> edges;
	
	public DotGraph() {
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	public String addNode(String label) {
		String nodeName = "N" + (nodes.size() + 1);		
		nodes.add(new Node(nodeName, label));
		return nodeName;
	}
		
	public void addEdge(String srcName, String dstName, String label) {
		edges.add(new Edge(srcName, dstName, label));
	}

	public void addEdge(String srcName, String dstName) {
		edges.add(new Edge(srcName, dstName, ""));
	}
	
	class Node {
		private String label;
		private String name;
		
		public Node(String name, String label) {
			this.name = name;
			this.label = label;
		}
				
		public String getName() {
			return name;
		}
		
		public String getLabel() {
			return label;
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj==null) return false;
			if(obj==this) return true;
			if(obj.getClass() != this.getClass()) 
				return false;
			return ((Node) obj).name.equals(name);
		}
		
		@Override
		public String toString() {
			StringBuilder buf = new StringBuilder();
			buf.append("  "); buf.append(name); buf.append( "[label=\"");
			buf.append(label); buf.append("\"];\n");
			return buf.toString();
		}
	}
	
	class Edge {
		private String srcName;
		private String dstName;
		private String label;
		
		public Edge(String srcName, String dstName, String label) {
			this.srcName = srcName;
			this.dstName = dstName;
			this.label = label;
		}
		
		@Override
		public String toString() {
			StringBuilder buf = new StringBuilder();
			buf.append("  "); buf.append(srcName); buf.append( " -> "); buf.append(dstName);
			buf.append(" [label=\""); buf.append(label); buf.append("\"];\n");
			return buf.toString();
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("digraph {\n");
		buf.append("  rankdir=\"LR\";\n");
		for (Node node : nodes) {
			buf.append(node.toString());
		}
		for (Edge edge: edges) {
			buf.append(edge.toString());
		}
		buf.append("}\n");
		return buf.toString();
	}
	
}
