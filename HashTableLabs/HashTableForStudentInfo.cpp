#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

struct Student {
    int isu;
    int group;
    int points;

    Student(int group, int isu, int points) : group(group), isu(isu), points(points) {}
};

class HashTable {
    const int TableSize = 10;
    std::vector<std::vector<Student>> HTable;

public:
    HashTable() {
        HTable.resize(TableSize);
    }

    int hash(int isu) {
        return isu % TableSize;
    }

    void addStudent(int& isu, int& group, int& points) {
        int index = hash(isu);
        HTable[index].emplace_back(group, isu, points);
    }

    void delStudent(int& isu) {
        int index = hash(isu);
        auto& students = HTable[index];

        for (auto it = students.begin(); it != students.end(); ++it) {
            if (it->isu == isu) {
                students.erase(it);
                return;
            }
        }
    }

    int GroupPointsAverage(int& group) {
        int sum = 0;
        int count = 0;

        for (const auto& students : HTable) {
            for (const auto& student : students) {
                if (student.group == group) {
                    sum += student.points;
                    count++;
                }
            }
        }

        if (count > 0) {
            return sum / count;
        } else {
            return 0;
        }
    }

    int BestStudentInGroup(int& group) {
        int maxPoints = 0;

        for (const auto& students : HTable) {
            for (const auto& student : students) {
                if (student.group == group && student.points > maxPoints) {
                    maxPoints = student.points;
                    if (maxPoints == 100) {
                        return maxPoints;
                    }
                }
            }
        }

        return maxPoints;
    }
};

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);

    int numberGroups, requests;
    std::cin >> numberGroups >> requests;

    HashTable hashtable;

    for (int i = 0; i < requests; i++) {
        char c;
        std::cin >> c;

        if (c == '+') {
            int group, isu, points;
            std::cin >> group >> isu >> points;
            hashtable.addStudent(isu, group, points);
        } else if (c == '-') {
            int group, isu;
            std::cin >> group >> isu;
            hashtable.delStudent(isu);
        } else if (c == 'm') {
            int group;
            std::cin >> group;
            int best = hashtable.BestStudentInGroup(group);
            std::cout << best << '\n';
        } else if (c == 'a') {
            int group;
            std::cin >> group;
            int avg = hashtable.GroupPointsAverage(group);
            std::cout << avg << '\n';
        }
    }

    return 0;
}
