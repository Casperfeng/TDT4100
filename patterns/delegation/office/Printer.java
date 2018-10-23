package patterns.delegation.office;
import java.util.*;
public class Printer {
	
	private Map<Employee, List<String>> printHistory = new HashMap<>();
	
	public void printDocument(String document, Employee employee) {
		// TODO Auto-generated method stub
		if (!printHistory.containsKey(employee)) {
			List<String> documents = new ArrayList(Arrays.asList(document));
			printHistory.put(employee, documents);
		}
		else {
			printHistory.get(employee).add(document);
		}
	}
	
	public List<String> getPrintHistory(Employee employee){
		if (!printHistory.containsKey(employee)) {
		List<String> emptyList = new ArrayList<>();
		return emptyList;
		}
		return printHistory.get(employee);
	}

}
