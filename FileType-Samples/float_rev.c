
#include<stdio.h>
#include "stack_float.h"

main()
{
	stack S;
	initialisation(&S);
	int i;
	float v;
	float val[20];

	printf("\nEnter vals:");
	for(i=0;i<5;i++)
		scanf("%f",&val[i]);

	// PUSH INTO STACK
	
	while(!isempty(&S))
	{
		for(i=0;i<5;i++)
			push(val[i],&S);
	}

	//POP FROM STACK
	
	while(!isfull(&S))
	{
		for(i=0;i<5;i++)
		{
			v=pop(&S);
                        printf("\t %f",v);
		}
	}
	
}
		
