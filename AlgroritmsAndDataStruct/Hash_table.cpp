#include <iostream>
#include <vector>
#include <list>

class HashTable {
    int TableSize;
    std::vector<std::list<int>> HTable;
public:
    HashTable(int TS) : TableSize(TS) {
        HTable.resize(TableSize);
    }

    int hash(int key) const {
        return key % TableSize;
    }

    void insert(int key) {
        int index = hash(key);
        HTable[index].push_back(key);
    }

    bool isEqual(const HashTable& other) const {
        if (TableSize != other.TableSize) {
            return false;
        }

        for (int i = 0; i < TableSize; ++i) {
            if (HTable[i].size() != other.HTable[i].size()) {
                return false;
            }

            for (int element : HTable[i]) {
                bool found = false;

                for (int otherElement : other.HTable[i]) {
                    if (element == otherElement) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }
        }

        return true;
    }
};

int main() {
    int N;
    std::cin >> N;

    HashTable first(N);
    HashTable second(N);


    for (int i = 0; i < N; ++i) {
        int element;
        std::cin >> element;
        first.insert(element);
    }


    for (int i = 0; i < N; ++i) {
        int element;
        std::cin >> element;
        second.insert(element);
    }

    if (first.isEqual(second)) {
        std::cout << "YES" << std::endl;
    } else {
        std::cout << "NO" << std::endl;
    }

    return 0;
}
