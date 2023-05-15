#include <iostream>
#include "Complex.h"
#include "Queue.h"
#include <string>


int main() {
    double real, imaginary, real2, imaginary2;
    std::cout << "Enter a Complex Number 1" << std::endl;
    std::cin >> real >> imaginary;
    Complex ComplexNumber1(real, imaginary);

    std::cout << "Enter a Complex Number 2" << std::endl;
    std::cin >> real2 >> imaginary2;
    Complex ComplexNumber2(real2, imaginary2);
    Complex result1 = ComplexNumber1 * ComplexNumber2;
    std::cout << "result of ComplexNumber1 * ComplexNumber2" << std::endl;
    std::cout << result1 << std::endl;
    Complex result2 = ComplexNumber1 + ComplexNumber2;
    std::cout << "result of ComplexNumber1 + ComplexNumber2" << std::endl;
    std::cout << result2 << std::endl;
    double number;
    std::cout << "Enter a float number" << std::endl;
    std::cin >> number;
    Complex result3 = ComplexNumber1 * number;
    std::cout << "result of ComplexNumber1 * float number" << std::endl;
    std::cout << result3 << std::endl;
    Complex result4 = ComplexNumber2 * number;
    std::cout << "result of ComplexNumber2 * float number" << std::endl;
    std::cout << result4 << std::endl;
    auto len1 = double (ComplexNumber1);
    auto len2 = double (ComplexNumber2);
    std::cout << "length of ComplexNumber1" << std::endl;
    std::cout << len1 << std::endl;
    std::cout << "length of ComplexNumber2" << std::endl;
    std::cout << len2 << std::endl;

    
    int element;
    int request;
    std::cout << "Enter the number of requests" << std::endl;
    std::cin >> request;
    std::cout << "Instruction:" << std::endl;
    std::cout << "add is a command for push element in queue" << std::endl;
    std::cout << "del is a command for delete a first element in queue" << std::endl;
    Queue queue;
    for(std::size_t i = 0; i < request; i++)
    {
        std::string command;
        std::cin >> command;
        if(command == "add")
        {
            std::cin >> element;
            queue << element;
        }
        if(command == "del")
        {
            int val;
            queue >> val;
            std::cout << val << std::endl;
        }
    }



    return 0;
}
