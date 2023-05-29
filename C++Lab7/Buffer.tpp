#pragma once
#include <iterator>
#include <iostream>
#include "Buffer.hpp"

template<typename T>
Buffer<T>::Buffer(std::size_t capacity) : capacity_(capacity) , size_(0), start_(0) {
    buffer = new T[capacity];
}

template<typename T>
Buffer<T>::~Buffer() {
    delete[] buffer;
}


template<typename T>
void Buffer<T>::push_back(const T &value) {
    if(size_ == capacity_){
        expandCapacity();
    }
    buffer[start_ + size_] = value;
    size_++;
}

template<typename T>
void Buffer<T>::push_front(const T &value) {
    if(size_ == capacity_){
        expandCapacity();
    }
    shiftRight(start_);
    buffer[start_] = value;
    size_++;
}

template<typename T>
typename std::iterator<std::random_access_iterator_tag, T>::pointer Buffer<T>::begin() {
    return (buffer + start_);
}

template<typename T>
typename std::iterator<std::random_access_iterator_tag, T>::pointer Buffer<T>::end() {
    return (buffer + size_);
}

template<typename T>
void Buffer<T>::pop_front() {
    for (size_t i = 0; i < size_ - 1; i++)
        buffer[i] = buffer[i + 1];
    size_--;
}

template<typename T>
void Buffer<T>::pop_back() {
    if(size_ > 0){
        size_--;
    }
}

template<typename T>
int Buffer<T>::size() const {
    return size_;
}


template<typename T>
void Buffer<T>::shiftRight(std::size_t index) {
    for (size_t i = size_; i > index; --i) {
        buffer[(start_ + i) % capacity_] = buffer[(start_ + i - 1) % capacity_];
    }
}

template<typename T>
void Buffer<T>::shiftLeft(std::size_t index) {
    for (size_t i = index; i < size_ - 1; ++i) {
        buffer[(start_ + i) % capacity_] = buffer[(start_ + i + 1) % capacity_];
    }
}

template<typename T>
void Buffer<T>::insert(const T &value, std::size_t index) {
    if(size_ == capacity_){
        expandCapacity();
    }
    size_t insertIndex = (start_ + index) % capacity_;
    shiftRight(insertIndex);
    buffer[insertIndex] = value;
    size_++;
}

template<typename T>
void Buffer<T>::erase(std::size_t index) {
    if (size_ > 0) {
        size_t removeIndex = (start_ + index) % capacity_;
        shiftLeft(removeIndex);
        size_--;
    }
}

template<typename T>
T &Buffer<T>::operator[](std::size_t index) {
    return (buffer[start_ + index]);
}


template<typename T>
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