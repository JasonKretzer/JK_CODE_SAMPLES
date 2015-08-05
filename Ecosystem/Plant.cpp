/************************************************************************
File	:  Plant.cpp
Purpose	:  implementation file for the Plant object
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/


#include "Plant.h"

Plant::Plant(double initSize, double initRate)
:Organism(initSize, initRate)
{
}


void Plant::nibbledOn(double amount)
{
	if(amount<0.0)
	{
		cout<<endl<<"Invalid setting, amount nibbled on is unchanged."<<endl;
	}
	else
	{
		alterSize(((-1)*amount));
	}
}
