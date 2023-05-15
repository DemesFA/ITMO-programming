#include <iostream>
#include "Stack.h"


template<typename T>
T MaxElementInArray(std::vector<T>& vec)
{   T result;
    for(int i = 0;i < vec.size();i++)
    {
        if(vec[i] > vec[i + 1])
        {
            result = vec[i];
        }
    }
    return result;

}




int main() {

    Stack<5,int> stack;
    try {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.pop();
        stack.pop();
        stack.print();
    } catch (const std::overflow_error& e) {
        std::cout << "Error: " << e.what() << std::endl;
    } catch (const std::underflow_error& e) {
        std::cout << "Error: " << e.what() << std::endl;
    }

    std::cout << "Enter a size of Array" << std::endl;
    int n;
    std::cin >> n;
    std::cout << "Enter a elements in Array" << std::endl;
    std::vector<int> vec(n);
    for(int i = 0; i < vec.size(); i++){
        std::cin >> vec[i];
    }
    int maxElement = MaxElementInArray(vec);
    std::cout << "Max element in Array is:" << maxElement << std::endl;
    return 0;
}
