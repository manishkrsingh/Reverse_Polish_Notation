
import java.util.Stack;

public class Calculator
{
	public static String calc(String[] args)
	{
		
		Stack<Integer> results = new Stack<Integer>();
		for(int i=0;i<args.length;i++)
		 {
			 String input = args[i];
			 input=input.trim();
			// If the command is an operator, pop the arguments and push the result
			 if(input.equals("+"))
			{
				 if(check(results))
				 {
					 results.push(results.pop() + results.pop());
				 }
				 else
				 return "F";

			}
			 else if(input.equals("-"))
			{
				 if(check(results)) 
				 {
			   Integer arg2 = results.pop();
			   results.push(results.pop() - arg2);
			   }
				 else
					 return "F";
			}
			 else if(input.equals("*") || input.equals("x"))
			{
				 if(check(results)) 
				 {
			   results.push(results.pop() * results.pop());
				 }
				 else
					 return "F";
			}
			 else if(input.equals("/"))
			{
				 if(check(results)) 
				 {
			   Integer arg2 = results.pop();
			   if(arg2==0) return "F";
			   
			   results.push(results.pop() / arg2);
				 }
				 else
					 return "F";
			}
			 else if(CheckFor.isNumber(input))
			{
			  // Not an operator—push the input value
			   results.push(Integer.parseInt(input.trim()));
			}
			else
			{
				return "F";
			}
			
		 }
		if(results.size()>1)
			return "F";
		return String.valueOf(results.pop());
	}
	public static boolean check(Stack s)
	{
		if(!s.isEmpty() && s.size()>1)
			return true;
		return false;
	}
	
	
}
