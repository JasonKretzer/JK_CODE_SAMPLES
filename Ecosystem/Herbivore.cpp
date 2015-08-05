/************************************************************************
File	:  Herbivore.cpp
Purpose	:  implementation file for the Herbivore object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/



#include "Herbivore.h"
#include<iostream>

using namespace std;

Herbivore::Herbivore(double initSize, double initRate, double initNeed)
:Animal(initSize, initRate, initNeed)
{
}


Herbivore::Herbivore(Herbivore &copy)
:Animal(copy.getSize(), copy.getRate(), copy.getNeed())
{
}



void Herbivore::nibble(Plant &meal)
{
	const double PORTION = 0.5;
	const double MAX_FRACTION = 0.1;

	double amount = 0.0;

	int fds = 0;
	amount = PORTION * meal.getSize();
	if(amount > MAX_FRACTION * getNeed())
	{
		amount = MAX_FRACTION * getNeed();
	}
	if(amount > stillNeed())
	{
		amount = stillNeed();
	}
	eat(amount);
	meal.nibbledOn(amount);
}
