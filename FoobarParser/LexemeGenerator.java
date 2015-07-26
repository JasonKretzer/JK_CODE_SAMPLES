import java.util.*;
import java.io.*;


/******************************************************
LexemeGenerator extends the LinkedList and has FIFO 
queue-like behavior.  It accepts a source file in its 
constructor and gathers the tokens from it.  Tokens 
are added to the list at the tail and can only be 
accessed (outside the class) from the head.
******************************************************/




class LexemeGenerator extends LinkedList
{
	private File input = null;
	
	
	
	public LexemeGenerator(File f) throws IOException
	{
		super();
		input = f;
		if(!input.exists())
		{
			throw new IOException(input.getPath()+" does not exist");
		}
		generateLexemes();
		
	}
	
	public boolean hasMoreLexemes()
	{
		return (!this.isEmpty());
	}
	
	private void generateLexemes() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(input));
		String line = br.readLine().trim();
		Token token = null;
		StringBuffer value = new StringBuffer("");
		boolean isKeyword = false;
		int type = -1;
		int lineNumber = 1;
		
		while(line != null)
		{
			for(int z=0; z<line.length(); z++)
			{
				if(line.charAt(z) == ' ' 
				||(line.charAt(z) == '\t'))
				{
					continue;
				}
				else if(line.charAt(z) == '#')
				{
					break;
				}
				else if(Character.isDigit(line.charAt(z)))
				{
					//start of an integer
					int ind = z;
					int startCol = z;
					while(ind<line.length() && Character.isDigit(line.charAt(ind)))
					{
						value.append(line.charAt(ind));
						ind++;
					}
					z = ind - 1;
					type = LexemeType.INTEGER_LITERAL;
					token = new Token(value.toString(), "INTEGER_LITERAL", lineNumber, startCol);
					this.offer(token);
					value = new StringBuffer("");
				}
				else if(line.charAt(z) == '<' && ((z+1)<line.length()) && line.charAt(z+1) == '-')
				{
					int startCol = z;
					z++;
					value.append("<-");
					type = LexemeType.ASSIGNMENT_OP;
					token = new Token(value.toString(), "ASSIGNMENT_OP", lineNumber, startCol);
					this.offer(token);
					value = new StringBuffer("");
				}
				else if(line.charAt(z) == '-' && ((z+1)<line.length()) && Character.isDigit(line.charAt(z+1)))
				{
					//System.out.println("\n\n\n================\nGOT HERE\n============\n\n\n");
					//start of a negative number
					int ind = z+1;
					int startCol = z;
					
					value.append('-');
					while(Character.isDigit(line.charAt(ind)) && ind<line.length())
					{
						value.append(line.charAt(ind));
						ind++;
					}
					z = ind - 1;
					type = LexemeType.INTEGER_LITERAL;
					token = new Token(value.toString(), "INTEGER_LITERAL", lineNumber, startCol);
					this.offer(token);
					value = new StringBuffer("");
				}
				else if(Character.isLetter(line.charAt(z)))
				{
					//start of a identifier or keyword
					int ind = z;
					int startCol = z;
					while((ind<line.length()) && ((Character.isLetter(line.charAt(ind))) 
					|| (line.charAt(ind) == '_')
					|| (Character.isDigit(line.charAt(ind)))))
					{
						value.append(line.charAt(ind));
						ind++;
					}
					z = ind - 1;
					
					if(value.toString().equals("do"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.DO;
						isKeyword = true;
					}
					else if(value.toString().equals("else")) 
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.ELSE;
						isKeyword = true;
					}
					else if(value.toString().equals("if"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.IF;
						isKeyword = true;
					}
					else if(value.toString().equals("program"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.PROGRAM;
						isKeyword = true;
					}
					else if(value.toString().equals("then"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.THEN;
						isKeyword = true;
					}
					else if(value.toString().equals("var"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.VAR;
						isKeyword = true;
					}
					else if(value.toString().equals("true"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.TRUE;
						isKeyword = true;
					}
					else if(value.toString().equals("false"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.FALSE;
						isKeyword = true;
					}
					else if(value.toString().equals("while"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.WHILE;
						isKeyword = true;
					}
					else if(value.toString().equals("print"))
					{
						//this is a keyword
						value = new StringBuffer(value.toString().toUpperCase());
						type = LexemeType.PRINT;
						isKeyword = true;
					}
					else
					{
						type = LexemeType.IDENTIFIER;
					}
					
					if(isKeyword)
					{
						token = new Token(value.toString(), value.toString(), lineNumber, startCol);
						isKeyword = false;
					}
					else
					{
						token = new Token(value.toString(), "IDENTIFIER", lineNumber, startCol);
					}
					this.offer(token);
					value = new StringBuffer("");
					
				}
				else if(line.charAt(z) == '\"')
				{
					//start of string
					int ind = z+1;
					int startCol = z;
					
					boolean secondQuoteFound = false;
					while(ind<line.length())
					{
						if(line.charAt(ind) == '\\' && line.charAt(ind-1) != '\\')
						{
							ind++;
							continue;
						}
						else if(line.charAt(ind) =='\"' && line.charAt(ind-1) != '\\')
						{
							//end of the string literal
							secondQuoteFound = true;
							break;
						}
						value.append(line.charAt(ind));
						ind++;
					}
					if(secondQuoteFound)
					{
						//good string literal	
						z = ind;
						token = new Token(value.toString(), "STRING_LITERAL", lineNumber, startCol);
						value = new StringBuffer("");
						this.offer(token);
					}
					else
					{
						//this is an error
						throw new IOException("Line "+lineNumber+": No closing quote");
					}
					
				}
				else
				{
					//start of a symbol
					int startCol = z;
					if(line.charAt(z) == '(')
					{
						type = LexemeType.LEFT_PAREN;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == ')')
					{
						type = LexemeType.RIGHT_PAREN;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '{')
					{
						type = LexemeType.LEFT_BRACE;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '}')
					{
						type = LexemeType.RIGHT_BRACE;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '+')
					{
						type = LexemeType.ADD_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '-')
					{
						type = LexemeType.SUB_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '*')
					{
						type = LexemeType.MUL_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '/')
					{
						type = LexemeType.DIV_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '<' && ((z+1)<line.length()) && line.charAt(z+1) == '-')
					{
						type = LexemeType.ASSIGNMENT_OP;
						value.append(line.charAt(z));
						value.append(line.charAt(z+1));
						z++;
					}
					else if(line.charAt(z) == '<')
					{
						type = LexemeType.LESS_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '>')
					{
						type = LexemeType.GREATER_OP;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == '=' && ((z+1)<line.length()) && line.charAt(z+1) == '=')
					{
						type = LexemeType.EQUAL_OP;
						value.append(line.charAt(z));
						value.append(line.charAt(z+1));
						z++;
					}
					else if(line.charAt(z) == '!' && ((z+1)<line.length()) && line.charAt(z+1) == '=')
					{
						type = LexemeType.NOTEQUAL_OP;
						value.append(line.charAt(z));
						value.append(line.charAt(z+1));
						z++;
					}
					else if(line.charAt(z) == '&')
					{
						type = LexemeType.STRING_CONCAT;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == ';')
					{
						type = LexemeType.SEMICOLON;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == ':')
					{
						type = LexemeType.COLON;
						value.append(line.charAt(z));
					}
					else if(line.charAt(z) == ',')
					{
						type = LexemeType.COMMA;
						value.append(line.charAt(z));
					}
					else
					{
						throw new IOException("Line "+lineNumber+": Unknown symbol \'"+line.charAt(z)+"\'");							
					}
					
					
					switch(type)
					{
						case LexemeType.LEFT_PAREN:
						{
							token = new Token(value.toString(), "LEFT_PAREN", lineNumber, startCol);
							break;
						}
						case LexemeType.RIGHT_PAREN:
						{
							token = new Token(value.toString(), "RIGHT_PAREN", lineNumber, startCol);
							break;
						}
						case LexemeType.LEFT_BRACE:
						{
							token = new Token(value.toString(), "LEFT_BRACE", lineNumber, startCol);
							break;
						}
						case LexemeType.RIGHT_BRACE:
						{
							token = new Token(value.toString(), "RIGHT_BRACE", lineNumber, startCol);
							break;
						}
						case LexemeType.ADD_OP:
						{
							token = new Token(value.toString(), "ADD_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.SUB_OP:
						{
							token = new Token(value.toString(), "SUB_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.MUL_OP:
						{
							token = new Token(value.toString(), "MUL_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.DIV_OP:
						{
							token = new Token(value.toString(), "DIV_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.LESS_OP:
						{
							token = new Token(value.toString(), "LESS_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.GREATER_OP:
						{
							token = new Token(value.toString(), "GREATER_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.EQUAL_OP:
						{
							token = new Token(value.toString(), "EQUAL_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.NOTEQUAL_OP:
						{
							token = new Token(value.toString(), "NOTEQUAL_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.ASSIGNMENT_OP:
						{
							token = new Token(value.toString(), "ASSIGNMENT_OP", lineNumber, startCol);
							break;
						}
						case LexemeType.STRING_CONCAT:
						{
							token = new Token(value.toString(), "STRING_CONCAT", lineNumber, startCol);
							break;
						}
						case LexemeType.SEMICOLON:
						{
							token = new Token(value.toString(), "SEMICOLON", lineNumber, startCol);
							break;
						}
						case LexemeType.COLON:
						{
							token = new Token(value.toString(), "COLON", lineNumber, startCol);
							break;
						}
						case LexemeType.COMMA:
						{
							token = new Token(value.toString(), "COMMA", lineNumber, startCol);
							break;
						}
						default:
						{
							break;
						}				
					}
					this.offer(token);
					value = new StringBuffer("");
					
				}
				
				
				
			}
			
			line = br.readLine();
			if(line != null)
			{
				//line = line.trim();
			}
			lineNumber++;
		}
		br.close();
	}
	
	public Token getNextToken()
	{
		return (Token)this.poll();
	}
	
	public Token getLookAheadToken()
	{
		return (Token)this.peek();
	}
	
}
