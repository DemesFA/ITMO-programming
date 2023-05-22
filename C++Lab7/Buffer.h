#include <iostream>

template <typename T>
class  Buffer{

public:
    explicit Buffer(std::size_t capacity);
    ~Buffer();
    void push_back(T& value);
    void push_front(T& value);
    void insert(T& value, std::size_t index);
    void pop_back();
    void pop_front();
    void erase(std::size_t index);
    T& front();
    T& back();
    T& operator[](std::size_t index);
    void resize(std::size_t newCapacity);
    std::size_t size();

private:
    T* buffer;
    std::size_t size_;
    std::size_t capacity_;
    std::size_t start_;

    void expandCapacity();
    void shiftLeft();
    void shiftRight();
};