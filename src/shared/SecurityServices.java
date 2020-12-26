package shared;

public final class SecurityServices
{
	//Constant
	private static final int SHIFT = 201;
	
	//Public Methods
	public static String encode(String s)
	{
		return rot(hexaRep(s), SHIFT);
	}
	public static String decode(String s)
	{
		return unHexaRep(unrot(s, SHIFT));
	}
	
	//Private methods
	private static String rot(String s, int shift)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<s.length(); i++)
		{
			char c = s.charAt(i);
			int dec = (int)c;			//Decimal representation of char ascii
			dec += shift;
			if (dec > 255)
			{
				dec = dec % 255;
				dec = 31 + dec;		//Only use printable chars
			}
			c = (char)dec;		//Update char
			
			sb.append(c);
		}
		
		return sb.toString();
	}
	private static String unrot(String s, int shift)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<s.length(); i++)
		{
			char c = s.charAt(i);
			int dec = (int)c;			//Decimal representation of char ascii
			dec -= shift;
			if (dec < 32)
			{
				dec = 32 - dec;
				dec = 256 - dec;		//Only use printable chars
			}
			
			c = (char)dec;				//Update char
			
			sb.append(c);
		}
		
		return sb.toString();
	}
	private static String hexaRep(String s)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<s.length(); i++)
		{
			char c = s.charAt(i);
			int dec = (int)c;
			String hex = Integer.toHexString(dec);
			hex = hex.length() == 1? 0 + hex : hex;		//Add leading zero if needed
			
			sb.append(hex);
		}
		
		return sb.toString();
	}
	private static String unHexaRep(String s)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<s.length(); i+=2)
		{
			char[] couple = {s.charAt(i), s.charAt(i+1)};
			int dec = Integer.parseInt(new String(couple), 16);
			sb.append((char)dec);
		}
		
		return sb.toString();
	}
	
	//Testing
	public static void main(String args[])
	{
		String s = "Hello There Sir123[]";
		
		String encoded = encode(s);
		String decoded = decode(encoded);
		
		System.out.println(encoded);
		System.out.println(decoded);
	}
}
