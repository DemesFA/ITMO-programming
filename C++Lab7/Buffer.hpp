#pragma once
#include <iterator>

template <typename T>
class Buffer {
public:
    explicit Buffer(std::size_t capacity);
    ~Buffer();
    void push_back(const T& value);
    void push_front(const T& value);
    void insert(const T& value, std::size_t index);
    void pop_back();
    void pop_front();
    void erase(std::size_t index);
    T& operator[](std::size_t index);
    int size() const;
    typename std::iterator<std::random_access_iterator_tag, T>::pointer begin();
    typename std::iterator<std::random_access_iterator_tag, T>::pointer end();

private:
    T* buffer;
    std::size_t size_;
    std::size_t capacity_;
    std::size_t start_;

    void expandCapacity();
    void shiftLeft(std::size_t index);
    void shiftRight(std::size_t index);
};