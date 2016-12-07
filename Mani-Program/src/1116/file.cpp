#include <iostream>
#include <string>
#include <fstream>

int main()
{
	std::ofstream ofs("test.txt");

	ofs << "testmessage" << 123 << std:: endl;

	return 0;
}
 
