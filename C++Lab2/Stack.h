class Stack{
    int data;
    Stack* head;
    Stack* next;
    int max_depth;
public:
    Stack();
    ~Stack();
    Stack(int max_depth);
    int Stack_size();
    void push(int value);
    void pop();
    void elements_count(int& count);
    void isEmpty();
    void top();
    void print();
};