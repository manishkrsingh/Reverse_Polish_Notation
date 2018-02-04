import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RPN extends HttpServlet {
 
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
  	{
			//doPost(request, response);
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
	        value = req.getParameter(param);
			
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
					//String met = req.getMethod();
					out.print(resp.getStatus()+" OK "+answer);
				
				}
				else
					out.print(resp.getStatus());
			}
			else
				out.print(resp.getStatus());
							
		  } 
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException 
    {
	  
   }
}
  
 