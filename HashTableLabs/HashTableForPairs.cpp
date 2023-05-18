#include <iostream>
#include <vector>
#include <list>

class HashTable {
private:
    std::vector<std::list<int>> table;
    int size;

public:
    HashTable(int s) : size(s) {
        table.resize(size);
    }

    void insert(int value) {
        int index = hash(value);
        table[index].push_back(value);
    }

    int countPairs() {
        int count = 0;
        for (const auto& chain : table) {
            std::vector<int> countMap(size, 0);
            for (auto it1 = chain.begin(); it1 != chain.end(); ++it1) {
                int target = *it1 - std::distance(table[0].begin(), it1);
                if (target >= 0 && target < size) {
                    count += countMap[target];
                }
                countMap[*it1]++;
            }
        }
        return count;
    }



private:
    int hash(int value) {
        return value % size;
    }
};

int main() {
    int N;
    std::cin >> N;

    HashTable hashTable(N);

    for (int i = 0; i < N; i++) {
        int height;
        std::cin >> height;
        hashTable.insert(height);
    }

    int pairs = hashTable.countPairs();
    std::cout << pairs << std::endl;

    return 0;
}
