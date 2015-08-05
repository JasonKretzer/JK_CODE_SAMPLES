/************************************************************************
File	:  Animal.h
Purpose	:  public inteface file for the Animal object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/

#ifndef ANIMAL_H
#define ANIMAL_H


#include <iostream>
#include "Organism.h"

using namespace std;

class Animal : public Organism
{

public:
	// Method Name	:  Animal
	// Purpose		:  construct an Animal with a specified size and growth rate
	// Parameters	:  initSize -- initial size of this Animal in ounces
	//				   initRate -- initial growth rate of this Animal in ounces per week
	//				   initNeed -- initial weekly eating requirement fot this Animal in ounces per week
	// Returns		:  NA
	Animal(double initSize, double initRate, double initNeed);

	// Method Name	:  eat
	// Purpose		:  have this Animal eat the given amount of food in ounces
	// Parameters	:  amount -- the amount of food for this Animal to eat in ounces
	// Returns		:  void
	void eat(double amount);

	// Method Name	:  getNeed
	// Purpose		:  determine the amount of food that this Animal needs each week
	// Parameters	:  none
	// Returns		:  the total amount of food that this Animal needs to survive one week in ounces
	double getNeed();

	// Method Name	:  setNeed
	// Purpose		:  set the current growth weekly food requirement of this Animal
	// Parameters	:  newNeed -- the new weekly food requirement for this Animal in ouces per week
	// Returns		:  void
	void setNeed(double newNeed);

	// Method Name	:  stillNeed
	// Purpose		:  determine the amount of that this Animal still needs to survive this week
	// Parameters	:  none
	// Returns		:  the ounces of food that this Animal still needs to survive this week
	double stillNeed();

	// Method Name	:  simulateWeek
	// Purpose		:  Simulate the passage of one week in the life of this Animal
	// Parameters	:  none
	// Returns		:  void
	virtual void simulateWeek();

private:
	// amount of food this Animal needs
	double needEachWeek;

	// amount of food this Animal has eaten this week
	double eatenThisWeek;
};


#endif
