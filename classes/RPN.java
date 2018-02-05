
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.*;

public class RPN extends HttpServlet {

	
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
  	{
			PrintWriter out = resp.getWriter();
			HttpSession session = req.getSession(true);
			String newItem=null;//,prev=null;
			Stack previousItems = (Stack)session.getAttribute("previousItems");
				if (previousItems == null) 
				{
					previousItems = new Stack();
					session.setAttribute("previousItems", previousItems);
				}
				if(previousItems.size()>=1)
				{
					String prev=(String)previousItems.pop();
					//out.println(prev);
					newItem = calculate(req,resp,prev);
				}
				else
					newItem = calculate(req,resp,"F");
				/*else if(previousItems.size()<=0)
				{
					newItem = calculate(req,resp,null);
				}*/
				synchronized(previousItems) 
				{
					if (newItem.equals("F")) 
					{
						session.removeAttribute("previousItems");
						previousItems.clear();
						out.println("400 Bad Request");
					}
					
					else 
					{
						
						previousItems.add(newItem);
						out.println("200 OK "+newItem);
						
					}
				}
	}
	String calculate(HttpServletRequest req, HttpServletResponse resp,String prev) throws ServletException, IOException
	{
		
			String param = "op";
			String value = req.getParameter(param);
			if (value == null) 
			{
				resp.setStatus(400);
				//return;
				
			} 
			else if ("".equals(value)) 
			{
				resp.setStatus(400);
				//return;
			}
	    
				 PrintWriter out = resp.getWriter();

	   Enumeration enu = req.getParameterNames();

		
	     for (; enu.hasMoreElements(); ) 
		 {
	        param = (String)enu.nextElement();
			if(!param.equalsIgnoreCase("op"))
			{
				resp.setStatus(400);
				//return;
			}
	        // Get the value of the request parameter
	      //  value = req.getParameter(param);
			
			// If the request parameter can appear more than once in the query string, get all values
	       String[] values = req.getParameterValues(param);
			

			for(int i=0;i<values.length;i++) // checks for other parameter null values
			{
				if(Test.legal(values[i]) || CheckFor.isletter(values[i]))
				{
					
					resp.setStatus(400);
					//return;
				}
				if(values[i].equals(" "))
				{
					String str1 = Test.extract(req.getQueryString(),i);
					if(str1.equals("+"))
					{
								//out.print(str1);
								values[i]="+";
					}
					else
					{
						resp.setStatus(400);
						//return;
					}
				}
				if(values[i].equals("_") && !prev.equals("F"))
				{
					values[i]=prev;
					//out.print(values[i]+" "+prev+" "+!prev.equals("F"));
				}
				
				
			}
			if(resp.getStatus()==HttpServletResponse.SC_OK)
			{
				
				String answer = Calculator.calc(values);
				if(answer.equals("F"))
				{
					resp.setStatus(400);//return;
				}
				if(resp.getStatus()==HttpServletResponse.SC_OK)
				{

					//out.println(resp.getStatus()+" "+answer);
					return answer;
							  
						
				}
				else
					//out.print(resp.getStatus());
					return "F";
								
			}
			else
				 //out.print(resp.getStatus());
				return "F";
							
		  } 
	return "F";
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
	  
   }
}
  
 
