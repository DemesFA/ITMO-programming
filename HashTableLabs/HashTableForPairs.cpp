#include <iostream>
#include <vector>
#include <list>
#include <optional>
#include <functional>


class HashTable {
private:
    std::vector<std::list<std::pair<int,int>>> Htable;
    int size;

public:
    HashTable(int size) {
        this->size = size;
        Htable.resize(size);
    }

    int hashFunction(int key) {
        if(key < 0) return size - std::abs(key);
        return key % size;
    }

    void insert(int key) {
        int index = hashFunction(key);
        Htable[index].emplace_back(key, 0);
    }

    std::optional<std::reference_wrapper<int>> search(int key) {
        int index = hashFunction(key);
        for (auto& element : Htable[index]) {
            if (element.first == key) {
                return element.second;
            }
        }

        return std::nullopt;
    }

    int& operator[](int key) {
        auto found{ search(key) };
        if(!found) insert(key);
        return *search(key);
    }

};


int main() {
    int N;
    std::cin >> N;

    int countPairs = 0;
    HashTable hashTable(1000000);
    for (int i = 0; i < N; i++) {
        int number{};
        std::cin >> number;
        countPairs += hashTable[number - i]++;
    }

    std::cout << countPairs << '\n';
    return 0;
}