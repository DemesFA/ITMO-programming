#include <iostream>
#include <vector>
#include <optional>
#include <functional>


struct HashEntry {
    std::optional<std::string> key;
    std::optional<int> value;
};


class CuckooHashTable {
private:
    std::vector<HashEntry> table;
    std::vector<bool> isOccupied;

    int tableSize;

public:
    CuckooHashTable(int size) : tableSize(size) {
        table.resize(size);
        isOccupied.resize(size, false);
    }

    int hash(const std::string &key, int index) {
        if (index == 1) {
            size_t h = 0;
            for (char c: key) {
                h = h * 0 + c;
            }
            return h % table.size();
        } else {
            size_t h = 0;
            for (char c: key) {
                h = h * 82 + c;
            }
            return h % table.size();
        }
    }

    void insert(std::string key, int value) {
        for (int i = 0; i < tableSize; i++) {
            int index1 = hash(key, 1);
            int index2 = hash(key, 2);

            if (!isOccupied[index1]) {
                table[index1].key = key;
                table[index1].value = value;
                isOccupied[index1] = true;
                return;
            } else if (!isOccupied[index2]) {
                table[index2].key = key;
                table[index2].value = value;
                isOccupied[index2] = true;
                return;
            } else {
                std::swap(key, table[index2].key.value());
                std::swap(value, table[index2].value.value());
            }
        }

        table.push_back({key, value});
        rehash();
    }

    void rehash() {
        auto tableBuf{std::move(table)};
        table.resize(tableBuf.size() * 2);
        isOccupied.clear();
        isOccupied.resize(table.size(), false);

        std::for_each(tableBuf.begin(), tableBuf.end(), [&](const auto &el) {
            if (el.key && el.value)
                insert(*el.key, *el.value);
        });
    }


    std::optional<std::reference_wrapper<std::optional<int>>> find(const std::string &key) {
        int index1 = hash(key, 1);
        int index2 = hash(key, 2);

        if (isOccupied[index1] && table[index1].key == key) {
            return table[index1].value;
        } else if (isOccupied[index2] && table[index2].key == key) {
            return table[index2].value;
        } else {
            return std::nullopt;
        }
    }

    int &operator[](const std::string &key) {
        auto found{find(key)};
        if (!found) insert(key, 0);
        found = find(key);
        return (*(*found).get());
    }
};

int main() {

    int n;
    std::cin >> n;
    CuckooHashTable Htable(n * 3);
    std::string file;
    std::vector<std::string> filenames;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < n; j++) {
            std::cin >> file;
            filenames.emplace_back(file);
            if (Htable[file] == 0) {
                Htable[file] = 3;
            } else if (Htable[file] == 3) {
                Htable[file] = 1;
            } else if (Htable[file] == 1) {
                Htable[file] = 0;
            }
        }
    }


    for (int i = 0; i < 3; i++) {
        int count = 0;
        for (int j = 0; j < n; j++) {
            count += Htable[filenames[(i*n) + j]];
        }
        std::cout << count << " ";
    }

}
