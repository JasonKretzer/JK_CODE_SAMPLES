import java.io.*;

/******************************************************
LexemeGenerator uses the Token class to hold both the
value and name of all lexemes.
******************************************************/


class Token
{
	private String value = null;
	private String lexName = null;
	private int lineNumber = -1;
	private int columnNumber = -1;
	
	
	public Token(String val, String lex, int ln, int cn) throws IOException
	{
		value = val;
		lexName = lex;
		lineNumber = ln;
		columnNumber = cn;
		
		if(lexName == null)
		{
			throw new IOException("Cannot create this lexeme, the lexeme must not be null.");
		}
		else if(lineNumber<0)
		{
			throw new IOException("Cannot create this lexeme, the line number must be greater than or equal to zero.");
		}
		else if(columnNumber<0)
		{
			throw new IOException("Cannot create this lexeme, the column number must be greater than or equal to zero.");
		}
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String getLexemeName()
	{
		return lexName;
	}

	public int getColumnNumber() 
	{
		return columnNumber;
	}

	public int getLineNumber() 
	{
		return lineNumber;
	}
}
