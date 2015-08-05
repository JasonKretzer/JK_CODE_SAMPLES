/************************************************************************
File	:  Plant.h
Purpose	:  implementation file for the Plant object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/


#ifndef PLANT_H
#define PLANT_H


#include <iostream>
#include "Organism.h"

using namespace std;

class Plant : public Organism
{

public:

	// Method Name	:  Plant
	// Purpose		:  construct an Plant with a specified size and growth rate
	// Parameters	:  initSize -- initial size of this Plant in ounces
	//				   initRate -- initial growth rate of this Plant in ounces per week
	// Returns		:  NA
	Plant(double initSize, double initRate);

	// Method Name	:  nibbledOn
	// Purpose		:  decreases the size of the Plant since amount of it has been eaten
	// Parameters	:  amount -- the amount to decrease the size of the Plant
	// Returns		:  void
	void nibbledOn(double amount);

};


#endif
