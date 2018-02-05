class Test
{
	static boolean legal(String str)
	{
		if(str.equals(""))
			return true;
		else return false;
	}
	static String extract(String str,int place)
	{
		String strArr[] = str.split("&");
		String operator=extractStr(strArr, place);
		return operator;
	}

	static String extractStr(String []strArr,int place)
	{
		String arr[] = strArr[place].split("=");
		return arr[1];
	}
}