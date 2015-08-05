/************************************************************************
File	:  Node.h
Purpose	:  public inteface and implementation file for the Node struct
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/


template<class Z>
struct Node
{
	//the data held by this Node
	Z *entry;

	//the next Node in the list
	Node<Z> *next;

	// Method Name	:  Node
	// Purpose		:  construct a default Node
	// Parameters	:  none
	// Returns		:  NA
	Node();

	// Method Name	:  Node
	// Purpose		:  construct a default Node with specified data an link
	// Parameters	:  x -- the data held by this Node
	//				   link -- the link to the next Node
	// Returns		:  NA
	Node(Z &x, Node<Z> *link=NULL);
};


//implementation
template<class Z>
Node<Z>::Node()
{
	next = NULL;
}


template<class Z>
Node<Z>::Node(Z &x, Node<Z> *link)
{
	entry = &x;
	next = link;
}
