
/**
 * Efficient Version of StrngStrand
 * @author shengzhecheng
 *
 */
public class LinkStrand implements IDnaStrand {

	public LinkStrand() {
		this("");
	}
	/**
	 * 
	 * @param s String used to initialize a LinkStrand Object 
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
;

	/**
	 * @return size of the internal linked list
	 */
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}
	/**
	 * String representation of the object
	 */
	@Override
	public String toString() {
		myLast = myFirst;
		StringBuilder x = new StringBuilder(myFirst.info);
		while (myLast.next != null) {
			myLast = myLast.next;
			x.append(myLast.info);
		}
		return x.toString();
		
	}
    /**
     * Initialize all instance variables
     */
	@Override
	public void initialize(String source) {
		// TODO Auto-generated method stub
		myFirst =new Node(source);
		myLast = myFirst;
		myAppends = 0;
		myCurrent = myFirst;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myIndex = 0;

	}
	
	
    /**
     * Get instance of the object
     */
	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}
	/**
	 * Append new nodes to the link list
	 */
	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next = new Node (dna);
		myLast = myLast.next;
		mySize = mySize+dna.length();
		myAppends = myAppends+1;
		return this;
	}

	
	/**
	 * Reverse the linkStrand
	 * @return A reversed version of the linkStrand
	 */
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		
		Node list = myFirst;
		Node mid = new Node(myFirst.info);
		
		while(list.next != null) {
			list = list.next;
			Node rev =new Node(list.info);
			rev.next = mid;
			mid = rev;
		}
		StringBuilder copy = new StringBuilder(mid.info);
		copy.reverse();
        mid.info = copy.toString();
		LinkStrand revs = new LinkStrand(mid.info);
		while(mid.next!=null) {
			mid = mid.next;
			StringBuilder copy1 = new StringBuilder(mid.info);
			copy1.reverse();
            revs.append(copy1.toString());
			
		}
		
		return revs;
	}
	/**
	 * Get myAppends
	 */
	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}
	/**
	 * Return the character at certain index
	 */
	@Override
	public char charAt(int index) {

		// TODO Auto-generated method stub
		if(mySize-1<index||index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index < myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		
		while(myIndex != index) {
			myIndex+=1;
			myLocalIndex+=1;
			if(myLocalIndex > myCurrent.info.length()-1) {
				myCurrent = myCurrent.next;
				myLocalIndex = 0;
			}
		}		
//		while(myCurrent.info.length() -1 < index - myIndex + myLocalIndex) {
//			myIndex += (myCurrent.info.length()-1);
//			myCurrent = myCurrent.next;
//			myLocalIndex = 0;
//		}
//		myLocalIndex += (index-myIndex-1);
//		myIndex = index;
		return myCurrent.info.charAt(myLocalIndex);
	}

}
