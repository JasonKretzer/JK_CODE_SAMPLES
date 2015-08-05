/************************************************************************
File	:  Herbivore.h
Purpose	:  public inteface file for the Herbivore object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/



#ifndef HERBIVORE_H
#define HERBIVORE_H

#include<iostream>
#include "Animal.h"
#include "Plant.h"
using namespace std;

class Herbivore : public Animal
{

public:

	// Method Name	:  Herbivore
	// Purpose		:  construct an Herbivore with a specified size and growth rate
	// Parameters	:  initSize -- initial size of this Herbivore in ounces
	//				   initRate -- initial growth rate of this Herbivore in ounces per week
	//				   initNeed -- initial weekly eating requirement fot this Herbivore in ounces per week
	// Returns		:  NA
	Herbivore(double initSize, double initRate, double initNeed);

	// Method Name	:  Herbivore
	// Purpose		:  copy construct an Herbivore with a specified size and growth rate
	// Parameters	:  copy -- the Herbivore to copy
	// Returns		:  NA
	Herbivore(Herbivore &copy);

	// Method Name	:  Herbivore
	// Purpose		:  have this herbivore eat part of a Plant
	// Parameters	:  Plant -- the Plant that will be partly eaten
	// Returns		:  void
	void nibble(Plant &);


};


#endif
