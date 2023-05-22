#include "Buffer.h"


template <typename T>
Buffer<T>::Buffer(std::size_t capacity) : capacity_ (capacity), size_(0), start_(0){
    buffer = new T[capacity_];
};

template <typename T>
Buffer<T>::~Buffer() {
    delete[] buffer;
}

template <typename T>
void Buffer<T>::expandCapacity() {
    size_t newCapacity = capacity_ * 2;
    T* newBuffer = new T[newCapacity];
    for (size_t i = 0; i < size_; ++i) {
        newBuffer[i] = buffer[(start_ + i) % capacity_];
    }
    delete[] buffer;
    buffer = newBuffer;
    capacity_ = newCapacity;
    start_ = 0;
}

template <typename T>
void Buffer<T>::push_back(T &value) {
    if(capacity_ == size_){
        expandCapacity();
    }
    buffer[(start_ + size_) % capacity_] = value;
    size_++;
}

