#include "Buffer.h"

template <typename T>
Buffer<T>::Buffer(std::size_t capacity) : capacity_(capacity), size_(0), start_(0) {
    buffer = new T[capacity_];
}

template <typename T>
Buffer<T>::~Buffer() {
    delete[] buffer;
}

template <typename T>
void Buffer<T>::expandCapacity() {
    std::size_t newCapacity = capacity_ * 2;
    T* newBuffer = new T[newCapacity];
    for (std::size_t i = 0; i < size_; ++i) {
        newBuffer[i] = buffer[(start_ + i) % capacity_];
    }
    delete[] buffer;
    buffer = newBuffer;
    capacity_ = newCapacity;
    start_ = 0;
}

template <typename T>
void Buffer<T>::shiftRight(std::size_t index) {
    for (std::size_t i = size_; i > index; --i) {
        buffer[(start_ + i) % capacity_] = buffer[(start_ + i - 1) % capacity_];
    }
}

template <typename T>
void Buffer<T>::shiftLeft(std::size_t index) {
    for (std::size_t i = index; i < size_ - 1; ++i) {
        buffer[(start_ + i) % capacity_] = buffer[(start_ + i + 1) % capacity_];
    }
}

template <typename T>
void Buffer<T>::push_back(const T& value) {
    if (capacity_ == size_) {
        expandCapacity();
    }
    buffer[(start_ + size_) % capacity_] = value;
    size_++;
}

template <typename T>
void Buffer<T>::push_front(const T& value) {
    if (capacity_ == size_) {
        expandCapacity();
    }
    shiftRight(0);
    buffer[start_ % capacity_] = value;
    size_++;
}

template <typename T>
void Buffer<T>::insert(const T& value, std::size_t index) {
    if (capacity_ == size_) {
        expandCapacity();
    }
    shiftRight(index);
    buffer[(start_ + index) % capacity_] = value;
    size_++;
}

template <typename T>
void Buffer<T>::pop_back() {
    if (size_ > 0) {
        size_--;
    }
}

template <typename T>
T& Buffer<T>::operator[](std::size_t index) {
    return buffer[(start_ + index) % capacity_];
}

template <typename T>
const T& Buffer<T>::operator[](std::size_t index) const {
    return buffer[(start_ + index) % capacity_];
}

template <typename T>
std::size_t Buffer<T>::size() const {
    return size_;
}

template <typename T>
void Buffer<T>::sort() {
    std::sort(buffer + start_, buffer + start_ + size_);
}
