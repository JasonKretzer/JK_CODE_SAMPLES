import java.io.IOException;
import java.io.PrintStream;

public class Parser 
{
	private LexemeGenerator gen = null;
	private StringBuffer output = null;
	private int indents = 0;
	
	
	public Parser(LexemeGenerator lg) throws IOException
	{
		if(lg == null || !lg.hasMoreLexemes())
		{
			throw new IOException("No lexemes to parse.");
		}
		
		gen = lg;
		output = new StringBuffer("");
		parse();		
	}
	
	public void output(PrintStream out)
	{
		out.println(output);
	}
	
	private void parse() throws IOException
	{
		Token token = gen.getNextToken();
		Token laToken = null; 
		if(gen.hasMoreLexemes())
		{
			laToken = gen.getLookAheadToken();
		}
		else
		{
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		
		
		//since this is the first token read, it better be "PROGRAM"
		//handling the first line here since it is unique
		if(!token.getLexemeName().equalsIgnoreCase("PROGRAM"))
		{
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getColumnNumber()+" =>All FOOBAR programs must start with \"program.\"");
		}
		
		if(token.getLexemeName().equalsIgnoreCase("PROGRAM") 
			&& !laToken.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
		{
			throw new IOException("AT OR NEAR Line: "+laToken.getLineNumber()+" Column: "+laToken.getColumnNumber()+" =>All FOOBAR programs must start with \"program\" followed by an identifier.");
		}
		
		if(token.getLexemeName().equalsIgnoreCase("PROGRAM") 
				&& laToken.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
		{
			if(gen.hasMoreLexemes())
			{
				token = gen.getNextToken();
				laToken = gen.getLookAheadToken();
			}
			else
			{
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			}
			if(gen.hasMoreLexemes())
			{
				
				token = gen.getNextToken();
			}
			else
			{
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			}
			
			if(!token.getLexemeName().equalsIgnoreCase("LEFT_PAREN"))
			{
				
				throw new IOException("AT OR NEAR Line: "+laToken.getLineNumber()+" Column: "+laToken.getColumnNumber()+" =>All FOOBAR programs must start with \"program\" followed by an identifier and a set of parentheses.");
			}
		}
		
		if(gen.hasMoreLexemes())
		{
			token = gen.getNextToken();
		}
		else
		{
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		
		
		if(token.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
		{
			//entered a parameter list
			while(gen.hasMoreLexemes() && token.getLexemeName().equalsIgnoreCase("IDENTIFIER") && !token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN"))
			{
				laToken = gen.getLookAheadToken();
				if(!laToken.getLexemeName().equalsIgnoreCase("COLON"))
				{
					//this is bad
				}
				else
				{
					//consume the colon
					token = gen.getNextToken();
					if(gen.hasMoreLexemes())
					{
						laToken = gen.getLookAheadToken();
						if(!laToken.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
						{
							//this is bad
							throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
						}
						else
						{
							token = gen.getNextToken();
							if(gen.hasMoreLexemes())
							{
								laToken = gen.getLookAheadToken();
								if(laToken.getLexemeName().equalsIgnoreCase("IDENTIFIER") || laToken.getLexemeName().equalsIgnoreCase("RIGHT_PAREN"))
								{
									token = gen.getNextToken();
								}
								else
								{
									//this is bad
									throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
								}
							}
						}
					}
				}
			}
			
			
			if(!token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN"))
			{
				//this is bad!!
				
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unclosed parentheses.");
			}
			
		}
		else
		{
			if(!token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN"))
			{
				//this is bad!!
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unclosed parentheses.");
			}
		}
		
		
		
		output.append("program\n");
		parseBlockStatement();
				
	}

	/**
	 * parses block of code within {}
	 */
	private void parseBlockStatement() throws IOException
	{
		Token lat = null;
		Token token = null;
		if(gen.hasMoreLexemes())
		{
			token = gen.getNextToken();
		}
		else
		{
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		
		
		if(!token.getLexemeName().equalsIgnoreCase("LEFT_BRACE"))
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>No opening left brace in block statement.");
		}
		else
		{
			
			for(int i=0; i<indents; i++)
			{
				output.append("\t");
			}
			output.append("{\n");
			indents++;
			
			//this token is a left brace
			if(gen.hasMoreLexemes())
			{
				lat = gen.getLookAheadToken();
				
			}
			else
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			}
			
			
			if(lat.getLexemeName().equalsIgnoreCase("RIGHT_BRACE"))
			{
				//empty statementlist
				indents--;
				for(int i=0; i<indents; i++)
				{
					output.append("\t");
				}
				output.append("}\n");
				return;
			}
			else
			{
				while(gen.hasMoreLexemes())
				{
					
					lat = gen.getLookAheadToken();
					
					if(lat.getLexemeName().equalsIgnoreCase("VAR"))
					{
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("variable declaration\n");
						parseVarStmt();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("PRINT"))
					{
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("print\n");
						parsePrintStmt();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
					{
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("assignment\n");
						parseAsgnStmt();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("WHILE"))
					{
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("while loop\n");
						parseWhileStmt();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("LEFT_BRACE"))
					{
						parseBlockStatement();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("IF"))
					{
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("if statement\n");
						parseIfStatement();
					}
					else if(lat.getLexemeName().equalsIgnoreCase("RIGHT_BRACE"))
					{
						indents--;
						for(int i=0; i<indents; i++)
						{
							output.append("\t");
						}
						output.append("}\n");
						//end of block
						gen.getNextToken();
						return;
					}					
					else
					{
						//this is bad						
						throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected token.");
					}
				}
			}
			throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			
		}
		
	}

	/**
	 * parses an if construct
	 */
	private void parseIfStatement() throws IOException
	{
		Token token = gen.getNextToken();
		Token lat = null;		
		
		
		if(token.getLexemeName().equalsIgnoreCase("IF") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		else if(token.getLexemeName().equalsIgnoreCase("IF") && gen.hasMoreLexemes())
		{
			parseExprStmt();
			
			if(gen.hasMoreLexemes())
			{
				lat = gen.getLookAheadToken();
				if(lat.getLexemeName().equalsIgnoreCase("THEN") && !gen.hasMoreLexemes())
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected end of program.");
				}
				else if(lat.getLexemeName().equalsIgnoreCase("THEN") && gen.hasMoreLexemes())
				{
					token = gen.getNextToken();
					parseBlockStatement();
					
					if(gen.hasMoreLexemes())
					{
						lat = gen.getLookAheadToken();
						if(lat.getLexemeName().equalsIgnoreCase("ELSE"))
						{
							//for the else
							gen.getNextToken();
							parseBlockStatement();
							return;
						}
						else
						{
							return;
						}
					}
					else
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
					}
				}
				else
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: "+lat.getColumnNumber()+"  =>Improperly formed if-then statement.");
				}
			}
			else
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			}
		}
		else
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getColumnNumber()+"  =>Improperly formed if-then statement.");
		}
		
		
		
	}

	/**
	 * parses assignment statements
	 */
	private void parseAsgnStmt() throws IOException
	{
		Token token = null;
		Token lat = null;
		
		token = gen.getNextToken();
			
		
		
		if(token.getLexemeName().equalsIgnoreCase("IDENTIFIER") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		else if(token.getLexemeName().equalsIgnoreCase("IDENTIFIER") && gen.hasMoreLexemes())
		{
			lat = gen.getLookAheadToken();
			if(lat.getLexemeName().equalsIgnoreCase("ASSIGNMENT_OP"))
			{
				token = gen.getNextToken();
				parseExprStmt();
				
				if(gen.hasMoreLexemes())
				{
					lat = gen.getLookAheadToken();
					if(lat.getLexemeName().equalsIgnoreCase("SEMICOLON") && !gen.hasMoreLexemes())
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected end of program.");
					}
					else if(lat.getLexemeName().equalsIgnoreCase("SEMICOLON") && gen.hasMoreLexemes())
					{
						gen.getNextToken();
						return;
					}
					else
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: "+lat.getColumnNumber()+"  =>Missing semicolon.");
					}
				}
				else
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
				}
			}
		}
		else
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getColumnNumber()+"  =>Improperly formed assignment.");
		}
		
		
	}

	/**
	 * parses while constructs
	 */
	private void parseWhileStmt() throws IOException
	{
		Token token = null;
		Token lat = null;
		
		token = gen.getNextToken();
		
		
		if(token.getLexemeName().equalsIgnoreCase("WHILE") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
		}
		else if(token.getLexemeName().equalsIgnoreCase("WHILE") && gen.hasMoreLexemes())
		{
			parseExprStmt();
			if(gen.hasMoreLexemes())
			{
				lat = gen.getLookAheadToken();
			}
			else
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
			}
			
			if(!lat.getLexemeName().equalsIgnoreCase("DO"))
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: "+lat.getColumnNumber()+"  =>Missing Do after while.");
			}
			else
			{
				if(gen.hasMoreLexemes())
				{
					gen.getNextToken();
					parseBlockStatement();
					return;
				}
				else
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: 0  =>Unexpected end of program.");
				}
			}
			
		}
		else
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getColumnNumber()+"  =>Improperly formed while.");
		}
		
		
	}

	
	/**
	 * parse the print statements
	 */
	private void parsePrintStmt() throws IOException
	{
		Token token = null;
		Token lat = null;
		
		token = gen.getNextToken();
		
		if(token.getLexemeName().equalsIgnoreCase("PRINT") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getColumnNumber()+"  =>Unexpected end of program.");
		}
		else if(token.getLexemeName().equalsIgnoreCase("PRINT") && gen.hasMoreLexemes())
		{
			lat = gen.getLookAheadToken();
			if(lat.getLexemeName().equalsIgnoreCase("LEFT_PAREN") && !gen.hasMoreLexemes())
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: "+lat.getLineNumber()+"  =>Unexpected end of program.");
			}
			else if(lat.getLexemeName().equalsIgnoreCase("LEFT_PAREN") && gen.hasMoreLexemes())
			{
				token = gen.getNextToken();
				parseExprStmt();
				
				if(gen.hasMoreLexemes())
				{
					token = gen.getNextToken();
					if(token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN") && !gen.hasMoreLexemes())
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
					}
					else if(token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN") && gen.hasMoreLexemes())
					{
						token = gen.getNextToken();
						if(token.getLexemeName().equalsIgnoreCase("SEMICOLON") && !gen.hasMoreLexemes())
						{
							//this is bad
							throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
						}
						else if(token.getLexemeName().equalsIgnoreCase("SEMICOLON") && gen.hasMoreLexemes())
						{
							//this is end of print
							return;
						}
						else
						{
							//this is bad
							throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
						}
						
					}
				}
				else
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: 0  =>Unexpected end of program.");
				}
			}
			else
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+lat.getLineNumber()+" Column: "+lat.getLineNumber()+"  =>Improperly formed print.");
			}
		}
		
		
		
		
	}

	/**
	 * parses expressions within ()
	 */
	private void parseExprStmt() throws IOException
	{
		Token token = null;
		Token lat = null;
		
		token = gen.getNextToken();
		
		
		if(token.getLexemeName().equalsIgnoreCase("LEFT_PAREN") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
		}
		else if(token.getLexemeName().equalsIgnoreCase("LEFT_PAREN") && gen.hasMoreLexemes())
		{
			parseExprStmt();
			if(gen.hasMoreLexemes())
			{
				token = gen.getNextToken();
				if(!token.getLexemeName().equalsIgnoreCase("RIGHT_PAREN"))
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
				}
				else
				{
					return;
				}
			}
		}
		
		if((token.getLexemeName().equalsIgnoreCase("INTEGER_LITERAL"))
			||(token.getLexemeName().equalsIgnoreCase("STRING_LITERAL"))
			||(token.getLexemeName().equalsIgnoreCase("IDENTIFIER"))
			||(token.getLexemeName().equalsIgnoreCase("TRUE"))
			||(token.getLexemeName().equalsIgnoreCase("FALSE")))
		{
			
			if(gen.hasMoreLexemes())
			{
				lat = gen.getLookAheadToken();
				if(((lat.getLexemeName().equalsIgnoreCase("EQUAL_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("NOTEQUAL_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("LESS_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("GREATER_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("ADD_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("SUB_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("MUL_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("DIV_OP"))
					|| (lat.getLexemeName().equalsIgnoreCase("STRING_CONCAT"))))
				{
					gen.getNextToken();
					parseExprStmt();
				}
				else
				{
					return;
				}
				
			}
			else
			{
				//this is bad
				throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
			}
			
		}
		else
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Improperly formed expression.");
		}
		
		
		
	}

	/**
	 * parse variable declaration statements
	 */
	private void parseVarStmt() throws IOException
	{
		Token token = null;
		Token lat = null;
		
		token = gen.getNextToken();
		
		
		if (token.getLexemeName().equalsIgnoreCase("VAR") && !gen.hasMoreLexemes())
		{
			//this is bad
			throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
		}
		else if (token.getLexemeName().equalsIgnoreCase("VAR") && gen.hasMoreLexemes())
		{
			token = gen.getNextToken();
			if(token.getLexemeName().equalsIgnoreCase("IDENTIFIER") && gen.hasMoreLexemes())
			{
				token = gen.getNextToken();
				if(token.getLexemeName().equalsIgnoreCase("COLON") && gen.hasMoreLexemes())
				{
					token = gen.getNextToken();
					if(token.getLexemeName().equalsIgnoreCase("IDENTIFIER") && gen.hasMoreLexemes())
					{
						token = gen.getNextToken();
						if(token.getLexemeName().equalsIgnoreCase("SEMICOLON") && gen.hasMoreLexemes())
						{
							//end of statement
							return;
						}
						else
						{
							if(gen.hasMoreLexemes())
							{
								//this is bad
								throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Missing semicolon after declaration.");
							}
							else
							{
								//this is bad
								throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
							}
							
						}
					}
					else
					{
						if(gen.hasMoreLexemes())
						{
							//this is bad
							throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Improperly formed declaration.");
						}
						else
						{
							//this is bad
							throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
						}
					}
					
				}
				else
				{
					if(gen.hasMoreLexemes())
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Improperly formed declaration.");
					}
					else
					{
						//this is bad
						throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
					}				
				}
			}
			else
			{
				if(gen.hasMoreLexemes())
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Improperly formed declaration.");
				}
				else
				{
					//this is bad
					throw new IOException("AT OR NEAR Line: "+token.getLineNumber()+" Column: "+token.getLineNumber()+"  =>Unexpected end of program.");
				}			
			}
		}
		
	}
	
	

}
