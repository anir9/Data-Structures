package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	/**
	 * Builds the DOM tree from input HTML file, through scanner passed
	 * in to the constructor and stored in the sc field of this object. 
	 * 
	 * The root of the tree tha t is built is referenced by the root field of this object.
	 */
	public void build() {
		/** COMPLETE THIS METHOD **/
		
		
		System.out.println("Hello world");
		Stack <TagNode> stack = new Stack <TagNode> ();
		//int count = 0;
		
		if(sc.hasNextLine()) {
			String y = sc.nextLine();
			y = y.substring(1,y.length()-1);
			root = new TagNode(y, null, null);
			stack.push(root);

		}
		
		if(root == null) return;		
		
		
		/*if(sc.hasNextLine()) {
			String y = sc.nextLine();
			y = y.substring(1,y.length()-1);
			root.firstChild = new TagNode(y, null, null);
		}
		
		temp = root.firstChild;
		
		stack.push(temp);
	*/
		
		while(sc.hasNext()) {
			String x = sc.nextLine();
			
			/*if(root.firstChild == null) {
				
				if(x.contains("/") && x.contains("<")) { //skips /
					stack.pop();				
				} 
				
				else if(x.contains("<")) { //normal HTML tag
					x = x.substring(1,x.length()-1);  //removes <>
					
					root.firstChild = new TagNode(x, null, null); 
					temp = root.firstChild;
					stack.push(temp);
				} 
				else {	//texts
				root.firstChild = stack.peek().firstChild;
				temp = root.firstChild;
				
				}
			
			}
			*/
			////////////////////////////////////////////////////////////////////////////////////////////
			
			
			if(x.contains("/") && x.contains("<")) { //skips /
					stack.pop();	
					System.out.println("pop");
			} 
				
			else if(x.contains("<")) { //normal HTML tag
					x = x.substring(1,x.length()-1);  //removes <>
					
					TagNode n = new TagNode(x, null, null); 
					//temp = temp.firstChild;
					
					if(stack.peek().firstChild != null) {
						TagNode maybe = stack.peek().firstChild;
						while (maybe.sibling != null){
							maybe = maybe.sibling;
							}
					maybe.sibling = n;
					}
					else {
						stack.peek().firstChild = n;
					}
					
					stack.push(n);
				}
			
			else {	//texts
				TagNode n = new TagNode(x, null, null); 
				if(stack.peek().firstChild != null) {
					TagNode maybe = stack.peek().firstChild;
					while (maybe.sibling != null){
						maybe = maybe.sibling;
						}
				maybe.sibling = n;
				}
				else {
					stack.peek().firstChild = n;
				}
				
				
			}
				
			
		} //end of while loop
		
		 
		
		
		
		/*while(root.firstChild != null) { //while the current node has a child
			temp = root;
			root = root.firstChild; 
			r.push(root);
			count++;
		}
		
		if(temp.sibling != null) { //while the current branch has ended, and the previous node has multiple children
				temp = temp.sibling;
				root = temp; 
				build(); //goes through next branch
				
		}
	
	for(int i = 0; i < count; i++) {
		System.out.println(r.pop());
	}
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
		
		
		
		
		
private static void replaceTraverse(TagNode root, String oldTag, String newTag, Stack<TagNode> stack) { 
		
		
		for (TagNode ptr = root; ptr != null; ptr = ptr.sibling) { //traverse

			//input code
			if(ptr.tag.equals(oldTag)) {
				TagNode prev = stack.peek();
				
				 if(prev.tag.equals(newTag)) { //if last tag == new tag, skip
					System.out.println(prev.tag + "HI");
					prev.firstChild = ptr.firstChild;
				} else if(ptr.firstChild.tag.equals(newTag)) { //if next tag == new tag, skip
					System.out.println(ptr.firstChild.tag + "HEY");
					prev.firstChild = ptr.firstChild;
				} else { // otherwise, insert tag
					System.out.println(ptr.tag + "HELLO");
					ptr.tag = newTag;
				}
			}
			

			if (ptr.firstChild != null) { //traverse
				stack.push(ptr);
				replaceTraverse(ptr.firstChild, oldTag, newTag, stack);
			} else {
				stack.push(ptr);
			}
		}
}
	
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
		Stack<TagNode> stack = new Stack<TagNode>();
		replaceTraverse(root, oldTag, newTag, stack);
		
		
	}
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	
private static void boldTraverse(TagNode root, int row, int count) { 
	
	
		for (TagNode ptr = root; ptr != null; ptr = ptr.sibling) { //traverse

			//input code
			if(ptr.tag.equals("tr")) {
				count++;
			}
			if(count == row) {
				if(ptr.tag.equals("td")) {
					if(!ptr.firstChild.tag.equals("b")) { //if next tag != new tag, insert tag
					TagNode bold = new TagNode("b", ptr.firstChild, null);
					ptr.firstChild = bold;
					}
				}
			}
			if (ptr.firstChild != null) { //traverse
				boldTraverse(ptr.firstChild, row, count);
			}
			
		}
}
	
	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		int count = 0;
		boldTraverse(root, row, count);
		
	}
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	
	
	private static void removeTraverse(TagNode root, String tag, Stack<TagNode> stack) { 
		TagNode temp = null;
		
		for (TagNode ptr=root; ptr != null; ptr=ptr.sibling) {

			//input code
			if(ptr.tag.equals(tag)) {
				
				if(tag.equals("p") || tag.equals("em") || tag.equals("b")) {
					TagNode prev = stack.peek();
					//node deletion
					if(ptr.equals(prev.sibling)) { //prev is sibling
							for (TagNode x = ptr.firstChild; x != null; x = x.sibling) {
								temp = x; //need to make connection from last sibling of ptr.firstChild	to first sibling of ptr					
							}
							if(temp != null) { //if ptr.firstchild has sibling(s)
								temp.sibling = ptr.sibling; //connects last to first
								prev.sibling = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
							} else { //only child
								ptr.firstChild.sibling = ptr.sibling; //connects last to first
								prev.sibling = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
							}
					} 
					else { //prev is parent node
						for (TagNode x = ptr.firstChild; x != null; x = x.sibling) {
							temp = x; //need to make connection from last sibling of ptr.firstChild	to first sibling of ptr					
						}
						if(temp != null) { //if ptr.firstchild has sibling(s)
							temp.sibling = ptr.sibling; //connects last to first
							prev.firstChild = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						} else { //only child
							ptr.firstChild.sibling = ptr.sibling; //connects last to first
							prev.firstChild = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						}
					}
					
				}
				
				if(tag.equals("ol") || tag.equals("ul")) {
					TagNode current = ptr;
					ptr = ptr.firstChild;
					while(ptr != null) { //changes all li to p
						ptr.tag = "p"; 
						ptr = ptr.sibling;
					}
					ptr = current;
					
					TagNode prev = stack.peek();
					//node deletion
					if(ptr.equals(prev.sibling)) { //prev is sibling
						for (TagNode x = ptr.firstChild; x != null; x = x.sibling) {
							temp = x; //need to make connection from last sibling of ptr.firstChild	to first sibling of ptr					
						}
						if(temp != null) { //if ptr.firstchild has sibling(s)
							temp.sibling = ptr.sibling; //connects last to first
							prev.sibling = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						} else { //only child
							ptr.firstChild.sibling = ptr.sibling; //connects last to first
							prev.sibling = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						}
					} 
					else { //prev is parent node
						for (TagNode x = ptr.firstChild; x != null; x = x.sibling) {
							temp = x; //need to make connection from last sibling of ptr.firstChild	to first sibling of ptr					
						}
						if(temp != null) { //if ptr.firstchild has sibling(s)
							temp.sibling = ptr.sibling; //connects last to first
							prev.firstChild = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						} else { //only child
							ptr.firstChild.sibling = ptr.sibling; //connects last to first
							prev.firstChild = ptr.firstChild; //connect prev to ptr.firstChild ---  deletes ptr
						}
					}
				}
				
			}
			
			if (ptr.firstChild != null) {
				stack.push(ptr);
				removeTraverse(ptr.firstChild, tag, stack);
			} else {
				stack.push(ptr);
			}
		}
		
		
		
		
		
		
	}
	
	
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		//TagNode prev = null;
		Stack<TagNode> stack = new Stack<TagNode>();
		removeTraverse(root, tag, stack);

		
		
	}
	
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	

	
private static void addTraverse(TagNode root, String word, String tag, Stack<TagNode> stack, int endOfLast, Stack<String> main) { 
		
		
		for (TagNode ptr=root; ptr != null; ptr=ptr.sibling) {
			 
			//input code
			StringTokenizer tokens = new StringTokenizer(ptr.tag, " ?!.:;", true);
			 while(tokens.hasMoreTokens()) {
				 String tkn = tokens.nextToken(); //splits text and saves each word
				 main.push(tkn); //saves word
				 
				if(word.matches("(?i:" + tkn +  ")")) {
					
					while(tokens.hasMoreTokens()) {
						 tkn = tokens.nextToken(); //checks character after the word
					 }
						 String now = main.pop(); //pulls out word, so main.peek() == the character before the word
					
					int originalEnd = endOfLast;
					String beforeSub;
					
					System.out.println(originalEnd + " original");
					
					String temp = now.toLowerCase();
					System.out.println(temp + " temp");
					int newBegin = ptr.tag.toLowerCase().indexOf(temp, endOfLast); //can't find the word??
					
					System.out.println(newBegin + " newBegin");
					
					String newSub = null; 
					if(newBegin >= 0) {
						beforeSub = ptr.tag.substring(0, newBegin); // saves any text before the word
					} else {
						continue;
					}	
					String afterSub = null; 
					
					TagNode afterWord = null;
					TagNode beforeWord = null;
					TagNode emphasis = null;
					
					System.out.println(now + " now");
					if( (word.equalsIgnoreCase(now)  && tkn.equals(" ")) //spaces
						|| ((word.equalsIgnoreCase(now)) && !tkn.equals("^[a-zA-Z0-9]+$"))
						) { // NO PUNCTUATION 
						endOfLast = newBegin + word.length(); // saves index of the character immediately after the word w/ NO PUNCTUATION 
						
						if(tkn.matches("[.!?:;]")) {
							endOfLast++;
						}
						
						System.out.println(endOfLast + " end");														
						
						newSub = ptr.tag.substring(newBegin, endOfLast); // saves word w/ NO PUNCTUATION 
						
						System.out.println(newSub + " newSub");
						
						TagNode newWord = new TagNode(newSub, null, null); // creates a node for the word
						TagNode prev = stack.pop();
						TagNode prev2 = stack.peek();
						stack.push(prev);
						
						boolean duplicate = false;
						
						if(prev.tag.equals(tag) || prev2.tag.equals(tag)) {
							
							if( (prev.equals(prev2.firstChild) && ptr.equals(prev.firstChild) )
								||  (prev.equals(prev2.sibling) && ptr.equals(prev.firstChild) && prev.tag.equals(tag)) ) {
								duplicate = true;
							} 
							else if( (prev.equals(prev2.sibling) && ptr.equals(prev.firstChild) && prev2.tag.equals(tag))
									|| (prev.equals(prev2.firstChild) && ptr.equals(prev2.sibling))) {
								duplicate = false;
							}
							
						}
						
							
						if (duplicate == true) {
							continue;
						}

						
						if(originalEnd > 0 && endOfLast < ptr.tag.length()) { // found one instance of the word in the node AND there is still text left to scan	
							addTraverse(ptr, word, tag, stack, endOfLast, main); // searches same node for other instances of word
						} 
						else {
							afterSub = ptr.tag.substring(endOfLast, ptr.tag.length()); // saves any text after the word w/ NO PUNCTUATION (fully scanned)
								if(afterSub.length() > 0) { // if there is text after the word
									afterWord = new TagNode(afterSub, null, ptr.sibling); // creates a node for text after the word
									emphasis = new TagNode(tag, newWord, afterWord); // creates a node for the new emphasis on the word
									
									System.out.println(afterSub + " after");
									System.out.println(emphasis + " emphasis1");

								} 
								else {
									emphasis = new TagNode(tag, newWord, ptr.sibling);
									
									System.out.println(emphasis + " emphasis2");

								}
						}
						
					
							//emphasis = new TagNode(tag, newWord, ptr.sibling);
							if(beforeSub.length() > 0) { // if there is text before the word
								beforeWord = new TagNode(beforeSub, null, emphasis);
								
								System.out.println(beforeSub + " beforeSub");
								
								if(ptr.equals(prev.firstChild)) { //emphasis is prev.firstChild
									prev.firstChild = beforeWord;
									
									System.out.println("one");

								} else { // text --- emphasis is prev.sibling
									while(!ptr.equals(prev.sibling)) {
										prev = stack.pop();
									}
									if(endOfLast >= 1) {
										prev.sibling = beforeWord;
										
										System.out.println("two");

									} else {
										prev.sibling = emphasis;
										
										System.out.println("three");

									}
								}
							} 
							else {	
									if(ptr.equals(prev.firstChild)) { //emphasis is prev.firstChild
										prev.firstChild = emphasis;
									} else { // text --- emphasis is prev.sibling
										while(!ptr.equals(prev.sibling)) {
											prev = stack.pop();
										}
										prev.sibling = emphasis;
									}	
									endOfLast = 0;
							} 
						
						
						
						/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						
					} 
					
				/*	
					if(!prev.tag.equals(tag)) {
						if(afterSub.length() > 0) { // if there is text after the word
							afterWord = new TagNode(afterSub, null, ptr.sibling); // creates a node for text after the word
							emphasis = new TagNode(tag, newWord, afterWord); // creates a node for the new emphasis on the word
						} else {
							emphasis = new TagNode(tag, newWord, ptr.sibling);
						}
						
						if(beforeSub.length() > 0) { // if there is text before the word
							beforeWord = new TagNode(beforeSub, null, emphasis);
						} else {
							
								if(prev.tag.equals("p") || prev.tag.equals("td") || prev.tag.equals("li") || prev.tag.equals("body")) { //emphasis is prev.firstChild
									prev.firstChild = emphasis;
								} else { //emphasis is prev.sibling
									prev.sibling = emphasis;
								}
							}
							*/

							
					//main.push(now);

				}
			tkn = main.peek();
			 } //while loop
			
			if (ptr.firstChild != null) {
				stack.push(ptr);
				addTraverse(ptr.firstChild, word, tag, stack, endOfLast, main);
			} else {
				stack.push(ptr);
			}
		}
}
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
		//TagNode prev = null;
		int endOfLast = 0;
		Stack<TagNode> stack = new Stack<TagNode>();
		Stack<String> main = new Stack<String>();
		addTraverse(root, word, tag, stack, endOfLast, main);
		
	}
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	/**
	 * Prints the DOM tree. 
	 *
	 */
	public void print() {
		print(root, 1);
	}
	
	private void print(TagNode root, int level) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			for (int i=0; i < level-1; i++) {
				System.out.print("      ");
			};
			if (root != this.root) {
				System.out.print("|----");
			} else {
				System.out.print("     ");
			}
			System.out.println(ptr.tag);
			if (ptr.firstChild != null) {
				print(ptr.firstChild, level+1);
			}
		}
	}
}