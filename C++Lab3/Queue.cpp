#include "Queue.h"

Queue::Queue() {
    head = nullptr;
    tail = nullptr;
    next = nullptr;
    data = 0;
}
Queue::~Queue() {
    Queue* temp = head;
    while(temp != nullptr)
    {   next = temp->next;
        delete temp;
        temp = next;
    }
}

void Queue::push(int value) {
    auto* node = new Queue;
    node->data = value;
    node->next = nullptr;
    if(head == nullptr)
    {
        head = tail = node;
    }
    else
    {
        tail->next = node;
        tail = node;
    }
}

void Queue::del() {
    if(head == nullptr)
    {
        return;
    }
    Queue* temp = head;
    head = head->next;
    delete temp;
}

Queue& Queue::operator<<(int value) {
    Queue::push(value);
    return *this;
}

Queue& Queue::operator>>(int& value) {
    if (head != nullptr) {
        value = head->data;
        del();
    }
    return *this;
}