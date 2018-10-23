package patterns.delegation.office;
import java.util.*;
import java.util.function.BinaryOperator;

public class Manager implements Employee{
	private List<Employee> employees;
	private int neste = 0;
	private int tasks;
	
	public Manager(Collection<Employee> employees) {
		if (employees.isEmpty()) {
			throw new IllegalArgumentException("No one to boss with, try again!");
		}
		this.employees = new ArrayList<Employee>(employees);
	}
	
	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		// TODO Auto-generated method stub
		tasks++;
		if (neste +1 < employees.size()) {
			neste ++;
		}
		else {
			neste = 0;
		}
		return employees.get(neste).doCalculations(operation, value1, value2);
	}

	@Override
	public void printDocument(String document) {
		// TODO Auto-generated method stub
		tasks++;
		if(neste+1 < employees.size()){
			neste ++;
		}
		else {
			neste = 0;
		}
		this.employees.get(neste).printDocument(document);
	}

	@Override
	public int getTaskCount() {
		return tasks; 
	}

	@Override
	public int getResourceCount() {
		// TODO Auto-generated method stub
		int antall = 1;
		for (Employee e : employees) {
			antall += e.getResourceCount();
		}
		return antall;
	}

}
