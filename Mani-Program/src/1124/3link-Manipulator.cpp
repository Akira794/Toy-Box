#include <iostream>
#include "func.h"

//Link1 Link2 joint1 joint2 end
using namespace std;

int main()
{
	double x,y;
	double a,b,c;
	cout << "input deg1 & deg2 & deg3 =>";
	cin >> a;
	cin >> b;
	cin >> c;

	Forward_Kinematics(6.0, 5.0, 3.0, a, b, c, &x, &y);

	cout << "x = " << x << endl;
	cout << "y = " << y << endl;
}


