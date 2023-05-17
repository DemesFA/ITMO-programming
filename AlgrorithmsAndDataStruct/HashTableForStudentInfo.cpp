#include <iostream>
#include <vector>
#include <list>


struct Student{
    int isu;
    int group;
    int points;

    Student(int group, int isu, int points) : group(group), isu(isu), points(points) {}
};


class HashTable {
    const int TableSize = 10000;
    std::vector<std::list<Student>> HTable;
public:
    HashTable() {
        HTable.resize(TableSize);
    }

    int hash(int isu) {
        return isu % TableSize;
    }

    void addStudent(int isu, int group, int points) {
        int index = hash(isu);
        HTable[index].emplace_back(group, isu, points);
    }

    void delStudent(int isu) {
        int index = hash(isu);
        std::list<Student>& students = HTable[index];
        for (auto it = students.begin(); it != students.end(); ++it) {
            if (it->isu == isu) {
                students.erase(it);
                break;
            }
        }
    }

    int GroupPointsAverage(int group){
        int sum = 0;
        int count = 0;

        for(const auto& students : HTable){
            for(const auto& student : students){
                if(student.group == group){
                    sum += student.points;
                    count ++;
                }
            }
        }
        if(count > 0){
            return sum / count;
        }
        else{
            return 0;
        }

    }

    int BestStudentInGroup(int group) {
        int maxPoints = 0;

        for (const auto& students : HTable) {
            for (const auto& student : students) {
                if (student.group == group && student.points > maxPoints) {
                    maxPoints = student.points;
                }
            }
        }

        return maxPoints;
    }


};


int main(){
    int numberGroups,requests;
    std::cin >> numberGroups >> requests;

    HashTable hashtable;

    for(int i = 0; i < requests; i++){
        char c;
        std::cin >> c;

        if(c == '+'){
            int group,isu,points;
            std::cin >> group >> isu >> points;
            hashtable.addStudent(isu,group,points);
        }
        if(c == '-'){
            int group,isu;
            std::cin >> isu;
            hashtable.delStudent(isu);
        }
        if(c == 'm'){
            int group;
            std::cin >> group;
            int best = hashtable.BestStudentInGroup(group);
            std::cout << best << std::endl;
        }
        if(c == 'a'){
            int group;
            std::cin >> group;
            int avg = hashtable.GroupPointsAverage(group);
            std::cout << avg << std::endl;
        }

    }

}