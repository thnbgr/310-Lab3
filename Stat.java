import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;


public class Stat {
public static void main(String[] args) throws IOException {
	
	PrintWriter writer = new PrintWriter("pre2.csv", "UTF-8");
	BufferedReader reader = new BufferedReader(new FileReader("/bldg_utility_pre_install.csv"));
	String line = null;
	HashMap<String, ArrayList<Float>> h = new HashMap<String, ArrayList<Float>>();
	ArrayList<Float> f = new ArrayList<Float>();
	boolean firstLine = true;
	while ((line = reader.readLine()) != null) {
		if (firstLine) {
			firstLine = false;
			continue;
		}
		if (line.length() == 0) continue;
		String[] values = line.split(",");
		if (!h.containsKey(values[0])) {
			h.put(values[0], new ArrayList<Float>());
		}
		if (values.length < 8) continue;
		h.get(values[0]).add(Float.parseFloat(values[7]));
	}
	for (Entry<String, ArrayList<Float>> s : h.entrySet()) {
		ArrayList<Float> a = s.getValue();
		float avg = (float) ((Collections.max(a)+Collections.min(a))/2.0);
		writer.println(s.getKey()+","+avg);
	}
	writer.close();

}
}
