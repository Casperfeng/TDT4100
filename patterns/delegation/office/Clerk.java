package patterns.delegation.office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee{
	
	private Printer printer;
	private int taskCount = 0;
	
	public Clerk(Printer printer) {
		this.printer = printer;
	}
	
	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		// TODO Auto-generated method stub
		taskCount++;
		return operation.apply(value1, value2);
	
	}

	@Override
	public void printDocument(String document) {
		// TODO Auto-generated method stub
		this.printer.printDocument(document, this);
		taskCount++;
	}

	@Override
	public int getTaskCount() {
		// TODO Auto-generated method stub
		return taskCount;
	}

	@Override
	public int getResourceCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	

}
