
/* 	
   	Roll No. 216 Batch 2
   	Program to display person's age & heart rate per minute
*/

#include<iostream>
using namespace std;

int main()
{
	int age,beat_rate_per_min;
	long total_heartbeats;

	cout<<"Enter the age of the person:";
	cin>>age;
	cout<<'\n'<<"Enter the heart-beat rate per minute:";
	cin>>beat_rate_per_min;
	
	total_heartbeats=24*60*365*age*beat_rate_per_min;

	cout<<'\n'<<"The age and total_heartbeats are:";
	cout<<'\t'<<age<<'\t'<<total_heartbeats;

} 

/* 
  	Output:

	Enter the age of the person:1

	Enter the heart-beat rate per minute:10

	The age and total_heartbeats are:       1       5256000

*/
