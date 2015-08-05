/************************************************************************
File	:  MyVector.cpp
Purpose	:  implementation and interface file for MyVector
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/

#include<iostream>
#include "Node.h"

using namespace std;



template<class T> class MyVector
{
public:

	// Method Name	:  MyVector
	// Purpose		:  construct a data structure called MyVector
	// Parameters	:  none
	// Returns		:  NA
	MyVector();

	// Method Name	:  ~MyVector
	// Purpose		:  delete all nodes in this MyVector
	// Parameters	:  none
	// Returns		:  NA
	~MyVector();

	// Method Name	:  size
	// Purpose		:  get the current number of elements in this MyVector
	// Parameters	:  none
	// Returns		:  the number of elements in this MyVector
	int size() const;

	// Method Name	:  clear
	// Purpose		:  deletes all nodes in this MyVector
	// Parameters	:  none
	// Returns		:  void
	void clear();

	// Method Name	:  addToEnd
	// Purpose		:  add an element to the end of this MyVector
	// Parameters	:  x -- the element to add
	// Returns		:  true, if successful
	bool addToEnd(T &x);

	// Method Name	:  addToFront
	// Purpose		:  add an element to the front of this MyVector
	// Parameters	:  x -- the element to add
	// Returns		:  true, if successful
	bool addToFront(T &x);

	// Method Name	:  retrieve
	// Purpose		:  retrieve a pointer to the element at the given position
	// Parameters	:  position -- the index in this MyVector of wanted element
	// Returns		:  a pointer to the element at the given position
	T *retrieve(int position);

	// Method Name	:  remove
	// Purpose		:  remove the element at the given position in this MyVector
	// Parameters	:  position -- the index in this MyVector of unwanted element
	// Returns		:  void
	void remove(int position);

	// Method Name	:  insert
	// Purpose		:  place the given element into this MyVector at the given position
	// Parameters	:  position -- the index in this MyVector to place given element
	//				   x -- the element to insert
	// Returns		:  true, if successful
	bool insert(int position, T &x);

private:

	//the front of this MyVector
	Node<T> *head;

	//the end of this MyVector
	Node<T> *tail;

	//the number of elements in this MyVector
	int count;
};



//implementation
template <class T>
MyVector<T>::MyVector()
{
	count = 0;
	head = NULL;
}

template <class T>
MyVector<T>::~MyVector()
{
	clear();
}

template <class T>
int MyVector<T>::size() const
{
	return count;
}


template <class T>
T *MyVector<T>::retrieve(int position)
{
	if(position<0||position>=count)
	{
		return NULL;
	}

	Node<T> *current = head;
	for(int i=0;i<position;i++)
	{
		current = current->next;
	}
	return((current->entry));
}

template <class T>
bool MyVector<T>::insert(int position, T &x)
{
	if(position<0||position>count) //no positions out of range
	{
		return false;
	}
	Node<T> *new_node = NULL;
	if(count == 0) //if there are no elements currently in this MyVector
	{
		new_node = new Node<T>(x, NULL);
		head = new_node;
		tail = new_node;
		count++;
		return true;
	}
	if(position == (count-1)&&(count!=1)) //if not inserting at end or at 2nd position
	{
		new_node = new Node<T> (x,NULL);
		tail->next = new_node;
		tail = new_node;
		count++;
		return true;
	}

	Node<T> *previous, *following;
	if(position>0) //no negative positions please
	{
		previous = head;
		for(int i=1; i<position; i++)
		{
			previous = previous->next;
		}
		following=previous->next;
	}
	else
	{
		following=head;
	}

	new_node = new Node<T> (x,following);

	if(new_node==NULL)
	{
		return false;
	}
	if(position==0) //if inserting at the front with other elements in theis MyVector
	{
		head=new_node;
	}
	else
	{
		previous->next=new_node;
	}
	count++;
	return true;
}

template <class T>
bool MyVector<T>::addToFront(T &x)
{
	return(insert(0, x));
}

template <class T>
bool MyVector<T>::addToEnd(T &x)
{
	if(count == 0)
	{
		return (addToFront(x));
	}
	else
	{
		return(insert((count-1), x));
	}
}

template <class T>
void MyVector<T>::clear()
{
	Node<T> *current, *previous;
	current=head;
	while(current!=NULL)
	{
		previous=current;
		current = current->next;
		delete previous;
		count--;
	}
	head = NULL;
}

template <class T>
void MyVector<T>::remove(int position)
{
	Node<T> *current, *previous;
	current=head;
	if(count ==0||position>=count) //no positions out of range
	{
		return;
	}
	if(position == 0) //if removing the first element
	{
		head = head->next;
		delete current;
		count--;
		return;
	}
	if(count == 1) //if removing the only element
	{
		head = NULL;
		tail = NULL;
		delete current;
		count--;
		return;
	}
	if(position == (count-1)) //if removing the last element
	{
		for(int i=0; i<position; i++)
		{
			previous=current;
			current = current->next;
		}
		previous->next = current->next;
		tail = previous;
		delete current;
		count--;
		return;
	}
	//if removing something in the middle
	for(int i=0; i<position; i++)
	{
		previous=current;
		current = current->next;
	}
	previous->next = current->next;
	delete current;
	count--;
}
