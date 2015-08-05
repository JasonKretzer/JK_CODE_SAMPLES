/************************************************************************
File	:  Organism.cpp
Purpose	:  implementation file for the Organism object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/



#include "Organism.h"



Organism::Organism(double initSize, double initRate)
{
	size = initSize;
	rate = initRate;

	if(size<0.0||rate<0.0)
	{
		cout<<endl<<"Invalid initializations, both values set to zero."<<endl;
		cout<<"Use alterSize and setRate to modify."<<endl;
		size = 0.0;
		rate = 0.0;
	}
}

void Organism::alterSize(double amount)
{
	size += amount;
	if(size<=0.0)
	{
		rate = 0.0;
		expire();
	}
}

void Organism::expire()
{
	size = rate = 0.0;
}

double Organism::getRate()
{
	return rate;
}

double Organism::getSize()
{

	return size;
}

bool Organism::isAlive()
{
	return (size>0.0);
}

void Organism::setRate(double newRate)
{
	if(size == 0.0 && newRate != 0.0)
	{
		rate = 0.0;
		return;
	}
	rate = newRate;
}

void Organism::simulateWeek()
{
	size += rate;
	if(size<=0.0)
	{
		expire();
	}
}
