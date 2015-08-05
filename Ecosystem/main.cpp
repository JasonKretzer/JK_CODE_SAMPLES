/************************************************************************
File	:  main.cpp
Purpose	:  driver for the Ecosystem simulation
Author	:  Jason R. Kretzer
Date	:  9/15/2003
*************************************************************************/


#include "Herbivore.h"
#include "Plant.h"
#include "MyVector.h"
#include <iostream>
#include <ctime>
#include <stdlib.h>
#include <string.h>

using namespace std;


//prototypes

// Method Name	:  pondWeek
// Purpose		:  simulate a week in the Ecosystem simulation
// Parameters	:  fish -- MyVector of Herbivore objects
//				   weeds -- MyVector of Plant objects
// Returns		:  void
void pondWeek(MyVector<Herbivore> &fish, MyVector<Plant> &weeds);

// Method Name	:  totalMass
// Purpose		:  calculate the total mass of all the organisms
// Parameters	:  organisms -- MyVector of Plant objects
// Returns		:  the total mass of all Plants in the MyVector organisms
double totalMass(MyVector<Plant> &organisms);


//constants -- made global so they would be visible to all functions in this file
const double FISH_SIZE = 50.0;
const double FRACTION = 0.5;
const int AVERAGE_NIBBLES = 30;
const double BIRTH_RATE = 0.05;

//settable on the command line
int MANY_WEEKS = 3;


// Method Name	:  main
// Purpose		:  driver function for the Ecosystem simualation
// Parameters	:  none
// Returns		:  0, if successful
int main(int argc, char* argv[])
{
	if(argc > 1)
	{
		//there is no checking here!!!!
		MANY_WEEKS = atoi(argv[1]);
	}
	//constants only needed for this function
	const int MANY_WEEDS = 2000;
	const double WEED_SIZE = 15.0;
	const double WEED_RATE = 2.5;
	const int INIT_FISH = 300;


	MyVector<Herbivore> fish;
	MyVector<Plant> weeds;

	Herbivore *aFish = NULL;
	for(int j=0; j<INIT_FISH; j++)
	{
		aFish = new Herbivore(FISH_SIZE, 0, (FISH_SIZE*FRACTION));
		fish.addToEnd(*aFish);
	}

	Plant *aPlant = NULL;
	for(int i=0; i<MANY_WEEDS; i++)
	{
		aPlant = new Plant(WEED_SIZE, WEED_RATE);
		weeds.addToEnd(*aPlant);
	}


	cout<<"Week    # of Fish    Plant Mass (oz.)"<<endl;
	cout<<"-------------------------------------"<<endl;

	for(int z=1; z<= MANY_WEEKS; z++)
	{
		pondWeek(fish, weeds);
		cout.width(3);
		cout<<z;
		cout.width(10);
		cout<<fish.size();
		cout.width(18);
		cout<<(static_cast<int>(totalMass(weeds)))<<endl;
		cout<<"-------------------------------------"<<endl;

	}

	return 0;
}


double totalMass(MyVector<Plant> &organisms)
{
	double answer = 0.0;
	Plant *next;

	for(int i=0; i<organisms.size(); i++)
	{
		next = organisms.retrieve(i);

		if(next!=NULL)
		{
			answer += next->getSize();
		}
	}
	return answer;
}


static int callCount = 0;
void pondWeek(MyVector<Herbivore> &fish, MyVector<Plant> &weeds)
{
	//seed the random number generator
	srand(time(NULL));

	int manyIterations = 0;
	int index = 0;

	Herbivore *nextFish;
	Plant *nextWeed;

	manyIterations = AVERAGE_NIBBLES*fish.size();

	//random fish eating random weeds for a few iterations
	for(int i=0; i<manyIterations; i++)
	{
		index = (rand()%fish.size());
		nextFish = fish.retrieve(index);
		index = (rand()%weeds.size());
		nextWeed = weeds.retrieve(index);
		nextFish->nibble((*nextWeed));

	}

	int z=0;

	//go through and remove any dead fish
	while(z<fish.size())
	{
		nextFish = fish.retrieve(z);
		nextFish->simulateWeek();
		if(nextFish->isAlive())
		{
			z++;
		}
		else
		{
			fish.remove(z);
		}
	}

	//let the plants grow another week
	for(z=0; z<weeds.size(); z++)
	{
		nextWeed = weeds.retrieve(z);
		nextWeed->simulateWeek();
	}

	manyIterations = static_cast<int>(BIRTH_RATE*fish.size());

	//more fish are born
	for(int w=0; w<manyIterations; w++)
	{
		Herbivore *aFish = new Herbivore(FISH_SIZE, 0, (FISH_SIZE*FRACTION));
		fish.addToEnd(*aFish);
	}

}
