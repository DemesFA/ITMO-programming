#include <iostream>
#include "Functions.h"
#include <vector>
#include <algorithm>


int main() {

    int Size;
    std::cout << "Enter a size of vector" << std::endl;
    std::cin >> Size;
    std::vector<int> vec(Size);
    std::cout << "Enter elements in vector" << std::endl;
    for(int i = 0; i < vec.size(); i++){
        std::cin >> vec[i];
    }

    Functions functions;
    bool noneOfResult = functions.none_of(vec.begin(),vec.end(), [](int num) {return num > 10; });
    std::cout << "none_of Result: " << (noneOfResult ? "true" : "false") << std::endl;

    bool isSortedResult = functions.is_sorted(vec.begin(), vec.end(), std::less<int>());
    std::cout << "is_sorted Result: " << (isSortedResult ? "true" : "false") << std::endl;



}
