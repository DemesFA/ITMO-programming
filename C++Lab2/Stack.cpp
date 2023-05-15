#include <iostream>
#include "Stack.h"
#include <vector>
#include <string>

Stack::Stack(){
    data = 0;
    head = nullptr;
    next = nullptr;
    max_depth = 0;
}
Stack::Stack(int max_depth) : max_depth(max_depth){}
Stack::~Stack() {
    while(head != nullptr)
    {
        Stack* temp = head;
        head = head->next;
        delete temp;
    }
}

int Stack::Stack_size() {
    Stack* temp = head;
    int count = 0;
    while(temp != nullptr)
    {
        count++;
        temp = temp->next;
    }
    return count;
}

void Stack::elements_count(int& count) {
    Stack* temp = head;
    while(temp != nullptr)
    {
        count++;
        temp = temp->next;
    }
    std::cout << count << std::endl;
}


void Stack::push(int value)
{
    if (Stack_size() >= max_depth) {
        std::cout << "Stack is full" << std::endl;
        std::exit(1);
    }
    auto* node = new Stack;
    node->data = value;
    node->next = head;
    head = node;
}

void Stack::pop() {
    Stack* temp = head;
    head = head->next;
    delete temp;
}

void Stack::isEmpty() {
    if(head != nullptr)
    {
        std::cout << "NO" << std::endl;
    }
    else
    {
        std::cout << "YES" << std::endl;
    }
}


void Stack::top() {
    if(head == nullptr)
    {
        std::cout << "nothing on top";
    }
    else
    {
        int TopElement = head->data;
        std::cout << TopElement << std::endl;
    }
}
void Stack::print() {
    Stack* temp = head;
    std::vector<int> result;
    while (temp != nullptr)
    {
        result.push_back(temp->data);
        temp = temp->next;
    }
    for(auto& x : result)
    {
        std::cout << x << " " << "\n";
    }
}