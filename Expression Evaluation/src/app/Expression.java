package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	public static String delims = " \t*+-/()[]";
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 *
    	 *
    	 *enqueue arrays to stack to get array size
    	 * 
    	 **/
        
    	/* splits original expression by tokens(delims)
    	 * throws out tokens
    	 * puts variables/numbers into new expression 
    	*/
    	String r = "";
    	for(int i = 0; i < expr.length(); i++) { //iterate through chars in string
    		
    		if (Character.isDigit(expr.charAt(i)) || Character.isWhitespace(expr.charAt(i))) {
    			continue;
    		}
    		if((delims.contains(Character.toString(expr.charAt(i))) != true)){ //if expr != have delims
    			r += expr.charAt(i);
    		}else if (delims.contains(Character.toString(expr.charAt(i)))){ //loop through with for each or something
    			if(r != "") {
    				Variable var = new Variable(r); // create instance Variable to add to vars
    				if(vars.contains(var) != true) {
		    			vars.add(var); //attempt to add the char at i to the vars arraylist
    				}
		    	r = "";
    			}
    		}else if (expr.charAt(i) == '[') {
    			if(r != "") {
	    			Array arr = new Array(r);
	    			if(arrays.contains(arr) != true) {
		    			arrays.add(arr); //attempt to add the char at i to the vars arraylist
    				}// create instance Variable to add to vars
	    			 //attempt to add the char at i to the vars arraylist
	    			r = "";
    			}
    		}
    	}
    	Variable nvar = new Variable(r); // create instance Variable to add to vars
    	if((vars.contains(nvar) != true) && (delims.contains(r)!=true)) {
    	vars.add(nvar); //attempt to add the char at i to the vars arraylist
    	}

        /*
    	Variable var = new Variable(expr.charAt(index));
    	Array arr = null;
    	String exprNew = "";
        String[] str = expr.split(delims,0); 
   
         for (String a : str) {
        	 System.out.println(a); 
        	 exprNew += a;
        	 System.out.println(exprNew); 

         }
          */
    	
    /*
     * 
     * 
     * 
     * 
     * 
     * 	
    	for (int i = 0; i < Array.length-1; i++) { 
    		
    	expr += Array[i].name;
    	}
    */
    	
    	
    	
    	
    	
    	
    	
    }
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD 
    	 * **/
    	// following line just a placeholder for compilation
    	//Stack<Integer> numbers = new Stack<Integer>();
    	//Stack<Character> operators = new Stack<Character>();    	

    	   		float result = 0;
    	        Stack<String> it = new Stack<String>(); 
    	        Stack<String> notit = new Stack<String>(); 
    	        Stack<String> oh = new Stack<String>();
    	        String queen = "";
    	        String king = "";
    	        String jack = "";
    	        String ace = "";
    	        String heart = "";
            	int m = 0;

    	        float answer = 0;

    	        String[] arr = new String[arrays.size()]; 
    	        arr = arrays.toArray(arr); 
    	        for (String x : arr) {
    	            System.out.print(x + " "); 
    	    } 
    	        
    	        expr = expr.replaceAll("\\s+", "");
    	        StringTokenizer tokens = new StringTokenizer(expr, "()*/+-", true); 
    	        while(tokens.hasMoreTokens()){
    	            String wow = tokens.nextToken();
    	            
    	            for(int y = 0; y < vars.size(); y++) {
    	            	if(vars.get(y).name.equals(wow)) {
    	            		it.push(Integer.toString(vars.get(y).value));
    	            	}
    	            }
    	            
    	           /* for(int x = 0; x < expr.length(); x++){ //array indices
    					if(arrays.get(x).name.equals(wow)){
    						al.push(arrays.get(x));
    						System.out.println(arrays.get(x).name);
    					}
    				}
    				*/
    	            
    	            if(wow.equals("(")) { 
    	            	while(!wow.equals(")")) {
    	            		wow = tokens.nextToken();
    	            		queen = queen.concat(wow);
    	            	}
    	            	float w = evaluate(queen, vars, arrays); //create sub-results
        	            it.push(Float.toString(w)); //push result of parentheses expression onto stack
    	            } 
    	         
    	            else if(wow.equals("[")) { 
		                oh.push("[");
		                ace = ace.concat(wow);
		                if(!wow.equals("]")) {
		                	wow = tokens.nextToken();
		                }
		                while(!wow.equals("]")) {
			                ace = ace.concat(wow);
		                	wow = tokens.nextToken();
		                	if(wow.equals("[")) {
		                		oh.push("[");
		                	}
		                	if(wow.equals("[")) { 
		                		for (int s = 0; s < ace.length(); s++) {
		                			
		                			String joker = Character.toString(ace.charAt(s));
		                			int r = 0;
		                			int l = 0; 
		             
		                			if (joker.equals("[")) {
		                				l+=l;
		                			}
		                			else if (joker.equals("]")) {
		                				r+=r;
		                				if (r == l) 
		                					break;
		                				else {
		            		                ace = ace.concat(wow);
		                					wow = tokens.nextToken();
		                				}
		               
		                			}
		               
		                		}
		                	}
		              }
		             		                
		          StringTokenizer bleh = new StringTokenizer(ace, "()*/+-", true); 
		          String mer = bleh.nextToken();
		          for(int y = 0; y < arrays.size(); y++){ 
         	          if(arrays.get(y).name.equals(mer)){
         	        	  m = y;
         	        	  break;
         	           }
		          }
		          mer = bleh.nextToken();
           
		          while(bleh.hasMoreTokens()) {
		        	  mer = bleh.nextToken();
		        	  heart = heart.concat(mer);
		          }
		          heart = heart.substring(0, heart.length() - 1);


		          int mat = Math.round(evaluate(heart,vars,arrays));
		          it.push(Float.toString(arrays.get(m).values[mat])); 
            
    	         }
    	            
    	            /*else if(wow.equals("[")) { //expression inside brackets
    	               	do {
    	               	wow = tokens.nextToken();
    	                   	king = king.concat(wow);
    	               	//it.push(wow); //math
    	               	} while(!tokens.nextToken().equals("]"));
    	               	float w = evaluate(king, vars, arrays); //create sub-results
    	               	int index = (int) w; //index of Array
    	               	
    	               	
    	             	 for(int i = expr.length()-1; i >= 0; i--) { 
    	             	while(!delims.contains(expr)) {
    	             	oh.push(Character.toString(expr.charAt(i)));
    	             	   jack = jack.concat(oh.pop()); //contains Array name
    	             	   }
    	       	             }
    	             	 
    	             	for(int x = 0; x < arrays.size(); x++){ //array indices
    	             		if(arrays.get(x).name.equals(jack)){
    	             			System.out.println(arrays.get(x).name);
    	             			it.push(Float.toString(arrays.get(x).values[index]));
    	             		}
    	             	}
    	               
    	           /* else if (wow.equals("]")) { 
    	            	while(!delims.contains()) {
    	            		  whoa = tokens.nextToken();
    	      	              jack = jack.concat(whoa); //array name
    	      	            }
    	            	
    	                while (it.pop() != "[") { 

    	                	//king = king.concat(it.pop());
    	                }
    	                	//float w = evaluate(king, vars, arrays); //create sub-results
    	                	//int m = (int) w; //index
    	                	for(int x = 0; x < al.size(); x++){ //array indices
    	    					if(arrays.get(x).name.equals(wow)){
    	    						al.push(arrays.get(x-1));
    	    						System.out.println(arrays.get(x-1).name);
    	    					}
    	    				}
	            			it.push(Float.toString(arrays.get(wow-1).values[m])); //push result of parentheses expression onto stack
    	            }    	            
    	            
    	            
    	            */
    	            
    	            
    	            else if(wow.matches("^[-+]?\\d+(\\.\\d+)?$") || wow.equals("+") || wow.equals("-")) {
    	            	it.push(wow);	
    	            } 
    	            else if(wow.equals("*")){
    	            	wow = tokens.nextToken();
    	            	if(wow.matches("[a-zA-Z]+\\.?")) {
    	            		for(int y = 0; y < vars.size(); y++) {
    	    	            	if(vars.get(y).name.equals(wow)) {
    	    	            	  notit.push((Integer.toString(vars.get(y).value)));
    	    	            	}
    	            		}
    	            	} else {
    	            		 if(wow.equals("(")) { 
    	     	            	while(!wow.equals(")")) {
    	     	            		wow = tokens.nextToken();
    	     	            		queen = queen.concat(wow);
    	     	            	}
    	     	            	float w = evaluate(queen, vars, arrays); //create sub-results
    	         	            notit.push(Float.toString(w)); //push result of parentheses expression onto stack
    	     	            } 
    	            		 else {notit.push(wow); 
    	            		 }
    	            	}
    	                result = Float.parseFloat(it.pop()) * Float.parseFloat(notit.pop()); 
    	                it.push(Float.toString(result)+"");
    	            }
    	            else if(wow.equals("/")){
    	            	wow = tokens.nextToken();
    	            	if(wow.matches("[a-zA-Z]+\\.?")) {
    	            		for(int y = 0; y < vars.size(); y++) {
    	    	            	if(vars.get(y).name.equals(wow)) {
    	    	            	  notit.push((Integer.toString(vars.get(y).value))); 
    	    	            	}
    	            		}
    	            	} else {
    	            		if(wow.equals("(")) { 
    	     	            	while(!wow.equals(")")) {
    	     	            		wow = tokens.nextToken();
    	     	            		queen = queen.concat(wow);
    	     	            	}
    	     	            	float w = evaluate(queen, vars, arrays); //create sub-results
    	         	            notit.push(Float.toString(w)); //push result of parentheses expression onto stack
    	     	            } 
    	            		 else {notit.push(wow); 
    	            		 }
    	            	}
    	                result = Float.parseFloat(it.pop()) / Float.parseFloat(notit.pop()); 
    	                it.push(Float.toString(result)+"");
    	            }

    	            
    	            
    	            
    	            
    	        }
    	        
    	        
    	        
    	        
    	        
    	        
    	        while(!it.isEmpty() && it.size() > 2) {
        	        notit.push(it.pop()); //the second number so the operand is at the top of the stack
        	        String operand = it.pop();
        	        if(operand.equals("+")) {
        	                result = Float.parseFloat(it.pop()) + Float.parseFloat(notit.pop());
        	                it.push(Float.toString(result) + "");
        	        }
        	        else if(operand.equals("-")) {
        	        		result = Float.parseFloat(it.pop()) - Float.parseFloat(notit.pop());
        	                it.push(Float.toString(result) + "");
        	        }
        	    }
	            
    	        
    	            	/*if(wow.equals("[")) {
    	            	while(!wow.equals("]")) {
    	            		wow = tokens.nextToken();
        	            	queen = queen.concat(wow);
        	            	}
    	            	float w = evaluate(queen, vars, arrays); //create sub-results
	            		stk.push(Float.toString(w)); //push result of parentheses expression onto stk
    	            }
    	            */
    	          
    	            
    	           
    	            
    	            /* if(wow.equals("["){
    	             * for(int x = 0; x < arrays.length(); x++){ //array indices
	            	if(expr.equals(arrays.get(x).values)){
	            	 arrays.get(x).values[x]
	            	 
	            	 }
	            	}
	            }
	            */
    	            
    	       /* else if(wow.equals("+")) {
	            	wow = tokens.nextToken();
	            	if(wow.matches("[a-zA-Z]+\\.?")) {
	            		for(int y = 0; y < vars.size(); y++) {
	    	            	if(vars.get(y).name.equals(wow)) {
	    	            	  temp.push((Integer.toString(vars.get(y).value))); //meant to store the second number which is after the operand
	    	            	}
	            		}
	            	} else {
	            	temp.push(wow); //meant to store the second number which is after the operand
	            	}
	                result = Float.parseFloat(stk.pop()) + Float.parseFloat(temp.pop()); 
	                stk.push(Float.toString(result)+"");
	            }else if(wow.equals("-")) {
	            	wow = tokens.nextToken();
	            	if(wow.matches("[a-zA-Z]+\\.?")) {
	            		for(int y = 0; y < vars.size(); y++) {
	    	            	if(vars.get(y).name.equals(wow)) {
	    	            	  temp.push((Integer.toString(vars.get(y).value))); //meant to store the second number which is after the operand
	    	            	}
	            		}
	            	} else {
	            		temp.push(wow); //meant to store the second number which is after the operand
	            	}
	                result = Float.parseFloat(stk.pop()) - Float.parseFloat(temp.pop()); 
	                stk.push(Float.toString(result)+"");
	            }
    	            
    	            
    	            /**else if(wow.equals("+") || wow.equals("-")) {
    	            temp.push(tokens.nextToken()); //meant to store the second number which is after the operand
    	            float num1 = Float.parseFloat(stk.pop());
    	            if(wow.equals("+")){
    	                    result = num1 + Float.parseFloat(temp.pop());
    	                } else if(wow.equals("-")){
    	                    result = num1 - Float.parseFloat(temp.pop()); 
    	                }
    	                stk.push(Float.toString(result) + "");
    	            } 
    	            **/
    	            //vars.get(index).value returns some value


    	         
    	           
    	       System.out.println("hello world");
    	       
    	        //end of while loop
    	        if(!it.isEmpty()) {
    	            answer = Float.parseFloat(it.pop());
    	        }
    	        return answer;

    	    
    }
}
