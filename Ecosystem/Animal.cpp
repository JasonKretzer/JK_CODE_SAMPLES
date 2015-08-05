/************************************************************************
File	:  Animal.cpp
Purpose	:  implementation file for the Animal object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/
#include"Animal.h"

Animal::Animal(double initSize, double initRate, double initNeed)
:Organism(initSize, initRate)
{
	needEachWeek = initNeed;
	eatenThisWeek = 0.0;
	if(initNeed<0.0)
	{
		cout<<endl<<"Invalid initialization, amount of food needed per week is set to zero."<<endl;
		cout<<"Use setNeed to modify."<<endl;
		needEachWeek = 0.0;
	}
}

void Animal::eat(double amount)
{
	if(amount<0.0)
	{
		cout<<endl<<"Invalid setting, amount eaten this week is unchanged."<<endl;
	}
	else
	{
		eatenThisWeek += amount;
	}
}

double Animal::getNeed()
{
	return needEachWeek;
}

void Animal::setNeed(double newNeed)
{
	if(newNeed<0.0)
	{
		cout<<endl<<"Invalid setting, amount of food needed per week is unchanged."<<endl;
	}
	else
	{
		needEachWeek = newNeed;
	}

}

double Animal::stillNeed()
{
	double left = (needEachWeek - eatenThisWeek);
	if(left < 0.0)
	{
		return 0.0;
	}
	return left;
}

void Animal::simulateWeek()
{
	Organism::simulateWeek();
	if(eatenThisWeek<needEachWeek)
	{
		expire();
	}
	eatenThisWeek = 0.0;
}
