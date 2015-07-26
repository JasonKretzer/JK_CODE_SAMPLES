import java.io.*;

/******************************************************
The main class used to test LexemeGenerator.
******************************************************/


class main
{
	public static void main(String[] args) throws IOException
	{
		File f = null;
		if(args.length <1)
		{
			System.err.println("Usage:  main <source_code_file>");
			System.exit(1);
		}
		else
		{
			f = new File(args[0]);
			if(!f.exists())
			{
				throw new IOException("Source file does not exist.");
			}
		}
		LexemeGenerator lg = new LexemeGenerator(f);
		
		Parser p = new Parser(lg);
		
		p.output(System.out);
		


	}
}
