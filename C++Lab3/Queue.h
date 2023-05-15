class Queue{
    int data;
public:
    Queue();
    ~Queue();
    void push(int value);
    void del();
    Queue& operator<<(int value);
    Queue& operator>>(int& value);
private:
    Queue* head;
    Queue* tail;
    Queue* next;

};
