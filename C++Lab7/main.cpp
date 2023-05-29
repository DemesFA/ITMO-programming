#include "Buffer.hpp"
#include "Buffer.tpp"
#include <iostream>
#include <algorithm>




int main(){
    Buffer<int> buffer(10);
    buffer.push_back(2);
    buffer.push_back(3);
    buffer.push_back(5);
    buffer.push_front(4);
    buffer.insert(8,1);
    buffer.push_back(19);
    buffer.push_back(13);




    for(auto result : buffer) {
        std::cout << result << " ";
    }
    std::cout << std::endl;

    buffer.pop_front();
    buffer.pop_back();
    buffer.erase(3);
    int sizeResult = buffer.size();
    std::sort(buffer.begin(),buffer.end());

    for(auto sorted : buffer){
        std::cout << sorted << " ";
    }
    std::cout << std::endl;

    std::cout << "Buffer size is: " << sizeResult << std::endl;
}