#include "Stack.h"
#include <stdexcept>
#include <vector>
#include <iostream>


template<int N,typename T>
Stack<N,T>::Stack() : head(nullptr),next(nullptr) {};

template<int N,typename T>
Stack<N,T>::~Stack() {
    while(head != nullptr)
    {
        auto * temp = head;
        head = head->next;
        delete temp;
    }
}

template<int N,typename T>
void Stack<N, T>::push(T value) {
    if(Stack_size() >= N)
    {
        throw std::overflow_error("Stack overflow");
    }
    auto* node = new Stack;
    node->data = value;
    node->next = head;
    head = node;
}


template<int N,typename T>
void Stack<N, T>::pop() {
    if(head == nullptr)
    {
        throw std::underflow_error("Stack underflow");
    }
    auto* temp = head;
    head = head->next;
    delete temp;
}


template<int N,typename T>
void Stack<N, T>::print() {
    auto* temp = head;
    std::vector<T> result;
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


template<int N,typename T>
int Stack<N, T>::Stack_size() {
    auto* temp = head;
    int count = 0;
    while(temp != nullptr)
    {
        count++;
        temp = temp->next;
    }
    return count;
}
template class Stack <5,int>;