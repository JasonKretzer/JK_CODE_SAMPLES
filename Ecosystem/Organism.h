/************************************************************************
File	:  Organism.h
Purpose	:  public inteface file for the Organism object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/


#ifndef ORGANISM_H
#define ORGANISM_H

#include <iostream>

using namespace std;

class Organism
{

public:
	// Method Name	:  Organism
	// Purpose		:  Construct an Organism with a specified size and growth rate
	// Parameters	:  initSize -- initial size of this Organism in ounces
	//				   initRate -- initial growth rate of this Organism in ounces per week
	// Returns		:  NA
	Organism(double initSize, double initRate);

	// Method Name	:  alterSize
	// Purpose		:  Change the current size of this Organism by given amount
	// Parameters	:  amount -- the value by which to increase or decrease the size of this Organism
	// Returns		:  void
	void alterSize(double amount);

	// Method Name	:  expire
	// Purpose		:  kills this Organism by setting size and rate to zero
	// Parameters	:  none
	// Returns		:  void
	void expire();

	// Method Name	:  getRate
	// Purpose		:  get the growth rate of this Organism
	// Parameters	:  none
	// Returns		:  the growth rate of this Organism in ounces per week
	double getRate();

	// Method Name	:  getSize
	// Purpose		:  get the current size of this Organism
	// Parameters	:  none
	// Returns		:  the current size in ounces of this Organism
	double getSize();

	// Method Name	:  isAlive
	// Purpose		:  determine whether this Organism is still alive
	// Parameters	:  none
	// Returns		:  if this Organism's size is greater than zero then returns true
	bool isAlive();

	// Method Name	:  setRate
	// Purpose		:  set the current growth rate of this Organism
	// Parameters	:  newRate -- the new growth rate for this Organism in ouces per week
	// Returns		:  void
	void setRate(double newRate);

	// Method Name	:  simulateWeek
	// Purpose		:  Simulate the passage of one week in the life of this Organism
	// Parameters	:  none
	// Returns		:  void
	virtual void simulateWeek();

private:
	// size of the organism in ounces
	double size;

	//rate of growth of the organism in ounces per week
	double rate;
};

#endif
