package refreshy.abe.algorithms;

import java.util.ArrayList;
import java.util.List;

public class GraphNody {
	char visited = '0';
	int depth=-1;
	String data;
	List<GraphNody> neighbors = new ArrayList<GraphNody>();

	public GraphNody(String data) {
		this.data = data;
	}
}
