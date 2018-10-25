
public class LinkStrand implements IDnaStrand {

	public LinkStrand() {
		this("");
	}
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
	public int myIndex;
	public int myLocalIndex;
	public Node myCurrent;
;

	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}
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

	@Override
	public IDnaStrand getInstance(String source) {
		// TODO Auto-generated method stub
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next = new Node (dna);
		myLast = myLast.next;
		mySize = mySize+dna.length();
		myAppends = myAppends+1;
		return this;
	}

	
	
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		Node mid  = myFirst;
		Node list = myFirst;
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

	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

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
