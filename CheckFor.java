class CheckFor
{
	public static boolean isletter(String str)
	{
		for(char c:str.toCharArray())
		{
			if(Character.isLetter(c))
			{
				if(c=='x')
					continue;
				return true;
			}
		}
		return false;
	}
	public static boolean isNumber(String str)
	{
		for(char c:str.toCharArray())
		{
			if(!Character.isDigit(c))
				return false;
		}
		return true;
	}
	
	
}