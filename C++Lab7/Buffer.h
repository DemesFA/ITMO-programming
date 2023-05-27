#pragma once
#include <iostream>
#include <algorithm> // Для использования std::sort

template <typename T>
class Buffer {
public:
    explicit Buffer(std::size_t capacity);
    ~Buffer();
    void push_back(const T& value);
    void push_front(const T& value);
    void insert(const T& value, std::size_t index);
    void pop_back();
    T& operator[](std::size_t index);
    const T& operator[](std::size_t index) const;
    std::size_t size() const;
    void sort();

private:
    T* buffer;
    std::size_t size_;
    std::size_t capacity_;
    std::size_t start_;

    void expandCapacity();
    void shiftLeft(std::size_t index);
    void shiftRight(std::size_t index);
};

Buffer<int>;