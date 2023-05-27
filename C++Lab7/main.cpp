#include <iostream>
#include "Buffer.h"

int main() {
    // Создаем буфер и добавляем элементы
    Buffer<int> buffer(5);
    buffer.push_back(5);
    buffer.push_back(3);
    buffer.push_front(9);
    buffer.insert(2, 1);

    // Выводим элементы буфера
    std::cout << "Buffer elements: ";
    for (std::size_t i = 0; i < buffer.size(); ++i) {
        std::cout << buffer[i] << " ";
    }
    std::cout << std::endl;

    // Сортируем элементы буфера
    buffer.sort();

    // Выводим отсортированные элементы буфера
    std::cout << "Sorted buffer elements: ";
    for (std::size_t i = 0; i < buffer.size(); ++i) {
        std::cout << buffer[i] << " ";
    }
    std::cout << std::endl;

    return 0;
}
