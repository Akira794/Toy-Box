#include <iostream>
#include "func.h"

//Link1 Link2 joint1 joint2 end
using namespace std;

int main()
{
	double x,y;
	double th_1,th_2;
	cout << "input x & y =>";
	cin >> x;
	cin >> y;

	Inverse_Kinematics(6.0, 3.0, x, y, &th_1, &th_2);

	cout << "th1 = " << th_1 << endl;
	cout << "th2 = " << th_2 << endl;
}


