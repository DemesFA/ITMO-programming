#include <iostream>
#include "Stack.h"
#include <vector>
#include <string>


int main() {
    Stack stack(5);
    int NumberOfCommands;
    std::cin >> NumberOfCommands;
    for(std::size_t i = 0; i < NumberOfCommands; i++){
        std::string command;
        std::cin >> command;
        if(command == "add"){
            int n;
            std::cin >> n;
            stack.push(n);
        }
        if(command == "count")
        {
            int count = 0;
            stack.elements_count(count);
        }

        if(command == "del")
        {
            stack.pop();
        }
        if(command == "empty")
        {
            stack.isEmpty();
        }
        if(command == "top")
        {
            stack.top();
        }
        if(command == "print")
        {
            stack.print();
        }
    }

    return 0;
}