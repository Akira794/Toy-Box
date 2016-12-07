#include <iostream>
#include <math.h>
#include "func.h"
#define PI 3.14159265

//Link1 Link2 joint1 joint2 end
using namespace std;

void Forward_Kinematics(double L1, double L2, double deg1, double deg2, double *x, double *y){
	/*-deg to rad-*

	double rad1 = deg1 * (PI/180);
	double rad2 = deg2 * (PI/180);
	/*------------*/
	
	double rad1 = DtR(deg1);
	double rad2 = DtR(deg2);

	*x =L1 * cos (rad1) +L2 * cos (rad1 + rad2);
	*y =L1 * sin (rad2) +L2 * sin (rad1 + rad2);
}

main()
{
	double x,y;
	double a,b;
	cout << "input deg1 & deg2 =>";
	cin >> a;
	cin >> b;

	Forward_Kinematics(6.0, 5.0, a, b, &x, &y);

	cout << "x = " << x << endl;
	cout << "y = " << y << endl;
}


