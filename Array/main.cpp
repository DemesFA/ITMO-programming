#include <iostream>
#include "Array.h"


int main() {

    std::cout << "Enter a size of array:" << std::endl;
    int size;
    std::cin >> size;
    Array array(size);
    std::cout << "Enter a elements for array:" << std::endl;
    for(int i = 0;i < size;i++){
        std::cin >> array[i];
    }

    std::cout << "Enter a K for arr[] + K" << std::endl;
    float k;
    std::cin >> k;
    array + k;

    std::cout << "Result after operation:" << std::endl;
    for(int i = 0;i< size;i++){
        std::cout << array[i] << " ";
    }

    bool IfTenResult = array.IfTen();
    std::cout << "IfTen Result: " << (IfTenResult ? "true" : "false") << std::endl;
    float GeomResult = array.geometricMean();
    std::cout << "geometricMean Result: " << GeomResult << std::endl;

    return 0;
}
